package by.epam.tc.web.controller.impl;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Message;
import by.epam.tc.web.controller.constant.Redirect;
import by.epam.tc.web.controller.constant.Utility;
import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.Role;
import by.epam.tc.web.entity.user.User;
import by.epam.tc.web.service.*;
import by.epam.tc.web.service.exception.LoginAlreadyExistsException;
import by.epam.tc.web.service.exception.PassportIdAlreadyExistsException;
import by.epam.tc.web.service.exception.ServiceException;

/** 
 * The class {@code RegistrationCommand} implements {@code Command}
 * 
 * @author Lizzi9223
 *
 */
public class RegistrationCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.RegistrationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
			String login = request.getParameter(Utility.LOGIN);
			String password = request.getParameter(Utility.PASSWORD);
			String passportId = request.getParameter(Utility.PASSPORT_ID);

			if (passportId != null) {				
				String name = request.getParameter(Utility.NAME);
				String surname = request.getParameter(Utility.SURNAME);
				LocalDate dateOfBith = LocalDate.parse(request.getParameter(Utility.DATE_OF_BIRTH));
				String country = request.getParameter(Utility.COUNTRY);
				String phone = request.getParameter(Utility.PHONE);
				String email = request.getParameter(Utility.EMAIL);
				Client client = new Client(login, password, name, surname, passportId, dateOfBith, country, phone,
						email);	
				password = null;
				if(ServiceFactory.getInstance().getUserService().findClientByPassportId(passportId)!=null) {
					Client existingClient = ServiceFactory.getInstance().getUserService().findClientByPassportId(passportId);
					if(ServiceFactory.getInstance().getUserService().isInBlacklist(existingClient.getClientId())) {
						request.getSession().setAttribute(Utility.ERROR, Message.BLACKLIST_USER);
						response.sendRedirect(Redirect.TO_REGISTRATION_PAGE);
					}else{
						boolean isDoneSuccessfully = false;
						if(existingClient.getBirthDate().equals(dateOfBith)) {
							User user = new User(login, password, Role.CLIENT);
							isDoneSuccessfully = ServiceFactory.getInstance().getUserService().addUser(user);
							if(isDoneSuccessfully) {
								int userId = ServiceFactory.getInstance().getUserService().getUserIdByLogin(login);
								ServiceFactory.getInstance().getUserService().editClientsUserId(existingClient.getClientId(), userId);
								isDoneSuccessfully = ServiceFactory.getInstance().getUserService().edit(client, existingClient.getClientId());
							}else {
								logger.info("Validation failed while client registration");
								request.getSession().setAttribute(Utility.ERROR, Message.VALIDATION);
								response.sendRedirect(Redirect.TO_REGISTRATION_PAGE);
							}						
						}else {
							isDoneSuccessfully = ServiceFactory.getInstance().getUserService().signUp(client);
						}						
						if(isDoneSuccessfully){
							request.getSession().setAttribute(Utility.ROLE, Role.CLIENT.toString());
							request.getSession().setAttribute(Utility.LOGIN, login);
							response.sendRedirect(Redirect.TO_ACCOUNT_PAGE);
						} else {
							logger.info("Validation failed while client registration");
							request.getSession().setAttribute(Utility.ERROR, Message.VALIDATION);
							response.sendRedirect(Redirect.TO_REGISTRATION_PAGE);
						}
					}
					
				}else {
					if(ServiceFactory.getInstance().getUserService().signUp(client)){
						request.getSession().setAttribute(Utility.ROLE, Role.CLIENT.toString());
						request.getSession().setAttribute(Utility.LOGIN, login);
						response.sendRedirect(Redirect.TO_ACCOUNT_PAGE);
					} else {
						logger.info("Validation failed while client registration");
						request.getSession().setAttribute(Utility.ERROR, Message.VALIDATION);
						response.sendRedirect(Redirect.TO_REGISTRATION_PAGE);
					}
				}				
			} else {
				String name = request.getParameter(Utility.NAME);
				String photo = request.getParameter(Utility.PHOTO);
				Admin admin = new Admin(login, password, name, photo);
				password = null;
				boolean isSignedUp = ServiceFactory.getInstance().getUserService().signUp(admin);
				if (isSignedUp) {
					request.getSession().setAttribute(Utility.ROLE, Role.ADMIN.toString());
					request.getSession().setAttribute(Utility.LOGIN, login);
					response.sendRedirect(Redirect.TO_ACCOUNT_PAGE);
				} else {
					logger.info("Validation failed while admin registration");
					request.getSession().setAttribute(Utility.ERROR, Message.VALIDATION);
					response.sendRedirect(Redirect.CREATE_ADMIN);
				}
			}
		} catch (LoginAlreadyExistsException e) {
			logger.warn("Login already exists");
			request.getSession().setAttribute(Utility.ERROR, Message.LOGIN_EXISTS);
			response.sendRedirect(Redirect.TO_REGISTRATION_PAGE);
		} catch (PassportIdAlreadyExistsException e) {
			logger.warn("Passport ID already exists");
			request.getSession().setAttribute(Utility.ERROR, Message.PASSPORT_EXISTS);
			response.sendRedirect(Redirect.TO_REGISTRATION_PAGE);
		} catch (ServiceException e) {
			logger.warn("error while registration", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}
}
