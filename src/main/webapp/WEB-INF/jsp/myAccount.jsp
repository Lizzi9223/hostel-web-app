<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>My account</title>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/script.js" ></script>
    <link rel="stylesheet" href="css/style.css" /> 
    
    <style>
    
    	.main{
            margin-top: 3em;
            flex-grow: 1;
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
        }
        
        .form{
            margin: 0em 3em;
            margin-bottom: 3em;
        }
    
    </style>
    
    <fmt:setLocale value="${sessionScope.language}"/>
	<fmt:setBundle basename="prop" var="lang"/>
	<fmt:message bundle="${lang}" key="menu.main" var="main" />
	<fmt:message bundle="${lang}" key="menu.rooms" var="rooms" />
	<fmt:message bundle="${lang}" key="menu.photos" var="photos" />
	<fmt:message bundle="${lang}" key="menu.contacts" var="contacts" />
	<fmt:message bundle="${lang}" key="menu.my_account" var="my_account" />
	<fmt:message bundle="${lang}" key="menu.sign_in" var="sign_in" />
	<fmt:message bundle="${lang}" key="menu.sign_up" var="sign_up" />
	<fmt:message bundle="${lang}" key="menu.log_out" var="log_out" />
	<fmt:message bundle="${lang}" key="menu.ru" var="ru" />
	<fmt:message bundle="${lang}" key="menu.en" var="en" />

</head>
<body style="background-color: #D2B48C">

	<c:set var="passportId" scope="page" value="${passportId}" />

    <div class="container">
    
        <div class="menu">

            <div class="tabs" style="justify-content: flex-start">
                <div><a href="Controller?command=GO_TO_WELCOME_PAGE"><c:out value="${main}"/></a></div>|
                <div><a href="Controller?command=GO_TO_ROOMS_PAGE"><c:out value="${rooms}"/></a></div>|
                <div><a href="Controller?command=GO_TO_CONTACTS_PAGE"><c:out value="${contacts}"/></a></div>|
                <c:if test="${not empty sessionScope.login}" >
                    <div><a href="Controller?command=GO_TO_MY_ACCOUNT_PAGE"><c:out value="${my_account}"/></a></div>                    
                </c:if>                
            </div>

            <div class="tabs" style="justify-content: flex-end">
                <form>
                    <input type="hidden" name="command" value="ChangeLanguage" >
                    <select name="language" onchange="submit()">
                            <option value="ru" ${language == 'ru' ? 'selected' : ''}><c:out value="${ru}"/></option>
                            <option value="en" ${language == 'en' ? 'selected' : ''}><c:out value="${en}"/></option>
                    </select>  
                </form>
                <c:choose>
        			<c:when test="${empty sessionScope.role}">
		        		<div><a href="Controller?command=GO_TO_LOGINATION_PAGE"><c:out value="${sign_in}"/></a></div>|
		        		<div><a href="Controller?command=GO_TO_REGISTRATION_PAGE"><c:out value="${sign_up}"/></a></div>
        			</c:when>
        			<c:otherwise>
        				<div><a href="Controller?command=GO_TO_WELCOME_PAGE&logOut=true"><c:out value="${log_out}"/></a></div>|
        			</c:otherwise>
        		</c:choose>
            </div>

        </div>

        <div class="main">            
                      
            <div class="form">
            
                <form action="Controller" method="post">
                <table>
                    <tr>
                        <td><h2>My account</h2><br></td>
                        <td><div></div></td>
                        <c:if test="${login ne 'ADMIN'}">
                        	<td><button class="buttons" type="button" onclick="edit()">Edit</button></td>
                        </c:if>                        
                        <td><input type="hidden" name="command" value="Edit" /><br></td>
                    </tr>

                    <tr>
                        <td>Login:<br><br></td>
                        <td><input class="input" disabled="true" type="text" name="login" value = "${sessionScope.login}"/><br><br></td>
                    </tr>
                    
                    		<c:choose>
                                <c:when test="${sessionScope.role eq 'ADMIN'}">
                                    <tr>
                                        <td>Name:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="name" value = "${admin.getName()}"/><br><br></td>
                                    </tr>
                               <%--      <tr>
                                        <td>Photo:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="photo" value = "${admin.getPhotoPath()}"/><br><br></td>
                                    </tr>  --%>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td>Name:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="name" value="${client.getFirstName()}"/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td>Surname:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="surname" value = "${client.getLastName()}"/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td>Passport id:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="passportId" value = "${client.getPassportId()}"/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td>Date of birth:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="dateOfBith" value = "${client.getBirthDate()}"/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td>Country:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="country" value = "${client.getCountry()}"/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td>Phone number:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="phone" value = "${client.getPhoneNumber()}"/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td>Email:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="email" value = "${client.getEmail()}"/><br><br></td>
                                    </tr>	
                                </c:otherwise>
                            </c:choose>
                    
                    <tr>
                    	  <c:set var="passportId" scope="session" value="${client.getPassportId()}"/> 
                          <td><input id="save_button" style="visibility: hidden;" class="buttons" type="submit" value="Save"/><br><br><br></td>
                          <td><div></div></td>
                          <td><button id="cancel_button" class="buttons" style="visibility: hidden" type="button" onclick="cancelEdit()">
                          	<a href="Controller?command=GO_TO_MY_ACCOUNT_PAGE">Cancel</a></button><br><br><br></td>
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
	                            	<a href="Controller?command=GO_TO_CLIENTS_PAGE">                            	
	                                	<input class="buttons" type="button" value="Clients"/>
	                                </a>
	                            </form>
	                        </td>
	                    </tr>
                	</c:if> 
                	<tr>
                        <td>
                            <form> 
                            	<a href="Controller?command=GO_TO_BOOKINGS_PAGE">                            	
                                	<input class="buttons" type="button" value="Bookings"/>
                                </a>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form> 
                            	<a href="Controller?command=GO_TO_STAYS_PAGE">                            	
                                	<input class="buttons" type="button" value="Stays"/>
                                </a>
                            </form>
                        </td>
                    </tr>
                    <tr>
                    <tr>
                        <td>
                            <form>   
                            	<c:if test="${sessionScope.login ne 'ADMIN'}" >
                                	<input class="buttons open-popup change-pass" type="button" value="Change password"/>                                	
								</c:if> 
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form>
                                <c:if test="${sessionScope.login eq 'ADMIN'}" >
                                	<a href="Controller?command=GO_TO_REGISTRATION_PAGE&create=admin">
                                		<button class="buttons" type="button">Create new administrator</button>
                                	</a>                                	
								</c:if>
                            </form> 
                        </td>                       
                    </tr>
                    <tr>
                        <td>
                            <form>
                            	<c:if test="${sessionScope.login ne 'ADMIN'}" >
                                	<input class="buttons open-popup del-acc" type="button" value="Delete account"/>                                	
								</c:if>   
                            </form>
                        </td>
                    </tr>
                </table>            
            </div>
            
            <div class="popup-bg change-pass">
                <div class="popup">
                    <img class="close-popup change-pass" src="images/close.png" style="width:25px">
                    <form class="change-password-form">
                        <input type="hidden" name="command" value="ChangePassword" />
                        <p>Current password:</p>
                        <input type="password" name="initialPassword" value="" required/>
                        <p>New password:</p>
                        <input type="password" name="newPassword" value="" required/>
                        <p>Repeat new password:</p>
                        <input type="password" name="confirmPassword" value="" required/>
                        <input class="buttons" type="submit" name="changePassword" value="Change password"/>
                    </form>
                </div>
            </div>
            
            <div class="popup-bg del-acc">
                <div class="popup">
                     <img class="close-popup del-acc" src="images/close.png" style="width:25px">
                    <form class="change-password-form">
                        <input type="hidden" name="command" value="DeleteAccount" />
                        <p>Are you sure you want to delete your account?</p>
                        <div style="display:flex; flex-direction: row">
                        	<input class="buttons" type="submit" name="changePassword" value="Delete" style="width:100px; margin-right:100px"/>
                        	<input class="close-popup del-acc buttons" type="button" name="changePassword" value="Cancel" style="width:100px"/>
                        </div>
                    </form>
                </div>
            </div>
            
            <c:if test="${not empty error}">
            	<div class="popup-bg info" style="display:block">
	                <div class="popup" style="width:auto">
	                    <img id="closePopUp" class="close-popup" src="images/close.png" style="width:25px">
	                    <p><c:out value="${error}" /></p>
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