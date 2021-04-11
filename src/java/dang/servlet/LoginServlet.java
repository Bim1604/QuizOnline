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
import javax.servlet.http.HttpSession;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

    private final String searchPage = "Sub";
    private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LoginServlet.class.getName());

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");

        ServletContext context = request.getServletContext();
        Map<String, String> map = (Map<String, String>) context.getAttribute("MAP");
        String url = map.get(searchPage);
        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        System.out.println("2 " + code);
        try {
            BasicConfigurator.configure();
            log.error("Exception");
            TblUserDAO userDAO = new TblUserDAO();
            if (code == null || code.isEmpty()) {
                String passwordEncypted = PasswordEncrypt.encryptPassword(password);
                String fullName = userDAO.checkLogin(username, passwordEncypted);
                boolean role = userDAO.getRole(username);
                if (fullName != null) {
                    session.setAttribute("EMAIL", username);
                    session.setAttribute("FULLNAME", fullName);
                    session.setAttribute("ROLE", role);
                } else {
                    url = map.get("");
                    String msg = "Invalid password or username !!";
                    request.setAttribute("MSG", msg);
                }
            } else {
//                login with fb
                String accessToken = RestFB.getToken(code, "http://localhost:8084/QuizOnline/login");
                User user = RestFB.getUserInfo(accessToken);
                username = user.getId();
                String fullname = user.getName();
                String result = userDAO.checkLogin(username, "");
                boolean role = userDAO.getRole(username);
                if (result != null) {
                    session.setAttribute("FULLNAME", fullname);
                    session.setAttribute("EMAIL", username);
                    session.setAttribute("ROLE", role);
                } else {
                    url = map.get("");
                    session.setAttribute("MSG", "Login facebook fail. Please try again.");
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
