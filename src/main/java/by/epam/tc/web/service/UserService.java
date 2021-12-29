package by.epam.tc.web.service;

import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.User;

public interface UserService {
	User signIn(String login, String password) throws ServiceException;
	
	Admin signUp(Admin admin) throws ServiceException;
    Client signUp(Client client) throws ServiceException;
    
    Admin edit(Admin admin, String login) throws ServiceException;
    Client edit(Client client, String login, String passportId) throws ServiceException;
    void editPassword(String login, String password) throws ServiceException;
    
    Admin findAdminByLogin(String login) throws ServiceException;
    Client findClientByLogin(String login) throws ServiceException;
    
    void deleteAccount(String login) throws ServiceException;
}
