<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>My account</title>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <style>
    
        body{
            background-color: #D2B48C;
            background-size: cover;
            margin: 0;
            padding: 0;
            font-family: sans-serif;            
        }
        
        .container{
            display: flex;
            flex-direction: column;
            height: 100vh;
        }
        
        .menu{
            background-color: #DEB887;
            border: 3px outset black;  
            height: 6vh; 
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: space-between;
            box-sizing: border-box; 
        }        
                
        .tabs{
            display: flex;
            flex-direction: row;
            align-items: center;
            margin: 0 3em 0 3em;
        }
        
        .tabs div {
            margin: 0 2.5em;
            width: auto;
        }
        
        .main{
            flex-grow: 1;
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
        }
        
        .form{
            margin: 0em 3em;
            padding: 3em 4em;
            padding-bottom: 0;
            background-color: rgba(255, 228, 181, 1);
            border: 2px solid black;
            border-radius: 10px;
        } 
        
        .base-form{
            display: flex;
            flex-direction: column;
        }
        
        .buttons{
            font-size: 16px;
            border: 0;
            padding: 0.5em 1em;
            background-color: #CD853F;            
        }
        
        .buttons:hover{
            cursor: pointer;
        }
        
        a{
            text-decoration: none;
            color: black;
        }
        
        .popup-bg{
            position: fixed;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            background: rgba(255,255,255,0.8);
            display: none;
        }
        
        .popup{
            position: absolute;
            top: 50%;
            left: 50%;
            width: 600px;
            background: rgba(255, 228, 181, 1);
            padding: 20px;
            transform: translate(-50%, -50%);
            padding-top: 60px;
        }
        
        .close-popup:not(.buttons){
            position: absolute;
            top: 30px;
            left: 30px;
            cursor: pointer;
        }
        
        .no-scroll{
            overflow-y: hidden;
        }
        
        .change-password-form{
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            
        }
        
        .change-password-form input{
            width: 40%;
            margin: 2% 0;
        }
        
        .buttons-list{
            margin-bottom: 45px;
        }
        
        .buttons-list input, .buttons-list button{
        	width: 220px;
        }
        
    
    </style>   
    
    <fmt:setLocale value="${sessionScope.language}"/>
	<fmt:setBundle basename="prop" var="lang"/>
	<fmt:message bundle="${lang}" key="menu.main" var="main" />

</head>
<body>

	<c:set var="passportId" scope="page" value="${passportId}" />
    
    <script>
    
	    function edit(){
	        var inputs = document.getElementsByClassName("input");
	        Array.prototype.forEach.call(inputs, function(el) {
	            el.disabled = false;
	        });
	        document.getElementById("save_button").style.visibility="visible";
	        document.getElementById("cancel_button").style.visibility="visible";
	    }
	    
	    function cancelEdit(){
	        var inputs = document.getElementsByClassName("input");
	        Array.prototype.forEach.call(inputs, function(el) {
	            el.disabled = true;
	        });
	        document.getElementById("save_button").style.visibility="hidden";
	        document.getElementById("cancel_button").style.visibility="hidden";
	        window.location.reload();
	    }
        
        $(document).ready(function(){
                
            $('.open-popup.change-pass').click(function(e){
                e.preventDefault();
                $('.popup-bg.change-pass').fadeIn(300);
                $('html').addClass('no-scroll');
            })
        
            $('.close-popup.change-pass').click(function(){
                $('.popup-bg.change-pass').fadeOut(300);
                $('html').removeClass('no-scroll');
            })
            
            $('.open-popup.del-acc').click(function(e){
                e.preventDefault();
                $('.popup-bg.del-acc').fadeIn(300);
                $('html').addClass('no-scroll');
            })
        
            $('.close-popup.del-acc').click(function(){
                $('.popup-bg.del-acc').fadeOut(300);
                $('html').removeClass('no-scroll');
            })
            
            $("input[name=confirmPassword]").keyup(function(){
              if ($('input[name=newPassword]').val() == $('input[name=confirmPassword]').val()) {
                  $('input[name=confirmPassword]').css('background', 'white');
                  $('input[name=changePassword]').removeAttr('disabled');
                  $('input[name=changePassword]').css("cursor", 'pointer');
              } else {
                  $('input[name=confirmPassword]').css('background', 'red');
                  $('input[name=changePassword]').attr("disabled", 'disabled');
                  $('input[name=changePassword]').css("cursor", 'default');
              }                
            })
        })
        
        
    
    </script>

    <div class="container">
    
        <div class="menu">

            <div class="tabs" style="justify-content: flex-start">
                <div><a href="Controller?command=GO_TO_WELCOME_PAGE"><c:out value="${main}"/></a></div>|
                <div>Rooms</div>|
                <div>Photos</div>|
                <div>Contacts</div>|
                <c:if test="${not empty sessionScope.login}" >
                    <div><a href="Controller?command=GO_TO_MY_ACCOUNT_PAGE">My account</a></div>
                </c:if>                
            </div>

            <div class="tabs" style="justify-content: flex-end">
                <form>
                    <input type="hidden" name="command" value="ChangeLanguage" >
                    <select name="language" onchange="submit()">
                            <option value="ru" ${language == 'ru' ? 'selected' : ''}>ru</option>
                            <option value="en" ${language == 'en' ? 'selected' : ''}>en</option>
                    </select>  
                </form>
                <c:choose>
        			<c:when test="${empty role}">
		        		<div><a href="Controller?command=GO_TO_LOGINATION_PAGE">Sign In</a></div>|
		        		<div><a href="Controller?command=GO_TO_REGISTRATION_PAGE">Sign Up</a></div>
        			</c:when>
        			<c:otherwise>
        				<div><a href="Controller?command=GO_TO_WELCOME_PAGE&logOut=true">Log out</a></div>|
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
                        <td><button class="buttons" type="button" onclick="edit()">Edit</button></td>
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
                                        <td><input class="input" disabled="true" type="text" name="name" value = "${sessionScope.name}"/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td>Photo:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="photo" value = "${sessionScope.photo}"/><br><br></td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td>Name:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="name" value="${sessionScope.name}"/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td>Surname:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="surname" value = "${sessionScope.surname}"/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td>Passport id:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="passportId" value = "${sessionScope.passportId}"/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td>Date of birth:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="dateOfBith" value = "${sessionScope.dateOfBith}"/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td>Country:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="country" value = "${sessionScope.country}"/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td>Phone number:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="phone" value = "${sessionScope.phone}"/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td>Email:<br><br></td>
                                        <td><input class="input" disabled="true" type="text" name="email" value = "${sessionScope.email}"/><br><br></td>
                                    </tr>	
                                </c:otherwise>
                            </c:choose>
                    
                    <tr>
                          <td><input id="save_button" style="visibility: hidden;" class="buttons" type="submit" value="Save"/><br><br><br></td>
                          <td><div></div></td>
                          <td><button id="cancel_button" class="buttons" style="visibility: hidden;" type="button" command="GO_TO_MY_ACCOUNT_PAGE" onclick="cancelEdit()">
                          	<a href="Controller?command=GO_TO_MY_ACCOUNT_PAGE">Cancel</a></button><br><br><br></td>
                    </tr>
                    
                    <tr>
                        <td><c:out value="${param.errorMessage}" /></td>
                        <td></td>
                    </tr>
                    
                </table>
               </form>  
            
            </div>
            
            <div class="form base-form">
                <table class="buttons-list">
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
                                <input class="buttons open-popup del-acc" type="button" value="Delete account"/>
                            </form>
                        </td>
                    </tr>
                </table>            
            </div>
            
            <div class="popup-bg change-pass">
                <div class="popup">
                    <img class="close-popup change-pass" alt="icon">
                    <form class="change-password-form">
                        <input type="hidden" name="command" value="ChangePassword" />
                        <p>Current password:</p>
                        <input type="password" name="initialPassword" value="" />
                        <p>New password:</p>
                        <input type="password" name="newPassword" value="" />
                        <p>Repeat new password:</p>
                        <input type="password" name="confirmPassword" value="" />
                        <input class="buttons" type="submit" name="changePassword" value="Change password"/>
                    </form>
                </div>
            </div>
            
            <div class="popup-bg del-acc">
                <div class="popup">
                    <img class="close-popup del-acc" alt="icon">
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
            

        </div>  
    
    </div>

</body>
</html>