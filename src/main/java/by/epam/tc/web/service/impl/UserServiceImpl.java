package by.epam.tc.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.dao.DAOException;
import by.epam.tc.web.dao.DAOFactory;
import by.epam.tc.web.dao.UserDAO;
import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.User;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.UserService;
import by.epam.tc.web.service.validator.UserValidator;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.service.impl.UserServiceImpl.class);	
	private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	
	public UserServiceImpl(){}
	
    @Override
    public User signIn(String login, String password) throws ServiceException {    	
    	User user = null;
    	try {    		
    		user = userDAO.findUserByLoginAndPassword(login, password);
    		password = null;    		
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
    public boolean signUp(Client client) throws ServiceException{
		int clientId = 0;
    	try {
    		if(!UserValidator.isValidClient(client)) {
    			logger.info("Validation failed while client registration");
    			return false;
    		}
    		clientId = userDAO.addUserClient(client);
    		client.setClientId(clientId);
    	}catch (DAOException e) {
    		throw new ServiceException(e);
		}    
    	return true;
    }
	
	@Override
	public void addClient(Client client) throws ServiceException {
    	try {    	
    		if(client.getLogin()!=null && !client.getLogin().equals("")) {
    			int userId = userDAO.getUserId(client.getLogin());
    			client.setUserId(userId);
    		}    		
    		userDAO.addClient(client);
    	}catch (DAOException e) {
    		throw new ServiceException(e);
		}
	}

	@Override
	public Admin edit(Admin admin, String login) throws ServiceException {    	
    	try {
    		int userId = userDAO.getUserId(login);
    		userDAO.updateUser(userId, admin);
    		userDAO.updateAdmin(userId, admin);
    	}catch (DAOException e) {
    		throw new ServiceException(e);
		}    
    	return admin;
	}
	
	@Override
	public Client edit(Client client, String login, String passportId) throws ServiceException{
    	try {
    		int userId = userDAO.getUserId(login);
    		userDAO.updateUser(userId, client);
    		int clientId = userDAO.getClientId(passportId);
    		userDAO.updateClient(clientId, client);
    	}catch (DAOException e) {
    		throw new ServiceException(e);
		}    
    	return client;
    }
	
	@Override
	public void editPassword(String login, String password) throws ServiceException{
		try {
			userDAO.updatePassword(login, password);
			password = null;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public Admin findAdminByLogin(String login) throws ServiceException{
		Admin admin = null;
		try {
			admin = userDAO.findAdminByLogin(login);
		}catch (DAOException e) {
    		throw new ServiceException(e);
		}    
    	return admin;
	}
	
	@Override
	public Client findClientByLogin(String login) throws ServiceException{
		Client client = null;
		try {
			client = userDAO.findClientByLogin(login);
		}catch (DAOException e) {
    		throw new ServiceException(e);
		}    
    	return client;
	}
	
	@Override
	public void deleteAccount(String login) throws ServiceException{
		try {
			int userId = userDAO.getUserId(login);
			userDAO.deleteUser(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Client> getAllClients() throws ServiceException {
		List<Client> clients = new ArrayList<Client>();
		try {
			clients = userDAO.getAllClients();
			for(Client client : clients) {
				User user = userDAO.findUserById(client.getUserId());
				client.setLogin(user.getLogin());
			}			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return clients;
	}

	@Override
	public List<User> getAllUsers() throws ServiceException {
		List<User> users = new ArrayList<User>();
		try {
			users = userDAO.getAllUsers();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return users;
	}

	@Override
	public List<Client> getAllClientUsers() throws ServiceException {
		List<Client> clientUsers = new ArrayList<Client>();
		try {
			clientUsers = userDAO.getAllClientUsers();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return clientUsers;
	}
}
