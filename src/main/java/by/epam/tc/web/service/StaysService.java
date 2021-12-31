package by.epam.tc.web.service;

import java.time.LocalDate;
import java.util.List;

import by.epam.tc.web.entity.room.Room;
import by.epam.tc.web.entity.stay.Booking;

public interface StaysService {
	List<Booking> getAllBookings() throws ServiceException;
	List<Booking> getAllUserBookings(String userLogin) throws ServiceException;
	void addBooking(String userLogin, LocalDate fromDate, LocalDate toDate, int guestsNumber, int roomNumber) throws ServiceException;
	boolean areAvailablePlaces(int roomNumber, LocalDate fromDate, LocalDate toDate, int guestsNumber) throws ServiceException;
	List<Room> areAvailablePlaces(LocalDate fromDate, LocalDate toDate, int guestsNumber) throws ServiceException;
}
