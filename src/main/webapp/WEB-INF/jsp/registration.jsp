<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<c:set var="create" value="${param.create}" />
	
	<form action="Controller" method="post">
		Registration:
		<br>
		<input type="hidden" name="command" value="Registration" />
		login:
		<input type="text" name="login" value = ""/>
		<br>
		password:
		<input type="password" name="password" value = ""/>
		<br><br><br>
		
		<c:choose>
			<c:when test="${create eq 'ADMIN'}">
				Name:
				<input type="text" name="name" value = ""/>
				<br>
				Photo:
				<input type="text" name="photo" value = ""/>
			</c:when>
			<c:otherwise>
				Name:
				<input type="text" name="name" value = ""/>
				<br>
				Surname:
				<input type="text" name="surname" value = ""/>
				<br>
				Passport id:
				<input type="text" name="passportId" value = ""/>
				<br>
				Date of birth:
				<input type="text" name="dateOfBith" value = "2000-10-10"/>
				<br>
				Country:
				<input type="text" name="country" value = "Belarus"/>
				<br>
				Phone number:
				<input type="text" name="phone" value = "+4546"/>
				<br>
				Email:
				<input type="text" name="email" value = "a@gmail.com"/>		
			</c:otherwise>
		</c:choose>
		<br><br><br>
		<input type="submit" value="Press me"/>
	</form>
	
	<br>
	<a href="Controller?command=GO_TO_WELCOME_PAGE">back</a>
	
	<br>
	<h2>
	<c:out value="${param.errorMessage}" />
	</h2>

</body>
</html>