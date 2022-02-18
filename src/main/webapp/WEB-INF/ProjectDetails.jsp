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

<a href="http://localhost:8080/dashboard">Return to dashboard</a>
<p>Current user:<c:out value="${currentUser.firstName }" /></p>
<h1>Project Details</h1>


<h3>Title: <c:out value="${thisProject.title }" /></h3>
<h3>Description:<c:out value="${thisProject.description }" /></h3>
<h3>Due date:<c:out value="${thisProject.dueDate }" /></h3>
<h3>Lead manager:<c:out value="${thisProject.lead.firstName }" /> <c:out value="${thisProject.lead.lastName }" /></h3>
<h3>Team members</h3>
<ul>
<c:forEach var="user" items="${thisProject.users}">
	<li><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></li>
</c:forEach>
</ul>

<p>
<c:if test="${teamMember!=null}">
<a href="/tasks/project/${thisProject.id}">See Tasks for this project</a>







</c:if>
</p>
</body>
</html>