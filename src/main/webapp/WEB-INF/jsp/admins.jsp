<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="by.epam.tc.web.controller.constant.*"%>
<%@ include file="changeLanguageTags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Clients</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css"
	rel="stylesheet" />
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	rel="stylesheet" />

<script type="text/javascript" src="js/script.js"></script>
<link rel="stylesheet" href="css/style.css">

<style>
.main {
	margin-top: 3em;
	flex-grow: 1;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.form {
	margin: 0em 3em;
	margin-bottom: 3em;
	width: 1100px;
}

.buttons-list input, .buttons-list button {
	width: 150px;
}
</style>

</head>

<body style="background-color: #D2B48C">

	<script>
                
		$(document).ready(function() {
			
			$('.open-popup').click(function(e){
                e.preventDefault();
                $('.popup-bg').fadeIn(300);
                $('html').addClass('no-scroll');
                $('#chosen-client-id').val($(this).find('.client-id').text());
                
            })
        
            $('.close-popup').click(function(){
                $('.popup-bg').fadeOut(300);
                $('html').removeClass('no-scroll');
            })
            
            $('.choose-client').click(function(){
            	$(this).find('.target').submit();
            })
            
        });
        
    </script>

	<div class="container">

		<c:if test="${!(newStayGuestsNumber > 0)}">
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
		</c:if>

		<div class="container-body">
			<div class="main">
				<div class="form" style="padding-left: 20px">
					<h3>
						<c:out value="${all_admins_word}" />
						<c:if test="${sessionScope.login eq 'ADMIN'}">
							<a href="Controller?command=GO_TO_REGISTRATION_PAGE&create=admin">
								<button class="buttons" type="button" style="margin-left: 20px">
									<c:out value="${create_new_admin}" />
								</button>
							</a>
						</c:if>
					</h3>
					<br>

					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col"><c:out value="${admin_id_word}" /></th>
								<th scope="col"><c:out value="${login_word}" /></th>
								<th scope="col"><c:out value="${name_word}" /></th>
								<%-- <th scope="col"><c:out value="${photo_word}" /></th> --%>
							</tr>
						</thead>

						<c:forEach var="admin" items="${admins}">
							<tbody>
								<tr ${admin.getLogin() ne 'ADMIN' ? 'class="choose-client"' : '' } style="cursor: pointer">
									<td><c:out value="${admin.getUserId()}" /></td>
									<td><c:out value="${admin.getLogin()}" /></td>
									<td><c:out value="${admin.getName()}" /></td>
									<%-- <td><c:out value="${admin.getPhotoPath()}" /></td> --%>
									<td style="visibility: hidden">
										<form class="target">
											<input type="hidden" name="${Utility.COMMAND}"
												value="${CommandName.CHOOSE_ADMIN}" /> <input type="hidden"
												name="${Utility.CHOSEN_ADMIN_ID}"
												value="${admin.getUserId()}" />
										</form>
									</td>
								</tr>
							</tbody>
						</c:forEach>
					</table>
				</div>

				<c:if test="${popUpView eq 'options'}">
					<div class="popup-bg options" style="display: block">
						<div class="popup"
							style="width: auto; padding: 40px; padding-bottom: 0">
							<img class="close-popup" src="images/close.png"
								style="width: 25px"><br>
							<table>
								<tr>
									<td><c:out value="${admin_id_word}" />:</td>
									<td><input type="text" name="${Utility.ID}"
										value="${chosenAdmin.getUserId()}" disabled><br></td>
								</tr>
								<tr>
									<td><c:out value="${login_word}" />:</td>
									<td><input type="text" name="${Utility.LOGIN}"
										value="${chosenAdmin.getLogin()}" disabled><br></td>
								</tr>
								<tr>
									<td><c:out value="${name_word}" />:</td>
									<td><input type="text" name="${Utility.NAME}"
										value="${chosenAdmin.getName()}" disabled><br></td>
								</tr>
								<%-- <tr>
									<td><c:out value="${photo_word}" />:</td>
									<td><input type="text" name="${Utility.PHOTO}"
										value="${chosenAdmin.getPhotoPath()}" disabled><br></td>
								</tr> --%>
							</table>
							<br>
							<table>
								<c:if test="${sessionScope.login eq 'ADMIN'}">
									<tr>
										<td>
											<form>
												<input class="buttons open-popup del-acc" type="button"
													value="${delete_account}" />
											</form>
										</td>
									</tr>
								</c:if>
							</table>
							<br>
						</div>
					</div>
				</c:if>

				<div class="popup-bg del-acc">
					<div class="popup">
						<img class="close-popup del-acc" src="images/close.png"
							style="width: 25px">
						<form class="change-password-form">
							<input type="hidden" name="${Utility.COMMAND}"
								value="${CommandName.DELETE_ADMIN}" /> <input type="hidden"
								name="${Utility.CHOSEN_ADMIN_ID}" value="${chosenAdminId}" />
							<p>
								<c:out value="${del_admin_check_msg}" />
								<c:out value=" ${chosenAdmin.getLogin()}" />
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

			</div>
		</div>
	</div>

	<c:remove var="popUpView" />
	<c:remove var="chosenAdmin" />
	<c:remove var="chosenAdminId" />

</body>
</html>