<%-- Document : quiz Created on : Feb 9, 2021, 4:40:46 PM Author : Admin --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz</title>
        <link rel="stylesheet" href="CSS/s.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
        <div class="bodyExam">
            <div class="container">
                <c:forEach begin="1" end="${requestScope.ENDPAGE}" var="1">
                    
                </c:forEach>
                <div class="container__first">
                    Time left: <span id="hours"></span>:
                    <span id="minutes"></span>:
                    <span id="seconds"></span>
                </div>
            </div>
            <div class="bodyQuiz">
                <form action="quizProccess" method="POST">
                    <c:forEach var="dto" items="${requestScope.QUIZ}" varStatus="counter">
                        <c:set var="count" value="${counter.count}" />
                        <table>
                            <tbody>
                                <tr>
                                    <td class="stt">
                                        <div>
                                            Question <b>${counter.count}</b>
                                        </div>
                                    </td>
                                    <td class="question">
                                        <div>
                                            ${dto.question_content}<br><br>
                                            <div>Select one:</div>
                                        </div>
                                            <c:forEach var="answer" items="${dto.answer}" >
                                            <div>
                                                <input type="radio" name="answerID${counter.count}" value="${answer.answerID}" /> 
                                                 ${answer.answerContent}
                                            </div>
                                        </c:forEach>
                                    </td>
                            <input type="hidden" name="txtID" value="${dto.id}" />
                            </tr>
                        </c:forEach>
                        <td>
                            <input type="hidden" name="txtSubject" value="${param.txtSubject}" />
                            <input type="hidden" name="txtCount" value="${count}" />
                            <button id="submit" type="submit" value="Finish"> Finish </button>
                        </td>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>


        <div class="footerLogin">

        </div>
        <script>
            (function () {
                const second = 1000,
                        minute = second * 60,
                        hour = minute * 60;
                day = hour * 24;
                var timeNow = new Date().getTime();
                let timeUp = new Date(timeNow + 900000).toString(),
                        countDown = new Date(timeUp).getTime(),
                        x = setInterval(function () {

                            let now = new Date().getTime(),
                                    distance = countDown - now;
                            document.getElementById("hours").innerText = Math.floor((distance % (day)) / (hour)),
                                    document.getElementById("minutes").innerText = Math.floor((distance % (hour)) / (minute)),
                                    document.getElementById("seconds").innerText = Math.floor((distance % (minute)) / second);

                            //do something later when date is reached
                            if (distance < 0) {
                                document.getElementById('submit').click();
                                clearInterval(x);
                            }
                            //seconds
                        }, 0)
            }());
        </script>
    </body>

</html>