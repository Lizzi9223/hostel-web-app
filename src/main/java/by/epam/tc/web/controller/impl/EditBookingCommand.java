package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Constant;
import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class EditBookingCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.EditBookingCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter(Constant.Utility.BOOKING_ID));			
		try {
			Booking booking = ServiceFactory.getInstance().getStaysService().getBookingById(id);
			request.getSession().setAttribute(Constant.Utility.BOOKING, booking);
			request.getSession().setAttribute(Constant.Utility.POPUP_VIEW, Constant.Utility.EDIT_BOOKING);
			response.sendRedirect(Constant.Redirect.TO_BOOKINGS_PAGE);
		}
		catch (ServiceException e) {
			logger.error("error while editing booking", e);
			response.sendRedirect(Constant.Redirect.TO_ERROR_PAGE);
		}	
	}

}
