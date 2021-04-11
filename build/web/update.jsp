<%-- Document : update Created on : Feb 6, 2021, 3:17:51 PM Author : Admin --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/s.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Update Question</title>
    </head>

    <body <c:if test="${not empty requestScope.EMPTYUPDATE}">
            onload="alertMessage()"
        </c:if>>
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
                <button value="Search" name="btAction"><i class="fa fa-search" aria-hidden="true"></i> </button>
                <select class="statusBox" name="txtStatus">
                    <option label="Active" value="true" />
                    <option label="deActive" value="false" <c:if test="${param.txtStatus eq 'false'}">
                            selected="selected"
                        </c:if>/>
                </select>
                <select class="subjectBox" name="txtSubjectID">
                    <c:forEach var="sub" items="${sessionScope.SUBJECT}">
                        <option label="${sub.subjectID}-${sub.subjectName}" value="${sub.subjectID}" <c:if
                                    test="${sub.subjectID eq param.txtSubjectID}">
                                    selected="selected"
                                </c:if> />
                    </c:forEach>
                </select>
            </form>
        </div>
        <!--Update Question-->
        <div class="bodySearch">
            <table border="1">
                <form action="UpdateServlet" method="POST">
                    <tbody>
                        <tr>
                            <td>Question Content</td>
                            <td>
                                <input type="text" name="txtQuesContent" value="${param.txtQuestionContent}" />
                            </td>
                        </tr>
                        <tr>
                            <td>Answer Content</td>
                            <td>
                                <div class="updateInput">
                                    <c:forEach var="dto" items="${sessionScope.RESULTFOUND}">                                            
                                        <c:forEach var="answerContent" items="${dto.answer}"> 
                                            <c:if test="${dto.id eq param.txtID}">
                                                <div>
                                                    <input type="text" name="answerContent" value="${answerContent.answerContent}" />
                                                    <input type="hidden" name="answerID" value="${answerContent.answerID}" />
                                                </div>
                                            </c:if>
                                        </c:forEach>                                           
                                    </c:forEach>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Answer Correct</td>
                            <td>
                                <div>
                                    <c:forEach var="dto" items="${sessionScope.RESULTFOUND}" >                                            
                                        <c:forEach var="answerCorrect" items="${dto.answer}">    
                                            <c:if test="${dto.id eq param.txtID}">
                                                <div>
                                                    <input type="radio" name="answerCorrect" value="${answerCorrect.answerID}"
                                                             <c:if test="${answerCorrect.answerCorrect eq 'true'}">
                                                                 checked="checked" 
                                                             </c:if> />
                                                    ${answerCorrect.answerContent}
                                                </div>
                                            </c:if>
                                        </c:forEach>                                           
                                    </c:forEach>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Subject ID</td>
                            <td>
                                <select class="subjectBox" name="txtSubject">
                                    <c:forEach var="subject" items="${sessionScope.SUBJECT}">
                                        <option label="${subject.subjectID}-${subject.subjectName}"
                                                value="${subject.subjectID}" <c:if
                                                    test="${subject.subjectID eq param.txtSubjectID}">
                                                    selected="selected"
                                                </c:if> />
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Status</td>
                            <td>
                                <select name="txtStatus">
                                    <option label="Active" value="true" />
                                    <option label="Deactive" value="false" <c:if
                                                test="${param.txtStatus eq 'false'}">
                                                selected="selected"
                                            </c:if>/>
                                </select>
                            </td>
                        </tr>
                        <tr class="CreateQuestionButton">
                            <td>
                                <input type="hidden" name="txtID" value="${param.txtID}" />
                                <input type="submit" value="Update" />
                            </td>
                        </tr>
                    </tbody>
                </form>
            </table>
        </div>
        <div class="backSearch">
            <a href="search.jsp"><i class="fa fa-arrow-circle-left">Return Search Page</i></a>
        </div>
        <div class="footerLogin">

        </div>
        <script>
            function alertMessage() {
                alert("${requestScope.EMPTYUPDATE}");
            }
        </script>
    </body>

</html>