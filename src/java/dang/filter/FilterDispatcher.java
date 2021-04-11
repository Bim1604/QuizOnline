/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.filter;

import dang.servlet.CreateQuestionServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class FilterDispatcher implements Filter {

    private static final boolean debug = true;
    private final Logger log = Logger.getLogger(CreateQuestionServlet.class.getName());
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FilterDispatcher() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterDispatcher:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterDispatcher:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("FilterDispatcher:doFilter()");
        }

        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        String url;
        ServletContext context = request.getServletContext();
        HttpSession session = req.getSession();

        try {
            BasicConfigurator.configure();
            log.error("Exception");
            Map<String, String> map = (Map<String, String>) context.getAttribute("MAP");
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex + 1);
            if (session.getAttribute("EMAIL") == null && !resource.equals("registration")
                    && !resource.equals("registrationServlet") && !resource.equals("login")
                    && !resource.equals("Sub") && !resource.equals("s.css")) {
                resource = "";
            } else if (session.getAttribute("EMAIL") != null && (resource.equals("login.jsp") || resource.equals("") || resource.equals("login"))) {
                boolean role = (boolean) session.getAttribute("ROLE");
                if (role == true) {
                    resource = "search";
                } else {
                    resource = "subject";
                }
            } else if (session.getAttribute("EMAIL") != null) {
                boolean role = (boolean) session.getAttribute("ROLE");
                if (role == true) {
                    if (resource.equals("subject")
                            || resource.equals("subjectQuiz.jsp")
                            || resource.equals("Quiz")
                            || resource.equals("quiz")
                            || resource.equals("quiz.jsp")
                            || resource.equals("quizProccess")
                            || resource.equals("result")
                            || resource.equals("result.jsp")
                            || resource.equals("History")
                            || resource.equals("history")
                            || resource.equals("history.jsp")
                            || resource.equals("historyDetails")
                            || resource.equals("historyDetailsPage")
                            || resource.equals("historyDetailsPage.jsp")
                            || resource.equals("SearchHistory")) {
                        resource = "search";
                    }
                } else {
                    if (resource.equals("search")
                            || resource.equals("search.jsp")
                            || resource.equals("Sub")
                            || resource.equals("Search")
                            || resource.equals("create")
                            || resource.equals("createQuestion.jsp")
                            || resource.equals("CreateServlet")
                            || resource.equals("update")
                            || resource.equals("update.jsp")
                            || resource.equals("Update")
                            || resource.equals("Delete")) {
                        resource = "subject";
                    }
                }
            }
            url = map.get(resource);
            if (url != null) {
                RequestDispatcher rd = req.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } catch (IOException | ServletException e) {
            log.error(e);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     *
     * @return
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FilterDispatcher:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterDispatcher()");
        }
        StringBuffer sb = new StringBuffer("FilterDispatcher(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
