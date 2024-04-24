<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="./css/login.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%--<c:set var="user" value="${sessionScope.user}" />--%>

        <h1>TA Application Management</h1>
        <h2>Login</h2>
        <!--<form method="post">-->
        <form:form modelAttribute="user" >
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
            <br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <br>
            <input type="submit" value="Login">
        </form:form>
        <br>
        <a href="register.htm">Register Here</a><br>
    </body>
</html>
