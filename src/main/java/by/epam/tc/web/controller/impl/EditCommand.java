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
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.LoginAlreadyExistsException;
import by.epam.tc.web.service.exception.PassportIdAlreadyExistsException;
import by.epam.tc.web.service.exception.ServiceException;

public class EditCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.EditCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String passportId = request.getParameter(Utility.PASSPORT_ID);
			
			if(passportId != null) { 
				String login = request.getParameter(Utility.LOGIN);
				String name = request.getParameter(Utility.NAME);
				String surname = request.getParameter(Utility.SURNAME);
				LocalDate dateOfBith = LocalDate.parse(request.getParameter(Utility.DATE_OF_BIRTH));
				String country = request.getParameter(Utility.COUNTRY);
				String phone = request.getParameter(Utility.PHONE);
				String email = request.getParameter(Utility.EMAIL);				
				Client client = new Client(login, name, surname, passportId, dateOfBith, 
						country, phone, email);
				boolean isEdited = ServiceFactory.getInstance().getUserService().edit(client, 
						(String)request.getSession().getAttribute(Utility.LOGIN),
						(String)request.getSession().getAttribute(Utility.PASSPORT_ID));
				request.getSession().removeAttribute(Utility.PASSPORT_ID);
				if(isEdited) {
					request.getSession().setAttribute(Utility.LOGIN, login);					
				}else {
					logger.info("Validation failed while editing client");
					request.getSession().setAttribute(Utility.ERROR, Message.VALIDATION);
				}
			}
			else {
				String login = request.getParameter(Utility.LOGIN);
				String name = request.getParameter(Utility.NAME);
				String photo = request.getParameter(Utility.PHOTO);				
				Admin admin = new Admin(login, name, photo);				
				boolean isEdited = ServiceFactory.getInstance().getUserService().edit(admin, 
						request.getSession().getAttribute(Utility.LOGIN).toString());
				if(isEdited) {
					request.getSession().setAttribute(Utility.LOGIN, login);
				}else {
					logger.info("Validation failed while editing admin");
					request.getSession().setAttribute(Utility.ERROR, Message.VALIDATION);
				}
			}
			response.sendRedirect(Redirect.TO_ACCOUNT_PAGE);
		}catch (LoginAlreadyExistsException e) {
			logger.warn("Login already exists");
			request.getSession().setAttribute(Utility.ERROR, Message.LOGIN_EXISTS);
		} catch (PassportIdAlreadyExistsException e) {
			logger.warn("Passport ID already exists");
			request.getSession().setAttribute(Utility.ERROR, Message.PASSPORT_EXISTS);
		} catch (ServiceException e) {
			logger.error("error while editing account", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		} 
	}

}
