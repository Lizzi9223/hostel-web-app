<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="by.epam.tc.web.controller.constant.*" %>
<%@ include file="changeLanguageTags.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>Stays</title>
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
                	 			${all_stays}
                	 			<input class="submit_button open-popup stay" type="button" value="${new_stay}" style="margin-left:20px"/>
                	 		</c:when>
                	 		<c:otherwise>
                	 			${my_stays}
                	 		</c:otherwise>
                	 	</c:choose>
                	</h3><br>
                
                    <table class="table table-hover">
                    
                        <thead>
                            <tr>
                            	<c:if test="${role eq 'ADMIN'}">
	                            	<th scope="col">${clientId_word}</th>                               	
		                        </c:if>                           	
                                <th scope="col">${since}</th>
                                <th scope="col">${to}</th>                                
                                <th scope="col">${room}</th>
                                <th scope="col">${notes}</th>
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
                                    		<input type="hidden" name="${Utility.COMMAND}" value="${CommandName.CHOOSE_STAY}" />
                                    		<input type="hidden" name="${Utility.CHOSEN_STAY_ID}" value="${stay.getId()}" />
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
		                    				<tr>
		                    					<td>
		                    						<form>
					                    				<input type="hidden" name="${Utility.COMMAND}" value="${CommandName.EDIT_STAY}" />
					                    				<input type="hidden" name="${Utility.STAY_ID}" value="${chosenStayId}" />
					                    				<input class="submit_button" type="submit" value="${edit}" style="margin-right:20px"/>
					                    			</form>
		                    					</td>
		                    				</tr>
			                    			<c:if test="${login eq 'ADMIN'}">
			                    				<tr>
			                    					<td>
			                    						<form>
						                    				<input type="hidden" name="${Utility.COMMAND}" value="${CommandName.DELETE_STAY}" />
						                    				<input type="hidden" name="${Utility.STAY_ID}" value="${chosenStayId}" />
						                    				<input class="submit_button" type="submit" value="${delete}"/>
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
	        	
	        	<c:if test="${popUpView eq 'EditStay'}">
	        		<div class="popup-bg editStay" style="display: block">
		                <div class="popup" style="width:auto; padding: 40px">	                    
		                    <img class="close-popup" src="images/close.png" style="width:25px"><br>	                    
		                    <form>
			                    <label for="toDate"><c:out value="${departure_date}" />:</label>
			                    <input type="date" id="toDate" name="${Utility.TO_DATE}" value="${stay.getToDate()}" min="${stay.getFromDate().plusDays(1)}">                
			                    <br><br>
			                    <label><c:out value="${notes}" />:&#160;</label><br>
			                    <textarea id="notes" name="${Utility.NOTES}" rows="5" cols="30"><c:out value="${stay.getNotes()}"></c:out></textarea>
			                    <input type="hidden" name="${Utility.STAY_ID}" value="${stay.getId()}" />
			                    <input type="hidden" name="${Utility.COMMAND}" value="${CommandName.EDIT_STAY_CHECK}" />
			                    <br>
			                    <input class="submit_button" type="submit" value="${check}" style="margin-right: 50px"/>
			                </form>
		                </div>
		            </div>
	        	</c:if>
	        	
	        	<div class="popup-bg stay">
	                <div class="popup">
	                    <img class="close-popup" src="images/close.png" style="width:25px">
	                    <form>                
		                    <label for="fromDate"><c:out value="${arrive_date}" />:</label>
		                    <input type="date" id="fromDate" name="${Utility.FROM_DATE}" onchange="setToDate()" required>
		                    <label for="toDate">&#160;&#160;&#160;&#160;<c:out value="${departure_date}" />:</label>
		                    <input type="date" id="toDate" name="${Utility.TO_DATE}" required>                
		                    <br><br>
		                    <label for="questsNumber"><c:out value="${number_of_guests}" />:&#160;</label>
		                    <input type="number" id="guestsNumber" name="${Utility.GUESTS_NUMBER}" value="1" min="1" max="15" style="margin-right: 90px">
		                    <input type="hidden" name="${Utility.COMMAND}" value="${CommandName.ADD_STAY}" />
		                    <input class="submit_button" type="submit" value="${check}" style="margin-right: 50px"/>
		                    <input id="check-among-all-rooms" type="checkbox" name="${Utility.CHECK_AMONG_ALL_ROOMS}" value="${check_among_all_rooms}" checked style="visibility:hidden"/>
		                </form>
	                </div>
	            </div>
	            
	            <c:choose>
	            	<c:when test="${checkResult eq false}">
	            		<div class="popup-bg" style="display: block">
			                <div class="popup" style="width:auto">
			                	<a href="Controller?command=GO_TO_STAYS_PAGE">
			                		<img src="images/close.png" style="width:25px">
			                	</a>
			                    <p>Sorry, no available places</p>
			                </div>
			            </div>
	            	</c:when>
	            	<c:when test="${create eq true && checkResult eq true}">
	            		<div class="popup-bg" style="display: block">
			                <div class="popup" style="width:auto; padding-top:30px">
			                    <a href="Controller?command=GO_TO_STAYS_PAGE">
			                		<img src="images/close.png" style="width:25px;">
			                	</a>
			                    <h5 style="margin-top:15px">Please, check data:</h5>
			                    <form>
			                    	<b><c:out value="${room}" />:</b><br>
				                    <c:forEach var="room" items="${availableRooms}">
	                                    <input type="radio" name="${Utility.ROOM_NUMBER}" value="${room.getRoomNumber()}" required>
				                    	<c:out value="${room.getRoomNumber()}" /><br>
				                    </c:forEach>
				                    <table>
				                    	<tr>
				                    		<td><b><c:out value="${since}" />:</b></td>
				                    		<td><input type="date" name="${Utility.FROM_DATE}" value="${fromDate}" readonly /></td>
				                    	</tr>
				                    	<tr>
				                    		<td><b><c:out value="${to}" />:</b></td>
				                    		<td><input type="date" name="${Utility.TO_DATE}" value="${toDate}" readonly /></td>
				                    	</tr>
				                    	<tr>
				                    		<td><b><c:out value="${number_of_guests}" />:</b></td>
				                    		<td><input type="number" name="${Utility.GUESTS_NUMBER}" value="${guestsNumber}" readonly /></td>
				                    	</tr>
				                    </table>			                    
				                    <br>
				                    <input type="hidden" name="${Utility.COMMAND}" value="${CommandName.SUBMIT_STAY}" />
				                    <input class="submit_button" type="submit" value="${submit_stay}"/>
			                    </form>			                    
			                </div>
			            </div>
	            	</c:when>
	            	<c:when test="${checkResult eq true}">
	            		<div class="popup-bg" style="display: block">
			                <div class="popup" style="width:auto; padding-top:30px">
			                    <a href="Controller?command=GO_TO_STAYS_PAGE">
			                		<img src="images/close.png" style="width:25px;">
			                	</a>
			                    <h5 style="margin-top:15px"><c:out value="${please_check_data}"/>:</h5>
			                    <form>
				                    <table>
				                    	<tr>
					                    	<td><b>${to}:</b></td>
					                    	<td><input type="date" name="${Utility.TO_DATE}" value="${toDate}" readonly /></td>
					                    </tr>			                    	
				                    	<tr>
				                    		<td><b>${notes}:</b></td>
				                    		<td><textarea name="${Utility.NOTES}" rows="5" cols="30" readonly><c:out value="${editedNotes}"></c:out></textarea></td>
				                    	</tr>
				                    </table>			                    
				                    <br>
				                    <input type="hidden" name="${Utility.EDITED_STAY_ID}" value="${editedStayId}" />
				                    <input type="hidden" name="${Utility.COMMAND}" value="${CommandName.SUBMIT_STAY}" />
				                    <input class="submit_button" type="submit" value="${submit_stay}"/>
			                    </form>			                    
			                </div>
			            </div>
	            	</c:when>
	            </c:choose>
	         	
	         	<c:remove var="stay"/>
	         	<c:remove var="chosenStayId"/>
	         	<c:remove var="popUpView"/>
	         	
	         	
	         	<c:if test="${newStayGuestsNumber == 0}">
		       		<c:remove var="newStayGuestsNumber"/>
		       		<c:remove var="newStayFromDate"/>
		       		<c:remove var="newStayToDate"/>
		       		<c:remove var="newStayRoomNumber"/>
		       </c:if>
	         	
          </div> 
        </div>
       </div> 

	</body>
</html>