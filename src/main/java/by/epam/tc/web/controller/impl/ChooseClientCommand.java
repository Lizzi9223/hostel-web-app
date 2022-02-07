package by.epam.tc.web.controller.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.CommandName;
import by.epam.tc.web.controller.constant.Redirect;
import by.epam.tc.web.controller.constant.Utility;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

public class ChooseClientCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.ChooseClientCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getParameter(Utility.COMMAND).toString().equals(CommandName.FINISH_CHOOSING_CLIENT)) {
				request.getSession().removeAttribute(Utility.NEW_STAY_FROM_DATE);
				request.getSession().removeAttribute(Utility.NEW_STAY_TO_DATE);
				request.getSession().removeAttribute(Utility.NEW_STAY_GUESTS_NUMBER);
				request.getSession().removeAttribute(Utility.NEW_STAY_ROOM_NUMBER);
				request.getSession().removeAttribute(Utility.NEW_STAY_GUESTS_TO_ADD);
				response.sendRedirect(Redirect.TO_STAYS_PAGE);
			} else {
				int id = Integer.parseInt(request.getParameter(Utility.CHOSEN_CLIENT_ID));
				if (request.getSession().getAttribute(Utility.NEW_STAY_GUESTS_NUMBER) != null
						&& (int) request.getSession().getAttribute(Utility.NEW_STAY_GUESTS_NUMBER) > 0) {
					List<Integer> clients = (ArrayList<Integer>) request.getSession()
							.getAttribute(Utility.NEW_STAY_GUESTS_TO_ADD);
					if (clients.contains(id)) {
						request.getSession().setAttribute(Utility.CLIENT_IS_ALREADY_ADDED, true);
						response.sendRedirect(Redirect.TO_CLIENTS_PAGE);
					} else {
						clients.add(id);
						request.getSession().setAttribute(Utility.NEW_STAY_GUESTS_TO_ADD, clients);
						int guestsNumber = (int) request.getSession().getAttribute(Utility.NEW_STAY_GUESTS_NUMBER);
						request.getSession().setAttribute(Utility.NEW_STAY_GUESTS_NUMBER, --guestsNumber);
						if (guestsNumber == 0) {
							for (int clientId : clients) {
								ServiceFactory.getInstance().getStaysService().addStay(clientId,
										(LocalDate) request.getSession().getAttribute(Utility.NEW_STAY_FROM_DATE),
										(LocalDate) request.getSession().getAttribute(Utility.NEW_STAY_TO_DATE),
										(int) request.getSession().getAttribute(Utility.NEW_STAY_ROOM_NUMBER),
										Utility.EMPTY);
							}
							request.getSession().removeAttribute(Utility.NEW_STAY_FROM_DATE);
							request.getSession().removeAttribute(Utility.NEW_STAY_TO_DATE);
							request.getSession().removeAttribute(Utility.NEW_STAY_GUESTS_NUMBER);
							request.getSession().removeAttribute(Utility.NEW_STAY_ROOM_NUMBER);
							request.getSession().removeAttribute(Utility.NEW_STAY_GUESTS_TO_ADD);
							if (request.getSession().getAttribute(Utility.GUESTS_ARRIVED_BOOKING_ID) != null) {
								int bookingId = (int) request.getSession()
										.getAttribute(Utility.GUESTS_ARRIVED_BOOKING_ID);
								ServiceFactory.getInstance().getStaysService().deleteBooking(bookingId);
								request.getSession().removeAttribute(Utility.GUESTS_ARRIVED_BOOKING_ID);
							}
							response.sendRedirect(Redirect.TO_STAYS_PAGE);
						} else {
							response.sendRedirect(Redirect.TO_CLIENTS_PAGE);
						}
					}
				} else {
					Client client = ServiceFactory.getInstance().getUserService().findClientById(id);
					if(ServiceFactory.getInstance().getUserService().isInBlacklist(client.getClientId())) {
						client.setInBlackList(true);
					}
					if(ServiceFactory.getInstance().getUserService().isRegularCustomer(client.getClientId())) {
						client.setRegularCustomer(true);
					}
					request.getSession().setAttribute(Utility.CHOSEN_CLIENT, client);
					request.getSession().setAttribute(Utility.CHOSEN_CLIENT_ID, id);
					request.getSession().setAttribute(Utility.POPUP_VIEW, Utility.OPTIONS);
					response.sendRedirect(Redirect.TO_CLIENTS_PAGE);
				}
			}
		} catch (ServiceException e) {
			logger.error("error while choosing client", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
