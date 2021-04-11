<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%-- Document : registration Created on : Jan 29, 2021, 2:45:18 PM Author : Admin --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/s.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Registration</title>
    </head>

    <body <c:if test="${not empty requestScope.CREATEEMPTY}">
            onload="alertEmptyMessage()"
        </c:if>
        <c:if test="${not empty requestScope.EXISTEDCREATE}">
            onload="alertExistMessage()"
        </c:if> >

        <div class="headerLogin">

        </div>
        <!--Search Question-->
        <div class="searchLogin">
        </div>
        <div class="bodyCreate">
            <div class="body__title">
                Create New Account
            </div>
            <div>
                <form action="registrationServlet" method="POST">
                    <div>
                        Email<input type="text" name="txtEmail" value="" />
                    </div>
                    <div>
                        Name<input type="text" name="txtName" value="" />
                    </div>
                    <div class="createPassword">
                        Password<input type="password" name="txtPassword" value="" />
                    </div>
                    <div class="createButton">
                        <input type="submit" value="Create New Account" name="btAction" />
                    </div>
                </form>
            </div>
        </div>
        <div class="backCreate">
            <a href="login.jsp"><i class="fa fa-arrow-circle-left">Return Login Page</i></a>
        </div>
        <div class="footerLogin">

        </div>

        <script>
            function alertExistMessage() {
                alert("${requestScope.EXISTEDCREATE}");
            }
            function alertEmptyMessage() {
                alert("${requestScope.CREATEEMPTY}");
            }
        </script>

    </body>

</html>