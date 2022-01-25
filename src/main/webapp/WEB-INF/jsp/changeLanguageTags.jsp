<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<fmt:setLocale value="${sessionScope.language}"/>
	<fmt:setBundle basename="prop" var="lang"/>
	<fmt:message bundle="${lang}" key="menu.main" var="main" />
	<fmt:message bundle="${lang}" key="menu.rooms" var="rooms" />
	<fmt:message bundle="${lang}" key="menu.contacts" var="contacts" />
	<fmt:message bundle="${lang}" key="menu.my_account" var="my_account" />
	<fmt:message bundle="${lang}" key="menu.sign_in" var="sign_in" />
	<fmt:message bundle="${lang}" key="menu.sign_up" var="sign_up" />
	<fmt:message bundle="${lang}" key="menu.log_out" var="log_out" />
	<fmt:message bundle="${lang}" key="menu.ru" var="ru" />
	<fmt:message bundle="${lang}" key="menu.en" var="en" />
	
	<fmt:message bundle="${lang}" key="main.welcome_to" var="welcome_to" />
	<fmt:message bundle="${lang}" key="main.hostel_Samartia" var="hostel_Samartia" />
	
	<fmt:message bundle="${lang}" key="logination.logination" var="logination" /> 
	<fmt:message bundle="${lang}" key="logination.login" var="login_word" /> 
	<fmt:message bundle="${lang}" key="logination.password" var="password_word" /> 
	<fmt:message bundle="${lang}" key="logination.back" var="back" />
	 
	<fmt:message bundle="${lang}" key="registration.registration" var="registration" />
	<fmt:message bundle="${lang}" key="registration.name" var="name_word" />
	<fmt:message bundle="${lang}" key="registration.photo" var="photo_word" />
	<fmt:message bundle="${lang}" key="registration.surname" var="surname_word" />
	<fmt:message bundle="${lang}" key="registration.passport_id" var="passport_id_word" />
	<fmt:message bundle="${lang}" key="registration.date_of_birth" var="date_of_birth_word" />
	<fmt:message bundle="${lang}" key="registration.country" var="country_word" />
	<fmt:message bundle="${lang}" key="registration.phone_number" var="phone_number_word" />
	<fmt:message bundle="${lang}" key="registration.email" var="email_word" />
	
	<fmt:message bundle="${lang}" key="stays.all_stays" var="all_stays" />
	<fmt:message bundle="${lang}" key="stays.my_stays" var="my_stays" />
	<fmt:message bundle="${lang}" key="stays.new_stay" var="new_stay" />
	<fmt:message bundle="${lang}" key="stays.since" var="since" />
	<fmt:message bundle="${lang}" key="stays.to" var="to" />
	<fmt:message bundle="${lang}" key="stays.room" var="room" />
	<fmt:message bundle="${lang}" key="stays.notes" var="notes" />
	<fmt:message bundle="${lang}" key="stays.clientId" var="clientId" />
	<fmt:message bundle="${lang}" key="stays.edit" var="edit" />
	<fmt:message bundle="${lang}" key="stays.delete" var="delete" />
	<fmt:message bundle="${lang}" key="stays.departure_date" var="departure_date" />
	<fmt:message bundle="${lang}" key="stays.check" var="check" />
	<fmt:message bundle="${lang}" key="stays.arrive_date" var="arrive_date" />	
	<fmt:message bundle="${lang}" key="stays.number_of_guests" var="number_of_guests" />
	<fmt:message bundle="${lang}" key="stays.check_among_all_rooms" var="check_among_all_rooms" />
	<fmt:message bundle="${lang}" key="stays.submit_stay" var="submit_stay" />
	<fmt:message bundle="${lang}" key="stays.please_check_data" var="please_check_data" />
	
	<fmt:message bundle="${lang}" key="rooms.roomLabel" var="roomLabel" />
	<fmt:message bundle="${lang}" key="rooms.search" var="search" />
	<fmt:message bundle="${lang}" key="rooms.clear" var="clear" />
	<fmt:message bundle="${lang}" key="rooms.from" var="from" />
	<fmt:message bundle="${lang}" key="rooms.cost" var="cost" />
	<fmt:message bundle="${lang}" key="rooms.capacity" var="capacity" />
	<fmt:message bundle="${lang}" key="rooms.gender" var="gender" />
	<fmt:message bundle="${lang}" key="rooms.m" var="m" />
	<fmt:message bundle="${lang}" key="rooms.f" var="f" />
	<fmt:message bundle="${lang}" key="rooms.mf" var="mf" />	
	<fmt:message bundle="${lang}" key="rooms.private_bathroom" var="private_bathroom" />
	<fmt:message bundle="${lang}" key="rooms.yes" var="yes" />	
	<fmt:message bundle="${lang}" key="rooms.photos_of_common_areas" var="photos_of_common_areas" />
	<fmt:message bundle="${lang}" key="rooms.booking" var="booking_word" />
	<fmt:message bundle="${lang}" key="rooms.sign_in_first" var="sign_in_first" />
	<fmt:message bundle="${lang}" key="rooms.private_bathroom_in_room" var="private_bathroom_in_room" />
	
	<fmt:message bundle="${lang}" key="account.my_account" var="my_account" />
	<fmt:message bundle="${lang}" key="account.clients_word" var="clients_word" />
	<fmt:message bundle="${lang}" key="account.bookings_word" var="bookings_word" />
	<fmt:message bundle="${lang}" key="account.stays_word" var="stays_word" />	
	<fmt:message bundle="${lang}" key="account.change_password" var="change_password" />
	<fmt:message bundle="${lang}" key="account.delete_account" var="delete_account" />
	<fmt:message bundle="${lang}" key="account.create_new_admin" var="create_new_admin" />
	<fmt:message bundle="${lang}" key="account.current_password" var="current_password" />	
	<fmt:message bundle="${lang}" key="account.new_password" var="new_password" />
	<fmt:message bundle="${lang}" key="account.repeat_new_password" var="repeat_new_password" />
	<fmt:message bundle="${lang}" key="account.change_password" var="change_password" />
	<fmt:message bundle="${lang}" key="account.delete" var="delete" />
	<fmt:message bundle="${lang}" key="account.cancel" var="cancel" />
	<fmt:message bundle="${lang}" key="account.save" var="save" />
	<fmt:message bundle="${lang}" key="account.del_acc_check_msg" var="del_acc_check_msg" />
</head>
</html>