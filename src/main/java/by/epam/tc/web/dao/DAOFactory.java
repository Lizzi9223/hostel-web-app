package by.epam.tc.web.dao;

import by.epam.tc.web.dao.impl.RoomDAOImpl;
import by.epam.tc.web.dao.impl.StaysDAOImpl;
import by.epam.tc.web.dao.impl.UserDAOImpl;

public final class DAOFactory {
	private final static DAOFactory instance = new DAOFactory();

	private UserDAO userDAO;
	private StaysDAO staysDAO;
	private RoomDAO roomDAO;

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
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
}
