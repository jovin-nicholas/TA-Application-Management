<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/register.css">
    </head>
    <body>
        <%--<c:set var="user" value="${sessionScope.user}" />--%>

        <h1>TA Application Management</h1>
        <h2>Register</h2>
        <form:form modelAttribute="user" >
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
            <br>
            <label for="fname">First Name</label>
            <input type="text" id="fname" name="fname" required>
            <br>
            <label for="lname">Last Name</label>
            <input type="text" id="lname" name="lname" required>
            <br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <br>
            <label for="role">Role:</label>
            <select name="role" id="role">
                <option value="student">Student</option>
                <option value="faculty">Faculty</option>
                 <option value="admin">Admin</option>
            </select> 
            <br>
            <br>
            <input type="submit" value="Register">
            
        </form:form>
        <br>
        <a href="login.htm">Login Here</a><br>
    </body>
</html>
