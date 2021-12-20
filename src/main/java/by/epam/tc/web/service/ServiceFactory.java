package by.epam.tc.web.service;

import by.epam.tc.web.service.impl.RoomServiceImpl;
import by.epam.tc.web.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private UserService userService;
    private RoomService roomService;

    private ServiceFactory() {
    }

    public UserService getUserService() throws ServiceException {
    	try {
    		if(userService == null) {
    			userService = new UserServiceImpl();
    		}
		} 
    	catch (ServiceException e) {
			throw e;
		}
    	return userService;
    }
    
    public RoomService geRoomService() throws ServiceException {
    	try {
    		if(roomService == null) {
    			roomService = new RoomServiceImpl();
    		}
		} 
    	catch (ServiceException e) {
			throw e;
		}
    	return roomService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}

