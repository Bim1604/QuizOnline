/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.servlet;

import dang.quizdao.TblTestDAO;
import dang.quizdto.TblTestDTO;
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
public class HistoryServlet extends HttpServlet {
    private final String historyPage = "history";
    private final int size = 2;
    private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(HistoryServlet.class.getName());
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
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("EMAIL");
        ServletContext context = request.getServletContext();
        Map<String, String> map = (Map<String, String>) context.getAttribute("MAP");
        String url = map.get(historyPage);
        try {
            BasicConfigurator.configure();
            log.error("Exception");
            String txtPageIndex;
            int pageIndex;
            if (request.getParameter("txtPageIndex") == null){
                pageIndex = 1;
            } else {
                txtPageIndex = request.getParameter("txtPageIndex");
                pageIndex = Integer.parseInt(txtPageIndex);
            }
            TblTestDAO testDAO = new TblTestDAO();
            int count = testDAO.countHistory(userID);
            int pageSize = size;
            int endPage = count/pageSize;
            if (count % pageSize != 0 ){
                endPage++;
            }
            testDAO.getHistory(userID, pageIndex, pageSize);
            List<TblTestDTO> list = testDAO.getList();
            request.setAttribute("TESTHISTORY", list);
            request.setAttribute("ENDPAGEHISTORY", endPage);
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
