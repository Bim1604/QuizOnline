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
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class CreateQuestionServlet extends HttpServlet {

    private final String searchPage = "search";
    private final String createPage = "create";
    private final Logger log = Logger.getLogger(CreateQuestionServlet.class.getName());

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
        ServletContext context = request.getServletContext();
        Map<String, String> map = (Map<String, String>) context.getAttribute("MAP");
        try {
            BasicConfigurator.configure();
            log.error("Exception");
            TblQuestionDAO dao = new TblQuestionDAO();
            String id = dao.getQuestionID();
            String question_content = request.getParameter("txtQuesContent");
            String rdoAnswerCorrect = request.getParameter("rdoAnswerCorrect");
            String check = null;
            String subjectID = request.getParameter("txtSubject");
            String count = request.getParameter("count");
            String answerContent[] = new String[Integer.parseInt(count)];
            boolean status = true;
            boolean rsQuestion = false;
            boolean rsAnswer;
            int increase = 1;
            boolean answerCorrect = false;
            boolean checkCreateQuestion = false;
            for (int i = 0; i < answerContent.length; i++) {
                answerContent[i] = request.getParameter("txtAnswerContent" + increase);
                increase++;
                //          Lấy đáp án đúng
                String answer = String.valueOf(i + 1);

                if (rdoAnswerCorrect.equals(answer)) {
                    answerCorrect = true;
                }
                if (!"".equals(question_content) && !"".equals(answerContent[i]) && !"".equals(rdoAnswerCorrect) && !"".equals(subjectID)) {                   
                    if (!checkCreateQuestion) {
                        rsQuestion = dao.createQuestion(id, question_content, subjectID, status, check);
                        checkCreateQuestion = true;
                    }
                    String answerID = dao.getAnswerID();
                    rsAnswer = dao.createAnswer(answerID, id, answerContent[i], answerCorrect);
                    answerCorrect = false;
                    if (rsQuestion == true && rsAnswer == true) {
                        String msg = "Create Question Successfully !!";
                        request.setAttribute("CREATESUCCESS", msg);
                        url = map.get(searchPage);
                    }
                } else {
                    String msg = "Please fill all information";
                    request.setAttribute("EMPTYCREATEQUES", msg);
                    url = map.get(createPage);
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
