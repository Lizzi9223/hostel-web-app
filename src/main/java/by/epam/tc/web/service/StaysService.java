package by.epam.tc.web.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import by.epam.tc.web.entity.room.Room;
import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.entity.stay.Stay;
import by.epam.tc.web.service.exception.ServiceException;

public interface StaysService {
	List<Booking> getAllBookings() throws ServiceException;

	List<Booking> getAllUserBookings(String userLogin) throws ServiceException;

	Booking getBookingById(int id) throws ServiceException;

	BigDecimal getBookingPrice(Booking booking) throws ServiceException;

	List<Stay> getAllStays() throws ServiceException;

	List<Stay> getAllUserStays(String userLogin) throws ServiceException;

	Stay getStayById(int id) throws ServiceException;

	void addBooking(String userLogin, LocalDate fromDate, LocalDate toDate, int guestsNumber, int roomNumber)
			throws ServiceException;

	void addStay(int clietnId, LocalDate fromDate, LocalDate toDate, int roomNumber, String notes)
			throws ServiceException;

	boolean areAvailablePlaces(int roomNumber, LocalDate fromDate, LocalDate toDate, int guestsNumber,
			int bookingToEditId, int stayToEditId) throws ServiceException;

	List<Room> areAvailablePlaces(LocalDate fromDate, LocalDate toDate, int guestsNumber, int bookingToEditId,
			int stayToEditId) throws ServiceException;

	void approveBooking(int id, boolean isApproved) throws ServiceException;

	void updateBooking(int id, Booking booking) throws ServiceException;

	void updateStay(int id, Stay stay) throws ServiceException;

	void deleteBooking(int id) throws ServiceException;

	void deleteStay(int id) throws ServiceException;
}
