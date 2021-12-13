<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

<c:set var="role" scope="session" value="${role}" />
<c:set var="login" value="${login}" />
<c:set var="msg" value="${msg}" />


<h2><c:out value="${info}" default="no_info" /></h2>

<c:if test="${not empty login}"  var="testif">
	<p>You logged as <c:out value="${role}"/>. Your login: <c:out value="${login}" /></p>
</c:if>


<c:if test="${not empty name}">
	<p>Welcome <c:out value="${name}"/>!</p>
</c:if>


<c:if test="${not empty login and login eq 'ADMIN'}" >
	<p>
		This is BOSS ADMIN account. Only you can create new admins: 
		<br>
		<a href="Controller?command=GO_TO_REGISTRATION_PAGE&create=ADMIN">New admin</a>
	</p>
</c:if>

</body>
</html>