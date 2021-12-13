package by.epam.tc.web.dao;

import by.epam.tc.web.entity.user.*;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws DAOException;
    List<Admin> getAllAdmins() throws DAOException;
    List<Client> getAllClientUsers() throws DAOException;
    List<Client> getAllClients() throws DAOException;
    List<BlackListClient> getAllBlackListClients() throws DAOException;
    List<RegularClient> getAllRegularClients() throws DAOException;
    
    int addUser(Admin user) throws DAOException;
    int addUser(User user) throws DAOException;
    int addClient(Client client) throws DAOException;
    void addToBlackList(BlackListClient client) throws DAOException;
    void addToRegularCustomers(RegularClient client) throws DAOException;

    User findUserByLoginAndPassword(String login, String password) throws DAOException;
    User findUserById(int userId) throws DAOException;
    User findUserByLogin(String userLogin) throws DAOException;
    Admin findAdminById(int userId) throws DAOException;
    Client findClientByUserId(int id) throws DAOException;
    Client findClientByClientId(int id) throws DAOException;
    BlackListClient findInBlacklistById(int id) throws DAOException;
    RegularClient findInRegularCustomersById(int id) throws DAOException;

    void updateUser(int userId, User user) throws DAOException;
    void updateAdmin(int userId, Admin user) throws DAOException;
    void updateClient(int clientId, Client client) throws DAOException;
    void updateBlacklistClient(int clientId, BlackListClient client) throws  DAOException;
    void updateRegularClient(int clientId, RegularClient client) throws DAOException;

    void deleteUser(int userId) throws DAOException;
    void deleteClient(int clientId) throws DAOException;
    void deleteFromBlackList(int clientId) throws DAOException;
    void deleteFromRegularCustomers(int clientId) throws DAOException;

    int getUserId(String passportId) throws DAOException;
    int getClientId(String login) throws DAOException;
}
