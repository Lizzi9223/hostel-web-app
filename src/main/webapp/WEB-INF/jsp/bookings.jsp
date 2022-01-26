<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="by.epam.tc.web.controller.constant.*" %>
<%@ include file="changeLanguageTags.jsp" %>
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
            
            $('.choose-booking').click(function(){
            	$(this).find('.target').submit();
            })
            
        });
        
    </script>

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

        <div class="container-body"> 
        	<div class="main">
                <div class="form">
                	<h3>
                	 	<c:choose>
                	 		<c:when test="${role eq 'ADMIN' }">
                	 			<c:out value="${all_bookings_word}"/>
                	 			<input class="submit_button open-popup booking" type="button" value="${new_booking_word}" style="margin-left:20px"/>
                	 		</c:when>
                	 		<c:otherwise>
                	 			<c:out value="${my_bookings_word}"/>
                	 		</c:otherwise>
                	 	</c:choose>
                	</h3><br>
                
                    <table class="table table-hover">
                        <thead>
                            <tr>
                            	<th scope="col"></th>
                            	<c:if test="${role eq 'ADMIN'}">
	                            	<th scope="col"><c:out value="${user_id_word}"/></th>                               	
		                        </c:if>                            	
                                <th scope="col"><c:out value="${since}"/></th>
                                <th scope="col"><c:out value="${to}"/></th>
                                <th scope="col"><c:out value="${guests_word}"/></th>
                                <th scope="col"><c:out value="${roomLabel}"/></th>
                                <th scope="col"><c:out value="${is_approved_word}"/></th>
                                <th scope="col"><c:out value="${approve_date_word}"/></th>
                                <th scope="col"><c:out value="${price_word}"/></th>
                                <th scope="col"><c:out value="${is_paid_word}"/></th>
                                <th scope="col" style="visibility: hidden"></th>
                            </tr>
                        </thead>

                        <c:forEach var="booking" items="${bookings}">                            
                            <tbody>
                                <tr class="choose-booking" style="cursor: pointer">
                                	<td scope="row">
                                		<c:if test="${role eq 'ADMIN' && empty booking.isApproved()}">
	                                		<c:out value="!!!"/>                                	
		                                </c:if>
                                	</td>
                                	<c:if test="${role eq 'ADMIN'}">
		                            	<td><c:out value="${booking.getUserId()}" /></td>                               	
			                        </c:if>
                                    <c:set var="dateToParse" value="${booking.getFromDate()}"/>
                                    <fmt:parseDate value="${dateToParse}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                                    <td><fmt:formatDate value="${parsedDate}" pattern="dd.MM.yyyy" /></td>	
                                    <c:set var="dateToParse" value="${booking.getToDate()}"/>
                                    <fmt:parseDate value="${dateToParse}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                                    <td><fmt:formatDate value="${parsedDate}" pattern="dd.MM.yyyy" /></td>
                                    <td><c:out value="${booking.getGuestsCount()}" /></td>
                                    <td><c:out value="${booking.getRoomNumber()}" /></td>
                                    <td><c:out value="${booking.isApproved()}" /></td>
                                    <c:set var="dateToParse" value="${booking.getApproveDate()}"/>
                                    <fmt:parseDate value="${dateToParse}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                                    <td><fmt:formatDate value="${parsedDate}" pattern="dd.MM.yyyy" /></td>
                                    <td><c:out value="${booking.getPrice()}" /></td>
                                    <td><c:out value="${booking.isPaid()}" /></td>
                                    <td style="visibility: hidden" >
                                    	<form class="target">
                                    		<input type="hidden" name="${Utility.COMMAND}" value="${CommandName.CHOOSE_BOOKING}" />
                                    		<input type="hidden" name="${Utility.CHOSEN_BOOKING_ID}" value="${booking.getId()}" />
                                    	</form>
                                    </td>
                                </tr>
                            </tbody>
                        </c:forEach>
                    </table>                
                </div> 
                
	        	<c:if test="${popUpView eq 'options'}">
	        		<div class="popup-bg options" style="display: block">
		                <div class="popup" style="width:auto; padding: 40px; padding-bottom:0">	                    
		                    <img class="close-popup" src="images/close.png" style="width:25px"><br>
		                    <table class="buttons-list">
		                    <c:choose>
		                    	<c:when test="${role eq 'ADMIN'}">
		                    		<c:choose>		                    			
		                    			<c:when test="${empty chosenBookingIsApproved}">
			                    			<tr>
			                    				<td>
			                    					<form>
					                    				<input type="hidden" name="${Utility.COMMAND}" value="${CommandName.APPROVE_BOOKING}" />
					                    				<input type="hidden" name="${Utility.BOOKING_ID}" value="${chosenBookingId}" />
					                    				<input type="hidden" name="${Utility.APPROVE}" value="true" />
					                    				<input class="submit_button" type="submit" value="${approve_word}" style="margin-right:20px"/>
					                    			</form>
			                    				</td>
			                    			</tr>
		                    				<tr>
		                    					<td>
		                    						<form>
					                    				<input type="hidden" name="${Utility.COMMAND}" value="${CommandName.APPROVE_BOOKING}" />
					                    				<input type="hidden" name="${Utility.BOOKING_ID}" value="${chosenBookingId}" />
					                    				<input type="hidden" name="${Utility.APPROVE}" value="false" />
					                    				<input class="submit_button" type="submit" value="${disapprove_word}"/>
					                    			</form>	
		                    					</td>
		                    				</tr>	                    				
		                    			</c:when>
		                    			<c:when test="${chosenBookingIsApproved eq false}">
			                    			<tr>
			                    				<td>
			                    					<form>
					                    				<input type="hidden" name="${Utility.COMMAND}" value="${CommandName.APPROVE_BOOKING}" />
					                    				<input type="hidden" name="${Utility.BOOKING_ID}" value="${chosenBookingId}" />
					                    				<input type="hidden" name="${Utility.APPROVE}" value="true" />
					                    				<input class="submit_button" type="submit" value="${approve_word}" style="margin-right:20px"/>
					                    			</form>
			                    				</td>
			                    			</tr>
		                    				<tr>
		                    					<td>
		                    						<form>
					                    				<input type="hidden" name="${Utility.COMMAND}" value="${CommandName.DELETE_BOOKING}" />
					                    				<input type="hidden" name="${Utility.BOOKING_ID}" value="${chosenBookingId}" />
					                    				<input class="submit_button" type="submit" value="${delete}"/>
					                    			</form>
		                    					</td>
		                    				</tr>		                    				
		                    			</c:when>
		                    			<c:otherwise>
		                    				<tr>
		                    					<td>
		                    						<form>
					                    				<input type="hidden" name="${Utility.COMMAND}" value="${CommandName.ADD_STAY}" />
					                    				<input type="hidden" name="${Utility.BOOKING_ID}" value="${chosenBookingId}" />
					                    				<input class="submit_button" type="submit" value="${guests_arrived_word}" style="margin-right:20px"/>
					                    			</form>	
		                    					</td>
		                    				</tr>		                    			
		                    				<tr>
		                    					<td>
		                    						<form>
					                    				<input type="hidden" name="${Utility.COMMAND}" value="${CommandName.EDIT_BOOKING}" />
					                    				<input type="hidden" name="${Utility.BOOKING_ID}" value="${chosenBookingId}" />
					                    				<input class="submit_button" type="submit" value="${edit}" style="margin-right:20px"/>
					                    			</form>
		                    					</td>
		                    				</tr>
			                    			<tr>
			                    				<td>
			                    					<form>
					                    				<input type="hidden" name="${Utility.COMMAND}" value="${CommandName.DELETE_BOOKING}" />
					                    				<input type="hidden" name="${Utility.BOOKING_ID}" value="${chosenBookingId}" />
					                    				<input class="submit_button" type="submit" value="${delete}"/>
					                    			</form>
			                    				</td>
			                    			</tr>			                    			
		                    			</c:otherwise>
		                    		</c:choose>
		                    	</c:when>
		                    	<c:otherwise>
		                    			<c:choose>
		                    				<c:when test="${chosenBookingIsApproved eq true}">
			                    				<c:if test="${chosenBookingIsPaid eq false}">
			                    					<tr>
			                    						<td>
			                    							<input class="submit_button" type="submit" value="${pay}" disabled style="margin-right:10px"/>
			                    						</td>
			                    					</tr>
			                    				</c:if>
			                    				<tr>
			                    					<td>
			                    						<form>
						                    				<input type="hidden" name="${Utility.COMMAND}" value="${CommandName.DELETE_BOOKING}" />
						                    				<input type="hidden" name="${Utility.BOOKING_ID}" value="${chosenBookingId}" />
						                    				<input class="submit_button" type="submit" value="${delete}"/>
						                    			</form>
			                    					</td>
			                    				</tr>
			                    			</c:when>
			                    			<c:when test="${chosenBookingIsApproved eq false}">
			                    				<tr><td><p><c:out value="${booking_was_rejected_msg}"/></p></td></tr>
			                    			</c:when>
			                    			<c:otherwise>
			                    				<tr><td><p><c:out value="${booking_not_approved_yet_msg}"/></p></td></tr>
			                    			</c:otherwise>
		                    			</c:choose>
		                    	</c:otherwise>
		                    </c:choose>
		                    </table>
		                </div>
		            </div>
	        	</c:if>
	        	
	        	<c:if test="${popUpView eq 'editBooking'}">
	        		<div class="popup-bg editBooking" style="display: block">
		                <div class="popup" style="width:auto; padding: 40px">	                    
		                    <img class="close-popup" src="images/close.png" style="width:25px"><br>	                    
		                    <form>                
			                    <label for="fromDate"><c:out value="${arrive_date}"/>:</label>
			                    <input type="date" id="fromDate" name="${Utility.FROM_DATE}" value="${booking.getFromDate()}" onchange="setToDate()" required>
			                    <label for="toDate">&#160;&#160;&#160;&#160;<c:out value="${departure_date}"/>:</label>
			                    <input type="date" id="toDate" name="${Utility.TO_DATE}" value="${booking.getToDate()}" required>                
			                    <br><br>
			                    <label for="questsNumber"><c:out value="${number_of_guests}"/>:&#160;</label>
			                    <input type="number" id="guestsNumber" name="${Utility.GUESTS_NUMBER}" value="${booking.getGuestsCount()}" min="1" max="15" style="margin-right: 40px">
			                    <label for="roomNumber"><c:out value="${current_room_word}"/>:</label>
			                    <input type="radio" id="roomNumber" name="${Utility.ROOM_NUMBER}" value="${booking.getRoomNumber()}" checked>
			                    <c:out value="${booking.getRoomNumber()}" /><br>
			                    <input type="hidden" name="${Utility.BOOKING_ID}" value="${booking.getId()}" />
			                    <input type="hidden" name="${Utility.COMMAND}" value="${CommandName.EDIT_BOOKING_CHECK}" />
			                    <input class="submit_button" type="submit" value="${check}" style="margin-right: 50px"/>
			                    <input id="check-among-all-rooms" type="checkbox" name="${Utility.CHECK_AMONG_ALL_ROOMS}" value="${check_among_all_rooms}" checked style="visibility:hidden"/>
			                </form>
		                </div>
		            </div>
	        	</c:if>

				<div class="popup-bg booking">
	                <div class="popup">
	                    <img class="close-popup" src="images/close.png" style="width:25px">
	                    <form>                
		                    <label for="fromDate"><c:out value="${arrive_date}"/>:</label>
		                    <input type="date" id="fromDate" name="${Utility.FROM_DATE}" onchange="setToDate()" required>
		                    <label for="toDate">&#160;&#160;&#160;&#160;<c:out value="${departure_date}"/>:</label>
		                    <input type="date" id="toDate" name="${Utility.TO_DATE}" required>                
		                    <br><br>
		                    <label for="questsNumber"><c:out value="${number_of_guests}"/>:&#160;</label>
		                    <input type="number" id="guestsNumber" name="${Utility.GUESTS_NUMBER}" value="1" min="1" max="15" style="margin-right: 90px">
		                    <input type="hidden" name="${Utility.COMMAND}" value="${CommandName.ADD_BOOKING}" />
		                    <input class="submit_button" type="submit" value="${check}" style="margin-right: 50px"/>
		                    <input id="check-among-all-rooms" type="checkbox" name="${Utility.CHECK_AMONG_ALL_ROOMS}" value="${check_among_all_rooms}" checked style="visibility:hidden"/>
		                </form>
	                </div>
	            </div>
	            
	            <c:choose>
	            	<c:when test="${checkResult eq false}">
	            		<div class="popup-bg" style="display: block">
			                <div class="popup" style="width:auto">
			                	<a href="Controller?command=GO_TO_BOOKINGS_PAGE">
			                		<img src="images/close.png" style="width:25px">
			                	</a>
			                    <p><c:out value="${sorry_no_avail_places}"/></p>
			                </div>
			            </div>
	            	</c:when>
	            	<c:when test="${checkResult eq true}">
	            		<div class="popup-bg" style="display: block">
			                <div class="popup" style="width:auto; padding-top:30px">
			                    <a href="Controller?command=GO_TO_BOOKINGS_PAGE">
			                		<img src="images/close.png" style="width:25px;">
			                	</a>
			                    <h5 style="margin-top:15px"><c:out value="${please_check_data_word}"/>:</h5>
			                    <form>
			                    	<c:choose>
			                    		<c:when test="${not empty editedBookingId and editedBookingId ne ''}">
			                    			<b><c:out value="${new_room_not_select_if_no_need}"/>:</b><br>
			                    			<c:forEach var="room" items="${availableRooms}">
			                                    <input type="radio" name="${Utility.ROOM_NUMBER}" value="${room.getRoomNumber()}">
						                    	<c:out value="${room.getRoomNumber()}" /><br>
						                    </c:forEach>
			                    		</c:when>
			                    		<c:otherwise>
			                    			<b><c:out value="${roomLabel}"/>:</b><br>
			                    			<c:forEach var="room" items="${availableRooms}">
			                                    <input type="radio" name="${Utility.ROOM_NUMBER}" value="${room.getRoomNumber()}" required>
						                    	<c:out value="${room.getRoomNumber()}" /><br>
						                    </c:forEach>
			                    		</c:otherwise>
			                    	</c:choose>
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
				                    <input type="hidden" name="${Utility.EDITED_BOOKING_ID}" value="${editedBookingId}" />
				                    <input type="hidden" name="${Utility.COMMAND}" value="${CommandName.SUBMIT_BOOKING}" />
				                    <input class="submit_button" type="submit" value="${submit_booking}"/>
			                    </form>			                    
			                </div>
			            </div>
	            	</c:when>
	            </c:choose>
	         	
	         	<c:remove var="booking"/>
	         	<c:remove var="chosenBookingId"/>
	         	<c:remove var="chosenBookingIsApproved"/>
	         	<c:remove var="chosenBookingIsPaid"/>
	         	<c:remove var="popUpView"/>
	         	
          </div>
        </div>
       </div> 
	</body>
</html>