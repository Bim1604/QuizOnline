/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.servlet;

import dang.logingoogle.GooglePojo;
import dang.logingoogle.GoogleUtils;
import dang.quizdao.TblUserDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class LoginWithGoogleServlet extends HttpServlet {

    private final String subPage = "Sub";
    private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LoginWithGoogleServlet.class.getName());

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
        String url = map.get(subPage);
        System.out.println("3");
        try {
            log.error("Exception");
            BasicConfigurator.configure();
            /* TODO output your page here. You may use following sample code. */
            String code = request.getParameter("code");
            if (code == null || code.isEmpty()) {
                RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
                dis.forward(request, response);
            } else {
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
                HttpSession session = request.getSession();
                String userID = googlePojo.getEmail();
                System.out.println("user " + userID);
                session.setAttribute("IDUSER", userID);
                TblUserDAO dao = new TblUserDAO();
                String name = dao.checkLoginWithGoogle(userID);
                boolean role = dao.getRole(userID);
                if (name != null) {
                    if (name.length() > 0) {
                        if (role == false) {
                            session.setAttribute("FULLNAME", name);
                            session.setAttribute("ROLE", role);
                        } else {
                            session.setAttribute("FULLNAME", name);
                            session.setAttribute("ROLE", role);
                        }
                    }
                } else {
                    url = "login.jsp";
                    String msg = "Invalid Gmail";
                    request.setAttribute("MSG", msg);
                }
            }
            response.sendRedirect(url);
        } catch (SQLException ex) {
            log.error("SQLException");
        } catch (NamingException ex) {
            log.error("NamingException");
        } finally {            
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
