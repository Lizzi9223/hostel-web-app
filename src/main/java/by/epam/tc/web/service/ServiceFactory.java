package by.epam.tc.web.service;

import by.epam.tc.web.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private UserService userService;

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

    public static ServiceFactory getInstance() {
        return instance;
    }
}

