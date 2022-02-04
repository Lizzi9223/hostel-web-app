package by.epam.tc.test.DAOTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import by.epam.tc.web.dao.DAOException;
import by.epam.tc.web.dao.DAOFactory;
import by.epam.tc.web.dao.UserDAO;
import by.epam.tc.web.dao.database.connection_pool.ConnectionPool;
import by.epam.tc.web.dao.database.connection_pool.ConnectionPoolException;
import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.BlackListClient;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.RegularClient;
import by.epam.tc.web.entity.user.Role;
import by.epam.tc.web.entity.user.User;

import by.epam.tc.test.DAOTest.testData.UserDAOTestData;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDAOTest {	
//	private static final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
//	
//	@BeforeClass
//	public static void setUp() throws SQLException, ConnectionPoolException {
//		ConnectionPool connectionPool = ConnectionPool.getInstance();
//		connectionPool.initPoolData();
//	}
//	
//	@Test
//	public void test1GetAllUsers() throws DAOException {
//		List<User> users = userDAO.getAllUsers();
//		List<User> expectedAllUsers = new ArrayList<User>(UserDAOTestData.expectedAdmins);
//		expectedAllUsers.addAll(UserDAOTestData.expectedClientUsers);
//		assertEquals(expectedAllUsers, users);		
//	}
//	
//	@Test
//	public void test2GetAllAdmins() throws DAOException {
//		List<Admin> admins = userDAO.getAllAdmins();
//		assertEquals(UserDAOTestData.expectedAdmins, admins);	
//	}
//	
//	@Test
//	public void test3GetAllClientUsers() throws DAOException {
//		List<Client> clients = userDAO.getAllClientUsers();
//		assertEquals(UserDAOTestData.expectedClientUsers, clients);	
//	}
//	
//	@Test
//	public void test4GetAllClients() throws DAOException {
//		List<Client> clients = userDAO.getAllClients();
//		assertEquals(UserDAOTestData.expectedClients, clients);	
//	}
//	
//	@Test
//	public void test5GetAllBlackListClients() throws DAOException {
//		List<BlackListClient> blackListClients = userDAO.getAllBlackListClients();
//		assertEquals(UserDAOTestData.expectedBlackListClients, blackListClients);
//	}
//	
//	@Test
//	public void test6GetAllRegularClients() throws DAOException {
//		List<RegularClient> regularClients = userDAO.getAllRegularClients();
//		assertEquals(UserDAOTestData.expectedRegularClients, regularClients);
//	}
//	
//	@Test
//	public void test7AddUser() throws DAOException {
//		int id = userDAO.addUser(UserDAOTestData.adminToAdd);
//		Admin admin = userDAO.findAdminByLogin(UserDAOTestData.adminToAdd.getLogin());
//		assertEquals(id, admin.getUserId());
//	}
//	
//	@Test
//	public void test8AddUser() throws DAOException {
//		int id = userDAO.addUser(UserDAOTestData.userToAdd);
//		User user = userDAO.findUserByLogin(UserDAOTestData.userToAdd.getLogin());
//		assertEquals(id, user.getUserId());
//	}
//	
//	@Test
//	public void test9AddClient() throws DAOException {
//		int id = userDAO.addClient(UserDAOTestData.clientToAdd);
//		Client client = userDAO.findClientByClientId(id);
//		assertEquals(UserDAOTestData.clientToAdd.getPassportId(), client.getPassportId());
//	}
//		
//	@Test
//	public void test10AddUserClient() throws DAOException {
//		int id = userDAO.addUserClient(UserDAOTestData.userClientToAdd);
//		Client userClient = userDAO.findClientByClientId(id);
//		assertEquals(UserDAOTestData.userClientToAdd.getPassportId(), userClient.getPassportId());
//	}
//	
//	@Test
//	public void test11AddToBlackList() throws DAOException {
//		userDAO.addToBlackList(UserDAOTestData.blackListClientToAdd);
//		BlackListClient blackListClient = userDAO.findInBlacklistById(UserDAOTestData.blackListClientToAdd.getClientId());
//		assertEquals(UserDAOTestData.blackListClientToAdd.getClientId(), blackListClient.getClientId());
//	}
//	
//	@Test
//	public void test12AddToRegularCustomers() throws DAOException {
//		userDAO.addToRegularCustomers(UserDAOTestData.regularClientToAdd);
//		RegularClient regularClient = userDAO.findInRegularCustomersById(UserDAOTestData.regularClientToAdd.getClientId());
//		assertEquals(UserDAOTestData.regularClientToAdd.getClientId(), regularClient.getClientId());
//	}
//	
//	@Test
//	public void test13FindUserByLoginAndPassword() throws DAOException{
//		User user = userDAO.findUserByLoginAndPassword(UserDAOTestData.userToAdd.getLogin(), UserDAOTestData.userToAdd.getPassword());
//		assertEquals(UserDAOTestData.userToAdd.getLogin(), user.getLogin());
//	}
//	
//	@Test
//	public void test14FindUserById() throws DAOException{
//		User user = userDAO.findUserById(UserDAOTestData.userToFind.getUserId());
//		assertEquals(UserDAOTestData.userToFind, user);
//	}
//	
//	@Test
//	public void test15FindUserByLogin() throws DAOException {
//		User user = userDAO.findUserByLogin(UserDAOTestData.userToFind.getLogin());
//		assertEquals(UserDAOTestData.userToFind.getLogin(), user.getLogin());	
//	}
//	
//	@Test
//	public void test16FindAdminById() throws DAOException{
//		Admin admin = userDAO.findAdminById(UserDAOTestData.adminToUpdate.getUserId());
//		assertEquals(UserDAOTestData.adminToUpdate, admin);
//	}
//	
//	@Test
//	public void test17FindAdminByLogin() throws DAOException{
//		Admin admin = userDAO.findAdminByLogin(UserDAOTestData.adminToUpdate.getLogin());
//		assertEquals(UserDAOTestData.adminToUpdate, admin);
//	}
//	
//	@Test
//	public void test18FindClientByUserId() throws DAOException{
//		Client client = userDAO.findClientByUserId(UserDAOTestData.userClientToFind.getUserId());
//		assertEquals(UserDAOTestData.userClientToFind, client);
//	}
//	
//	@Test
//	public void test19FindClientByLogin() throws DAOException{
//		Client client = userDAO.findClientByLogin(UserDAOTestData.userClientToFind.getLogin());
//		assertEquals(UserDAOTestData.userClientToFind, client);
//	}
//	
//	@Test
//	public void test20FindClientByClientId() throws DAOException{
//		Client client = userDAO.findClientByClientId(UserDAOTestData.clientToUpdate.getClientId());
//		assertEquals(UserDAOTestData.clientToUpdate, client);
//	}
//	
//	@Test
//	public void test21FindClientByPassportId() throws DAOException{
//		Client client = userDAO.findClientByPassportId(UserDAOTestData.clientToUpdate.getPassportId());
//		assertEquals(UserDAOTestData.clientToUpdate, client);
//	}
//	
//	@Test
//	public void test22FindInBlacklistById() throws DAOException{
//		BlackListClient blackListClient = userDAO.findInBlacklistById(UserDAOTestData.blackListClientToUpdate.getClientId());
//		assertEquals(UserDAOTestData.blackListClientToUpdate, blackListClient);
//	}
//	
//	@Test
//	public void test23FindInRegularCustomersById() throws DAOException{
//		RegularClient regularClient = userDAO.findInRegularCustomersById(UserDAOTestData.regularClientToUpdate.getClientId());
//		assertEquals(UserDAOTestData.regularClientToUpdate, regularClient);
//	}
//	
//	@Test
//	public void test24UpdateUser() throws DAOException{
//		userDAO.updateUser(UserDAOTestData.userToUpdateUser.getUserId(), UserDAOTestData.userToUpdateUser);
//		User user = userDAO.findUserById(UserDAOTestData.userToUpdateUser.getUserId());
//		assertEquals(UserDAOTestData.userToUpdateUser, user);
//	}
//	
//	@Test
//	public void test25UpdateAdmin() throws DAOException{
//		userDAO.updateAdmin(UserDAOTestData.adminToUpdate.getUserId(), UserDAOTestData.adminToUpdate);
//		Admin admin = userDAO.findAdminById(UserDAOTestData.adminToUpdate.getUserId());
//		assertEquals(UserDAOTestData.adminToUpdate, admin);
//	}
//	
//	@Test
//	public void test26UpdateClient() throws DAOException{
//		userDAO.updateClient(UserDAOTestData.clientToUpdate.getClientId(), UserDAOTestData.clientToUpdate);
//		Client client = userDAO.findClientByClientId(UserDAOTestData.clientToUpdate.getClientId());
//		assertEquals(UserDAOTestData.clientToUpdate, client);
//	}
//	
//	@Test
//	public void test27UpdateBlacklistClient() throws DAOException{
//		userDAO.updateBlacklistClient(UserDAOTestData.blackListClientToUpdate.getClientId(), UserDAOTestData.blackListClientToUpdate);
//		BlackListClient blackListClient = userDAO.findInBlacklistById(UserDAOTestData.blackListClientToUpdate.getClientId());
//		assertEquals(UserDAOTestData.blackListClientToUpdate, blackListClient);
//	}
//	
//	@Test
//	public void test28UpdateRegularClient() throws DAOException{
//		userDAO.updateRegularClient(UserDAOTestData.regularClientToUpdate.getClientId(), UserDAOTestData.regularClientToUpdate);
//		RegularClient regularClient = userDAO.findInRegularCustomersById(UserDAOTestData.regularClientToUpdate.getClientId());
//		assertEquals(UserDAOTestData.regularClientToUpdate, regularClient);
//	}
//	
//	@Test
//	public void test29UpdatePassword() throws DAOException{
//		userDAO.updatePassword(UserDAOTestData.userToUpdate.getLogin(), UserDAOTestData.passwordToUpdate);
//		User user = userDAO.findUserByLoginAndPassword(UserDAOTestData.userToUpdate.getLogin(), UserDAOTestData.passwordToUpdate);
//		assertEquals(UserDAOTestData.userToUpdate.getLogin(), user.getLogin());
//	}
//	
//	@Test
//	public void test30GetUserId() throws DAOException{
//		int id = userDAO.getUserId(UserDAOTestData.userToUpdate.getLogin());
//		assertEquals(UserDAOTestData.userToUpdate.getUserId(), id);
//	}
//	
//	@Test
//	public void test31GetUserRole() throws DAOException{
//		Role role = userDAO.getUserRole(UserDAOTestData.userToUpdate.getLogin());
//		assertEquals(UserDAOTestData.userToUpdate.getRole(), role);		
//	}
//	
//	@Test
//	public void test32GetClientId() throws DAOException{
//		int id = userDAO.getClientId(UserDAOTestData.clientToUpdate.getPassportId());
//		assertEquals(UserDAOTestData.clientToUpdate.getClientId(), id);		
//	}
//	
//	@Test
//	public void test33DeleteUser() throws DAOException{
//		userDAO.deleteUser(UserDAOTestData.userToUpdate.getUserId());
//		User user = userDAO.findUserById(UserDAOTestData.userToUpdate.getUserId());
//		assertNull(user);		
//	}
//	
//	@Test
//	public void test34DeleteClient() throws DAOException{
//		userDAO.deleteClient(UserDAOTestData.clientToUpdate.getClientId());
//		Client client = userDAO.findClientByClientId(UserDAOTestData.clientToUpdate.getClientId());
//		assertNull(client);
//	}
//	
//	@Test
//	public void test35DeleteFromBlackList() throws DAOException{
//		userDAO.deleteFromBlackList(UserDAOTestData.blackListClientToUpdate.getClientId());
//		BlackListClient blackListClient = userDAO.findInBlacklistById(UserDAOTestData.blackListClientToUpdate.getClientId());
//		assertNull(blackListClient);
//	}
//	
//	@Test
//	public void test36DeleteFromRegularCustomers() throws DAOException{
//		userDAO.deleteFromRegularCustomers(UserDAOTestData.regularClientToUpdate.getClientId());
//		RegularClient regularClient = userDAO.findInRegularCustomersById(UserDAOTestData.regularClientToUpdate.getClientId());
//		assertNull(regularClient);
//	}
//	
//	@AfterClass
//	public static void destroy() throws SQLException {
//		ConnectionPool.getInstance().dispose();
//	}
}
