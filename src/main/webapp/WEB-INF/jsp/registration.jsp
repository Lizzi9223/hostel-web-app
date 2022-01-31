<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="by.epam.tc.web.controller.constant.*" %>
<%@ include file="changeLanguageTags.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>Sign Up</title>

	<script type="text/javascript" src="js/script.js" ></script>
    <link rel="stylesheet" href="css/style.css">
    
    <style>
    
    	body{
            background: url("images/bg.jpg") no-repeat;
            background-size: cover;
            margin: 0;
            padding: 0;
            font-family: sans-serif;            
        }
        
        .main{
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
                <div><a href="Controller?command=GO_TO_WELCOME_PAGE"><c:out value="${main}"/></a></div>|
                <div><a href="Controller?command=GO_TO_ROOMS_PAGE"><c:out value="${rooms}"/></a></div>|
                <div><a href="Controller?command=GO_TO_CONTACTS_PAGE"><c:out value="${contacts}"/></a></div>|
                <c:if test="${not empty sessionScope.login}" >
                    <div><a href="Controller?command=GO_TO_MY_ACCOUNT_PAGE"><c:out value="${my_account}"/></a></div>
                </c:if>                
            </div>

            <div class="tabs" style="justify-content: flex-end">
                <form>
                    <input type="hidden" name="${Utility.COMMAND}" value="${CommandName.CHANGE_LANGUAGE}">
                    <select name="${Utility.LANGUAGE}" onchange="submit()">
                            <option value="ru" ${language == 'ru' ? 'selected' : ''}><c:out value="${ru}"/></option>
                            <option value="en" ${language == 'en' ? 'selected' : ''}><c:out value="${en}"/></option>
                    </select>  
                </form>
                <c:choose>
        			<c:when test="${empty role}">
		        		<div><a href="Controller?command=GO_TO_LOGINATION_PAGE"><c:out value="${sign_in}"/></a></div>|
		        		<div><a href="Controller?command=GO_TO_REGISTRATION_PAGE"><c:out value="${sign_up}"/></a></div>
        			</c:when>
        			<c:otherwise>
        				<div><a href="Controller?command=GO_TO_WELCOME_PAGE&logOut=true"><c:out value="${log_out}"/></a></div>|
        			</c:otherwise>
        		</c:choose>
            </div>

        </div>

        <div class="main" style="background: rgba(255,255,255,0.7)">
            <div class="form">
                <form action="Controller" method="post">
                <table>
                    <tr>
                        <td><h2><c:out value="${registration}"/>:</h2><br></td>
                        <td><input type="hidden" name="${Utility.COMMAND}" value="${CommandName.REGISTRATION}" /></td>
                    </tr>

                    <tr>
                        <td><c:out value="${login_word}"/>:</td>
                        <td><input type="text" name="${Utility.LOGIN}" value = "" required/></td>                        
                    </tr>
                    <tr><td></td><td style="color:red; font-size:12px"><c:out value="${valid_login_msg}"/><br><br></td></tr>
                    <tr>
                        <td><c:out value="${password_word}"/>:</td>
                        <td><input type="password" name="${Utility.PASSWORD}" value = "" required/></td>
                    </tr>
                    <tr><td></td><td style="color:red; font-size:12px"><c:out value="${valid_password_msg}"/><br><br><br></td></tr>
                    
                    		<c:choose>
                                <c:when test="${create eq 'admin'}">
                                    <tr>
                                        <td><c:out value="${name_word}"/>:<br><br></td>
                                        <td><input type="text" name="${Utility.NAME}" value = "" required/><br><br></td>
                                    </tr>
                        <%--            <tr>
                                        <td><c:out value="${photo_word}"/>:<br><br></td>
                                        <td><input type="text" name="photo" value = ""/><br><br></td>
                                    </tr>  --%> 
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td><c:out value="${name_word}"/>:<br><br></td>
                                        <td><input type="text" name="${Utility.NAME}" value = "" required/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td><c:out value="${surname_word}"/>:<br><br></td>
                                        <td><input type="text" name="${Utility.SURNAME}" value = "" required/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td><c:out value="${passport_id_word}"/>:<br><br></td>
                                        <td><input type="text" name="${Utility.PASSPORT_ID}" value = "" required/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td><c:out value="${date_of_birth_word}"/>:<br><br></td>
                                        <td><input type="date" id="datefield" name="${Utility.DATE_OF_BIRTH}" required></td>
                                    </tr>
                                    <tr>
                                        <td><c:out value="${country_word}"/>:<br><br></td>
                                        <td><input type="text" name="${Utility.COUNTRY}" value = ""/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td><c:out value="${phone_number_word}"/>:<br><br></td>
                                        <td><input type="text" name="${Utility.PHONE}" value = "" required/><br><br></td>
                                    </tr>
                                    <tr>
                                        <td><c:out value="${email_word}"/>:<br><br></td>
                                        <td><input type="text" name="${Utility.EMAIL}" value = "" required/><br><br></td>
                                    </tr>	
                                </c:otherwise>
                            </c:choose>

                    <tr>
                        <td><input class="submit_button" type="submit" value="${sign_up}"/><br><br><br></td>
                        <td></td>
                    </tr>

                    <tr>
                        <td><a href="Controller?command=GO_TO_WELCOME_PAGE"><c:out value="${back}"/></a><br><br></td>
                        <c:if test="${not empty error and error ne ''}">
                        	<fmt:message bundle="${lang}" key="${error}" var="error_msg" />
                        	<td><p class="error"><c:out value="${error_msg}" /></p></td>
                        	<c:remove var="error"/>
                        </c:if>
                    </tr>
                </table>
               </form> 
            </div>
            

        </div>  
    
    </div>
    
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
	    document.getElementById("datefield").setAttribute("max", today);
    </script>

</body>
</html>