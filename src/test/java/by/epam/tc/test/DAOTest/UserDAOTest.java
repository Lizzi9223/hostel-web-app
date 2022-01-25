package by.epam.tc.test.DAOTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epam.tc.web.dao.DAOException;
import by.epam.tc.web.dao.DAOFactory;
import by.epam.tc.web.dao.UserDAO;
import by.epam.tc.web.dao.database.connection_pool.ConnectionPool;
import by.epam.tc.web.dao.database.connection_pool.ConnectionPoolException;
import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.User;

public class UserDAOTest {	
	private static final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	
	@BeforeClass
	public static void setUp() throws SQLException, ConnectionPoolException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.initPoolData();
	}
	
	@Test
	public void testGetAllUsers() throws DAOException {
		List<User> users = userDAO.getAllUsers();
		assertEquals(8, users.size());	
	}
	
	@Test
	public void testGetAllAdmins() throws DAOException {
		List<Admin> admins = userDAO.getAllAdmins();
		assertNotNull(admins);
		assertEquals(4, admins.size());	
	}
	
	@Test
	public void testFindUserByLogin() throws DAOException {
		User user = userDAO.findUserByLogin("user2");
		assertNotNull(user);
		assertEquals("user2", user.getLogin());	
	}
	
	@AfterClass
	public static void destroy() throws SQLException {
		ConnectionPool.getInstance().dispose();
	}
}
