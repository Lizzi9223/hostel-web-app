package by.epam.tc.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.tc.web.dao.DAOException;
import by.epam.tc.web.dao.DAOFactory;
import by.epam.tc.web.dao.UserDAO;
import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.BlackListClient;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.RegularClient;
import by.epam.tc.web.entity.user.User;
import by.epam.tc.web.service.UserService;
import by.epam.tc.web.service.exception.LoginAlreadyExistsException;
import by.epam.tc.web.service.exception.PassportIdAlreadyExistsException;
import by.epam.tc.web.service.exception.ServiceException;
import by.epam.tc.web.service.validator.UserValidator;

/** 
 * The class {@code UserServiceImpl} implements {@code UserService}
 * 
 * @author Lizzi9223
 *
 */
public class UserServiceImpl implements UserService {
	private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

	public UserServiceImpl() {
	}

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
	public boolean signUp(Admin admin) throws ServiceException, LoginAlreadyExistsException {
		int userId = 0;
		try {
			if (!UserValidator.isValidAdmin(admin) || !UserValidator.isValidPassword(admin.getPassword())) {
				return false;
			}
			if (userDAO.findUserByLogin(admin.getLogin()) != null) {
				throw new LoginAlreadyExistsException();
			}
			userId = userDAO.addUser(admin);
			admin.setUserId(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}

	@Override
	public boolean signUp(Client client)
			throws ServiceException, LoginAlreadyExistsException, PassportIdAlreadyExistsException {
		int clientId = 0;
		try {
			if (!UserValidator.isValidUserClient(client) || !UserValidator.isValidPassword(client.getPassword())) {
				return false;
			}
			if (userDAO.findUserByLogin(client.getLogin()) != null) {
				throw new LoginAlreadyExistsException();
			}
			if (userDAO.findClientByPassportId(client.getPassportId()) != null) {
				throw new PassportIdAlreadyExistsException();
			}
			clientId = userDAO.addUserClient(client);
			client.setClientId(clientId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}

	@Override
	public boolean addClient(Client client) throws ServiceException, PassportIdAlreadyExistsException {
		try {
			if (!UserValidator.isValidClient(client)) {
				return false;
			}
			if (userDAO.findClientByPassportId(client.getPassportId()) != null) {
				throw new PassportIdAlreadyExistsException();
			}
			if (client.getLogin() != null && !client.getLogin().equals("")) {
				int userId = userDAO.getUserId(client.getLogin());
				client.setUserId(userId);
			}
			userDAO.addClient(client);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}

	@Override
	public boolean addUser(User user) throws ServiceException, LoginAlreadyExistsException {
		try {
			if (!UserValidator.isValidLogin(user.getLogin()) || !UserValidator.isValidPassword(user.getPassword())) {
				return false;
			}
			if (userDAO.findUserByLogin(user.getLogin()) != null) {
				throw new LoginAlreadyExistsException();
			}
			userDAO.addUser(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}

	@Override
	public void addBlacklistClient(BlackListClient client) throws ServiceException {
		try {
			userDAO.addToBlackList(client);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addRegularClient(RegularClient client) throws ServiceException {
		try {
			userDAO.addToRegularCustomers(client);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean edit(Admin admin, String login) throws ServiceException, LoginAlreadyExistsException {
		try {
			if (!UserValidator.isValidAdmin(admin)) {
				return false;
			}
			if (!login.equals(admin.getLogin()) && userDAO.findUserByLogin(admin.getLogin()) != null) {
				throw new LoginAlreadyExistsException();
			}
			int userId = userDAO.getUserId(login);
			userDAO.updateUser(userId, admin);
			userDAO.updateAdmin(userId, admin);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}

	@Override
	public boolean edit(Client client, String login, String passportId)
			throws ServiceException, LoginAlreadyExistsException, PassportIdAlreadyExistsException {
		try {
			if (!UserValidator.isValidUserClient(client)) {
				return false;
			}
			if (!login.equals(client.getLogin()) && userDAO.findUserByLogin(client.getLogin()) != null) {
				throw new LoginAlreadyExistsException();
			}
			if (!passportId.equals(client.getPassportId())
					&& userDAO.findClientByPassportId(client.getPassportId()) != null) {
				throw new PassportIdAlreadyExistsException();
			}
			int userId = userDAO.getUserId(login);
			userDAO.updateUser(userId, client);
			int clientId = userDAO.getClientId(passportId);
			userDAO.updateClient(clientId, client);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}

	@Override
	public boolean edit(Client client, int clientId)
			throws ServiceException, PassportIdAlreadyExistsException {
		try {
			if (!UserValidator.isValidClient(client)) {
				return false;
			}
			Client initialClient = userDAO.findClientByClientId(clientId);
			String passportId = initialClient.getPassportId();
			if (!passportId.equals(client.getPassportId())
					&& userDAO.findClientByPassportId(client.getPassportId()) != null) {
				throw new PassportIdAlreadyExistsException();
			}
			userDAO.updateClient(clientId, client);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}

	@Override
	public boolean editPassword(String login, String password) throws ServiceException {
		try {
			if (!UserValidator.isValidPassword(password)) {
				return false;
			}
			userDAO.updatePassword(login, password);
			password = null;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}

	@Override
	public void editClientsUserId(int clientId, int newUserId) throws ServiceException {
		try {
			userDAO.updateClientsUserId(clientId, newUserId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void editBlacklistClient(int clientId, BlackListClient client) throws ServiceException {
		try {
			userDAO.updateBlacklistClient(clientId, client);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void editRegularClient(int clientId, RegularClient client) throws ServiceException {
		try {
			userDAO.updateRegularClient(clientId, client);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Admin findAdminById(int id) throws ServiceException {
		Admin admin = null;
		try {
			admin = userDAO.findAdminById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return admin;
	}

	@Override
	public Admin findAdminByLogin(String login) throws ServiceException {
		Admin admin = null;
		try {
			admin = userDAO.findAdminByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return admin;
	}

	@Override
	public User findUserById(int id) throws ServiceException {
		User user = null;
		try {
			user = userDAO.findUserById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public Client findClientByLogin(String login) throws ServiceException {
		Client client = null;
		try {
			client = userDAO.findClientByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return client;
	}

	@Override
	public Client findClientById(int id) throws ServiceException {
		Client client = null;
		try {
			client = userDAO.findClientByClientId(id);
			User user = userDAO.findUserById(client.getUserId());
			if (user != null) {
				client.setLogin(user.getLogin());
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return client;
	}

	@Override
	public Client findClientByUserId(int id) throws ServiceException {
		Client client = null;
		try {
			client = userDAO.findClientByUserId(id);
			User user = userDAO.findUserById(client.getUserId());
			if (user != null) {
				client.setLogin(user.getLogin());
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return client;
	}

	@Override
	public Client findClientByPassportId(String passportId) throws ServiceException {
		Client client = null;
		try {
			client = userDAO.findClientByPassportId(passportId);
			if(client != null) {
				User user = userDAO.findUserById(client.getUserId());
				if (user != null) {
					client.setLogin(user.getLogin());
				}
			}			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return client;
	}

	@Override
	public BlackListClient findBlackListClientByClientId(int id) throws ServiceException {
		BlackListClient blackListClient = null;
		try {
			blackListClient = userDAO.findInBlacklistById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return blackListClient;
	}

	@Override
	public RegularClient findRegularClientByClientId(int id) throws ServiceException {
		RegularClient regularClient = null;
		try {
			regularClient = userDAO.findInRegularCustomersById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return regularClient;
	}

	@Override
	public void deleteAccount(String login) throws ServiceException {
		try {
			int userId = userDAO.getUserId(login);
			userDAO.deleteUser(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteFromBlacklist(int clientId) throws ServiceException {
		try {
			userDAO.deleteFromBlackList(clientId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteFromRegularCustomers(int clientId) throws ServiceException {
		try {
			userDAO.deleteFromRegularCustomers(clientId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Client> getAllClients() throws ServiceException {
		List<Client> clients = new ArrayList<Client>();
		try {
			clients = userDAO.getAllClients();
			for (Client client : clients) {
				User user = userDAO.findUserById(client.getUserId());
				if (user != null) {
					client.setLogin(user.getLogin());
				}
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

	@Override
	public List<Admin> getAllAdmins() throws ServiceException {
		List<Admin> admins = new ArrayList<Admin>();
		try {
			admins = userDAO.getAllAdmins();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return admins;
	}

	@Override
	public List<BlackListClient> getAllBlackListClients() throws ServiceException {
		List<BlackListClient> blackListClients = new ArrayList<BlackListClient>();
		try {
			blackListClients = userDAO.getAllBlackListClients();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return blackListClients;
	}

	@Override
	public List<RegularClient> getAllRegularClients() throws ServiceException {
		List<RegularClient> regularClients = new ArrayList<RegularClient>();
		try {
			regularClients = userDAO.getAllRegularClients();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return regularClients;
	}

	@Override
	public boolean isInBlacklist(int clientId) throws ServiceException {
		try {
			BlackListClient blackListClient = userDAO.findInBlacklistById(clientId);
			if(blackListClient != null) {
				return true;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}		
		return false;
	}

	@Override
	public boolean isRegularCustomer(int clientId) throws ServiceException {
		try {
			RegularClient regularClient = userDAO.findInRegularCustomersById(clientId);
			if(regularClient != null) {
				return true;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}		
		return false;
	}

	@Override
	public int getUserIdByLogin(String login) throws ServiceException {
		try {
			return userDAO.getUserId(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}	
}
