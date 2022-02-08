package by.epam.tc.web.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import by.epam.tc.web.entity.room.Room;
import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.entity.stay.Stay;
import by.epam.tc.web.service.exception.ServiceException;

/**
 * Provides with methods for work with booking and stays on server layer
 * 
 * @author Lizzi9223
 *
 */
public interface StaysService {
	/**
	 * Gets list containing all the existing bookings
	 * 
	 * @return list of booking objects
	 * @throws ServiceException if exception in DAO layer happens
	 */
	List<Booking> getAllBookings() throws ServiceException;

	/**
	 * Gets list containing all the existing bookings that belong to the user
	 * 
	 * @param userLogin login of the user whose bookings you want to find
	 * @return list of booking objects of the specific user
	 * @throws ServiceException if exception in DAO layer happens
	 */
	List<Booking> getAllUserBookings(String userLogin) throws ServiceException;

	/**
	 * Gets the booking object based on its id
	 * 
	 * @param id the identifier of the booking you want to find
	 * @return the booking object by id
	 * @throws ServiceException if exception in DAO layer happens
	 */
	Booking getBookingById(int id) throws ServiceException;

	/**
	 * Gets the price of the specific booking
	 * 
	 * @param booking the booking which price you want to find
	 * @return price of booking
	 * @throws ServiceException if exception in DAO layer happens
	 */
	BigDecimal getBookingPrice(Booking booking) throws ServiceException;

	/**
	 * Gets list containing all the existing stays
	 * 
	 * @return list of stays objects
	 * @throws ServiceException if exception in DAO layer happens
	 */
	List<Stay> getAllStays() throws ServiceException;

	/**
	 * Gets list containing all the existing stays that belong to the user
	 * 
	 * @param userLogin login of the user whose stays you want to find
	 * @return list of stay objects of the specific user
	 * @throws ServiceException if exception in DAO layer happens
	 */
	List<Stay> getAllUserStays(String userLogin) throws ServiceException;

	/**
	 * Gets the stay object based on its id
	 * 
	 * @param id the identifier of the stay you want to find
	 * @return the stay object by id
	 * @throws ServiceException if exception in DAO layer happens
	 */
	Stay getStayById(int id) throws ServiceException;

	/**
	 * Adds new booking 
	 * 
	 * @param userLogin login of the user who is adding booking
	 * @param fromDate arrive date
	 * @param toDate departure date
	 * @param guestsNumber number of guests who are going to visit hostel
	 * @param roomNumber number of the room 
	 * @throws ServiceException if exception in DAO layer happens
	 */
	void addBooking(String userLogin, LocalDate fromDate, LocalDate toDate, int guestsNumber, int roomNumber)
			throws ServiceException;

	/**
	 *  Adds new stay
	 * 
	 * @param clietnId identifier of the client who is adding stay
	 * @param fromDate arrive date
	 * @param toDate departure date
	 * @param roomNumber number of the room 
	 * @param notes notes (optional)
	 * @throws ServiceException if exception in DAO layer happens
	 */
	void addStay(int clietnId, LocalDate fromDate, LocalDate toDate, int roomNumber, String notes)
			throws ServiceException;

	/**
	 * Checks if there are certain number of available places in certain room for certain dates
	 * 
	 * @param roomNumber number of the room 
	 * @param fromDate arrive date
	 * @param toDate departure date
	 * @param guestsNumber number of guests
	 * @param bookingToEditId booking identifier if you are editing booking (otherwise 0)
	 * @param stayToEditId stay identifier if you are editing stay (otherwise 0)
	 * @return true if there are available places, otherwise false
	 * @throws ServiceException if exception in DAO layer happens
	 */
	boolean areAvailablePlaces(int roomNumber, LocalDate fromDate, LocalDate toDate, int guestsNumber,
			int bookingToEditId, int stayToEditId) throws ServiceException;

	/**
	 * Checks if there are certain number of available places in any room for certain dates
	 * 
	 * @param fromDate arrive date
	 * @param toDate departure date
	 * @param guestsNumber number of guests
	 * @param bookingToEditId booking identifier if you are editing booking (otherwise 0)
	 * @param stayToEditId stay identifier if you are editing stay (otherwise 0)
	 * @return true if there are available places, otherwise false
	 * @throws ServiceException if exception in DAO layer happens
	 */
	List<Room> areAvailablePlaces(LocalDate fromDate, LocalDate toDate, int guestsNumber, int bookingToEditId,
			int stayToEditId) throws ServiceException;

	/**
	 * Sets isApproved field in booking object to specific value
	 * 
	 * @param id identifier of the booking to approve/disapprove
	 * @param isApproved true if you want to approve booking, otherwise false
	 * @throws ServiceException if exception in DAO layer happens
	 */ 
	void approveBooking(int id, boolean isApproved) throws ServiceException;

	/**
	 * Updates booking 
	 * 
	 * @param id identifier of the booking to update
	 * @param booking booking object with data to update (if you do not update some field, fill it with initial data)
	 * @throws ServiceException if exception in DAO layer happens
	 */
	void updateBooking(int id, Booking booking) throws ServiceException;

	/**
	 * Updates stay
	 * 
	 * @param id identifier of the stay to update
	 * @param stay stay object with data to update (if you do not update some field, fill it with initial data)
	 * @throws ServiceException if exception in DAO layer happens
	 */
	void updateStay(int id, Stay stay) throws ServiceException;

	/**
	 * Deletes booking 
	 * 
	 * @param id identifier of the booking to delete
	 * @throws ServiceException if exception in DAO layer happens
	 */
	void deleteBooking(int id) throws ServiceException;

	/**
	 * Deletes stay 
	 * 
	 * @param id identifier of the stay to delete
	 * @throws ServiceException if exception in DAO layer happens
	 */
	void deleteStay(int id) throws ServiceException;
}
