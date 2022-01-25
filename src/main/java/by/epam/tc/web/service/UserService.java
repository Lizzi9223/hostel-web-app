package by.epam.tc.web.service;

import java.util.List;

import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.User;

public interface UserService {
	User signIn(String login, String password) throws ServiceException;	
	boolean signUp(Admin admin) throws ServiceException;
    boolean signUp(Client client) throws ServiceException;
    boolean addClient(Client client) throws ServiceException;
    boolean edit(Admin admin, String login) throws ServiceException;
    boolean edit(Client client, String login, String passportId) throws ServiceException;
    boolean editPassword(String login, String password) throws ServiceException;    
    Admin findAdminByLogin(String login) throws ServiceException;
    Client findClientByLogin(String login) throws ServiceException;    
    void deleteAccount(String login) throws ServiceException;    
    List<Client> getAllClients() throws ServiceException;
    List<User> getAllUsers() throws ServiceException;
    List<Client> getAllClientUsers() throws ServiceException;
}
