package by.epam.tc.web.controller.impl;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
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
			String login = request.getParameter(Constant.Utility.LOGIN);	
			String passportId = request.getParameter(Constant.Utility.PASSPORT_ID);
			
			if(passportId != null) { 
				String name = request.getParameter(Constant.Utility.NAME);
				String surname = request.getParameter(Constant.Utility.SURNAME);
				LocalDate dateOfBith = LocalDate.parse(request.getParameter(Constant.Utility.DATE_OF_BIRTH));
				String country = request.getParameter(Constant.Utility.COUNTRY);
				String phone = request.getParameter(Constant.Utility.PHONE);
				String email = request.getParameter(Constant.Utility.EMAIL);				
				Client client = new Client(login, name, surname, passportId, dateOfBith, 
						country, phone, email);				
				client = ServiceFactory.getInstance().getUserService().edit(client, 
						request.getSession().getAttribute(Constant.Utility.LOGIN).toString(),
						request.getSession().getAttribute(Constant.Utility.PASSPORT_ID).toString());				
				request.getSession().setAttribute(Constant.Utility.LOGIN, login);
			}
			else {
				String name = request.getParameter(Constant.Utility.NAME);
				String photo = request.getParameter(Constant.Utility.PHOTO);				
				Admin admin = new Admin(login, name, photo);				
				admin = ServiceFactory.getInstance().getUserService().edit(admin, 
						request.getSession().getAttribute(Constant.Utility.LOGIN).toString());
				request.getSession().setAttribute(Constant.Utility.LOGIN, login);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.Forward.TO_ACCOUNT_PAGE);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error("error while editing account", e);
			response.sendRedirect(Constant.Redirect.TO_ERROR_PAGE);
		}
	}

}
