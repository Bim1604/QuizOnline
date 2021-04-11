/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.servlet;

import dang.quizdao.TblQuestionDAO;
import dang.quizdto.TblQuestionDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
public class SearchServlet extends HttpServlet {

    private final String searchPage = "Sub";
    private final int size = 10;
    private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(SearchServlet.class.getName());
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
        String searchValue = request.getParameter("txtSearch");
        ServletContext context = request.getServletContext();
        Map<String, String> map = (Map<String, String>) context.getAttribute("MAP");
        HttpSession session = request.getSession();
        String url = map.get(searchPage);
        String txtStatus = request.getParameter("txtStatus");
        boolean status = true;
        if (txtStatus.equals("false")) {
            status = false;
        }
        String txtPageIndex = request.getParameter("txtPageIndex"); 
        String subjectID = request.getParameter("txtSubjectID");
        try {
            BasicConfigurator.configure();
            log.error("Exception");
            TblQuestionDAO dao = new TblQuestionDAO();
            int pageIndex = Integer.parseInt(txtPageIndex);
            int count = 0;
            int pageSize = size;
            if (searchValue.equals("") && subjectID.equals("")) {
            } else if (searchValue.equals("")) {
                dao.searchQuestionBySubject(subjectID, pageIndex, pageSize);   
                count = dao.countBySubjectID(subjectID);
            } else if (subjectID.equals("")) {
                dao.searchQuestionByName(searchValue, status, pageIndex, pageSize);
                count = dao.countByQuestionContent(searchValue, status);
            } else {
                dao.searchQuestion(searchValue, subjectID, status, pageIndex, pageSize);
                count = dao.count(subjectID, searchValue, status);
            }
            int endPage = count/pageSize;
            if (count % pageSize != 0){
                endPage++;
            }
            List<TblQuestionDTO> list = dao.getListQuestion();
            session.setAttribute("RESULTFOUND", list);
            request.setAttribute("ENDPAGE", endPage);
        } catch (SQLException | NamingException | NumberFormatException ex) {
            log.error(ex);
        }finally {
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
