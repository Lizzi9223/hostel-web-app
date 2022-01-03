<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Booking</title>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css" rel="stylesheet" />
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet" />

	<script type="text/javascript" src="js/script.js" ></script>
	<link rel="stylesheet" href="css/style.css">

    <style>
    
    	.form{
            margin: 0em 3em;
            margin-bottom: 3em;
        }
    
    	input[type=range] {
            -webkit-appearance: none;
            background: rgb(0,0,0,0.6);
            height: 10px;
        }
    
        input[type=range]::-webkit-slider-thumb {
          height: 20px;
          width: 11px;
          border-radius: 5px;
          background: #CD853F;
          cursor: pointer;
          -webkit-appearance: none;
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

        <div class="main" style="flex-direction: column; justify-content: center">
            
            <div class="form" style="padding-bottom: 20px">
                
                <h4>Check for available places room #<c:out value="${room.getRoomNumber()}"/>:</h4>
                <br>
                
                <form>
                
                    <label for="fromDate">Arrive date:</label>
                    <input type="date" id="fromDate" name="fromDate" onchange="setToDate()" required>
                    <label for="toDate">&#160;&#160;&#160;&#160;Departure date:</label>
                    <input type="date" id="toDate" name="toDate" required>                
                    <br><br>
                    <label for="questsNumber">Number of guests:&#160;</label>
                    <input type="number" id="guestsNumber" name="guestsNumber" value="1" min="1" max="15" style="margin-right: 90px">
                    <input type="hidden" name="roomNumber" value="${room.getRoomNumber()}" />
                    <input type="hidden" name="command" value="CheckRoomAvailability" />
                    <input class="submit_button" type="submit" value="Check" style="margin-right: 50px"/>
                    <input id="check-among-all-rooms" type="checkbox" name="checkAmongAllRooms" value="Check among all rooms" />
                    <label for="check-among-all-rooms" >Check among all rooms</label>
                
                </form>

            </div>

            <div class="form rooms">  
					    <div class="slider-container">
					      <div class="row">
					        <div class="col-10">
					          <div class="swiper-container product-slider">
					            <div class="swiper-wrapper">
					            <c:forEach var="image" items="${room.getImages()}">
					               <div class="swiper-slide">
					                   <img src="${image.getImgPath()}" alt="1">
					               </div>
					            </c:forEach>
					            </div>
					            <div class="swiper-button-next">
					              <i class="fa fa-chevron-right"></i>
					            </div>
					            <div class="swiper-button-prev">
					              <i class="fa fa-chevron-left"></i>
					            </div>
					          </div>
					        </div>
					        <div class="col-2">
					          <div class="swiper-container product-thumbs">
					            <div class="swiper-wrapper">
					                <c:forEach var="image" items="${room.getImages()}">
					                   <div class="swiper-slide">
					                       <img src="${image.getImgPath()}" alt="1">
					                   </div>
					                </c:forEach>
					            </div>
					          </div>
					        </div>
					      </div>
					    </div>					
	                
		                <div class="info">	                    
		                    
		                    <div>                    
		                        <div>${room.getRoomNumber()}</div>
		                        <div>${room.getCost()}</div>
		                        <div>${room.getCapacity()}</div>
		                        <div>${room.getGender()}</div>
		                        <div>${room.isBathroomInRoom()}</div>
		                        <div>${room.getNotes()}</div>                    
		                    </div>
		                    
		                </div>	                
	                </div>
            
            <div class="form"> 
                <div style="padding-bottom: 30px">                     
                    <c:forEach var="room" items="${allRooms}">
                        <div style="display: inline-block; margin-right: 20px; margin-bottom: 20px">
                        	<c:if test="${room.getImages().size()>0}">
                        		<img src="${room.getImages().get(0).getImgPath()}" style="display: block; width: 250px"/>
                        	</c:if>
                            
                            <form>
                                 <input type="hidden" name="command" value="BookRoom" />
							     <input type="hidden" name="roomNumber" value="${room.getRoomNumber()}" />
							     <input class="submit_button" type="submit" value="Room #${room.getRoomNumber()}"/>
                            </form>
                        </div>
                    </c:forEach>
                </div>  
                
            </div>
            
        <c:choose>
	            	<c:when test="${checkResult eq false}">
	            		<div class="popup-bg" style="display: block">
			                <div class="popup" style="width:auto">
			                     <img class="close-popup" src="images/close.png" style="width:25px">
			                    <p>Sorry, no available places</p>
			                </div>
			            </div>
	            	</c:when>
	            	<c:when test="${checkResult eq true}">
	            		<div class="popup-bg" style="display: block">
			                <div class="popup" style="width:auto">
			                     <img class="close-popup" src="images/close.png" style="width:25px">
			                    <h5>Please, check data:</h5>
			                    <form>
			                    	<b>Room number:</b><br>
				                    <c:forEach var="room" items="${availableRooms}">
	                                    <input type="radio" name="roomNumber" value="${room.getRoomNumber()}">
				                    	<c:out value="${room.getRoomNumber()}" /><br>
				                    </c:forEach>
				                    <b>Since:</b><input type="date" name="fromDate" value="${fromDate}" readonly /><br>
				                    <b>To:</b><input type="date" name="toDate" value="${toDate}" readonly /><br>				                    
				                    <b>Guests number:</b><input type="number" name="guestsNumber" value="${guestsNumber}" readonly /><br><br>
				                    <input type="hidden" name="command" value="SubmitBooking" />
				                    <input class="submit_button" type="submit" value="Submit booking"/>
			                    </form>			                    
			                </div>
			            </div>
	            	</c:when>
	            </c:choose>
            
        </div>

    </div>

</body>
</html>