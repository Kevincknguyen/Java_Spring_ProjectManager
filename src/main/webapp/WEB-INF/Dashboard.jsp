<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>
		Welcome
		<c:out value="${currentUser.firstName }" />
	</h1>
	EmployeeID: <c:out value="${currentID }" />

	<form action="/logout" method="post">
		<button type="submit">Logout</button>
	</form>
	<a href="http://localhost:8080/newProject">Add Project</a>


	<!--  ALL PROJECTS THAT ARE AVAILABLE TO JOIN FOR THIS USER -->
	<div>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Title</th>
					<th scope="col">Due Date</th>
					<th scope="col">Lead Manager</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="project" items="${availableProjects }">
					<tr>
						<td><a href="http://localhost:8080/project/${project.id }"><c:out
									value="${project.title } " /></a></td>
						<td><c:out value="${project.dueDate } " /></td>
						<td><c:out value="${project.lead.firstName } " /></td>
						<td><form:form action="/addUserToProject/${project.id}"
								method="post" modelAttribute="project">
								<input type="hidden" name="_method" value="put">
								<form:input type="hidden" path="users" value="${currentID}" />
								<input type=submit value="Join team" />
							</form:form></td>
						<td><form action="/deleteproject/${project.id}" method="post">
							<input type="hidden" name="_method" value="delete">
							<input type="submit" value="Delete">
							</form></td>
					</tr>
				</c:forEach>
				
				
			</tbody>
		</table>

	</div>

	<!--  ALL PROJECTS THAT ARE ATTACHED TO THIS USER -->
	<div>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">Title</th>
					<th scope="col">Due Date</th>
					<th scope="col">Lead Manager</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="project" items="${myProjects }">
					<tr>
						<td><a href="http://localhost:8080/project/${project.id }"><c:out
									value="${project.title } " /></a></td>
						<td><c:out value="${project.dueDate } " /></td>
						<td><c:out value="${project.lead.firstName } " /></td>
						<c:if test="${currentID ==project.lead.id }">
							
							<td><a href="http://localhost:8080/editProject/${project.id}">Edit</a></td>
							<td>
							<form action="/deleteproject/${project.id}" method="post">
							<input type="hidden" name="_method" value="delete">
							<input type="submit" value="Delete">
							</form>
							</td>
						</c:if>
						<c:if test="${currentID !=project.lead.id }">
							<td><form:form action="/removeUserToProject/${project.id}"
									method="post" modelAttribute="project">
									<input type="hidden" name="_method" value="put">
									<form:input type="hidden" path="users" value="${currentID}" />
									<input type=submit value="Leave team" />
								</form:form></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>


</body>
</html>