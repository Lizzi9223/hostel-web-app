package by.epam.tc.web.dao;

import by.epam.tc.web.entity.user.*;

import java.util.List;

/**
 * Provides with methods for work with users, clients, administrators, blacklist clients, regular clients on DAO layer
 * 
 * @author Lizzi9223
 *
 */
public interface UserDAO {
	/**
	 * Gets list containing all the existing users 
	 * 
	 * @return list of user objects
	 * @throws DAOException if exception in connection pool occurs
	 */
	List<User> getAllUsers() throws DAOException;

	/**
	 * Gets list containing all the existing administrators 
	 * 
	 * @return list of administrator objects
	 * @throws DAOException if exception in connection pool occurs
	 */
	List<Admin> getAllAdmins() throws DAOException;

	/**
	 * Gets list containing all the existing user clients 
	 * 
	 * @return list of client objects
	 * @throws DAOException if exception in connection pool occurs
	 */
	List<Client> getAllClientUsers() throws DAOException; 

	/**
	 * Gets list containing all the existing clients 
	 * 
	 * @return list of client objects
	 * @throws DAOException if exception in connection pool occurs
	 */
	List<Client> getAllClients() throws DAOException;

	/**
	 * Gets list containing all the existing blacklist clients 
	 * 
	 * @return list of client objects
	 * @throws DAOException if exception in connection pool occurs
	 */
	List<BlackListClient> getAllBlackListClients() throws DAOException;

	/**
	 * Gets list containing all the existing regular customers 
	 * 
	 * @return list of client objects
	 * @throws DAOException if exception in connection pool occurs
	 */
	List<RegularClient> getAllRegularClients() throws DAOException;

	/**
	 * Adds new administrator to the application
	 * 
	 * @param user administrator object to add 
	 * @return identifier of the added administrator
	 * @throws DAOException if exception in connection pool occurs
	 */
	int addUser(Admin user) throws DAOException;

	/**
	 * Adds new user to the application
	 * 
	 * @param user user object to add 
	 * @return identifier of the added user
	 * @throws DAOException if exception in connection pool occurs
	 */
	int addUser(User user) throws DAOException;

	/**
	 * Adds new client to the application
	 * 
	 * @param client client object to add 
	 * @return identifier of the added client
	 * @throws DAOException if exception in connection pool occurs
	 */ 
	int addClient(Client client) throws DAOException; 

	/**
	 * Adds new user client to the application
	 * 
	 * @param client client object to add 
	 * @return identifier of the added client
	 * @throws DAOException if exception in connection pool occurs
	 */
	int addUserClient(Client client) throws DAOException;

	/**
	 * Adds client to blacklist
	 * 
	 * @param client BlackListClient object to add
	 * @throws DAOException if exception in connection pool occurs
	 */
	void addToBlackList(BlackListClient client) throws DAOException;

	/**
	 * Adds client to regular customers
	 * 
	 * @param client RegularClient object to add
	 * @throws DAOException if exception in connection pool occurs
	 */
	void addToRegularCustomers(RegularClient client) throws DAOException;

	/**
	 * Finds user by its login and password
	 * 
	 * @param login login of the user to find
	 * @param password password of the user to find
	 * @return user object if it was found, otherwise null
	 * @throws DAOException if exception in connection pool occurs
	 */
	User findUserByLoginAndPassword(String login, String password) throws DAOException; 

	/**
	 * Finds user by its user identifier
	 * 
	 * @param userId user identifier of the user to find
	 * @return user object if it was found, otherwise null
	 * @throws DAOException if exception in connection pool occurs
	 */
	User findUserById(int userId) throws DAOException; 

	/**
	 * Finds user by its login
	 * 
	 * @param userLogin login of the user to find
	 * @return user object if it was found, otherwise null
	 * @throws DAOException if exception in connection pool occurs
	 */
	User findUserByLogin(String userLogin) throws DAOException;

	/**
	 * Finds administrator by its user identifier
	 * 
	 * @param userId user identifier of the administrator to find
	 * @return administrator object if it was found, otherwise null
	 * @throws DAOException if exception in connection pool occurs
	 */
	Admin findAdminById(int userId) throws DAOException;

	/**
	 * Finds administrator by its login
	 * 
	 * @param login login of the administrator to find
	 * @return administrator object if it was found, otherwise null
	 * @throws DAOException if exception in connection pool occurs
	 */
	Admin findAdminByLogin(String login) throws DAOException;

	/**
	 * Finds client by its user identifier
	 * 
	 * @param id user identifier of the client to find
	 * @return client object if it was found, otherwise null
	 * @throws DAOException if exception in connection pool occurs
	 */
	Client findClientByUserId(int id) throws DAOException;

	/**
	 * Finds clients by its login
	 * 
	 * @param login login of the client to find
	 * @return client object if it was found, otherwise null
	 * @throws DAOException if exception in connection pool occurs
	 */
	Client findClientByLogin(String login) throws DAOException;

	/**
	 * Finds client by its client identifier
	 * 
	 * @param id client identifier of the client to find
	 * @return client object if it was found, otherwise null
	 * @throws DAOException if exception in connection pool occurs
	 */
	Client findClientByClientId(int id) throws DAOException;

	/**
	 * Finds clients by its passport identifier
	 * 
	 * @param passportId passport identifier of the client to find
	 * @return client object if it was found, otherwise null
	 * @throws DAOException if exception in connection pool occurs
	 */
	Client findClientByPassportId(String passportId) throws DAOException;

	/**
	 * Finds blacklist client by its client identifier
	 * 
	 * @param id client identifier of the blacklist client to find
	 * @return BlackListClient object if it was found, otherwise null
	 * @throws DAOException if exception in connection pool occurs
	 */
	BlackListClient findInBlacklistById(int id) throws DAOException;

	/**
	 * Finds regular client by its client identifier
	 * 
	 * @param id client identifier of the regular client to find
	 * @return RegularClient object if it was found, otherwise null
	 * @throws DAOException if exception in connection pool occurs
	 */
	RegularClient findInRegularCustomersById(int id) throws DAOException;

	/**
	 * Edits user data based on user identifier
	 * 
	 * @param userId user identifier of the user to update
	 * @param user user object with data to update (if you do not update some field, fill it with initial data)
	 * @throws DAOException if exception in connection pool occurs
	 */
	void updateUser(int userId, User user) throws DAOException; 

	/**
	 * Edits administrator data based on user identifier
	 * 
	 * @param userId user identifier of the user to update
	 * @param user administrator object with data to update (if you do not update some field, fill it with initial data)
	 * @throws DAOException if exception in connection pool occurs
	 */
	void updateAdmin(int userId, Admin user) throws DAOException; 

	/**
	 * Edits client data based on client identifier
	 * 
	 * @param clientId client identifier of the client to update
	 * @param client client object with data to update (if you do not update some field, fill it with initial data)
	 * @throws DAOException if exception in connection pool occurs
	 */
	void updateClient(int clientId, Client client) throws DAOException; 

	/**
	 * Edits blacklist client data based on client identifier
	 * 
	 * @param clientId client identifier of the client to update
	 * @param client blacklist client object with data to update (if you do not update some field, fill it with initial data)
	 * @throws DAOException if exception in connection pool occurs
	 */
	void updateBlacklistClient(int clientId, BlackListClient client) throws DAOException;

	/**
	 * Edits regular client data based on client identifier
	 * 
	 * @param clientId client identifier of the client to update
	 * @param client regular client object with data to update (if you do not update some field, fill it with initial data)
	 * @throws DAOException if exception in connection pool occurs
	 */
	void updateRegularClient(int clientId, RegularClient client) throws DAOException;

	/**
	 * Edits user's password based on login
	 * 
	 * @param login login of the user to update
	 * @param password new password
	 * @throws DAOException if exception in connection pool occurs
	 */
	void updatePassword(String login, String password) throws DAOException; 
	
	/**
	 * Edits client's user identifier based on client identifier
	 * 
	 * @param clientId client identifier of the client to update
	 * @param newUserId new user identifier to set
	 * @throws DAOException if exception in connection pool occurs
	 */
	void updateClientsUserId(int clientId, int newUserId) throws DAOException; 

	/**
	 * Deletes user account 
	 * 
	 * @param userId identifier of the user to delete
	 * @throws DAOException if exception in connection pool occurs
	 */
	void deleteUser(int userId) throws DAOException; 

	/**
	 * Deletes client 
	 * 
	 * @param clientId identifier of the client to delete
	 * @throws DAOException if exception in connection pool occurs
	 */
	void deleteClient(int clientId) throws DAOException;

	/**
	 * Deletes client 
	 * 
	 * @param clientId identifier of the client to delete from blacklist
	 * @throws DAOException if exception in connection pool occurs
	 */
	void deleteFromBlackList(int clientId) throws DAOException;

	/**
	 * Deletes client from regular customers
	 * 
	 * @param clientId identifier of the client to delete from regular customers
	 * @throws DAOException if exception in connection pool occurs
	 */
	void deleteFromRegularCustomers(int clientId) throws DAOException;

	/**
	 * Gets user identifier of the specific user based on login
	 * 
	 * @param login user login whose identifier to get
	 * @return user identifier
	 * @throws DAOException if exception in connection pool occurs
	 */
	int getUserId(String login) throws DAOException;

	/**
	 * Gets Role of the specific user based on login
	 * 
	 * @param login user login whose role to get
	 * @return Role of the user
	 * @throws DAOException if exception in connection pool occurs
	 */
	Role getUserRole(String login) throws DAOException;

	/**
	 * Gets client identifier of the specific user based on passport identifier
	 * 
	 * @param passportId passport identifier of the client whose identifier to get
	 * @return client identifier
	 * @throws DAOException if exception in connection pool occurs
	 */
	int getClientId(String passportId) throws DAOException;

}
