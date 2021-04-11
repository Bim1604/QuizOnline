<%-- 
    Document   : historyDetails
    Created on : Feb 11, 2021, 8:08:34 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/s.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>History Details</title>
    </head>
    <body>
        <div class="headerLogin">
            <div class="welcome">
                <ul>
                    <li class="welcome__first">
                        welcome,${sessionScope.FULLNAME}
                    </li>
                   <li class="history">
                        <form action="HistoryServlet">
                            <button type="submit" ><i class="fa fa-history" aria-hidden="true"></i></button>                            
                            <input type="hidden" name="txtPageIndex" value="1" />
                        </form>
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
            <table>
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Test ID</th>
                        <th>Question Content</th>
                        <th>Your Answer</th>
                        <th>Point</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${requestScope.DETAILSLIST}" varStatus="counter">
                    <form action="historyDetails">
                        <tr>
                            <td class="historyDetail">
                                ${counter.count}
                            </td>
                            <td class="historyDetail">
                                ${dto.testID}
                            </td>
                            <td class="historyDetail">
                                ${dto.questionID}
                            </td>
                            <td class="historyDetail">
                                ${dto.answerStudent}
                            </td>
                            <td class="historyDetail">
                                ${dto.point}
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="backToCourse">
            <a href="HistoryServlet">BACK TO HISTORY</a>
        </div>
        <div class="footerLogin">

        </div>
    </body>
</html>
