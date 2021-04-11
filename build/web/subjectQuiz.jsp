<%-- Document : subjectQuiz Created on : Feb 9, 2021, 12:48:36 AM Author : Admin --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboards</title>
        <link rel="stylesheet" href="CSS/s.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>

    <body <c:if test="${not empty requestScope.NOQUIZ}">
            onload="alertMessage()"
        </c:if>
        >
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

        <div>
            <form class="subject" action="Quiz">
                <c:forEach var="subject" items="${SUBJECT}" varStatus="counter">
                    <div class="subjectQuiz">
                        ${subject.subjectName} - ${subject.subjectID} 
                        <input type="radio" name="txtSubject" value="${subject.subjectID}" 
                               <c:if test="${counter.count == 1}">
                                   checked="checked"
                               </c:if> />
                    </div>
                </c:forEach>
                <input type="hidden" name="txtPageIndex" value="1" />
                <button type="submit" value="Take Exam">Take Exam</button>
            </form>
        </div>

        <div class="footerLogin">

        </div>
        <script>
            function alertMessage() {
                alert("${requestScope.NOQUIZ}");
            }
        </script>
    </body>

</html>