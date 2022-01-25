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
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

public class ChooseBookingCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.ChooseBookingCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter(Utility.CHOSEN_BOOKING_ID));			
		try {
			Booking booking = ServiceFactory.getInstance().getStaysService().getBookingById(id);
			request.getSession().setAttribute(Utility.CHOSEN_BOOKING_ID, id);
			request.getSession().setAttribute(Utility.CHOSEN_BOOKING_IS_APPROVED, booking.isApproved());
			request.getSession().setAttribute(Utility.CHOSEN_BOOKING_IS_PAID, booking.isPaid());
			request.getSession().setAttribute(Utility.POPUP_VIEW, Utility.OPTIONS);
			response.sendRedirect(Redirect.TO_BOOKINGS_PAGE);
		}
		catch (ServiceException e) {
			logger.error("error while choosing booking", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
