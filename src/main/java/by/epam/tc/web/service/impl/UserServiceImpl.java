package by.epam.tc.web.service.impl;

import by.epam.tc.web.dao.DAOException;
import by.epam.tc.web.dao.DAOFactory;
import by.epam.tc.web.dao.UserDAO;
import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.Role;
import by.epam.tc.web.entity.user.User;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.UserService;

public class UserServiceImpl implements UserService {
	
	private final UserDAO userDAO;
	
	public UserServiceImpl() throws ServiceException {
		try {
			userDAO = DAOFactory.getInstance().getUserDAO();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
    @Override
    public User signIn(String login, String password) throws ServiceException {    	
    	User user = null;
    	try {
    		user = userDAO.findUserByLoginAndPassword(login, password);
    		
		} catch (DAOException e) {
			throw new ServiceException(e);
		} 
    	return user;
    }
    
    
    @Override
	public Admin signUp(Admin admin) throws ServiceException {
    	int userId = 0;
    	try {
    		userId = userDAO.addUser(admin); 
    		admin.setUserId(userId);
    	}catch (DAOException e) {
    		throw new ServiceException(e);
		}    
    	return admin;
	}

	@Override
    public Client signUp(Client client) throws ServiceException{
		int userId = 0;
		int clientId = 0;
    	try {
    		userId = userDAO.addUser(client);
    		client.setUserId(userId);
    		clientId = userDAO.addClient(client);
    		client.setClientId(clientId);
    	}catch (DAOException e) {
    		throw new ServiceException(e);
		}    
    	return client;
    }
}
