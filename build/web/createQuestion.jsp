<%-- 
    Document   : createQuestion
    Created on : Feb 5, 2021, 4:27:42 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/s.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Create Question</title>
    </head>
    <body <c:if test="${not empty requestScope.EMPTYCREATEQUES}">
            onload="alertMessage()"
        </c:if>
        >
        <div class="headerLogin">
            <div class="welcome">
                <ul>
                    <li class="welcome__first">
                        welcome,${sessionScope.FULLNAME}
                    </li>
                    <li>
                        <form action="LogOut">
                            <button type="submit" value="Log Out"><i class="fa fa-sign-out"></i></button>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
        <!--Search Question-->
        <div class="searchLogin">
            <form action="Search">
                <a href="createQuestion.jsp">Create Question</a>
                <input type="text" name="txtSearch" value="${param.txtSearch}" placeholder="Search Course" />
                <button value="Search" name="btAction" ><i class="fa fa-search" aria-hidden="true"></i> </button>
                <select class="statusBox" name="txtStatus">
                    <option label="Active" value="true" />
                    <option label="deActive" value="false" <c:if test="${param.txtStatus eq 'false'}">
                            selected="selected"
                        </c:if>/>
                </select>
                <select class="subjectBox" name="txtSubjectID">
                    <c:forEach var="sub" items="${sessionScope.SUBJECT}">
                        <option label="${sub.subjectID}-${sub.subjectName}" value="${sub.subjectID}" <c:if test="${sub.subjectID eq param.txtSubjectID}">
                                selected="selected"
                            </c:if> />
                    </c:forEach>
                </select>
            </form>
        </div>
        <!--Choose number of answers-->

        <c:if test="${empty param.numberOfAnswer}">           
            <div class="numOfAnswer">
                <div>
                    Choose number of Answer
                    <form action="create">
                        <input type="number" name="numberOfAnswer" value="" min="1"/>
                        <button type="submit">Next</button>
                    </form>    
                </div>                
            </div>                   
        </c:if>
        <c:if test="${not empty param.numberOfAnswer}">
            ${param.numberOfAnswer}
            <!--Create Question-->
            <div class="bodySearch">
                <table>
                    <form action="CreateServlet" method="POST">
                        <tbody>
                            <tr>
                                <td>ID</td>
                                <td>
                                    The ID will be automatic
                                </td>
                            </tr>
                            <tr>
                                <td>Question Content</td>
                                <td class="questionContent">
                                    <input type="text" name="txtQuesContent" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td>Answer Content</td>
                                <td>
                                    <div class="createInput">
                                        <c:forEach begin="1" end="${param.numberOfAnswer}"  var="num">
                                            <div>
                                                ${num}. <input type="text" name="txtAnswerContent${num}" value="" /> 
                                            </div>
                                        </c:forEach>
                                    </div>                                                                                                          
                                </td>
                            </tr>
                            <tr>
                                <td>Answer Correct</td>
                                <td>
                                    <div>
                                        <c:forEach begin="1" end="${param.numberOfAnswer}"  var="num" varStatus="counter">
                                            <c:set var="count" value="${counter.count}" />
                                            <div>                                                
                                                ${num}. <input type="radio" name="rdoAnswerCorrect" value="${num}" /> 
                                            </div>
                                        </c:forEach>
                                        <input type="hidden" name="count" value="${count}" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Create Date</td>
                                <td>
                                    Create Date will be automatic today.
                                </td>
                            </tr>
                            <tr>
                                <td>Subject ID</td>
                                <td>
                                    <select class="subjectBox" name="txtSubject">
                                        <c:forEach var="subject" items="${sessionScope.SUBJECT}">
                                            <option label="${subject.subjectID}-${subject.subjectName}" value="${subject.subjectID}" <c:if test="${subject.subjectID eq param.txtSubject}">
                                                    selected="selected"
                                                </c:if> />
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Status</td>
                                <td>
                                    Status will be automatic Active
                                </td>
                            </tr>
                            <tr class="CreateQuestionButton">
                                <td>
                                    <input type="submit" value="Create" />
                                </td>
                            </tr>
                        </tbody>
                    </form>
                </table>
            </div>
        </c:if>
        <div class="backSearch">
            <a href="search.jsp"><i class="fa fa-arrow-circle-left"> Return Search Page</i></a>
        </div>
        <div class="footerLogin">

        </div>
        <script>
            function alertMessage() {
                alert("${requestScope.EMPTYCREATEQUES}");
            }
        </script>                                
    </body>
</html>
