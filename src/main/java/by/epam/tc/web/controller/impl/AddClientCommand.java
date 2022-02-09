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
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.PassportIdAlreadyExistsException;
import by.epam.tc.web.service.exception.ServiceException;

/** 
 * The class {@code AddClientCommand} implements {@code Command}
 * 
 * @author Lizzi9223
 *
 */
public class AddClientCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.AddClientCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter(Utility.NAME);
			String surname = request.getParameter(Utility.SURNAME);
			String passportId = request.getParameter(Utility.PASSPORT_ID);
			LocalDate dateOfBith = LocalDate.parse(request.getParameter(Utility.DATE_OF_BIRTH));
			String country = request.getParameter(Utility.COUNTRY);
			String phone = request.getParameter(Utility.PHONE);
			String email = request.getParameter(Utility.EMAIL);
			Client client = new Client(name, surname, passportId, dateOfBith, country, phone, email);
			if (ServiceFactory.getInstance().getUserService().addClient(client)) {
				response.sendRedirect(Redirect.TO_CLIENTS_PAGE);
			} else {
				logger.info("Validation failed while adding new client (by admin)");
				request.getSession().setAttribute(Utility.ERROR, Message.VALIDATION);
				response.sendRedirect(Redirect.TO_CLIENTS_PAGE);
			}
		} catch (PassportIdAlreadyExistsException e) {
			logger.warn("Passport ID already exists");
			request.getSession().setAttribute(Utility.ERROR, Message.PASSPORT_EXISTS);
			response.sendRedirect(Redirect.TO_CLIENTS_PAGE);
		} catch (ServiceException e) {
			logger.error("error while adding new client", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
