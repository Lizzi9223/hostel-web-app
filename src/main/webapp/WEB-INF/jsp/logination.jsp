<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

	<form action="Controller" method="post">
		Logination:
		<br>
		<input type="hidden" name="command" value="Logination" />
		Login:
		<input type="text" name="login" value = ""/>
		<br>
		Password:
		<input type="password" name="password" value = ""/>
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