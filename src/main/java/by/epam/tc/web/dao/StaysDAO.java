package by.epam.tc.web.dao;

import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.entity.stay.Stay;
import by.epam.tc.web.service.exception.ServiceException;

import java.util.List;

/**
 * Provides with methods for work with stays, bookings on DAO layer
 * 
 * @author Lizzi9223
 *
 */
public interface StaysDAO {
	/**
	 * Gets list containing all the existing bookings
	 * 
	 * @return list of booking objects
	 * @throws DAOException if exception in connection pool occurs
	 */
	List<Booking> getAllBookings() throws DAOException;

	/**
	 * Gets list containing all the existing bookings that belong to the user
	 * 
	 * @param userId identifier of the user whose bookings you want to find
	 * @return list of booking objects of the specific user
	 * @throws DAOException if exception in connection pool occurs
	 */
	List<Booking> getAllUserBookings(int userId) throws DAOException;

	/**
	 * Gets list containing all the existing stays
	 * 
	 * @return list of stay objects
	 * @throws DAOException if exception in connection pool occurs
	 */
	List<Stay> getAllStays() throws DAOException;

	/**
	 * Gets list containing all the existing stays that belong to the client
	 * 
	 * @param clientId identifier of the client whose stays you want to find
	 * @return list of stay objects
	 * @throws DAOException if exception in connection pool occurs
	 */
	List<Stay> getAllClientStays(int clientId) throws DAOException;

	/**
	 * Finds booking by booking identifier
	 * 
	 * @param id identifier of the booking to find
	 * @return booking object
	 * @throws DAOException if exception in connection pool occurs
	 */
	Booking findBookingById(int id) throws DAOException;

	/**
	 * Finds bookings by room number
	 * 
	 * @param roomNumber number of the room for which booking are made
	 * @return list of booking objects
	 * @throws DAOException if exception in connection pool occurs
	 */
	List<Booking> findAllBookingsByRoomNumber(int roomNumber) throws DAOException;

	/**
	 * Finds stay by stay identifier
	 * 
	 * @param id identifier of the stay to find
	 * @return stay object
	 * @throws DAOException if exception in connection pool occurs
	 */
	Stay findStayById(int id) throws DAOException;

	/**
	 * Finds stays by room number
	 * 
	 * @param roomNumber number of the room for which stays are made
	 * @return list of stay objects
	 * @throws DAOException if exception in connection pool occurs
	 */
	List<Stay> findAllStaysByRoomNumber(int roomNumber) throws DAOException;

	/**
	 * Adds new booking
	 * 
	 * @param booking booking object to add
	 * @return identifier of the added booking
	 * @throws DAOException if exception in connection pool occurs
	 */
	int addBooking(Booking booking) throws DAOException;

	/**
	 * Adds new stay
	 * 
	 * @param stay stay object to add
	 * @return identifier of the added stay
	 * @throws DAOException if exception in connection pool occurs
	 */
	int addStay(Stay stay) throws DAOException;

	/**
	 * Updates booking
	 * 
	 * @param id identifier of the booking to update
	 * @param booking booking object with data to update (if you do not update some field, fill it with initial data)
	 * @throws DAOException if exception in connection pool occurs
	 */
	void updateBooking(int id, Booking booking) throws DAOException;

	/**
	 * Updates stay
	 * 
	 * @param id identifier of the stay to update
	 * @param stay stay object with data to update (if you do not update some field, fill it with initial data)
	 * @throws DAOException if exception in connection pool occurs
	 */
	void updateStay(int id, Stay stay) throws DAOException;

	/**
	 * @param id identifier of the booking to delete
	 * @throws DAOException if exception in connection pool occurs
	 */
	void deleteBooking(int id) throws DAOException;

	/**
	 * @param id identifier of the stay to update
	 * @throws DAOException if exception in connection pool occurs
	 */
	void deleteStay(int id) throws DAOException;
}
