<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TA Applications</title>
<link rel="stylesheet" href="../css/dashboard.css">
</head>
<body>
  	<form:form id="courseDashboard" class="form-horizontal" action="dashboard.htm" method="post" >
  
	  <h1>Welcome ${userEmail}</h1>	
	  
	  <table class="ta-table">
	    <caption>Courses</caption>
	    <thead>
	      <tr>
	      	<th>Course ID</th>
	        <th>Course Name</th>
	        <th>Course Code</th>
	        <th>Professor Email</th>
	        <th>Action</th>
	      </tr>
	    </thead>
	    <tbody>
	      <c:forEach items="${courseList}" var="course">    
		      <tr>
		       	<td>${course.courseId}</td>
		        <td>${course.courseName}</td>
		        <td>${course.courseCode}</td>
		        <td>${course.professorEmail}</td>
		        <td>
                    <button type="button" class="btn btn-primary remove-btn" data-course-id="${course.courseId}">Remove</button>
		        </td>
		      </tr>
	     </c:forEach>
	    </tbody>
	  </table>
	  
	  	<c:if test="${userRole!='student'}">
	    	<div class="col-sm-offset-4 col-sm-8">
	            <input type="submit" id="submit" name="submit"
	                   class="btn btn-primary" value="Add" />
	        </div>
		</c:if>
		
	</form:form>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
        $(document).ready(function () {
            $('.remove-btn').click(function () {
                var courseId = $(this).data('course-id');
                removeCourse(courseId);
            });

            function removeCourse(courseId) {
                $.ajax({
                    url: '/admin/remove.htm/' + courseId,
                    type: 'POST',
                    success: function (data) {
                        if(data.success){
                        	alert('Course removed successfully.');
                        	location.reload();
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
