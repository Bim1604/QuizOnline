/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.servlet;

import dang.quizdao.TblQuestionDAO;
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
public class UpdateServlet extends HttpServlet {

    private final String searchPage = "Sub";
    private final String updatePage = "update";
    private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UpdateServlet.class.getName());

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
        String url = null;
        String id = request.getParameter("txtID");
        String questionContent = request.getParameter("txtQuesContent");
        String[] answerContent = request.getParameterValues("answerContent");
        String[] answerID = request.getParameterValues("answerID");
        String rdoAnswerCorrect = request.getParameter("answerCorrect");
        String answerCorrect = null;
        String subjectID = request.getParameter("txtSubject");
        String txtStatus = request.getParameter("txtStatus");
        boolean status = true;
        if (txtStatus.equals("false")) {
            status = false;
        }
        ServletContext context = request.getServletContext();
        Map<String, String> map = (Map<String, String>) context.getAttribute("MAP");
        try {
            BasicConfigurator.configure();
            log.error("Exception");
            for (int i = 0; i < answerContent.length; i++) {
                if (!"".equals(rdoAnswerCorrect) && !"".equals(questionContent) && !"".equals(answerContent[i]) && !"".equals(answerCorrect) && !"".equals(subjectID)) {
                    TblQuestionDAO dao = new TblQuestionDAO();
                    boolean rsQuestion = dao.updateQuestion(id, questionContent, subjectID, status);
                    if (rsQuestion) {
                        boolean correctAnswer = false;
                        if (rdoAnswerCorrect.equals(answerID[i])) {
                            correctAnswer = true;
                        }
                        boolean rsAnswer = dao.updateAnswer(answerID[i], answerContent[i], correctAnswer);
                        if (rsAnswer) {
                            url = map.get(searchPage);
                            String msg = "Update Successfully !!";
                            request.setAttribute("UPDATESUCCESS", msg);
                            HttpSession session = request.getSession();
                            session.removeAttribute("RESULTFOUND");
                        }
                    }
                } else {
                    String msg = "Please fill all information";
                    request.setAttribute("EMPTYUPDATE", msg);
                    url = map.get(updatePage);
                }
            }

        } catch (SQLException | NamingException ex) {
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
