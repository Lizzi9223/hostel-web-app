package by.epam.tc.web.service;

import java.util.List;

import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.User;
import by.epam.tc.web.service.exception.LoginAlreadyExistsException;
import by.epam.tc.web.service.exception.PassportIdAlreadyExistsException;
import by.epam.tc.web.service.exception.ServiceException;

public interface UserService {
	User signIn(String login, String password) throws ServiceException;

	boolean signUp(Admin admin) throws ServiceException, LoginAlreadyExistsException;

	boolean signUp(Client client)
			throws ServiceException, LoginAlreadyExistsException, PassportIdAlreadyExistsException;

	boolean addClient(Client client) throws ServiceException, PassportIdAlreadyExistsException;

	boolean edit(Admin admin, String login) throws ServiceException, LoginAlreadyExistsException;

	boolean edit(Client client, String login, String passportId)
			throws ServiceException, LoginAlreadyExistsException, PassportIdAlreadyExistsException;
	
	boolean edit(Client client, int clientId)
			throws ServiceException, PassportIdAlreadyExistsException;

	boolean editPassword(String login, String password) throws ServiceException;
	
	Admin findAdminById(int id) throws ServiceException;

	Admin findAdminByLogin(String login) throws ServiceException;
	
	User findUserById(int id) throws ServiceException;
	
	Client findClientById(int id) throws ServiceException;

	Client findClientByLogin(String login) throws ServiceException;
	
	Client findClientByUserId(int id) throws ServiceException;

	void deleteAccount(String login) throws ServiceException;

	List<Client> getAllClients() throws ServiceException;

	List<User> getAllUsers() throws ServiceException;

	List<Client> getAllClientUsers() throws ServiceException;
	
	List<Admin> getAllAdmins() throws ServiceException;
	
	boolean isInBlacklist(int clientId) throws ServiceException;
	
	boolean isRegularCustomer(int clientId) throws ServiceException;
}
