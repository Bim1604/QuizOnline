<%-- Document : search Created on : Jan 27, 2021, 11:31:38 PM Author : Admin --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="CSS/s.css">
    </head>

    <body <c:if test="${not empty requestScope.CREATESUCCESS}">
            onload="alertCreateMessage()"
        </c:if>
        <c:if test="${not empty requestScope.UPDATESUCCESS}">
            onload="alertUpdateMessage()"
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
                <input type="text" name="txtSearch" value="${param.txtSearch}" placeholder="Search Question" />
                <button value="Search" name="btAction"><i class="fa fa-search" aria-hidden="true"></i> </button>
                <select class="statusBox" name="txtStatus">
                    <option label="Active" value="true" />
                    <option label="deActive" value="false" <c:if test="${param.txtStatus eq 'false'}">
                            selected="selected"
                        </c:if>/>
                </select>
                <select class="subjectBox" name="txtSubjectID">
                    <option label="Subject" value="" />
                    <c:forEach var="sub" items="${sessionScope.SUBJECT}">
                        <option label="${sub.subjectID}-${sub.subjectName}" value="${sub.subjectID}" <c:if
                                    test="${sub.subjectID eq param.txtSubjectID}">
                                    selected="selected"
                                </c:if> />
                    </c:forEach>
                </select>
                <input type="hidden" name="txtPageIndex" value="1" />
            </form>
        </div>
        <!--Load Result Search-->
        <div class="bodySearch">
            <c:set var="result" value="${sessionScope.RESULTFOUND}" />
            <c:if test="${not empty result}">
                <table>
                    <tr>
                        <th>id</th>
                        <th>Question Content</th>
                        <th>Answer Content</th>
                        <th>Answer Correct</th>
                        <th>Create Date</th>
                        <th>Subject ID</th>
                        <th>Status</th>
                        <th class="updateTH">Update</th>
                        <th class="deleteTH">Delete</th>
                    </tr>
                    <tbody>
                    <form action="Search">
                        <c:forEach var="dto" items="${result}">
                            <tr>
                                <td>
                                    ${dto.id}
                                </td>
                                <td class="questionCon">
                                    ${dto.question_content}
                                </td>
                                <td>
                                    <c:forEach var="answerContent" items="${dto.answer}">
                                        <div>
                                            ${answerContent.answerContent}
                                        </div>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:forEach var="answerCorrect" items="${dto.answer}">
                                        <c:if test="${answerCorrect.answerCorrect eq 'true'}">
                                            <div>
                                                ${answerCorrect.answerContent}
                                                <c:set var="answerCorrect" value="${answerCorrect.answerContent}" />
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    ${dto.createDate}
                                </td>
                                <td>
                                    ${dto.subjectID}
                                </td>
                                <td>
                                    <c:if test="${dto.status eq 'true'}">
                                        Active
                                    </c:if>
                                    <c:if test="${dto.status ne 'true'}">
                                        No Active
                                    </c:if>
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="update">
                                        <c:param name="txtID" value="${dto.id}" />
                                        <c:param name="txtQuestionContent" value="${dto.question_content}" />
                                        <c:param name="txtSubjectID" value="${dto.subjectID}" />
                                        <c:param name="txtStatus" value="${dto.status}" />
                                    </c:url>
                                    <div class="updateLink">
                                        <a href="${urlRewriting}">Update</a>
                                    </div>
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="Delete">
                                        <c:param name="txtID" value="${dto.id}" />
                                        <c:param name="txtSearch" value="${param.txtSearch}" />
                                        <c:param name="txtStatus" value="${dto.status}" />
                                        <c:param name="txtSubjectID" value="${dto.subjectID}" />
                                    </c:url>
                                    <div class="deleteLink">
                                        <a href="${urlRewriting}">Delete</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </form>
                    </tbody>
                </table>
            </c:if>
        </div>
        <div class="pagingSearch">
            <c:forEach begin="1" end="${requestScope.ENDPAGE}" var="pageIndex">
                <div>
                    <a href="Search?txtSearch=${param.txtSearch}&btAction=Search&txtStatus=${param.txtStatus}&txtSubjectID=${param.txtSubjectID}&txtPageIndex=${pageIndex}">${pageIndex}</a>
                </div>
            </c:forEach>
        </div>
        <div class="footerLogin">
        </div>
        <script>
            function alertCreateMessage() {
                alert("${requestScope.CREATESUCCESS}");
            }
            function alertUpdateMessage() {
                alert("${requestScope.UPDATESUCCESS}");
            }
        </script>
    </body>

</html>