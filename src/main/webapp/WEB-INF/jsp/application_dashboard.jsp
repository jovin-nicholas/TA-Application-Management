<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TA Applications</title>
<link rel="stylesheet" href="../css/dashboard.css">
</head>
<body>
	<form:form id="applicationDashboard" class="form-horizontal" action="dashboard.htm" method="post" >
  
  		<h1>Welcome ${userEmail}</h1>	
  
		<table class="ta-table">
	    	<caption>TA Applications</caption>
	    <thead>
	      <tr>
	        <th>Applicant Email</th>
	        <th>Course Number</th>
	        <th>Professor Email</th>
	        <th>Status</th>
	        <c:if test="${userRole!='student'}">
	        	<th> Select </th>
	        </c:if>
	      </tr>
	    </thead>
	    <tbody>
	      <c:forEach items="${applicationList}" var="application">    
		      <tr>
		        <td>${application.user.email}</td>
		        <td>${application.course.courseCode}</td>
		        <td>${application.professorEmail}</td>
		        <td>${application.status}</td>
		        <c:if test="${userRole!='student'}">
		        <td>
		        	<input type="checkbox" name="selectedApplication" value="${application.applicationId}"
			        	${application.status != 'Pending' ? 'disabled' : ''} 
	       				${application.status != 'Pending' ? 'checked' : ''} 
	       			/>
	        	</td>
		        </c:if>
		      </tr>
	     </c:forEach>
	    </tbody>
	  </table>
	  
		<c:if test="${userRole!='student'}">
	    	<div class="col-sm-offset-4 col-sm-8">
	            <input type="submit" id="submit" name="saveorupdate"
	                   class="btn btn-primary" value="Submit" />
	        </div>
		</c:if>
		
		<c:if test="${userRole=='student'}">
			<div class="col-sm-offset-4 col-sm-8">
	            <input type="submit" id="submit" name="saveorupdate"
	                   class="btn btn-primary" value="Add" />
	        </div>
        </c:if>
		
	</form:form>    
</body>
</html>
