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
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class AddClientCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.AddClientCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	
			String name = request.getParameter(Constant.Utility.NAME);
			String surname = request.getParameter(Constant.Utility.SURNAME);
			String passportId = request.getParameter(Constant.Utility.PASSPORT_ID);
			LocalDate dateOfBith = LocalDate.parse(request.getParameter(Constant.Utility.DATE_OF_BIRTH));
			String country = request.getParameter(Constant.Utility.COUNTRY);
			String phone = request.getParameter(Constant.Utility.PHONE);
			String email = request.getParameter(Constant.Utility.EMAIL);				
			Client client = new Client(name, surname, passportId, dateOfBith, country, phone, email);
			ServiceFactory.getInstance().getUserService().addClient(client);
			response.sendRedirect(Constant.Redirect.TO_CLIENTS_PAGE);
		} catch (ServiceException e) {
			logger.error("error while adding new client", e);
			response.sendRedirect(Constant.Redirect.TO_ERROR_PAGE);
		}
	}

}
