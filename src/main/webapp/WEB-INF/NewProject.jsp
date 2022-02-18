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

<h1>Create a new project today!</h1>
<div>
		<form:form action="/api/newProject" method="post" modelAttribute="project">
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
				<form:input type="hidden" path="lead" value="${currentUser.id}" />
				<form:input type="hidden" path="users" value="${currentUser.id}" />
				<p><input type=submit value="Submit"/></p>
			</form:form>
	</div>
</body>
</html>