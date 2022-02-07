<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="by.epam.tc.web.controller.constant.*"%>
<%@ include file="changeLanguageTags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Blacklist</title>
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
            
            $('#cancel_button').click(function(){
            	$('div').find('.target').submit();
            })
            
        });
		
		 function edit(){
			 var inputs = document.getElementsByClassName("input");
			 Array.prototype.forEach.call(inputs, function(el) {
			 el.disabled = false;
			 });
			 document.getElementById("save_button").style.visibility="visible";
			 document.getElementById("cancel_button").style.visibility="visible";
			 document.getElementById("colorfulButtons").style.visibility="hidden";
			 }
        
    </script>

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

		<div class="container-body">
			<div class="main">
				<div class="form" style="padding-left: 20px">
					<h3>
						<c:out value="${blacklist}" />
						<a href="Controller?command=GO_TO_CLIENTS_PAGE"> <input
										class="buttons" type="button" value="${all_clients_word}" style="margin-left: 310px"/>
									</a>						
					</h3>
					<%--
					<form style="margin-left: 50%">
						<label><c:out value="${search_by_word}:" /></label> <select
							name="${Utility.SEARCH_CRITERIA}" style="padding: 10">
							<option value="${Utility.LOGIN}"
								${currentSearchCriteria == Utility.LOGIN ? 'selected' : ''}><c:out
									value="${login_word}" /></option>
							<option value="${Utility.PASSPORT_ID}"
								${(currentSearchCriteria == Utility.PASSPORT_ID || currentSearchCriteria == null) ? 'selected' : ''}><c:out
									value="${passport_id_word}" /></option>
							<option value="${Utility.SURNAME}"
								${currentSearchCriteria == Utility.SURNAME ? 'selected' : ''}><c:out
									value="${surname_word}" /></option>
						</select> <input type="text" name="${Utility.SEARCH_DATA}"
							value="${currentSearchData}" /> <input type="hidden"
							name="${Utility.COMMAND}" value="${CommandName.SEARCH_CLIENT}" />
						<input class="submit_button" type="submit" value="${search}"
							style="padding: 10; font-size: 14px" />
					</form>
					 --%>
					<br>

					<table class="table table-hover">

						<thead>
							<tr>
								<th scope="col"><c:out value="${clientId_word}" /></th>
								<th scope="col"><c:out value="${passport_id_word}" /></th>
								<th scope="col"><c:out value="${since_date_word}" /></th>
								<th scope="col"><c:out value="${reason_word}" /></th>
							</tr>
						</thead>

						<c:forEach var="client" items="${blacklistClients}">
							<tbody>					
									<tr class="choose-client" style="cursor: pointer; ${client.isInBlackList() == true ? 'background:#808080; ' : ''}">
										<td><c:out value="${client.getClientId()}" /></td>
										<td><c:out value="${client.getPassportId()}" /></td>
										<fmt:parseDate value="${client.getSinceDate()}"
											pattern="yyyy-MM-dd" var="parsedDate" type="date" />
										<td><fmt:formatDate value="${parsedDate}"
												pattern="dd.MM.yyyy" /></td>
										<td><c:out value="${client.getReason()}" /></td>
										<td style="visibility: hidden">
											<form class="target">
												<input type="hidden" name="${Utility.COMMAND}"
													value="${CommandName.CHOOSE_BLACKLIST_CLIENT}" /> <input
													type="hidden" name="${Utility.CHOSEN_CLIENT_ID}"
													value="${client.getClientId()}" />
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
								style="width: 25px">
							<button class="buttons" type="button" onclick="edit()" style="margin-left:60%"><c:out value="${edit}" /></button><br><br>
							<form><table>
								<tr>
									<td><c:out value="${passport_id_word}" />:</td>
									<td><input type="text" name="${Utility.PASSPORT_ID}"
										value="${chosenClient.getPassportId()}" disabled><br></td>
								</tr>
								<tr>
									<td><c:out value="${since_date_word}" />:</td>
									<td><input class="input" type="date" name="${Utility.SINCE_DATE}"
										value="${chosenClient.getSinceDate()}" disabled><br></td>
								</tr>
								<tr>
									<td><c:out value="${reason_word}" />:</td>
									<td><input class="input" type="text" name="${Utility.REASON}"
										value="${chosenClient.getReason()}" disabled><br></td>
								</tr>
								<tr>
									<td><br>
										<input type="hidden" name="${Utility.COMMAND}"
												value="${CommandName.EDIT_BLACKLIST_CLIENT}" /><input type="hidden"
												name="${Utility.CHOSEN_CLIENT_ID}" value="${chosenClientId}" />
											<input id="save_button" class="submit_button" type="submit" value="${save}"
												style="margin-left: 20px; visibility:hidden" />
									</td>
									<td class="choose-client"><br>
										<button id="cancel_button" class="buttons"
									style="visibility: hidden" type="button"><c:out
											value="${cancel}" />
										</button>
									</td>
								</tr>
							</table>
							</form>
							
							<div style="visibility: hidden">
								<form class="target">
											<input type="hidden" name="${Utility.COMMAND}"
												value="${CommandName.CHOOSE_BLACKLIST_CLIENT}" /> <input
												type="hidden" name="${Utility.CHOSEN_CLIENT_ID}"
												value="${chosenClientId}" />
										</form>
							</div>
							
							<br>
							<table>
								<tr id="colorfulButtons">
									<td style="margin-left:20px">
												<form>
													<input type="hidden" name="${Utility.COMMAND}"
														value="${CommandName.DELETE_FROM_BLACKLIST}" /><input
														type="hidden" name="${Utility.CHOSEN_CLIENT_ID}"
														value="${chosenClientId}" /> <input class="submit_button"
														type="submit" value="${delete_from_blacklist}"
														style="margin-left: 20px;" />
												</form>
									</td>								
								</tr>								
							</table>
							<br>
						</div>
					</div>
				</c:if>

				<c:if test="${popUpView eq 'AddToBlacklist'}">
					<div class="popup-bg client" style="display: block">
					<div class="popup">
						<form>
							<table style="margin-left: 20px; margin-top: 10px">
								<tr>
									<td><c:out value="${passport_id_word}:" /></td>
									<td><input type="text" 
										name="${Utility.PASSPORT_ID}" value="${chosenClient.getPassportId()}" disabled></td>
								</tr>
								<tr>
									<td><c:out value="${since_date_word}:" /></td>
									<td><input type="date" id="newBlacklistSinceDate"
										name="${Utility.SINCE_DATE}" required></td>
								</tr>
								<tr>
									<td><c:out value="${reason_word}:" /></td>
									<td><input type="text" 
										name="${Utility.REASON}"></td>
								</tr>
							</table>
							<br> 
							<table>
							<tr>
							<td><input type="hidden" name="${Utility.COMMAND}"
								value="${CommandName.SUBMIT_ADD_TO_BLACKLIST}" /><input type="hidden" name="${Utility.CHOSEN_CLIENT_ID}"
								value="${chosenClientId}" /> <input
								class="submit_button" type="submit" value="${save}"
								style="margin-left: 20px" /></td>
							<td><a href="Controller?command=GO_TO_CLIENTS_PAGE"> <input
										class="buttons" type="button" value="${cancel}" style="margin-left: 310px"/>
									</a></td>
							</tr>
							</table>
							
						</form>
						
					</div>
				</div>
				</c:if>				
			</div>
		</div>
	</div>

	<c:remove var="popUpView" />
	<c:remove var="chosenClient" />
	<c:remove var="chosenClientId" />

	<script>
		    var today = new Date();
		    var dd = today.getDate();
		    var mm = today.getMonth() + 1; //January is 0!
		    var yyyy = today.getFullYear();
		
		    if (dd < 10) {
		       dd = '0' + dd;
		    }
		
		    if (mm < 10) {
		       mm = '0' + mm;
		    } 
		        
		    today = yyyy + '-' + mm + '-' + dd;
		    document.getElementById("newBlacklistSinceDate").setAttribute("max", today);
	    </script>

</body>
</html>