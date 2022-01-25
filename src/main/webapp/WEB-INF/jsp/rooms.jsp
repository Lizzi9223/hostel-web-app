<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="by.epam.tc.web.controller.constant.*" %>
<%@ include file="changeLanguageTags.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>Rooms</title>
	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css" rel="stylesheet" />
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet" />
    
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

</head>
<body style="background-color: #D2B48C">

	<script>
                
		$(document).ready(function() {
			
			$('.open-popup').click(function(e){
                e.preventDefault();
                $('.popup-bg').fadeIn(300);
                $('html').addClass('no-scroll');
            })
        
            $('.close-popup').click(function(){
                $('.popup-bg').fadeOut(300);
                $('html').removeClass('no-scroll');
            })
	
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
	       })
            
            
            $('#clear-button').click(function(){
             $('#price-left').val($('#price-left').prop('min'));
             setLeftValue();
             $('#price-right').val($('#price-right').prop('max'));
			 setRightValue();
			 $('#capacity-left').val($('#capacity-left').prop('min'));
			 setLeftValue2();
			 $('#capacity-right').val($('#capacity-right').prop('max'));
			 setRightValue2();
			 $('input[name="searchGender"]').prop('checked', false);
			 $('#bathroom').prop('checked', false);
			 $('#search-form').submit();
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
        
        	<div class="search form">
                
                <form id="search-form">
                
                    <br><h5><b><c:out value="${search}"/></b></h5><br>
                
                    <div style="margin-bottom: 15px"><b><c:out value="${cost}"/>:&#160;&#160;&#160;</b></div>

                    <div class="middle">
                        <label for="priceFrom"><c:out value="${from}"/>:&#160;</label>
                        <input id="priceFrom" type="text" readonly class="rangeInput" name="${Utility.PRICE_LEFT}">
                        <label for="priceTo">&#160;&#160;&#160;<c:out value="${to}"/>:&#160;</label>
                        <input id="priceTo" type="text" readonly class="rangeInput" name="${Utility.PRICE_RIGHT}"><br><br>

                        <div class="multi-range-slider" id="priceRange">

                            <input type="range" id="price-left" min="${minCost}" max="${maxCost}" value="${currentMinCost}">
                            <input type="range" id="price-right" min="${minCost}" max="${maxCost}" value="${currentMaxCost}">

                            <div class="slider">
                                <div class="track"></div>
                                <div class="range"></div>
                                <div class="thumb left"></div>
                                <div class="thumb right"></div>
                            </div>
                        </div>
                    </div>  		     


                    <div style="margin-bottom: 15px; margin-top: 50px;"><b><c:out value="${capacity}"/>:&#160;&#160;&#160;</b></div>

                    <div class="middle">
                        <label for="capacityFrom"><c:out value="${from}"/>:&#160;</label>
                        <input id="capacityFrom" type="text" readonly class="rangeInput" name="${Utility.CAPACITY_LEFT}">
                        <label for="capacityTo">&#160;&#160;&#160;<c:out value="${to}"/>:&#160;</label>
                        <input id="capacityTo" type="text" readonly class="rangeInput" name="${Utility.CAPACITY_RIGHT}"><br><br>

                        <div class="multi-range-slider" id="capacityRange">

                            <input type="range"  id="capacity-left" min="${minCapacity}" max="${maxCapacity}" value="${currentMinCapacity}">
                            <input type="range"  id="capacity-right" min="${minCapacity}" max="${maxCapacity}" value="${currentMaxCapacity}">

                            <div class="slider">
                                <div class="track"></div>
                                <div class="range"></div>
                                <div class="thumb left"></div>
                                <div class="thumb right"></div>
                            </div>
                        </div>
                    </div>


                    <div style="margin-bottom: 15px; margin-top: 50px;"><b><c:out value="${gender}"/>:&#160;&#160;&#160;</b></div>
                    <c:choose>
                    	<c:when test="${currentGender eq 'м'}">
                    		<input type="radio" id="genderChoice1" name="${Utility.SEARCH_GENDER}" value="м" checked>
                    		<label for="genderChoice1" style="margin-right: 20px"><c:out value="${m}"/></label>
                    		<input type="radio" id="genderChoice2" name="${Utility.SEARCH_GENDER}" value="ж">
                    		<label for="genderChoice2" style="margin-right: 20px"><c:out value="${f}"/></label>
                    		<input type="radio" id="genderChoice3" name="${Utility.SEARCH_GENDER}" value="мж">
                    		<label for="genderChoice3"><c:out value="${mf}"/></label>
                    	</c:when>
                    	<c:when test="${currentGender eq 'ж'}">
                    		<input type="radio" id="genderChoice1" name="${Utility.SEARCH_GENDER}" value="м">
                    		<label for="genderChoice1" style="margin-right: 20px"><c:out value="${m}"/></label>
                    		<input type="radio" id="genderChoice2" name="${Utility.SEARCH_GENDER}" value="ж" checked>
                    		<label for="genderChoice2" style="margin-right: 20px"><c:out value="${f}"/></label>
                    		<input type="radio" id="genderChoice3" name="${Utility.SEARCH_GENDER}" value="мж">
                    		<label for="genderChoice3"><c:out value="${mf}"/></label>
                    	</c:when>
                    	<c:when test="${currentGender eq 'мж'}">
                    		<input type="radio" id="genderChoice1" name="${Utility.SEARCH_GENDER}" value="м">
                    		<label for="genderChoice1" style="margin-right: 20px"><c:out value="${m}"/></label>
                    		<input type="radio" id="genderChoice2" name="${Utility.SEARCH_GENDER}" value="ж">
                    		<label for="genderChoice2" style="margin-right: 20px"><c:out value="${f}"/></label>
                    		<input type="radio" id="genderChoice3" name="${Utility.SEARCH_GENDER}" value="мж" checked>
                    		<label for="genderChoice3"><c:out value="${mf}"/></label>
                    	</c:when>
                    	<c:otherwise>
                    		<input type="radio" id="genderChoice1" name="${Utility.SEARCH_GENDER}" value="м">
                    		<label for="genderChoice1" style="margin-right: 20px"><c:out value="${m}"/></label>
                    		<input type="radio" id="genderChoice2" name="${Utility.SEARCH_GENDER}" value="ж">
                    		<label for="genderChoice2" style="margin-right: 20px"><c:out value="${f}"/></label>
                    		<input type="radio" id="genderChoice3" name="${Utility.SEARCH_GENDER}" value="мж">
                    		<label for="genderChoice3"><c:out value="${mf}"/></label>
                    	</c:otherwise>
                    </c:choose> 

                    <div style="margin-bottom: 15px; margin-top: 50px;"><b><c:out value="${private_bathroom}"/>:&#160;&#160;&#160;</b></div>
                    <c:choose>
                    	<c:when test="${currentIsBathroom eq true}">
                    		<input type="checkbox" id="bathroom" name="${Utility.SEARCH_BATHROOM}" checked>
                    	</c:when>
                    	<c:otherwise>
                    		<input type="checkbox" id="bathroom" name="${Utility.SEARCH_BATHROOM}">
                    	</c:otherwise>
                    </c:choose>                    
                    <label for="bathroom"><c:out value="${yes}"/></label><br>

                    <input type="hidden" name="${Utility.COMMAND}" value="${CommandName.SEARCH_ROOMS}">
                    <input style="margin-bottom: 15px; margin-top: 50px;" class="submit_button" type="submit" value="${search}">
                    <input id="clear-button" style="margin-left: 30px" class="buttons" type="button"  value="${clear}" >  
                </form>
        	</div>
        
        	<div class="main">
        
        	  <div class="form rooms">
                <div class="slider-container">
                  <div class="row">
                    <div class="col-10">
                      <div class="swiper-container product-slider">
                        <div class="swiper-wrapper">
                          <div class="swiper-slide">
                            <img src="images/0.jpg" alt="1">
                          </div>
                          <div class="swiper-slide">
                            <img src="images/0_2.jpg" alt="2">
                          </div>
                          <div class="swiper-slide">
                            <img src="images/0_3.jpg" alt="3">
                          </div>
                          <div class="swiper-slide">
                            <img src="images/0_4.jpg" alt="4">
                          </div>
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
                          <div class="swiper-slide">
                            <img src="images/0.jpg" alt="1">
                          </div>
                          <div class="swiper-slide">
                            <img src="images/0_2.jpg" alt="2">
                          </div>
                          <div class="swiper-slide">
                            <img src="images/0_3.jpg" alt="3">
                          </div>
                          <div class="swiper-slide">
                            <img src="images/0_4.jpg" alt="4">
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="info">
                	<c:out value="${photos_of_common_areas}"/> 
                </div>
            </div>  
        
	        <c:forEach var="room" items="${selected_rooms}">	
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
		                    <c:if test="${sessionScope.role ne 'ADMIN'}">
		                    	<div style="margin-bottom: 30px">
						        	<form>
						        		<c:choose>
						        			<c:when test="${empty sessionScope.role}">
							        			<input class="buttons open-popup" type="button" value="${booking}">
							        		</c:when>
							        		<c:otherwise>
							        			<input type="hidden" name="${Utility.COMMAND}" value="${CommandName.BOOK_ROOM}" />
							        			<input type="hidden" name="${Utility.ROOM_NUMBER}" value="${room.getRoomNumber()}" />
							        			<input class="submit_button" type="submit" value="${booking_word}"/>
							        		</c:otherwise>		
						        		</c:choose>		                		
						        	</form>
						        </div>	 
		                    </c:if>		                    
		                    
		                    <div>                    
		                        <div><span style="font-size:16px"><b><c:out value="${roomLabel}"/>:</b></span>${room.getRoomNumber()}</div>
		                        <div><b><c:out value="${cost}"/>:</b>${room.getCost()} BYN</div>
		                        <div><b><c:out value="${capacity}"/>: </b>${room.getCapacity()}</div>
		                        <c:if test="${not empty room.getGender() and room.getGender() ne ''}">
		                        	<div><b><c:out value="${gender}"/>: </b>${room.getGender()}</div>
		                        </c:if>
		                        <c:if test="${room.isBathroomInRoom() eq true }">
		                        	<c:out value="${private_bathroom_in_room}"/>
		                        </c:if>
		                        <div>${room.getNotes()}</div>                    
		                    </div>
		                </div>	                
	                </div>
	        </c:forEach>
	        
	        <c:if test="${empty sessionScope.role}">
	        	<div class="popup-bg">
	                <div class="popup">
	                    <h3><c:out value="${sign_in_first}"/>!</h3>
	                     <img class="close-popup" src="images/close.png" style="width:25px">
	                </div>
	            </div>
	        </c:if>
	        
	         
          </div> 
        </div> 
       </div>
    <script>    
               
        var inputLeft = document.getElementById("price-left");
        var inputRight = document.getElementById("price-right");

        var thumbLeft = document.querySelector("#priceRange > .slider > .thumb.left");
        var thumbRight = document.querySelector("#priceRange > .slider > .thumb.right");
        var range = document.querySelector("#priceRange > .slider > .range");

        function setLeftValue() {
            var _this = inputLeft,
                min = parseInt(_this.min),
                max = parseInt(_this.max);

            _this.value = Math.min(parseInt(_this.value), parseInt(inputRight.value) - 1);

            var percent = ((_this.value - min) / (max - min)) * 100;

            thumbLeft.style.left = percent + "%";
            range.style.left = percent + "%";
            document.getElementById("priceFrom").value = _this.value;
        }
        setLeftValue();

        function setRightValue() {
            var _this = inputRight,
                min = parseInt(_this.min),
                max = parseInt(_this.max);

            _this.value = Math.max(parseInt(_this.value), parseInt(inputLeft.value) + 1);

            var percent = ((_this.value - min) / (max - min)) * 100;

            thumbRight.style.right = (100 - percent) + "%";
            range.style.right = (100 - percent) + "%";
            document.getElementById("priceTo").value = _this.value;
        }
        setRightValue();

        inputLeft.addEventListener("input", setLeftValue);
        inputRight.addEventListener("input", setRightValue);
        
        
        
        var inputLeft2 = document.getElementById("capacity-left");
        var inputRight2 = document.getElementById("capacity-right");

        var thumbLeft2 = document.querySelector("#capacityRange > .slider > .thumb.left");
        var thumbRight2 = document.querySelector("#capacityRange > .slider > .thumb.right");
        var range2 = document.querySelector("#capacityRange > .slider > .range");

        function setLeftValue2() {
            var _this = inputLeft2,
                min = parseInt(_this.min),
                max = parseInt(_this.max);

            _this.value = Math.min(parseInt(_this.value), parseInt(inputRight2.value) - 1);

            var percent = ((_this.value - min) / (max - min)) * 100;

            thumbLeft2.style.left = percent + "%";
            range2.style.left = percent + "%";
            document.getElementById("capacityFrom").value = _this.value;
        }
        setLeftValue2();

        function setRightValue2() {
            var _this = inputRight2,
                min = parseInt(_this.min),
                max = parseInt(_this.max);

            _this.value = Math.max(parseInt(_this.value), parseInt(inputLeft2.value) + 1);

            var percent = ((_this.value - min) / (max - min)) * 100;

            thumbRight2.style.right = (100 - percent) + "%";
            range2.style.right = (100 - percent) + "%";
            document.getElementById("capacityTo").value = _this.value;
        }
        setRightValue2();

        inputLeft2.addEventListener("input", setLeftValue2);
        inputRight2.addEventListener("input", setRightValue2);
        
    
    </script>

	</body>
</html>