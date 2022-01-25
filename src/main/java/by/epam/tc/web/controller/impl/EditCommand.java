package by.epam.tc.web.controller.impl;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Constant;
import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class EditCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.EditCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String passportId = request.getParameter(Constant.Utility.PASSPORT_ID);
			
			if(passportId != null) { 
				String login = request.getParameter(Constant.Utility.LOGIN);
				String name = request.getParameter(Constant.Utility.NAME);
				String surname = request.getParameter(Constant.Utility.SURNAME);
				LocalDate dateOfBith = LocalDate.parse(request.getParameter(Constant.Utility.DATE_OF_BIRTH));
				String country = request.getParameter(Constant.Utility.COUNTRY);
				String phone = request.getParameter(Constant.Utility.PHONE);
				String email = request.getParameter(Constant.Utility.EMAIL);				
				Client client = new Client(login, name, surname, passportId, dateOfBith, 
						country, phone, email);
				boolean isEdited = ServiceFactory.getInstance().getUserService().edit(client, 
						(String)request.getSession().getAttribute(Constant.Utility.LOGIN),
						(String)request.getSession().getAttribute(Constant.Utility.PASSPORT_ID));
				request.getSession().removeAttribute(Constant.Utility.PASSPORT_ID);
				if(isEdited) {
					request.getSession().setAttribute(Constant.Utility.LOGIN, login);					
				}else {
					logger.info("Validation failed while editing client");
					request.getSession().setAttribute(Constant.Utility.ERROR, Constant.Message.VALIDATION);
				}
			}
			else {
				String login = request.getParameter(Constant.Utility.LOGIN);
				String name = request.getParameter(Constant.Utility.NAME);
				String photo = request.getParameter(Constant.Utility.PHOTO);				
				Admin admin = new Admin(login, name, photo);				
				boolean isEdited = ServiceFactory.getInstance().getUserService().edit(admin, 
						request.getSession().getAttribute(Constant.Utility.LOGIN).toString());
				if(isEdited) {
					request.getSession().setAttribute(Constant.Utility.LOGIN, login);
				}else {
					logger.info("Validation failed while editing admin");
					request.getSession().setAttribute(Constant.Utility.ERROR, Constant.Message.VALIDATION);
				}
			}
			response.sendRedirect(Constant.Redirect.TO_ACCOUNT_PAGE);
		} catch (ServiceException e) {
			logger.error("error while editing account", e);
			response.sendRedirect(Constant.Redirect.TO_ERROR_PAGE);
		}
	}

}
