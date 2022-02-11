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
body {
	background: url("images/bg.jpg") no-repeat;
	background-size: cover;
	margin: 0;
	padding: 0;
	font-family: sans-serif;
}

.main {
	flex-grow: 1;
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
}
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

		<div class="main">
			<div class="form">
				<div>
					<h3>
						<c:out value="${contacts_word}" />
					</h3>
				</div>
				<br> <br>
				<div>
					<i><c:out value="${phone_number_word}:" /></i><b><c:out
							value="  A1  +375(29)194-67-78" /></b>
				</div>
				<br>
				<div>
					<i><c:out value="${address_word}:" /></i><b><c:out
							value=" ${address_value}" /></b>
				</div>
				<br>
				<div>
					<i><c:out value="${email_word}:" /></i><b><c:out
							value="  hostel_samartia@gmail.com" /></b>
				</div>
				<br> <br>
				<div>
					<iframe
						src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2350.323783344461!2d27.569659215752818!3d53.908222080100614!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46dbcfbc207c9b33%3A0xd33f13a65d0ae529!2zSG9zdGVsIFRvd2VyIDMxLzE4ICjQpdC-0YHRgtC10Lsg0JzQuNC90YHQuik!5e0!3m2!1sru!2sby!4v1644144783084!5m2!1sru!2sby"
						width="600" height="450" style="border: 0;" allowfullscreen=""
						loading="lazy"></iframe>
				</div>
				<br>
			</div>
		</div>

	</div>

</body>
</html>