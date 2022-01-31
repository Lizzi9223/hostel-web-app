<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="by.epam.tc.web.controller.constant.*"%>
<%@ include file="changeLanguageTags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>

<script type="text/javascript" src="js/script.js"></script>
<link rel="stylesheet" href="css/style.css">

<style>
</style>

</head>
<body>

	<div class="container">

		<div class="menu">

			<div class="tabs" style="justify-content: flex-start">
				<div>
					<a href="Controller?command=GO_TO_WELCOME_PAGE"><c:out
							value="${main}" /></a>
				</div>
				|
				<div>
					<a href="Controller?command=GO_TO_ROOMS_PAGE"><c:out
							value="${rooms}" /></a>
				</div>
				|
				<div>
					<a href="Controller?command=GO_TO_CONTACTS_PAGE"><c:out
							value="${contacts}" /></a>
				</div>
				|
				<c:if test="${not empty sessionScope.login}">
					<div>
						<a href="Controller?command=GO_TO_MY_ACCOUNT_PAGE"><c:out
								value="${my_account}" /></a>
					</div>
				</c:if>
			</div>

			<div class="tabs" style="justify-content: flex-end">
				<form>
					<input type="hidden" name="${Utility.COMMAND}"
						value="${CommandName.CHANGE_LANGUAGE}"> <select
						name="${Utility.LANGUAGE}" onchange="submit()">
						<option value="ru" ${language == 'ru' ? 'selected' : ''}><c:out
								value="${ru}" /></option>
						<option value="en" ${language == 'en' ? 'selected' : ''}><c:out
								value="${en}" /></option>
					</select>
				</form>
				<c:choose>
					<c:when test="${empty sessionScope.role}">
						<div>
							<a href="Controller?command=GO_TO_LOGINATION_PAGE"><c:out
									value="${sign_in}" /></a>
						</div>|
		        		<div>
							<a href="Controller?command=GO_TO_REGISTRATION_PAGE"><c:out
									value="${sign_up}" /></a>
						</div>
					</c:when>
					<c:otherwise>
						<div>
							<a href="Controller?command=GO_TO_WELCOME_PAGE&logOut=true"><c:out
									value="${log_out}" /></a>
						</div>|
        			</c:otherwise>
				</c:choose>
			</div>

		</div>

		<div class="main"></div>

	</div>

</body>
</html>