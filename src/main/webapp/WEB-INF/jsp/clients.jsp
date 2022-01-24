<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Clients</title>
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
    	</c:if>

        <div class="container-body"> 
        	<div class="main">
                <div class="form" style="padding-left:20px">
                	<h3>
                		<c:choose>
                	 		<c:when test="${newStayGuestsNumber > 0}">
                	 			CHOOSE CLIENT:
                	 		</c:when>
                	 		<c:otherwise>
                	 			All clients                	 			
                	 		</c:otherwise>                	 		
                	 	</c:choose>
                	 	<input class="submit_button open-popup client" type="button" value="New client" style="margin-left:20px"/>
                	</h3>
                	<form style="margin-left:50%">
                		<label><c:out value="Search by:" /></label>
                	 	<select name="searchOption" style="padding:10">
                	 		<option value="login"><c:out value="Login" /></option>
                	 		<option value="passportId" selected><c:out value="Passport ID"/></option>
                	 		<option value="surname"><c:out value="Surname" /></option>
                	 	</select>
                	 	<input type="text" name="searchData" value=""/>
                	 	<input type="hidden" name="command" value="SearchClient"/>
                	 	<input class="submit_button" type="submit" value="Search" style="padding:10; font-size:14px"/>
                	</form><br>
                	
                    <table class="table table-hover">
                    
                        <thead>
                            <tr>
                            	<th scope="col">ClientId</th>                            	
                                <th scope="col">Login</th>
                                <th scope="col">Name</th>
                                <th scope="col">Surname</th>
                                <th scope="col">Passport ID</th>
                                <th scope="col">Date of birth</th>
                                <th scope="col">Country</th>
                                <th scope="col">Phone</th>
                                <th scope="col">Email</th>
                            </tr>
                        </thead>

                        <c:forEach var="client" items="${clients}">
                            
                            <tbody>
                                <tr class="choose-client" style="cursor: pointer">
                                	<td><c:out value="${client.getClientId()}" /></td>
                                	<c:choose>
                                		<c:when test="${not empty client.getUserId() and client.getUserId() ne ''}">
                                			<td><c:out value="${client.getLogin()}" /></td>
                                		</c:when>
                                		<c:otherwise>
                                			<td><c:out value="-" /></td>
                                		</c:otherwise>
                                	</c:choose>
                                	<td><c:out value="${client.getFirstName()}" /></td>
                                    <td><c:out value="${client.getLastName()}" /></td>
                                    <td><c:out value="${client.getPassportId()}" /></td>
                                    <fmt:parseDate value="${client.getBirthDate()}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                                    <td><fmt:formatDate value="${parsedDate}" pattern="dd.MM.yyyy" /></td>
                                    <td><c:out value="${client.getCountry()}" /></td>
                                    <td><c:out value="${client.getPhoneNumber()}" /></td>
                                    <td><c:out value="${client.getEmail()}" /></td>
                                 </tr>
                             </tbody>
                             <form class="target">
                                   <input type="hidden" name="command" value="ChooseClient" />
                                   <input type="hidden" name="chosenClientId" value="${client.getClientId()}"/>
                             </form>
                        </c:forEach>
                    </table>                
                </div>
                
	        	<c:if test="${popUpView eq 'options'}">
	        		<div class="popup-bg options" style="display: block">
		                <div class="popup" style="width:auto; padding: 40px; padding-bottom:0">	                    
		                    <img class="close-popup" src="images/close.png" style="width:25px"><br>		                    
		                </div>
		            </div>
	        	</c:if>
	        	
	        	<c:if test="${popUpView eq 'editClient'}">
	<%--        		<div class="popup-bg editBooking" style="display: block">
		                <div class="popup" style="width:auto; padding: 40px">	                    
		                    <img class="close-popup" src="images/close.png" style="width:25px"><br>	                    
		                    <form>                
			                    <label for="fromDate">Arrive date:</label>
			                    <input type="date" id="fromDate" name="fromDate" value="${booking.getFromDate()}" onchange="setToDate()" required>
			                    <label for="toDate">&#160;&#160;&#160;&#160;Departure date:</label>
			                    <input type="date" id="toDate" name="toDate" value="${booking.getToDate()}" required>                
			                    <br><br>
			                    <label for="questsNumber">Number of guests:&#160;</label>
			                    <input type="number" id="guestsNumber" name="guestsNumber" value="${booking.getGuestsCount()}" min="1" max="15" style="margin-right: 40px">
			                    <label for="roomNumber">Current room:</label>
			                    <input type="radio" id="roomNumber" name="roomNumber" value="${booking.getRoomNumber()}" checked>
			                    <c:out value="${booking.getRoomNumber()}" /><br>
			                    <input type="hidden" name="bookingId" value="${booking.getId()}" />
			                    <input type="hidden" name="command" value="EditBookingCheck" />
			                    <input class="submit_button" type="submit" value="Check" style="margin-right: 50px"/>
			                    <input id="check-among-all-rooms" type="checkbox" name="checkAmongAllRooms" value="Check among all rooms" checked style="visibility:hidden"/>
			                </form>
		                </div>
		            </div>  --%> 
	        	</c:if>
                                
				<div class="popup-bg client">
	                <div class="popup">
	                    <img class="close-popup" src="images/close.png" style="width:25px">
	                    <form>
		                    <table style="margin-left:20px;margin-top:10px">
		                    	<tr>
		                    		<td><c:out value="Name:"/></td>
		                    		<td><input type="text" id="newClientName" name="name" required></td>
		                    	</tr>
		                    	<tr>
		                    		<td><c:out value="Surname:"/></td>
		                    		<td><input type="text" id="newClientSurname" name="surname" required></td>
		                    	</tr>
		                    	<tr>
		                    		<td><c:out value="Passport ID:"/></td>
		                    		<td><input type="text" id="newClientPassportId" name="passportId" required></td>
		                    	</tr>
		                    	<tr>
		                    		<td><c:out value="Date of birth:"/></td>
		                    		<td><input type="date" id="newClientBirthDate" name="dateOfBith" required></td>
		                    	</tr>
		                    	<tr>
		                    		<td><c:out value="Country:"/></td>
		                    		<td><input type="text" id="newClientCountry" name="country"></td>
		                    	</tr>
		                    	<tr>
		                    		<td><c:out value="Phone:"/></td>
		                    		<td><input type="text" id="newClientPhone" name="phone"></td>
		                    	</tr>
		                    	<tr>
		                    		<td><c:out value="Email:"/></td>
		                    		<td><input type="text" id="newClientEmail" name="email"></td>
		                    	</tr>
		                    </table><br>
		                    <input type="hidden" name="command" value="AddClient" />
		                    <input class="submit_button" type="submit" value="Submit" style="margin-left:20px"/>
		               </form>
	                </div>
	            </div>
	            
	            <c:if test="">
	            		<div class="popup-bg" style="display: block">
			                <div class="popup" style="width:auto; padding-top:30px">
			                    <a href="Controller?command=GO_TO_BOOKINGS_PAGE">
			                		<img src="images/close.png" style="width:25px;">
			                	</a>
			                    <h5 style="margin-top:15px">Please, check data:</h5>
			                    <form>			                    	
				                    <table>
				                    <%-- 	<tr>
				                    		<td><b>Since:</b></td>
				                    		<td><input type="date" name="fromDate" value="${fromDate}" readonly /></td>
				                    	</tr>
				                    	<tr>
				                    		<td><b>To:</b></td>
				                    		<td><input type="date" name="toDate" value="${toDate}" readonly /></td>
				                    	</tr>
				                    	<tr>
				                    		<td><b>Guests number:</b></td>
				                    		<td><input type="number" name="guestsNumber" value="${guestsNumber}" readonly /></td>
				                    	</tr>   --%>
				                    </table>			                    
				                    <br>
				                    <input type="hidden" name="editedClientId" value="${editedClientId}" />
				                    <input type="hidden" name="command" value="SubmitClient" />
				                    <input class="submit_button" type="submit" value="Submit"/>
			                    </form>			                    
			                </div>
			            </div>
	            </c:if>
	         	
          </div> 
        </div>
       </div> 
       
       <c:remove var="popUpView"/>
       <c:remove var="chosenClientId"/>
       
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
		    document.getElementById("newClientBirthDate").setAttribute("max", today);
	    </script>

	</body>
</html>