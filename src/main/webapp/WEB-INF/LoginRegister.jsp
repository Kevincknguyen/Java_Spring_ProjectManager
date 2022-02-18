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
	<h1>Login or Register</h1>
	<!-- REGISTER -->
	<div>
		<form:form action="/register" method="post" modelAttribute="newUser">
				<p>
						<form:label path="firstName">First name</form:label>
						<form:errors path="firstName" class="text-danger"/>
						<form:input path="firstName" />
				</p>
				<p>
						<form:label path="lastName">Last name</form:label>
						<form:errors path="lastName" class="text-danger"/>
						<form:input path="lastName" />
				</p>
				<p>
						<form:label path="email">Email</form:label>
						<form:errors path="email" class="text-danger"/>
						<form:input path="email" />
				</p>
				<p>
						<form:label path="password">Password</form:label>
						<form:errors path="password" class="text-danger"/>
						<form:input path="password" />
				</p>
				<p>
						<form:label path="confirm">Confirm Password</form:label>
						<form:errors path="confirm" class="text-danger"/>
						<form:input path="confirm" />
				</p>
				<p><input type=submit value="Submit"/></p>
			</form:form>
	</div>
	<!-- Login -->
	<div>
		<form:form action="/login" method="post" modelAttribute="newLogin">
			<p>
					<form:label path="email">Email</form:label>
					<form:input path="email" />
			</p>
			<p>
					<form:label path="password">Password</form:label>
					<form:input path="password" />
			</p>
			<p>	
					<form:errors path="email" class="text-danger"/>
					<form:errors path="password" class="text-danger"/>
					<input type=submit value="Submit"/>
			</p>
		</form:form>
	</div>
</div>
</body>
</html>