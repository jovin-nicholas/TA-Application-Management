<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/form.css">
        <title>TA Application</title>
    </head>
    <body>
        <form:form id="applicant" class="form-horizontal" action="addCourse.htm" method="post" >
                  

            <div class="form-group">
                <label for="courseCode" class="col-sm-4 control-label">Course Code:</label>
                <div class="col-sm-8">
                    <input id="courseCode" name="courseCode" placeholder="Course Code" type="text" class="form-control" required="required" value=""/> <p class="text-danger"></p>
                </div>
            </div>

            <div class="form-group">
                <label for="courseName" class="col-sm-4 control-label">Course Name:
                </label>
                <div class="col-sm-8">
                    <textarea id="courseName" name="courseName" placeholder="Course Name" class="form-control" rows="4"></textarea>
                </div>
            </div>

            <div class="form-group">
                <label for="professorEmail" class="col-sm-4 control-label">Professor Email:</label>
                <div class="col-sm-8">
                    <input id="professorEmail" name="professorEmail" placeholder="Choose the email of the course's professor" type="text" class="form-control" required="required" value=""/> <p class="text-danger"></p>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-8">
                    <input type="submit" id="submit" name="saveorupdate"
                           class="btn btn-primary" value="Submit" />
                </div>

            </div>
        </form:form>
    </body>
</html>
