/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.servlet;

import com.restfb.types.User;
import dang.loginfb.RestFB;
import dang.quizdao.TblUserDAO;
import dang.sha256.PasswordEncrypt;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author Admin
 */
public class RegistrationServlet extends HttpServlet {

    private final String loginPage = "";
    private final String registrationPage = "registration";
    private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RegistrationServlet.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();        
        ServletContext context = request.getServletContext();
        Map<String, String> map = (Map<String, String>) context.getAttribute("MAP");
        String url = map.get(loginPage);
        String email = request.getParameter("txtEmail");
        String name = request.getParameter("txtName");
        String password = request.getParameter("txtPassword");
        String codeFB = request.getParameter("code");
        boolean checkEmpty = false;

        try {
            BasicConfigurator.configure();
            log.error("Exception");
            TblUserDAO userDAO = new TblUserDAO();
            if (codeFB == null || codeFB.isEmpty()) {
                String passwordEncrypted = PasswordEncrypt.encryptPassword(password);
                if (email.equals("") || name.equals("") || password.equals("")) {
                    checkEmpty = true;
                }
//            Kiểm tra tài khoản trùng 
                boolean checkExist = userDAO.checkExistAccount(email);
                if (checkEmpty == true) {
//           Kiểm tra thông tin điền có trống không
                    url = map.get(registrationPage);
                    String msg = "Please fill all information !!";
                    request.setAttribute("CREATEEMPTY", msg);
                } else if (!checkExist) {
                    boolean rs = userDAO.createNewAccount(email, name, passwordEncrypted, false, true);
                    if (rs == true) {
                        String msg = "Create Account Successfully";
                        request.setAttribute("CREATESUCCESSFULLY", msg);
                    }
                } else {
                    url = map.get(registrationPage);
                    String msg = "Create Account Failed !! Existed Account";
                    request.setAttribute("EXISTEDCREATE", msg);
                }
            } else {
                String accessToken = RestFB.getToken(codeFB, "http://localhost:8084/QuizOnline/registrationServlet");
                User user = RestFB.getUserInfo(accessToken);
                String username = user.getId();
                String fullname = user.getName();
                try {
                    boolean rs = userDAO.createNewAccount(username, fullname, "", false, true);
                    if (rs) {
                        String msg = "Create Account " + fullname + " Successful";
                        request.setAttribute("CREATESUCCESSFULLY", msg);
                    }
                } catch (SQLException ex) {
                    if (ex.getMessage().contains("duplicate")) {
                        request.setAttribute("MSG", "This Facebook account is duplicatied");
                    }
                }
            }

        } catch (SQLException | NamingException | NoSuchAlgorithmException ex) {
            log.error(ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
