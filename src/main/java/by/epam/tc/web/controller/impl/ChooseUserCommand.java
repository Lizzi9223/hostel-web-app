package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Redirect;
import by.epam.tc.web.controller.constant.Utility;
import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.entity.stay.Stay;
import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.Role;
import by.epam.tc.web.entity.user.User;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

public class ChooseUserCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.ChooseUserCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Client client = null;
			if(request.getParameter(Utility.BOOKING_ID)!= null) {
				int bookingId = Integer.parseInt(request.getParameter(Utility.BOOKING_ID));
				Booking booking = ServiceFactory.getInstance().getStaysService().getBookingById(bookingId);
				User user = ServiceFactory.getInstance().getUserService().findUserById(booking.getUserId());
				if(user.getRole().equals(Role.CLIENT)) {
					client = ServiceFactory.getInstance().getUserService().findClientByUserId(booking.getUserId());
					if(ServiceFactory.getInstance().getUserService().isInBlacklist(client.getClientId())) {
						client.setInBlackList(true);
					}
					if(ServiceFactory.getInstance().getUserService().isRegularCustomer(client.getClientId())) {
						client.setRegularCustomer(true);
					}
					request.getSession().setAttribute(Utility.CHOSEN_CLIENT, client);
					request.getSession().setAttribute(Utility.CHOSEN_CLIENT_ID, client.getClientId());
					request.getSession().setAttribute(Utility.POPUP_VIEW, Utility.OPTIONS);
					response.sendRedirect(Redirect.TO_CLIENTS_PAGE);
				}else {
					Admin admin = ServiceFactory.getInstance().getUserService().findAdminByLogin(user.getLogin());
					request.getSession().setAttribute(Utility.CHOSEN_ADMIN, admin);
					request.getSession().setAttribute(Utility.CHOSEN_ADMIN_ID, admin.getUserId());
					request.getSession().setAttribute(Utility.POPUP_VIEW, Utility.OPTIONS);
					response.sendRedirect(Redirect.TO_ADMINS_PAGE);
				}	
			}else {
				int stayId = Integer.parseInt(request.getParameter(Utility.STAY_ID));
				Stay stay = ServiceFactory.getInstance().getStaysService().getStayById(stayId);
				client = ServiceFactory.getInstance().getUserService().findClientById(stay.getClientId());
				if(ServiceFactory.getInstance().getUserService().isInBlacklist(client.getClientId())) {
					client.setInBlackList(true);
				}
				if(ServiceFactory.getInstance().getUserService().isRegularCustomer(client.getClientId())) {
					client.setRegularCustomer(true);
				}
				request.getSession().setAttribute(Utility.CHOSEN_CLIENT, client);
				request.getSession().setAttribute(Utility.CHOSEN_CLIENT_ID, client.getClientId());
				request.getSession().setAttribute(Utility.POPUP_VIEW, Utility.OPTIONS);
				response.sendRedirect(Redirect.TO_CLIENTS_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("error while choosing user", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
