<%-- Document : result Created on : Feb 10, 2021, 12:23:39 AM Author : Admin --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" href="CSS/s.css">
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
                <title>Result</title>
            </head>

            <body>
                <div class="headerLogin">
                    <div class="welcome">
                        <ul>
                            <li class="welcome__first">
                                welcome,${sessionScope.FULLNAME}
                            </li>
                            <li class="history">
                                <a href="HistoryServlet"><i class="fa fa-history" aria-hidden="true"></i></a>
                            </li>
                            <li>
                                <form action="LogOut">
                                    <button type="submit" value="Log Out"><i class="fa fa-sign-out"></i></button>
                                </form>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="searchLogin">
                </div>

                <div class="bodyResult">
                    <h1>${sessionScope.SUBJECT2}</h1>
                    <table>
                        <thead>
                            <tr>
                                <th>State</th>
                                <th>Number of correct answer</th>
                                <th>Point</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="dateResult">
                                    <div>Finished</div>
                                    Submitted ${requestScope.DATESUBMIT}
                                </td>
                                <td class="numberOfCorrect">
                                    ${requestScope.NUMOFCORRECTANSWER}
                                </td>
                                <td class="markOfResult">
                                    ${requestScope.MARK}
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="backToCourse">
                    <a href="subjectQuiz.jsp">BACK TO THE COURSE</a>
                </div>
                <div class="footerLogin">

                </div>
            </body>

            </html>