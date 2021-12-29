<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Rooms</title>
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
    
        .middle {
            position:relative;        
        }

        .slider {
            position: relative;
            z-index: 1;
            height: 7px;
            margin: 0 15px;
            width: 180px;
        }
        .slider > .track {
            position: absolute;
            z-index: 1;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            border-radius: 5px;
            background-color: rgb(0,0,0,0.4);
        }
        .slider > .range {
            position: absolute;
            z-index: 2;
            left: 25%;
            right: 25%;
            top: 0;
            bottom: 0;
            border-radius: 5px;
            background-color: rgb(0,0,0,0.45);
        }
        .slider > .thumb {
            position: absolute;
            z-index: 3;
            width: 20px;
            height: 20px;
            background-color: #CD853F;
            border-radius: 50%;
            box-shadow: 0 0 0 0 rgba(98,0,238,.1);    
            transition: box-shadow .3s ease-in-out;
        }
        .slider > .thumb.left {
            transform: translate(-15px, -7px);
        }
        .slider > .thumb.right {
            right: 0%;
            transform: translate(15px, -7px);
        }

        input[type=range] {
            position: absolute;
            pointer-events: none;
            -webkit-appearance: none;
            z-index: 2;
            height: 10px;
            width: 100%;
            background: rgb(0,0,0,0.6);
            opacity: 0;
        }
        input[type=range]::-webkit-slider-thumb {
            pointer-events: all;
            width: 30px;
            height: 30px;
            border-radius: 0;
            border: 0 none;
            background: #CD853F;
            cursor: pointer;
            -webkit-appearance: none;
        }
    
        .rangeInput{
            width: 30px;
            border: 0;
            border-radius: 10px;
            background: #CD853F;
        }
    
    </style>
</head>
<body>

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
                window.location.reload();
            })        
        
            
        });
        
    </script>

    <div class="container">
    
        <div class="menu">

            <div class="tabs" style="justify-content: flex-start">
                <div>Main</div>|
                <div>Rooms</div>|
                <div>Photos</div>|
                <div>Contacts</div>
            </div>

            <div class="tabs" style="justify-content: flex-end">
                <div><a href="">Sign In</a></div>|
                <div><a href="">Sign Up</a></div>
            </div>

        </div>

        <div class="container-body">
        
        	<div class="search form">
                
                <form>
                
                    <br><p><b>SEARCH</b></p><br>
                
                    <div style="margin-bottom: 15px"><b>Cost:&#160;&#160;&#160;</b></div>

                    <div class="middle">
                        <label for="priceFrom">from:&#160;</label>
                        <input id="priceFrom" type="text" readonly class="rangeInput" name="price-left">
                        <label for="priceTo">&#160;&#160;&#160;to:&#160;</label>
                        <input id="priceTo" type="text" readonly class="rangeInput" name="price-right"><br><br>

                        <div class="multi-range-slider" id="priceRange">

                            <input type="range"  id="price-left" min="${minCost}" max="${maxCost}" value="${minCost}">
                            <input type="range"  id="price-right" min="${minCost}" max="${maxCost}" value="${maxCost}">

                            <div class="slider">
                                <div class="track"></div>
                                <div class="range"></div>
                                <div class="thumb left"></div>
                                <div class="thumb right"></div>
                            </div>
                        </div>
                    </div>  		     


                    <div style="margin-bottom: 15px; margin-top: 50px;"><b>Capacity:&#160;&#160;&#160;</b></div>

                    <div class="middle">
                        <label for="capacityFrom">from:&#160;</label>
                        <input id="capacityFrom" type="text" readonly class="rangeInput" name="capacity-left">
                        <label for="capacityTo">&#160;&#160;&#160;to:&#160;</label>
                        <input id="capacityTo" type="text" readonly class="rangeInput" name="capacity-right"><br><br>

                        <div class="multi-range-slider" id="capacityRange">

                            <input type="range"  id="capacity-left" min="${minCapacity}" max="${maxCapacity}" value="${minCapacity}">
                            <input type="range"  id="capacity-right" min="${minCapacity}" max="${maxCapacity}" value="${maxCapacity}">

                            <div class="slider">
                                <div class="track"></div>
                                <div class="range"></div>
                                <div class="thumb left"></div>
                                <div class="thumb right"></div>
                            </div>
                        </div>
                    </div>


                    <div style="margin-bottom: 15px; margin-top: 50px;"><b>Gender:&#160;&#160;&#160;</b></div>                    
                    <input type="radio" id="genderChoice1" name="searchGender" value="м">
                    <label for="genderChoice1" style="margin-right: 20px">м</label>
                    <input type="radio" id="genderChoice2" name="searchGender" value="ж">
                    <label for="genderChoice2" style="margin-right: 20px">ж</label>
                    <input type="radio" id="genderChoice3" name="searchGender" value="мж">
                    <label for="genderChoice3">мж</label>


                    <div style="margin-bottom: 15px; margin-top: 50px;"><b>Private bathroom:&#160;&#160;&#160;</b></div>
                    <input type="checkbox" id="bathroom" name="searchBathroom">
                    <label for="bathroom">Yes</label><br>

                    <input type="hidden" name="command" value="SearchRooms">
                    <input style="margin-bottom: 15px; margin-top: 50px;" class="submit_button" type="submit" name="search" value="Search">
                    <input id="clear-button" style="margin-left: 30px" class="buttons" type="button"  value="Clear" >                    
                
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
                            <img src="C:/Users/User/eclipse-workspace/hostel-maven-project/src/main/webapp/images/bg.jpg" alt="1">
                          </div>
                          <div class="swiper-slide">
                            <img src="C:/Users/User/eclipse-workspace/hostel-maven-project/src/main/webapp/images/bg.jpg" alt="2">
                          </div>
                          <div class="swiper-slide">
                            <img src="" alt="3">
                          </div>
                          <div class="swiper-slide">
                            <img src="" alt="4">
                          </div>
                          <div class="swiper-slide">
                            <img src="" alt="5">
                          </div>
                          <div class="swiper-slide">
                            <img src="" alt="6">
                          </div>
                          <div class="swiper-slide">
                            <img src="" alt="7">
                          </div>
                          <div class="swiper-slide">
                            <img src="" alt="8">
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
                            <img src="" alt="1">
                          </div>
                          <div class="swiper-slide">
                            <img src="" alt="2">
                          </div>
                          <div class="swiper-slide">
                            <img src="" alt="3">
                          </div>
                          <div class="swiper-slide">
                            <img src="" alt="4">
                          </div>
                          <div class="swiper-slide">
                            <img src="" alt="5">
                          </div>
                          <div class="swiper-slide">
                            <img src="" alt="6">
                          </div>
                          <div class="swiper-slide">
                            <img src="" alt="7">
                          </div>
                          <div class="swiper-slide">
                            <img src="" alt="8">
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="info">                    
                    Photos of common areas                    
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
							        			<input class="buttons open-popup" type="button" value="Booking">
							        		</c:when>
							        		<c:otherwise>
							        			<input type="hidden" name="command" value="BookRoom" />
							        			<input type="hidden" name="roomNumber" value="${room.getRoomNumber()}" />
							        			<input class="submit_button" type="submit" value="Booking"/>
							        		</c:otherwise>		
						        		</c:choose>		                		
						        	</form>
						        </div>	 
		                    </c:if>		                    
		                    
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
	                
	        </c:forEach>
	        
	        <c:if test="${empty sessionScope.role}">
	        	<div class="popup-bg">
	                <div class="popup">
	                    <h3>Sign In first!</h3>
	                    <img class="close-popup" alt="icon">
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