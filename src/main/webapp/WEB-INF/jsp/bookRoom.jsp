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
            padding: 3em 4em;
            padding-bottom: 0;
            background-color: rgba(255, 228, 181, 1);
            border: 2px solid black;
            border-radius: 10px;
            width: 1100px;
        }
    
        .search.form{   
            margin-top: 3em;
            margin-right: 0;
            padding: 20px;            
            background-color: rgba(255, 228, 181, 1);
            border: 2px solid black;
            border-radius: 10px;
        }
        
        .base-form{
            display: flex;
            flex-direction: column;
        }
        
        .submit_button{
            font-size: 16px;
            border: 0;
            padding: 0.5em 1em;
            background-color: #CD853F;            
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
        
        
        .rooms{
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center;
        }
                
                
        .slider-container{
            width:700px;
            height: 500px;
            margin-right: 2em;
        }
        
        .product-slider {
          height: 450px;
          box-shadow: 0 0 15px black;
         }

        .product-slider .swiper-slide {
          display: flex;
          justify-content: center;
          align-items: center;
        }

        .product-thumbs {
          height: 400px;
        }

        .product-thumbs .swiper-wrapper {
          margin-top: calc(-100% + 5px);
        }

        .product-thumbs .swiper-slide {
          width: auto;
          padding: 0;
          display: flex;
          justify-content: center;
          align-items: center;
        }
        
        .swiper-slide img{
            width: 100%;
            height: 100%;
        }

        .product-thumbs .swiper-slide-active {
          border: solid 2px black;
        }
        
        .swiper-button-prev {
         background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23DEB887' viewBox='0 0 8 8'%3E%3Cpath d='M5.25 0l-4 4 4 4 1.5-1.5-2.5-2.5 2.5-2.5-1.5-1.5z'/%3E%3C/svg%3E") !important;
        }

        .swiper-button-next {
          background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23DEB887' viewBox='0 0 8 8'%3E%3Cpath d='M2.75 0l-1.5 1.5 2.5 2.5-2.5 2.5 1.5 1.5 4-4-4-4z'/%3E%3C/svg%3E") !important;
        }
        
        .info{
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            align-items:center;
        }
        
        .popup-bg{
            position: fixed;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            background: rgba(255,255,255,0.8);
            z-index: 5;
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
        
        .close-popup{
            position: absolute;
            top: 30px;
            left: 30px;
            cursor: pointer;
        }
        
        .no-scroll{
            overflow-y: hidden;
        }
        
        .container-body{
        	display:flex;
        	flex-direction: row;
        	justify-content: center;
            align-content: center;
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
        
        input[type=date], input[type=number] {
            background: #DEB887;
            border: 0;            
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
        
        .close-popup{
            position: absolute;
            top: 30px;
            left: 30px;
            cursor: pointer;
        }
        
        .no-scroll{
            overflow-y: hidden;
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
<body>

	<script>
        
		$(document).ready(function() { 
	
	        document.querySelectorAll('.slider-container').forEach(n => {
	           const slider = new Swiper(n.querySelector('.product-slider'), {
	               navigation: {
	                 nextEl: n.querySelector('.swiper-button-next'),
	                 prevEl: n.querySelector('.swiper-button-prev'),
	               },
	               spaceBetween: 10,
	           });
	
	         const thumbs = new Swiper(n.querySelector('.product-thumbs'), {
	           spaceBetween: 5,
	           centeredSlides: true,
	           slidesPerView: 4,
	           touchRatio: 0.2,
	           slideToClickedSlide: true,
	           direction: 'vertical',
	         });
	
	         slider.controller.control = thumbs;
	         thumbs.controller.control = slider;
	       });
            
            
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
            document.getElementById("fromDate").setAttribute("min", today);
            
            
            $('.close-popup').click(function(){
                $('.popup-bg').fadeOut(300);
                $('html').removeClass('no-scroll');
            })
            
	   });
        
        function setToDate() {
            var date = new Date(document.getElementById("fromDate").value);
            date.setDate(date.getDate() + 1);
            document.getElementById("toDate").valueAsDate = date;
            var dd = date.getDate();
            var mm = date.getMonth() + 1; //January is 0!
            var yyyy = date.getFullYear();
            if (dd < 10) {
               dd = '0' + dd;
            }
            if (mm < 10) {
               mm = '0' + mm;
            } 
            date = yyyy + '-' + mm + '-' + dd;            
            document.getElementById("toDate").setAttribute("min", date);            
        }
        
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

        <div class="main">
            
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
			                <div class="popup">
			                    <img class="close-popup" alt="icon">
			                    <p>Sorry, no available places</p>
			                </div>
			            </div>
	            	</c:when>
	            	<c:when test="${checkResult eq true}">
	            		<div class="popup-bg" style="display: block">
			                <div class="popup">
			                    <img class="close-popup" alt="icon">
			                    <p>Please, check data:</p>
			                    Room number:<br>
			                    <c:forEach var="room" items="${availableRooms}">
			                    	<c:out value=" ${room.getRoomNumber()}" />
			                    </c:forEach>
			                    Since:<c:out value=" ${fromDate}" /><br>
			                    To:<c:out value=" ${toDate}" /><br>
			                    Guests number:<c:out value=" ${guestsNumber}" /><br>
			                    
			                </div>
			            </div>
	            	</c:when>
	            </c:choose>
            
        </div>

    </div>

</body>
</html>