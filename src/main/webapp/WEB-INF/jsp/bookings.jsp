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
                	 			All bookings
                	 		</c:when>
                	 		<c:otherwise>
                	 			My bookings
                	 		</c:otherwise>
                	 	</c:choose>
                	</h3><br>
                
                    <table class="table table-hover">
                    
                        <thead>
                            <tr>
                            	<th scope="col"></th>
                            	<c:if test="${role eq 'ADMIN'}">
	                            	<th scope="col">UserId</th>                               	
		                        </c:if>                            	
                                <th scope="col">Since</th>
                                <th scope="col">To</th>
                                <th scope="col">Guests</th>
                                <th scope="col">Room</th>
                                <th scope="col">Is approved</th>
                                <th scope="col">Approve date</th>
                                <th scope="col">Is paid</th>
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
                                    <td><c:out value="${booking.getFromDate()}" /></td>
                                    <td><c:out value="${booking.getToDate()}" /></td>
                                    <td><c:out value="${booking.getGuestsCount()}" /></td>
                                    <td><c:out value="${booking.getRoomNumber()}" /></td>
                                    <td><c:out value="${booking.isApproved()}" /></td>
                                    <td><c:out value="${booking.getApproveDate()}" /></td>
                                    <td><c:out value="${booking.isPaid()}" /></td>
                                    <td style="visibility: hidden" >
                                    	<form class="target">
                                    		<input type="hidden" name="command" value="ChooseBooking" />
                                    		<input type="hidden" name="chosenBookingId" value="${booking.getId()}" />
                                    	</form>
                                    </td>
                                </tr>
                            </tbody>

                        </c:forEach>
                    </table>
                
                </div>  
                
	        	<c:if test="${popUpView eq 'options'}">
	        		<div class="popup-bg" style="display: block">
		                <div class="popup" style="width:auto; padding: 40px">	                    
		                    <img class="close-popup" src="images/close.png" style="width:25px"><br>	                    
		                    <c:choose>
		                    	<c:when test="${role eq 'ADMIN'}">
		                    		<c:choose>		                    			
		                    			<c:when test="${empty chosenBookingIsApproved}">
			                    			<form>
			                    				<input type="hidden" name="command" value="ApproveBooking" />
			                    				<input type="hidden" name="bookingId" value="${chosenBookingId}" />
			                    				<input type="hidden" name="approve" value="true" />
			                    				<input class="submit_button" type="submit" value="Approve" style="margin-right:20px"/>
			                    			</form>
		                    				<form>
			                    				<input type="hidden" name="command" value="ApproveBooking" />
			                    				<input type="hidden" name="bookingId" value="${chosenBookingId}" />
			                    				<input type="hidden" name="approve" value="false" />
			                    				<input class="submit_button" type="submit" value="Disapprove"/>
			                    			</form>		                    				
		                    			</c:when>
		                    			<c:otherwise>
		                    				<form>
			                    				<input type="hidden" name="command" value="EditBooking" />
			                    				<input type="hidden" name="bookingId" value="${chosenBookingId}" />
			                    				<input class="submit_button" type="submit" value="Edit" style="margin-right:20px"/>
			                    			</form>
			                    			<form>
			                    				<input type="hidden" name="command" value="DeleteBooking" />
			                    				<input type="hidden" name="bookingId" value="${chosenBookingId}" />
			                    				<input class="submit_button" type="submit" value="Delete"/>
			                    			</form>
		                    			</c:otherwise>
		                    		</c:choose>
		                    	</c:when>
		                    	<c:otherwise>
		                    			<c:choose>
		                    				<c:when test="${chosenBookingIsApproved eq true}">
			                    				<c:if test="${chosenBookingIsPaid eq false}">
			                    					<input class="submit_button" type="submit" value="Pay" style="margin-right:10px"/>
			                    				</c:if>
			                    				<form>
				                    				<input type="hidden" name="command" value="DeleteBooking" />
				                    				<input type="hidden" name="bookingId" value="${chosenBookingId}" />
				                    				<input class="submit_button" type="submit" value="Delete"/>
				                    			</form>
			                    			</c:when>
			                    			<c:when test="${chosenBookingIsApproved eq false}">
			                    				<p>Your booking has been rejected</p>
			                    			</c:when>
			                    			<c:otherwise>
			                    				<p>Your booking hasn't been approved yet</p>
			                    			</c:otherwise>
		                    			</c:choose>
		                    	</c:otherwise>
		                    </c:choose>
		                </div>
		            </div>
	        	</c:if>
	         	
	         	<c:remove var="chosenBookingId"/>
	         	<c:remove var="chosenBookingIsApproved"/>
	         	<c:remove var="chosenBookingIsPaid"/>
	         	<c:remove var="popUpView"/>
	         	
          </div>        
        
        </div>
        

       </div> 


	</body>
</html>