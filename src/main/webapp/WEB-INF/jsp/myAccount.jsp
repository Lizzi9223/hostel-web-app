<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="by.epam.tc.web.controller.constant.*"%>
<%@ include file="changeLanguageTags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>My account</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<link rel="stylesheet" href="css/style.css" />

<style>
.main {
	margin-top: 3em;
	flex-grow: 1;
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
}

.form {
	margin: 0em 3em;
	margin-bottom: 3em;
}
</style>

</head>
<body style="background-color: #D2B48C">

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
				<form>
					<table>
						<tr>
							<td><h2>
									<c:out value="${my_account}" />
								</h2> <br></td>
							<td><div></div></td>
							<c:if test="${login ne 'ADMIN'}">
								<td><button class="buttons" type="button" onclick="edit()">
										<c:out value="${edit}" />
									</button></td>
							</c:if>
							<td><input type="hidden" name="${Utility.COMMAND}"
								value="${CommandName.EDIT_ACCOUNT}" /><br></td>
						</tr>

						<tr>
							<td>${login_word}:<br> <br></td>
							<td><input class="input" disabled type="text"
								name="${Utility.LOGIN}" value="${sessionScope.login}" /><br>
								<br></td>
						</tr>
						<tr>
							<td></td>
							<td id="valid_login_msg"
								style="color: red; font-size: 12px; display: none"><c:out
									value="${valid_login_msg}" /><br> <br></td>
						</tr>

						<c:choose>
							<c:when test="${sessionScope.role eq 'ADMIN'}">
								<tr>
									<td>${name_word}:<br> <br></td>
									<td><input class="input" disabled type="text"
										name="${Utility.NAME}" value="${admin.getName()}" /><br>
										<br></td>
								</tr>
								<%--      <tr>
                                        <td>Photo:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="photo" value = "${admin.getPhotoPath()}"/><br><br></td>
                                    </tr>  --%>
							</c:when>
							<c:otherwise>
								<tr>
									<td><c:out value="${name_word}" />:<br> <br></td>
									<td><input class="input" disabled type="text"
										name="${Utility.NAME}" value="${client.getFirstName()}" /><br>
										<br></td>
								</tr>
								<tr>
									<td><c:out value="${surname_word}" />:<br> <br></td>
									<td><input class="input" disabled type="text"
										name="${Utility.SURNAME}" value="${client.getLastName()}" /><br>
										<br></td>
								</tr>
								<tr>
									<td><c:out value="${passport_id_word}" />:<br> <br></td>
									<td><input class="input" disabled type="text"
										name="${Utility.PASSPORT_ID}"
										value="${client.getPassportId()}" /><br> <br></td>
								</tr>
								<tr>
									<td><c:out value="${date_of_birth_word}" />:<br> <br></td>
									<td><input class="input" disabled type="date"
										name="${Utility.DATE_OF_BIRTH}"
										value="${client.getBirthDate()}" /><br> <br></td>
								</tr>
								<tr>
									<td><c:out value="${country_word}" />:<br> <br></td>
									<td><input class="input" disabled type="text"
										name="${Utility.COUNTRY}" value="${client.getCountry()}" /><br>
										<br></td>
								</tr>
								<tr>
									<td><c:out value="${phone_number_word}" />:<br> <br></td>
									<td><input class="input" disabled type="text"
										name="${Utility.PHONE}" value="${client.getPhoneNumber()}" /><br>
										<br></td>
								</tr>
								<tr>
									<td><c:out value="${email_word}" />:<br> <br></td>
									<td><input class="input" disabled type="text"
										name="${Utility.EMAIL}" value="${client.getEmail()}" /><br>
										<br></td>
								</tr>
							</c:otherwise>
						</c:choose>

						<tr>
							<c:set var="passportId" scope="session"
								value="${client.getPassportId()}" />
							<td><input id="save_button" style="visibility: hidden"
								class="buttons" type="submit" value="${save}" /><br> <br>
								<br></td>
							<td><div></div></td>
							<td><button id="cancel_button" class="buttons"
									style="visibility: hidden" type="button" onclick="cancelEdit()">
									<a href="Controller?command=GO_TO_MY_ACCOUNT_PAGE"><c:out
											value="${cancel}" /></a>
								</button> <br> <br> <br></td>
						</tr>

					</table>
				</form>

			</div>

			<div class="form base-form">
				<table class="buttons-list">
					<c:if test="${sessionScope.role eq 'ADMIN'}">
						<tr>
							<td>
								<form>
									<a href="Controller?command=GO_TO_CLIENTS_PAGE"> <input
										class="buttons" type="button" value="${clients_word}" />
									</a>
								</form>
							</td>
						</tr>
					</c:if>
					<tr>
						<td>
							<form>
								<a href="Controller?command=GO_TO_BOOKINGS_PAGE"> <input
									class="buttons" type="button" value="${bookings_word}" />
								</a>
							</form>
						</td>
					</tr>
					<tr>
						<td>
							<form>
								<a href="Controller?command=GO_TO_STAYS_PAGE"> <input
									class="buttons" type="button" value="${stays_word}" />
								</a>
							</form>
						</td>
					</tr>
					<tr>
					<tr>
						<td>
							<form>
								<c:if test="${sessionScope.login ne 'ADMIN'}">
									<input class="buttons open-popup change-pass" type="button"
										value="${change_password}" />
								</c:if>
							</form>
						</td>
					</tr>
					<tr>
						<td>
							<form>
								<c:if test="${sessionScope.role eq 'ADMIN'}">
									<a href="Controller?command=GO_TO_ADMINS_PAGE">
										<button class="buttons" type="button">
											<c:out value="${all_admins_word}" />
										</button>
									</a>
								</c:if>
							</form>
						</td>
					</tr>
					<tr>
						<td>
							<form>
								<c:if test="${sessionScope.login eq 'ADMIN'}">
									<a
										href="Controller?command=GO_TO_REGISTRATION_PAGE&create=admin">
										<button class="buttons" type="button">
											<c:out value="${create_new_admin}" />
										</button>
									</a>
								</c:if>
							</form>
						</td>
					</tr>
					<tr>
						<td>
							<form>
								<c:if test="${sessionScope.login ne 'ADMIN'}">
									<input class="buttons open-popup del-acc" type="button"
										value="${delete_account}" />
								</c:if>
							</form>
						</td>
					</tr>
				</table>
			</div>

			<div class="popup-bg change-pass">
				<div class="popup">
					<img class="close-popup change-pass" src="images/close.png"
						style="width: 25px">
					<form class="change-password-form">
						<input type="hidden" name="${Utility.COMMAND}"
							value="${CommandName.CHANGE_PASSWORD}" />
						<p>
							<c:out value="${current_password}" />
							:
						</p>
						<input type="password" name="${Utility.INITIAL_PASSWORD}" value=""
							required />
						<p>
							<c:out value="${new_password}" />
							:
						</p>
						<input type="password" name="${Utility.NEW_PASSWORD}" value=""
							required />
						<div style="color: red; font-size: 12px">
							<c:out value="${valid_password_msg}" />
						</div>
						<p>
							<c:out value="${repeat_new_password}" />
							:
						</p>
						<input type="password" name="confirmPassword" value="" required />
						<input class="buttons" type="submit" value="${change_password}" />
					</form>
				</div>
			</div>

			<div class="popup-bg del-acc">
				<div class="popup">
					<img class="close-popup del-acc" src="images/close.png"
						style="width: 25px">
					<form class="change-password-form">
						<input type="hidden" name="${Utility.COMMAND}"
							value="${CommandName.DELETE_ACCOUNT}" />
						<p>
							<c:out value="${del_acc_check_msg}" />
							?
						</p>
						<div style="display: flex; flex-direction: row">
							<input class="buttons" type="submit" value="${delete}"
								style="width: 100px; margin-right: 100px" /> <input
								class="close-popup del-acc buttons" type="button"
								name="changePassword" value="${cancel}" style="width: 100px" />
						</div>
					</form>
				</div>
			</div>

			<c:if test="${not empty error}">
				<div class="popup-bg info" style="display: block">
					<div class="popup" style="width: auto">
						<img id="closePopUp" class="close-popup" src="images/close.png"
							style="width: 25px">
						<fmt:message bundle="${lang}" key="${error}" var="error_msg" />
						<p>
							<c:out value="${error_msg}" />
						</p>
					</div>
				</div>
			</c:if>

			<c:remove var="error" />

		</div>

	</div>

	<script>
    	$('#closePopUp').click(function(){
    		 $('.popup-bg.info').fadeOut(300);
    		 $('html').removeClass('no-scroll');
    	});
    </script>

</body>
</html>