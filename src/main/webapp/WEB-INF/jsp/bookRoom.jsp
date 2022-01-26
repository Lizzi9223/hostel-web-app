<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="by.epam.tc.web.controller.constant.*" %>
<%@ include file="changeLanguageTags.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>Room booking</title>

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
    
</head>
<body style="background-color: #D2B48C">		

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
            
            <div class="form" style="padding-bottom: 20px; margin-top:20px">
                
                <h4><c:out value="${check_for_avail_places}"/>#<c:out value="${room.getRoomNumber()}"/>:</h4>
                <br>                
                <form>                
                    <label for="fromDate"><c:out value="${arrive_date}"/>:</label>
                    <input type="date" id="fromDate" name="${Utility.FROM_DATE}" onchange="setToDate()" required>
                    <label for="toDate">&#160;&#160;&#160;&#160;<c:out value="${departure_date}"/>:</label>
                    <input type="date" id="toDate" name="${Utility.TO_DATE}" required>                
                    <br><br>
                    <label for="questsNumber"><c:out value="${number_of_guests}"/>:&#160;</label>
                    <input type="number" id="guestsNumber" name="${Utility.GUESTS_NUMBER}" value="1" min="1" max="15" style="margin-right: 90px">
                    <input type="hidden" name="${Utility.ROOM_NUMBER}" value="${room.getRoomNumber()}" />
                    <input type="hidden" name="${Utility.COMMAND}" value="${CommandName.CHECK_ROOM_AVAILABILITY}" />
                    <input class="submit_button" type="submit" value="${check}" style="margin-right: 50px"/>
                    <input id="check-among-all-rooms" type="checkbox" name="${Utility.CHECK_AMONG_ALL_ROOMS}" value="Check among all rooms" />
                    <label for="check-among-all-rooms" ><c:out value="${check_among_all_rooms}"/></label>                
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
                            <form>
                                 <input type="hidden" name="${Utility.COMMAND}" value="${CommandName.BOOK_ROOM}" />
							     <input type="hidden" name="${Utility.ROOM_NUMBER}" value="${room.getRoomNumber()}" />
							     <input class="submit_button" type="submit" value="${roomLabel} #${room.getRoomNumber()}"/>
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
			                    <p><c:out value="${sorry_no_avail_places}"/></p>
			                </div>
			            </div>
	            	</c:when>
	            	<c:when test="${checkResult eq true}">
	            		<div class="popup-bg" style="display: block">
			                <div class="popup" style="width:auto">
			                     <img class="close-popup" src="images/close.png" style="width:25px">
			                    <h5><c:out value="${please_check_data_word}"/>:</h5>
			                    <form>
			                    	<b><c:out value="${roomLabel}"/> #:</b><br>
				                    <c:forEach var="room" items="${availableRooms}">
	                                    <input type="radio" name="${Utility.ROOM_NUMBER}" value="${room.getRoomNumber()}">
				                    	<c:out value="${room.getRoomNumber()}" /><br>
				                    </c:forEach>
				                    <table>
				                    	<tr>
				                    		<td><b><c:out value="${since}"/>:</b></td>
				                    		<td><input type="date" name="${Utility.FROM_DATE}" value="${fromDate}" readonly /></td>
				                    	</tr>
				                    	<tr>
				                    		<td><b><c:out value="${to}"/>:</b></td>
				                    		<td><input type="date" name="${Utility.TO_DATE}" value="${toDate}" readonly /></td>
				                    	</tr>
				                    	<tr>
				                    		<td><b><c:out value="${number_of_guests}"/>:</b></td>
				                    		<td><input type="number" name="${Utility.GUESTS_NUMBER}" value="${guestsNumber}" readonly /></td>
				                    	</tr>
				                    </table>			                    
				                    <br>
				                    <input type="hidden" name="${Utility.COMMAND}" value="${CommandName.SUBMIT_BOOKING}" />
				                    <input class="submit_button" type="submit" value="${submit_booking}"/>
			                    </form>			                    
			                </div>
			            </div>
	            	</c:when>
	            </c:choose>
        </div>
    </div>

</body>
</html>