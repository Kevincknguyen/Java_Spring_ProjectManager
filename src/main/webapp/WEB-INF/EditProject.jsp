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




	<div style="display:flex; justify-content:space-between; margin:2%;">
		<h1><c:out value="${temp}" /></h1>
		<div><a href="http://localhost:8080/dashboard">return home</a></div>
	
	</div>
	
	<div>
	
	
	<form:form action="/api/edit/${project.id}" method="post" modelAttribute="project">
	<input type="hidden" name="_method" value="put">
				<p>
						<form:label path="title">Title</form:label>
						<form:errors path="title" class="text-danger"/>
						<form:input path="title" />
				</p>
				<p>
						<form:label path="description">Description</form:label>
						<form:errors path="description" class="text-danger"/>
						<form:input path="description" />
				</p>
				<p>
						<form:label path="dueDate">dueDate</form:label>
						<form:errors path="dueDate" class="text-danger"/>
						<form:input type="date" path="dueDate" />
				</p>
				
				<p>
					<input type=submit value="Submit"/>
				</p>
				
				
	</form:form>
	
	
	</div>
	
</body>
</html>