 function edit(){
 var inputs = document.getElementsByClassName("input");
 Array.prototype.forEach.call(inputs, function(el) {
 el.disabled = false;
 });
 document.getElementById("save_button").style.visibility="visible";
 document.getElementById("cancel_button").style.visibility="visible";
 }
 
 function cancelEdit(){
 var inputs = document.getElementsByClassName("input");
 Array.prototype.forEach.call(inputs, function(el) {
 el.disabled = true;
 });
 document.getElementById("save_button").style.visibility="hidden";
 document.getElementById("cancel_button").style.visibility="hidden";
 window.location.reload();
 }

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


 $(document).ready(function(){
 
 $('.open-popup.change-pass').click(function(e){
 e.preventDefault();
 $('.popup-bg.change-pass').fadeIn(300);
 $('html').addClass('no-scroll');
 })
 
 $('.close-popup.change-pass').click(function(){
 $('.popup-bg.change-pass').fadeOut(300);
 $('html').removeClass('no-scroll');
 })
 
 $('.open-popup.del-acc').click(function(e){
 e.preventDefault();
 $('.popup-bg.del-acc').fadeIn(300);
 $('html').addClass('no-scroll');
 })
 
 $('.close-popup.del-acc').click(function(){
 $('.popup-bg.del-acc').fadeOut(300);
 $('html').removeClass('no-scroll');
 })

 $('.open-popup.options').click(function(e){
 e.preventDefault();
 $('.popup-bg.change-pass').fadeIn(300);
 $('html').addClass('no-scroll');
 })
 
 $('.close-popup.options').click(function(){
 $('.popup-bg.change-pass').fadeOut(300);
 $('html').removeClass('no-scroll');
 })

 $('.open-popup.booking').click(function(e){
 e.preventDefault();
 $('.popup-bg.booking').fadeIn(300);
 $('html').addClass('no-scroll');
 })
 
 $("input[name=confirmPassword]").keyup(function(){
 if ($('input[name=newPassword]').val() == $('input[name=confirmPassword]').val()) {
 $('input[name=confirmPassword]').css('background', 'white');
 $('input[name=changePassword]').removeAttr('disabled');
 $('input[name=changePassword]').css("cursor", 'pointer');
 } else {
 $('input[name=confirmPassword]').css('background', 'red');
 $('input[name=changePassword]').attr("disabled", 'disabled');
 $('input[name=changePassword]').css("cursor", 'default');
 } 
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
 
 
 $('.open-popup').click(function(e){
 e.preventDefault();
 $('.popup-bg').fadeIn(300);
 $('html').addClass('no-scroll');
 })
 
 $('.close-popup').click(function(){
 $('.popup-bg').fadeOut(300);
 $('html').removeClass('no-scroll');
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


 
 })