<%-- Document : login Created on : Jan 27, 2021, 11:08:27 PM Author : Admin --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="CSS/s.css">
        <link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>

    <body <c:if test="${not empty requestScope.MSG}">
            onload="alertMessage()"
        </c:if>
        <c:if test="${not empty requestScope.CREATESUCCESSFULLY}">
            onload="alertCreateMessage()"
        </c:if>
        >
        <div class="headerLogin">

        </div>
        <!--Search Question-->
        <div class="searchLogin">           
        </div>
        <div class="bodyLogin">
            <div class="body__title">
                Login into your account
            </div>
            <div class="body__loginForm">
                <form action="login " method="POST">
                    <input type="text" name="txtUsername" value="" placeholder="User Name" />
                    <input type="password" name="txtPassword" value="" placeholder="Password" />
                    <input id="login" type="submit" value="Login" name="btAction" />
                </form>
            </div>
            <div class="login">
                <div>
                    <a href="https://www.facebook.com/dialog/oauth?client_id=133206675390895&redirect_uri=http://localhost:8084/QuizOnline/login">Login With Facebook</a>
                </div>
                <div>
                    <a href="">Login With Google</a>
                </div>
            </div><hr>
            <div class="login">
                <div>        
                    <a href="registration">Create New Account</a>
                </div>
                <div>           
                    <a href="https://www.facebook.com/dialog/oauth?client_id=133206675390895&redirect_uri=http://localhost:8084/QuizOnline/registrationServlet">Create Account With Facebook</a>
                </div>
                <div>           
                    <a href="">Create Account With Google</a>                
                </div>
            </div>
        </div>
        <div class="footerLogin">

        </div>
        <script>
            function alertMessage() {
                alert("${requestScope.MSG}");
            }
            function alertCreateMessage() {
                alert("${requestScope.CREATESUCCESSFULLY}");
            }
        </script>
    </body>

</html>