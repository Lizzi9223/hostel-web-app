<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Bookings</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css" rel="stylesheet" />
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet" />

	<script type="text/javascript" src="js/script.js" ></script>
	<link rel="stylesheet" href="css/style.css">

	<style>
           
        .main{
            margin-top: 3em;
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        
        .form{
            margin: 0em 3em;
            margin-bottom: 3em;
            width: 1100px;
        }
        
        .buttons-list input, .buttons-list button{
		 	width: 150px;
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
	
	<script>
                
		$(document).ready(function() {
			
			$('.open-popup').click(function(e){
                e.preventDefault();
                $('.popup-bg').fadeIn(300);
                $('html').addClass('no-scroll');
                $('#chosen-booking-id').val($(this).find('.booking-id').text());
                
            })
        
            $('.close-popup').click(function(){
                $('.popup-bg').fadeOut(300);
                $('html').removeClass('no-scroll');
            })
            
            $('.choose-stay').click(function(){
            	$(this).find('.target').submit();
            })
            
        });
        
    </script>

    <div class="container">
    
        <div class="menu">

            <div class="tabs" style="justify-content: flex-start">
                <div><a href="Controller?command=GO_TO_WELCOME_PAGE"><c:out value="${main}"/></a></div>|
                <div><a href="Controller?command=GO_TO_ROOMS_PAGE"><c:out value="${rooms}"/></a></div>|
                <div><c:out value="${photos}"/></div>|
                <div><c:out value="${contacts}"/></div>|
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

        <div class="container-body">   
        
        	<div class="main">
                
                <div class="form">
                
                	<h3>
                	 	<c:choose>
                	 		<c:when test="${role eq 'ADMIN' }">
                	 			All stays 
                	 			<input class="submit_button open-popup stay" type="button" value="New stay" style="margin-left:20px"/>
                	 		</c:when>
                	 		<c:otherwise>
                	 			My stays
                	 		</c:otherwise>
                	 	</c:choose>
                	</h3><br>
                
                    <table class="table table-hover">
                    
                        <thead>
                            <tr>
                            	<c:if test="${role eq 'ADMIN'}">
	                            	<th scope="col">ClientId</th>                               	
		                        </c:if>                           	
                                <th scope="col">Since</th>
                                <th scope="col">To</th>                                
                                <th scope="col">Room</th>
                                <th scope="col">Notes</th>
                                <th scope="col" style="visibility: hidden"></th>
                            </tr>
                        </thead>

                        <c:forEach var="stay" items="${stays}">                            
                            <tbody>
                                <tr class="choose-stay" style="cursor: pointer">
                                	<c:if test="${role eq 'ADMIN'}">
		                            	<td><c:out value="${stay.getClientId()}" /></td>                               	
			                        </c:if>                        
                                    <c:set var="dateToParse" value="${stay.getFromDate()}"/>
                                    <fmt:parseDate value="${dateToParse}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                                    <td><fmt:formatDate value="${parsedDate}" pattern="dd.MM.yyyy" /></td>
                                    <c:set var="dateToParse" value="${stay.getToDate()}"/>
                                    <fmt:parseDate value="${dateToParse}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                                    <td><fmt:formatDate value="${parsedDate}" pattern="dd.MM.yyyy" /></td>
                                    <td><c:out value="${stay.getRoomNumber()}" /></td>
                                    <td><c:out value="${stay.getNotes()}" /></td>
                                    <td style="visibility: hidden" >
                                    	<form class="target">
                                    		<input type="hidden" name="command" value="ChooseStay" />
                                    		<input type="hidden" name="chosenStayId" value="${stay.getId()}" />
                                    	</form>
                                    </td>
                                </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                
                </div> 
                
	        	<c:if test="${popUpView eq 'options'}">
	        		<div class="popup-bg options" style="display: block">
		                <div class="popup" style="width:auto; padding: 40px">	                    
		                    <img class="close-popup" src="images/close.png" style="width:25px"><br>
		                    <table class="buttons-list">
		                    <c:choose>
		                    	<c:when test="${role eq 'ADMIN'}">             			
		                    				<tr>
		                    					<td>
		                    						<form>
					                    				<input type="hidden" name="command" value="EditStay" />
					                    				<input type="hidden" name="stayId" value="${chosenStayId}" />
					                    				<input class="submit_button" type="submit" value="Edit" style="margin-right:20px"/>
					                    			</form>
		                    					</td>
		                    				</tr>
			                    			<c:if test="${login eq 'ADMIN'}">
			                    				<tr>
			                    					<td>
			                    						<form>
						                    				<input type="hidden" name="command" value="DeleteStay" />
						                    				<input type="hidden" name="stayId" value="${chosenStayId}" />
						                    				<input class="submit_button" type="submit" value="Delete"/>
						                    			</form>
			                    					</td>
			                    				</tr>
			                    			</c:if>	
		                    	</c:when>
		                    </c:choose>
		                    </table>
		                </div>
		            </div>
	        	</c:if>
	        	
	        	<div class="popup-bg stay">
	                <div class="popup">
	                    <img class="close-popup" src="images/close.png" style="width:25px">
	                    
	                </div>
	            </div>
	         	
	         	<c:remove var="stay"/>
	         	<c:remove var="chosenStayId"/>
	         	<c:remove var="popUpView"/>
	         	
          </div>        
        
        </div>
        

       </div> 


	</body>
</html>