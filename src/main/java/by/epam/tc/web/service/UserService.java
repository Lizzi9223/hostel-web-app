package by.epam.tc.web.service;

import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.User;

public interface UserService {
	User signIn(String login, String password) throws ServiceException;
	Admin signUp(Admin admin) throws ServiceException;
    Client signUp(Client client) throws ServiceException;
}
