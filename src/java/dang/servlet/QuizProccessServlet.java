/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.servlet;

import dang.quizdao.TblAnswerDAO;
import dang.quizdao.TblTestDAO;
import dang.quizdao.TblDetailsTestDAO;
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
public class QuizProccessServlet extends HttpServlet {

    private final String resultPage = "result";
    private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(QuizProccessServlet.class.getName());

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
        String txtCount = request.getParameter("txtCount");
        String[] answerID = new String[Integer.parseInt(txtCount)];
        String[] id = request.getParameterValues("txtID");
        int increase = 0;
        float mark = 0;
        int numOfCorrectAns = 0;
//        Lấy para từng đáp án
        for (int i = 0; i < answerID.length; i++) {
            answerID[i] = request.getParameter("answerID" + ++increase);
        }
        ServletContext context = request.getServletContext();
        Map<String, String> map = (Map<String, String>) context.getAttribute("MAP");
        String url = map.get(resultPage);
        try {
            BasicConfigurator.configure();
            log.error("Exception");
            TblAnswerDAO dao = new TblAnswerDAO();
//            Chấm điểm
            for (int i = 0; i < answerID.length; i++) {
                float point;
                if (answerID[i] == null) {
                    point = 0;
                } else {
                    point = dao.getEachQuestionMark(answerID[i]);
                }
                mark += point;
                if (point == 1) {
                    numOfCorrectAns++;
                }
            }
//            Thêm vào tblTest
            TblTestDAO testDAO = new TblTestDAO();
            int testID = testDAO.getTestID();
            HttpSession session = request.getSession();
            String subjectID = (String) session.getAttribute("SUBJECT2");
            String email = (String) session.getAttribute("EMAIL");
            testDAO.saveQuiz(testID, numOfCorrectAns, email, "15", subjectID);
//            Thêm vào TblDetailsTestDAO
            TblDetailsTestDAO detailsDAO = new TblDetailsTestDAO();
            for (int i = 0; i < answerID.length; i++) {
                float point;
                if (answerID[i] == null) {
                    point = 0;
                } else {
                    point = dao.getEachQuestionMark(answerID[i]);
                }
                detailsDAO.saveDetailsTest(id[i], testID, point, answerID[i]);
            }
//            Lấy thời gian nộp bài
            String dateSubmit = testDAO.getDateSubmit(testID);
            request.setAttribute("DATESUBMIT", dateSubmit);
            request.setAttribute("NUMOFCORRECTANSWER", numOfCorrectAns);
            request.setAttribute("MARK", mark);

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
