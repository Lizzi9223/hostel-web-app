package by.epam.tc.web.dao;

import by.epam.tc.web.dao.impl.RoomDAOImpl;
import by.epam.tc.web.dao.impl.StaysDAOImpl;
import by.epam.tc.web.dao.impl.UserDAOImpl;

/**
 * Provides with access to DAO
 * 
 * @author Lizzi9223
 *
 */
public final class DAOFactory {
	private static DAOFactory instance;

	private UserDAO userDAO;
	private StaysDAO staysDAO;
	private RoomDAO roomDAO;

	private DAOFactory() {
	}

	public UserDAO getUserDAO() {
		if (userDAO == null) {
			userDAO = new UserDAOImpl();
		}
		return userDAO;
	}

	public StaysDAO getStaysDAO() {
		if (staysDAO == null) {
			staysDAO = new StaysDAOImpl();
		}
		return staysDAO;
	}

	public RoomDAO getRoomDAO() {
		if (roomDAO == null) {
			roomDAO = new RoomDAOImpl();
		}
		return roomDAO;
	}
	
	public static DAOFactory getInstance() {
		if(instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}
}
