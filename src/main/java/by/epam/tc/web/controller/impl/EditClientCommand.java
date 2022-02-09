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
 * The class {@code EditClientCommand} implements {@code Command}
 * 
 * @author Lizzi9223
 *
 */
public class EditClientCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.EditClientCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter(Utility.CHOSEN_CLIENT_ID));
			String name = request.getParameter(Utility.NAME);
			String surname = request.getParameter(Utility.SURNAME);
			String passportId = request.getParameter(Utility.PASSPORT_ID);
			LocalDate dateOfBith = LocalDate.parse(request.getParameter(Utility.DATE_OF_BIRTH));
			String country = request.getParameter(Utility.COUNTRY);
			String phone = request.getParameter(Utility.PHONE);
			String email = request.getParameter(Utility.EMAIL);
			Client client = new Client(name, surname, passportId, dateOfBith, country, phone, email);
			client.setClientId(id);
			if (ServiceFactory.getInstance().getUserService().edit(client, id)) {
				if(ServiceFactory.getInstance().getUserService().isInBlacklist(client.getClientId())) {
					logger.info("setInBlackList");
					client.setInBlackList(true);
				}
				if(ServiceFactory.getInstance().getUserService().isRegularCustomer(client.getClientId())) {
					logger.info("setRegularCustomer");
					client.setRegularCustomer(true);
				}
				request.getSession().setAttribute(Utility.CHOSEN_CLIENT, client);
				request.getSession().setAttribute(Utility.CHOSEN_CLIENT_ID, id);
				request.getSession().setAttribute(Utility.POPUP_VIEW, Utility.OPTIONS);
				response.sendRedirect(Redirect.TO_CLIENTS_PAGE);
			} else {
				logger.info("Validation failed while editing client (by admin)");
				request.getSession().setAttribute(Utility.ERROR, Message.VALIDATION);
				response.sendRedirect(Redirect.TO_CLIENTS_PAGE);
			}
		} catch (PassportIdAlreadyExistsException e) {
			logger.warn("Passport ID already exists");
			request.getSession().setAttribute(Utility.ERROR, Message.PASSPORT_EXISTS);
			response.sendRedirect(Redirect.TO_CLIENTS_PAGE);
		} catch (ServiceException e) {
			logger.error("error while editing client", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
