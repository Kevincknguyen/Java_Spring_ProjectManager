<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isErrorPage="true" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<div>
	<a href="http://localhost:8080/dashboard">return home</a>
	<p>
		Name:<c:out value="${currentUser.firstName }" /><br>
		EmployeeID: <c:out value="${currentID }" />
	</p>
</div>

<h1>Tasks for: <c:out value="${thisProject.title }"></c:out></h1>
<h4>Project lead: <c:out value="${thisProject.lead.firstName }" />  <c:out value="${thisProject.lead.lastName }" /></h4>




<div>
		<form:form action="/api/newTask/${thisProject.id}" method="post" modelAttribute="task">
			<p>
					<form:label path="description">Description</form:label>
					<form:errors path="description" class="text-danger"/>
					<form:input path="description" />
					
					<form:input type="hidden" path="user" value="${currentUser.id}" />
					<form:input type="hidden" path="project" value="${thisProject.id}" />
			</p>
			<p><input type=submit value="Submit"/></p>
		</form:form>
</div>



<div>
	<c:forEach var="item" items="${allTasks}">
		<p style="font-weight:bold;">Added by User: <c:out value="${item.user.firstName}" /> at <c:out value="${item.createdAt}" /> </p>
		<p>Message: <c:out value="${item.description }" /> </p>
	</c:forEach>

</div>


</body>
</html>