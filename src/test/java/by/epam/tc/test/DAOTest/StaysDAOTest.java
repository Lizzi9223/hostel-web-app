package by.epam.tc.test.DAOTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import by.epam.tc.test.DAOTest.testData.StaysDAOTestData;
import by.epam.tc.web.dao.DAOException;
import by.epam.tc.web.dao.DAOFactory;
import by.epam.tc.web.dao.RoomDAO;
import by.epam.tc.web.dao.StaysDAO;
import by.epam.tc.web.dao.database.connection_pool.ConnectionPool;
import by.epam.tc.web.dao.database.connection_pool.ConnectionPoolException;
import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.entity.stay.Stay;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaysDAOTest {
//	private static final StaysDAO staysDAO = DAOFactory.getInstance().getStaysDAO();
//	
//	@BeforeClass
//	public static void setUp() throws SQLException, ConnectionPoolException {
//		ConnectionPool connectionPool = ConnectionPool.getInstance();
//		connectionPool.initPoolData();
//	}
//	
//	@Test
//	public void test1GetAllBookings() throws DAOException{
//		List<Booking> bookings = staysDAO.getAllBookings();
//		assertEquals(StaysDAOTestData.expectedAllBooking, bookings);
//	}
//	
//	@Test
//	public void test2GetAllUserBookings() throws DAOException{
//		int userId = 5;
//		List<Booking> bookings = staysDAO.getAllUserBookings(userId);
//		assertEquals(StaysDAOTestData.expectedUser5Bookings, bookings);
//	}
//	
//	@Test
//	public void test3GetAllStays() throws DAOException{
//		List<Stay> stays = staysDAO.getAllStays();
//		assertEquals(StaysDAOTestData.expectedAllStays, stays);
//	}
//	
//	@Test
//	public void test4GetAllClientStays() throws DAOException{
//		int clientId = 4;
//		List<Stay> stays = staysDAO.getAllClientStays(clientId);
//		assertEquals(StaysDAOTestData.expectedClient4Stays, stays);
//	}
	
//	@Test
//	public void test5AddBooking() throws DAOException{
//		int id = staysDAO.addBooking(StaysDAOTestData.bookingToAdd);
//		Booking booking = staysDAO.findBookingById(id);
//		assertEquals(id, booking.getId());
//	}
//	
//	@Test
//	public void test6AddStay() throws DAOException{
//		int id = staysDAO.addStay(StaysDAOTestData.stayToAdd);
//		Stay stay = staysDAO.findStayById(id);
//		assertEquals(id, stay.getId());
//	}
//	
//	@Test
//	public void test7UpdateBooking() throws DAOException{
//		staysDAO.updateBooking(StaysDAOTestData.bookingToUpdate.getId(), StaysDAOTestData.bookingToUpdate);
//		Booking booking = staysDAO.findBookingById(StaysDAOTestData.bookingToUpdate.getId());
//		assertEquals(StaysDAOTestData.bookingToUpdate, booking);
//	}
//	
//	@Test
//	public void test8UpdateStay() throws DAOException{
//		staysDAO.updateStay(StaysDAOTestData.stayToUpdate.getId(), StaysDAOTestData.stayToUpdate);
//		Stay stay = staysDAO.findStayById(StaysDAOTestData.stayToUpdate.getId());
//		assertEquals(StaysDAOTestData.stayToUpdate, stay);
//	}
//		
//	@Test
//	public void test9FindBookingById() throws DAOException{
//		Booking booking = staysDAO.findBookingById(StaysDAOTestData.bookingToUpdate.getId());
//		assertEquals(StaysDAOTestData.bookingToUpdate, booking);
//	}
//	
//	@Test
//	public void test10FindAllBookingsByRoomNumber() throws DAOException{
//		int roomNumber = 5;
//		List<Booking> bookings = staysDAO.findAllBookingsByRoomNumber(roomNumber);
//		assertEquals(StaysDAOTestData.expectedRoom5Bookings, bookings);
//	}
//	
//	@Test
//	public void test11FindStayById() throws DAOException{
//		Stay stay = staysDAO.findStayById(StaysDAOTestData.stayToUpdate.getId());
//		assertEquals(StaysDAOTestData.stayToUpdate, stay);
//	}
//	
//	@Test
//	public void test12FindAllStaysByRoomNumber() throws DAOException{
//		int roomNumber = 3;
//		List<Stay> stays = staysDAO.findAllStaysByRoomNumber(roomNumber);
//		assertEquals(StaysDAOTestData.expectedRoom3Stays, stays);
//	}
//	
//	@Test
//	public void test13DeleteBooking() throws DAOException{
//		staysDAO.deleteBooking(StaysDAOTestData.bookingToUpdate.getId());
//		Booking booking = staysDAO.findBookingById(StaysDAOTestData.bookingToUpdate.getId());
//		assertNull(booking);
//	}
//	
//	@Test
//	public void test14DeleteStay() throws DAOException{
//		staysDAO.deleteStay(StaysDAOTestData.stayToUpdate.getId());
//		Stay stay = staysDAO.findStayById(StaysDAOTestData.stayToUpdate.getId());
//		assertNull(stay);
//	}
//	
//	@AfterClass
//	public static void destroy() throws SQLException {
//		ConnectionPool.getInstance().dispose();
//	}
}
