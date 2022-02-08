package by.epam.tc.web.service;

import java.util.List;

import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.BlackListClient;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.RegularClient;
import by.epam.tc.web.entity.user.User;
import by.epam.tc.web.service.exception.LoginAlreadyExistsException;
import by.epam.tc.web.service.exception.PassportIdAlreadyExistsException;
import by.epam.tc.web.service.exception.ServiceException;

/**
 * Provides with methods for work with users, clients, administrators, blacklist clients, regular clients on server layer
 * 
 * @author Lizzi9223
 *
 */
public interface UserService {
	/**
	 * Checks login and password of the user who is signing in
	 * 
	 * @param login login of the user to sign in
	 * @param password password of the user to sign in
	 * @return user object if login and password are correct, otherwise null
	 * @throws ServiceException if exception in DAO layer happens
	 */
	User signIn(String login, String password) throws ServiceException;

	/**
	 * Signs up new administrator in the application
	 * 
	 * @param admin administrator object to sign up
	 * @return true if administrator was signed up, otherwise false
	 * @throws ServiceException if exception in DAO layer happens
	 * @throws LoginAlreadyExistsException if this login already exists in application
	 */
	boolean signUp(Admin admin) throws ServiceException, LoginAlreadyExistsException;

	/**
	 * Signs up new user client in the application
	 * 
	 * @param client client object to sign up
	 * @return true if client was signed up, otherwise false
	 * @throws ServiceException if exception in DAO layer happens
	 * @throws LoginAlreadyExistsException if this login already exists in application
	 * @throws PassportIdAlreadyExistsException if this passport identifier already exists in application
	 */
	boolean signUp(Client client)
			throws ServiceException, LoginAlreadyExistsException, PassportIdAlreadyExistsException;

	/**
	 * Adds new client (not user, just client) to the application
	 * 
	 * @param client client object to add 
	 * @return true if client was added, otherwise false
	 * @throws ServiceException if exception in DAO layer happens
	 * @throws PassportIdAlreadyExistsException if this passport identifier already exists in application
	 */
	boolean addClient(Client client) throws ServiceException, PassportIdAlreadyExistsException;
		
	/**
	 * Adds new user to the application
	 * 
	 * @param user user object to add 
	 * @return true if user was added, otherwise false
	 * @throws ServiceException if exception in DAO layer happens
	 * @throws LoginAlreadyExistsException if this login already exists in application
	 */
	boolean addUser(User user) throws ServiceException, LoginAlreadyExistsException;
	
	/**
	 * Adds existing client to blacklist
	 * 
	 * @param client client object to add to blacklist
	 * @throws ServiceException if exception in DAO layer happens
	 */ 
	void addBlacklistClient(BlackListClient client) throws ServiceException;
	
	/**
	 * Adds existing client to regular clients
	 * 
	 * @param client client object to add to regular customers
	 * @throws ServiceException if exception in DAO layer happens
	 */
	void addRegularClient(RegularClient client) throws ServiceException;

	/**
	 * Edits administrator data based on user identifier
	 * 
	 * @param admin administrator object with data to update (if you do not update some field, fill it with initial data)
	 * @param login initial login of the administrator
	 * @return true if administrator was updated, otherwise false
	 * @throws ServiceException if exception in DAO layer happens
	 * @throws LoginAlreadyExistsException if login was changed and the new one already exists in application
	 */
	boolean edit(Admin admin, String login) throws ServiceException, LoginAlreadyExistsException;

	/**
	 * Edits user client data based on client identifier
	 * 
	 * @param client client object with data to update (if you do not update some field, fill it with initial data)
	 * @param login initial login of the client
	 * @param passportId initial passport identifier of the client
	 * @return true if client was updated, otherwise false
	 * @throws ServiceException if exception in DAO layer happens
	 * @throws LoginAlreadyExistsException if login was changed and the new one already exists in application
	 * @throws PassportIdAlreadyExistsException if passport identifier was changed and the new one already exists in application
	 */ 
	boolean edit(Client client, String login, String passportId)
			throws ServiceException, LoginAlreadyExistsException, PassportIdAlreadyExistsException;
	
	/**
	 * Edits client data based on client identifier
	 * 
	 * @param client client object with data to update (if you do not update some field, fill it with initial data)
	 * @param clientId identifier of the client to update
	 * @return true if client was updated, otherwise false
	 * @throws ServiceException if exception in DAO layer happens
	 * @throws PassportIdAlreadyExistsException if passport identifier was changed and the new one already exists in application
	 */
	boolean edit(Client client, int clientId)
			throws ServiceException, PassportIdAlreadyExistsException;

	/**
	 * Edits user password
	 * 
	 * @param login login of user whose password to update
	 * @param password new password
	 * @return true if password was updated, otherwise false
	 * @throws ServiceException if exception in DAO layer happens
	 */
	boolean editPassword(String login, String password) throws ServiceException;
	
	/**
	 * Edits client's user identifier
	 * 
	 * @param clientId identifier of the client to update
	 * @param newUserId new user identifier to set
	 * @throws ServiceException if exception in DAO layer happens
	 */
	void editClientsUserId(int clientId, int newUserId) throws ServiceException;
	
	/**
	 * Edits blacklist info
	 * 
	 * @param clientId identifier of the client to update
	 * @param client BlackListClient object with data to update (if you do not update some field, fill it with initial data)
	 * @throws ServiceException if exception in DAO layer happens
	 */
	void editBlacklistClient(int clientId, BlackListClient client) throws ServiceException;
	
	/**
	 * Edits regular client info
	 * 
	 * @param clientId identifier of the client to update
	 * @param client RegularClient object with data to update (if you do not update some field, fill it with initial data)
	 * @throws ServiceException if exception in DAO layer happens
	 */
	void editRegularClient(int clientId, RegularClient client) throws ServiceException;
	
	/**
	 * Finds administrator object based on its user identifier
	 * 
	 * @param user id identifier of the administrator to find
	 * @return if administrator is found, returns administrator object, otherwise null
	 * @throws ServiceException if exception in DAO layer happens
	 */
	Admin findAdminById(int id) throws ServiceException;

	/**
	 * Finds administrator object based on its login
	 * 
	 * @param login login of the administrator to find
	 * @return if administrator is found, returns administrator object, otherwise null
	 * @throws ServiceException if exception in DAO layer happens
	 */
	Admin findAdminByLogin(String login) throws ServiceException;
	
	/**
	 * Finds user object based on its user identifier
	 * 
	 * @param id user identifier of the user to find
	 * @return if user is found, returns user object, otherwise null
	 * @throws ServiceException if exception in DAO layer happens
	 */
	User findUserById(int id) throws ServiceException;
	
	/**
	 * Finds client object based on its client identifier
	 * 
	 * @param id client identifier of the client to find
	 * @return if client is found, returns client object, otherwise null
	 * @throws ServiceException if exception in DAO layer happens
	 */
	Client findClientById(int id) throws ServiceException;

	/**
	 * Finds client object based on its login
	 * 
	 * @param login login of the client to find
	 * @return if client is found, returns client object, otherwise null
	 * @throws ServiceException if exception in DAO layer happens
	 */
	Client findClientByLogin(String login) throws ServiceException;
	
	/**
	 * Finds client object based on its user identifier
	 * 
	 * @param id user identifier of the client to find
	 * @return if client is found, returns client object, otherwise null
	 * @throws ServiceException if exception in DAO layer happens
	 */
	Client findClientByUserId(int id) throws ServiceException;
	
	/**
	 * Finds client object based on its passport identifier
	 * 
	 * @param passportId passport identifier of the client to find
	 * @return if client is found, returns client object, otherwise null
	 * @throws ServiceException if exception in DAO layer happens
	 */
	Client findClientByPassportId(String passportId) throws ServiceException;
	
	/**
	 * Finds blacklist client object based on its client identifier
	 * 
	 * @param id client identifier of the client to find
	 * @return if blacklist client is found, returns BlackListClient object, otherwise null
	 * @throws ServiceException if exception in DAO layer happens
	 */
	BlackListClient findBlackListClientByClientId(int id) throws ServiceException;
	
	/**
	 * Finds regular client object based on its client identifier
	 * 
	 * @param id client identifier of the client to find
	 * @return if regular client is found, returns RegularClient object, otherwise null
	 * @throws ServiceException if exception in DAO layer happens
	 */
	RegularClient findRegularClientByClientId(int id) throws ServiceException;

	/**
	 * Deletes user account
	 * 
	 * @param login login of the user to delete
	 * @throws ServiceException if exception in DAO layer happens
	 */
	void deleteAccount(String login) throws ServiceException;
	
	/**
	 * Deletes client from blacklist
	 * 
	 * @param clientId identifier of the client to delete from blacklist
	 * @throws ServiceException if exception in DAO layer happens
	 */
	void deleteFromBlacklist(int clientId) throws ServiceException;
	
	/**
	 * Deletes client from regular customers
	 * 
	 * @param clientId identifier of the client to delete from regular customers
	 * @throws ServiceException if exception in DAO layer happens
	 */
	void deleteFromRegularCustomers(int clientId) throws ServiceException;

	/**
	 * Gets list containing all the existing clients
	 * 
	 * @return list of client objects
	 * @throws ServiceException if exception in DAO layer happens
	 */
	List<Client> getAllClients() throws ServiceException;

	/**
	 * Gets list containing all the existing users
	 * 
	 * @return list of user objects
	 * @throws ServiceException if exception in DAO layer happens
	 */
	List<User> getAllUsers() throws ServiceException;

	/**
	 * Gets list containing all the existing user clients
	 * 
	 * @return list of user client objects
	 * @throws ServiceException if exception in DAO layer happens
	 */
	List<Client> getAllClientUsers() throws ServiceException;
	
	/**
	 * Gets list containing all the existing administrators
	 * 
	 * @return list of administrator objects
	 * @throws ServiceException if exception in DAO layer happens
	 */
	List<Admin> getAllAdmins() throws ServiceException;
	
	/**
	 * Gets list containing all the existing blacklist clients
	 * 
	 * @return list of BlackListClient objects
	 * @throws ServiceException if exception in DAO layer happens
	 */
	List<BlackListClient> getAllBlackListClients() throws ServiceException;
	
	/**
	 * Gets list containing all the existing regular customers
	 * 
	 * @return list of RegularClient objects
	 * @throws ServiceException if exception in DAO layer happens
	 */
	List<RegularClient> getAllRegularClients() throws ServiceException;
	
	/**
	 * Checks if client is in blacklist
	 * 
	 * @param clientId identifier of the client to check
	 * @return true if client is in blacklist, otherwise false
	 * @throws ServiceException if exception in DAO layer happens
	 */
	boolean isInBlacklist(int clientId) throws ServiceException;
	
	/**
	 * Checks if client is a regular customer
	 * 
	 * @param clientId identifier of the client to check
	 * @return if client is a regular customer, otherwise false
	 * @throws ServiceException if exception in DAO layer happens
	 */
	boolean isRegularCustomer(int clientId) throws ServiceException;
	
	/**
	 * Gets user identifier based on user login
	 * 
	 * @param login login of the user whose identifier to get
	 * @return user identifier
	 * @throws ServiceException if exception in DAO layer happens
	 */
	int getUserIdByLogin(String login) throws ServiceException;
}
