package by.epam.tc.web.service;

import by.epam.tc.web.service.impl.RoomServiceImpl;
import by.epam.tc.web.service.impl.StaysServiceImpl;
import by.epam.tc.web.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private UserService userService;
    private RoomService roomService;
    private StaysService staysService;

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
    
    public RoomService getRoomService() throws ServiceException {
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
    
    public StaysService getStaysService() throws ServiceException {
    	try {
    		if(staysService == null) {
    			staysService = new StaysServiceImpl();
    		}
		} 
    	catch (ServiceException e) {
			throw e;
		}
    	return staysService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}

