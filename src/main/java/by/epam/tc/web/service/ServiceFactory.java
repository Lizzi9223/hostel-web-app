package by.epam.tc.web.service;

import by.epam.tc.web.service.exception.ServiceException;
import by.epam.tc.web.service.impl.RoomServiceImpl;
import by.epam.tc.web.service.impl.StaysServiceImpl;
import by.epam.tc.web.service.impl.UserServiceImpl;

/**
 * Provides with access to services
 * 
 * @author Lizzi9223
 *
 */
public final class ServiceFactory {
	private static ServiceFactory instance;

	private UserService userService;
	private RoomService roomService;
	private StaysService staysService;

	private ServiceFactory() {
	}

	public UserService getUserService() {
		if (userService == null) {
			userService = new UserServiceImpl();
		}
		return userService;
	}

	public RoomService getRoomService() throws ServiceException {
		try {
			if (roomService == null) {
				roomService = new RoomServiceImpl();
			}
		} catch (ServiceException e) {
			throw e;
		}
		return roomService;
	}

	public StaysService getStaysService() {
		if (staysService == null) {
			staysService = new StaysServiceImpl();
		}
		return staysService;
	}

	public static ServiceFactory getInstance() {
		if(instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}
}
