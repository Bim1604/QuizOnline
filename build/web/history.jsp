<%-- Document : history Created on : Feb 11, 2021, 6:19:45 PM Author : Admin --%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <%@page contentType="text/html" pageEncoding="UTF-8" %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <link rel="stylesheet" href="CSS/s.css">
                    <link rel="stylesheet"
                        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
                    <title>History</title>
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
                                        <button type="submit"><i class="fa fa-history" aria-hidden="true"></i></button>
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
                        <div class="searchHistory">
                            <form action="SearchHistory">
                                <select class="subjectBox" name="txtSubjectID">
                                    <option label="All Subject" value="" />
                                    <c:forEach var="sub" items="${sessionScope.SUBJECT}">
                                        <option label="${sub.subjectID}-${sub.subjectName}" value="${sub.subjectID}" <c:if
                                            test="${sub.subjectID eq param.txtSubjectID}">
                                            selected="selected"
                                            </c:if> />
                                    </c:forEach>
                                </select>
                                <input type="hidden" name="pageIndex" value="1" />
                                <button value="Search" name="btAction"><i class="fa fa-search" aria-hidden="true"></i>
                                </button>
                            </form>
                        </div>                        
                    </div>

                    <div class="bodyResult">
                        <table>
                            <thead>
                                <tr>
                                    <th>Test ID</th>
                                    <th>Subject Name</th>
                                    <th>Mark</th>
                                    <th>Time</th>
                                    <th>Date Submit</th>
                                    <th>Details</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!--Search History Test-->
                                <c:if test="${not empty requestScope.SEARCHHISTORY}">
                                    <c:forEach var="dto" items="${requestScope.SEARCHHISTORY}">
                                        <form action="historyDetails">
                                            <tr>
                                                <td class="historyDetail">
                                                    ${dto.testID}
                                                    <input type="hidden" name="txtTestID" value="${dto.testID}" />
                                                </td>
                                                <td class="historyDetail">
                                                    ${dto.subjectID}
                                                </td>
                                                <td class="historyDetail">
                                                    ${dto.mark}
                                                </td>
                                                <td class="historyDetail">
                                                    ${dto.time}
                                                </td>
                                                <td class="historyDetail">
                                                    ${dto.dateSubmit}
                                                </td>
                                                <td class="historyDetail">
                                                    <button type="submit" value="Details"> Details</button>
                                                </td>
                                            </tr>
                                        </form>
                                    </c:forEach>
                                </c:if>
                                <!--History Test-->
                                <c:if test="${empty requestScope.SEARCHHISTORY}">
                                    <c:forEach var="dto" items="${requestScope.TESTHISTORY}">
                                        <form action="historyDetails">
                                            <tr>
                                                <td class="historyDetail">
                                                    ${dto.testID}
                                                    <input type="hidden" name="txtTestID" value="${dto.testID}" />
                                                </td>
                                                <td class="historyDetail">
                                                    ${dto.subjectID}
                                                </td>
                                                <td class="historyDetail">
                                                    ${dto.mark}
                                                </td>
                                                <td class="historyDetail">
                                                    ${dto.time}
                                                </td>
                                                <td class="historyDetail">
                                                    ${dto.dateSubmit}
                                                </td>
                                                <td class="historyDetail">
                                                    <button type="submit" value="Details"> Details</button>
                                                </td>
                                            </tr>
                                        </form>
                                    </c:forEach>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                    <div class="pagingHistory">
                        <c:if test="${not empty requestScope.ENDPAGEHISTORY}">
                            <c:forEach begin="1" end="${requestScope.ENDPAGEHISTORY}" var="pageIndex">
                                <div>
                                    <a href="HistoryServlet?txtPageIndex=${pageIndex}">${pageIndex}</a>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${not empty requestScope.ENDSEARCHPAGE}">
                            <c:forEach begin="1" end="${requestScope.ENDSEARCHPAGE}" var="pageIndex">
                                <div>
                                    <a
                                        href="SearchHistory?txtSubjectID=${param.txtSubjectID}&pageIndex=${pageIndex}&btAction=Search">${pageIndex}</a>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <div class="backToCourse">
                        <a href="subjectQuiz.jsp">BACK TO COURSE</a>
                    </div>
                    <div class="footerLogin">

                    </div>
                </body>

                </html>