<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Sign In</title>

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
	<fmt:message bundle="${lang}" key="main.welcome_to" var="welcome_to" />
	<fmt:message bundle="${lang}" key="main.hostel_Samartia" var="hostel_Samartia" />
	<fmt:message bundle="${lang}" key="logination.logination" var="logination" /> 
	<fmt:message bundle="${lang}" key="logination.login" var="login" /> 
	<fmt:message bundle="${lang}" key="logination.password" var="password" /> 
	<fmt:message bundle="${lang}" key="logination.back" var="back" /> 
    

</head>
<body>

    <div class="container" style="background: rgba(255,255,255,0.7)">
    
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

        <div class="main">

            <div class="form">
                <form action="Controller" method="post">
                <table>
                    <tr>
                        <td><h2><c:out value="${logination}"/>:</h2><br></td>
                        <td><input type="hidden" name="command" value="Logination" /></td>
                    </tr>

                    <tr>
                        <td><c:out value="${login}"/>:<br><br></td>
                        <td><input style="width:200px" type="text" name="login" value = "" required/><br><br></td>
                    </tr>
                    <tr>
                        <td><c:out value="${password}"/>:<br><br><br></td>
                        <td><input style="width:200px" type="password" name="password" value = "" required/><br><br><br></td>
                    </tr>

                    <tr>
                        <td><input class="submit_button" type="submit" value="${sign_in}"/><br><br><br></td>
                        <td></td>
                    </tr>

                    <tr>
                        <td><a href="Controller?command=GO_TO_WELCOME_PAGE"><c:out value="${back}"/></a><br><br></td>
                        <td></td>
                    </tr>
                </table>                
               </form> 
               	<c:if test="${not empty error}">
               		<p class="error"><c:out value="${error}" /></p>
               	</c:if>
	        	<br>
            </div>
        </div>    
    </div>
    
    <c:remove var="error" />

</body>
</html>    