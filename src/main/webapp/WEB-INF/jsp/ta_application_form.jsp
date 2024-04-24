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
        <form:form id="application" class="form-horizontal" action="apply.htm" method="post" >
                   <%--enctype="multipart/form-data">--%>
		    
		    <c:if test="${checkDuplicate}">
		    	<h2>${checkDuplicate}</h2> 
		    </c:if>
		    
            <div class="form-group">
                <label for="applicantEmail" class="col-sm-4 control-label">Email:</label>
                <div class="col-sm-8">
                	<input type="hidden" name="applicantEmail" value="${userEmail}"/>
                    <input id="applicantEmail" name="email" placeholder="Your Husky e-mail Address." type="email" class="form-control" required="required" value="${userEmail}" disabled/> <p class="text-danger"></p>
                </div>
            </div>

			<div class="form-group">
                <label for="course">Course to be TA of:</label>
                <select class="form-control form-control-sm" id="course" name="applicationCourseId">
					<c:forEach items="${courseList}" var="course">
						<option
                                name="${course.courseCode}"
                                value="${course.courseId}"
                        >
                         ${course.courseCode} ${course.courseName}
                        </option>
                    </c:forEach>
                </select>
            </div>
                
            <div class="form-group">
                <label for="professorEmail" class="col-sm-4 control-label">Professor Email:</label>
                <div class="col-sm-8">
                    <input id="professorEmail" name="professorEmail" placeholder="Choose the email of the course's professor" type="text" class="form-control" required="required" value=""/> <p class="text-danger"></p>
                </div>
            </div>
            
<!--            <div class="form-group">
                <label for="resume" class="col-sm-4 control-label">Resume:
                </label>
                <div class="col-sm-4">
                    <input type="file" id="resume" name="resume" required="required" />
                    <p class="text-primary">Recent resume in PDF format</p>
                </div>
            </div>-->

            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-8">
                    <input type="button" id="applicationSubmit" name="saveorupdate"
                           class="btn btn-primary" value="Submit" />
                </div>

            </div>
        </form:form>
        
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
        $(document).ready(function () {
            $('#applicationSubmit').click(function () {
                var courseId = $("#course").val();
                var userId = ${userId}
                checkApplication(userId, courseId);
            });

            function checkApplication(userId, courseId) {
                $.ajax({
                    url: '/ta/checkApplication.htm/',
                    type: 'GET',
                    data: {
                    	"courseId" : courseId,
                    	"userId" : userId
                    },
                    success: function (data) {
                        if(data.success){
                        	$('#application').submit();
                        }
                        else{
                        	alert('Error: ' + data.message);
                        }
                    },
                    error: function (xhr, status, error) {
                        console.error(xhr.responseText);
                    }
                });
            }
        });
    </script>
    
    </body>
</html>
