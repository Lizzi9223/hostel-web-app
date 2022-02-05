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

import by.epam.tc.test.DAOTest.testData.RoomDAOTestData;
import by.epam.tc.web.dao.DAOException;
import by.epam.tc.web.dao.DAOFactory;
import by.epam.tc.web.dao.RoomDAO;
import by.epam.tc.web.dao.database.connection_pool.ConnectionPool;
import by.epam.tc.web.dao.database.connection_pool.ConnectionPoolException;
import by.epam.tc.web.entity.room.Image;
import by.epam.tc.web.entity.room.Room;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomDAOTest {
//	private static final RoomDAO roomDAO = DAOFactory.getInstance().getRoomDAO();
//	
//	@BeforeClass
//	public static void setUp() throws SQLException, ConnectionPoolException {
//		ConnectionPool connectionPool = ConnectionPool.getInstance();
//		connectionPool.initPoolData();
//	}
//	
//	@Test
//	public void test1GetAllRooms() throws DAOException{
//		List<Room> rooms = roomDAO.getAllRooms();
//		assertEquals(RoomDAOTestData.expectedRooms, rooms);
//	}
//	
//	@Test
//	public void test2GetAllRoomImages() throws DAOException{
//		List<Image> firstRoomImages = roomDAO.getAllRoomImages(1);
//		assertEquals(RoomDAOTestData.expectedFirstRoomImages, firstRoomImages);
//	}
//	
//	@Test
//	public void test3AddRoom() throws DAOException{
//		roomDAO.addRoom(RoomDAOTestData.roomToAdd);
//		Room room = roomDAO.findRoomByNumber(RoomDAOTestData.roomToAdd.getRoomNumber());
//		assertEquals(RoomDAOTestData.roomToAdd, room);
//	}
//	
//	@Test
//	public void test4AddImage() throws DAOException{
//		int id = roomDAO.addImage(RoomDAOTestData.imageToAdd);
//		Image image = roomDAO.findImageByPath(RoomDAOTestData.imageToAdd.getImgPath());
//		assertEquals(id, image.getImgId());
//	}
//	
//	@Test
//	public void test5UpdateRoom() throws DAOException{
//		roomDAO.updateRoom(RoomDAOTestData.roomToUpdate.getRoomNumber(), RoomDAOTestData.roomToUpdate);
//		Room room = roomDAO.findRoomByNumber(RoomDAOTestData.roomToUpdate.getRoomNumber());
//		assertEquals(RoomDAOTestData.roomToUpdate, room);
//	}
//	
//	@Test
//	public void test6UpdateImage() throws DAOException{
//		roomDAO.updateImage(RoomDAOTestData.imageToUpdate.getImgId(), RoomDAOTestData.imageToUpdate);
//		Image image = roomDAO.findImageByPath(RoomDAOTestData.imageToUpdate.getImgPath());
//		assertEquals(RoomDAOTestData.imageToUpdate, image);
//	}
//	
//	@Test
//	public void test7FindRoomByNumber() throws DAOException{
//		Room room = roomDAO.findRoomByNumber(RoomDAOTestData.roomToUpdate.getRoomNumber());
//		assertEquals(RoomDAOTestData.roomToUpdate, room);
//	}
//	
//	@Test
//	public void test8FindImageByPath() throws DAOException{
//		Image image = roomDAO.findImageByPath(RoomDAOTestData.imageToUpdate.getImgPath());
//		assertEquals(RoomDAOTestData.imageToUpdate, image);
//	}
//	
//	@Test
//	public void test9DeleteRoom() throws DAOException{
//		roomDAO.deleteRoom(RoomDAOTestData.roomToUpdate.getRoomNumber());
//		Room room = roomDAO.findRoomByNumber(RoomDAOTestData.roomToUpdate.getRoomNumber());
//		assertNull(room);
//	}
//	
//	@Test
//	public void test10DeleteImage() throws DAOException{
//		Image image = roomDAO.findImageByPath(RoomDAOTestData.imageToUpdate.getImgPath());
//		roomDAO.deleteImage(image.getImgId());
//		image = roomDAO.findImageByPath(RoomDAOTestData.imageToUpdate.getImgPath());
//		assertNull(image);
//	}
//	
//	@AfterClass
//	public static void destroy() throws SQLException {
//		ConnectionPool.getInstance().dispose();
//	}
}
