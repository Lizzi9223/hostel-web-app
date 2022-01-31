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
import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.entity.stay.Stay;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

public class SubmitStayCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.SubmitStayCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getParameter(Utility.COMMAND).equals(CommandName.GUESTS_ARRIVED)) {
				int bookingId = Integer.parseInt(request.getParameter(Utility.BOOKING_ID));
				Booking booking = ServiceFactory.getInstance().getStaysService().getBookingById(bookingId);
				List<Integer> clients = new ArrayList<>();
				request.getSession().setAttribute(Utility.NEW_STAY_FROM_DATE, booking.getFromDate());
				request.getSession().setAttribute(Utility.NEW_STAY_TO_DATE, booking.getToDate());
				request.getSession().setAttribute(Utility.NEW_STAY_GUESTS_NUMBER, booking.getGuestsCount());
				request.getSession().setAttribute(Utility.NEW_STAY_ROOM_NUMBER, booking.getRoomNumber());
				request.getSession().setAttribute(Utility.GUESTS_ARRIVED_BOOKING_ID, booking.getId());
				request.getSession().setAttribute(Utility.NEW_STAY_GUESTS_TO_ADD, clients);
				response.sendRedirect(Redirect.TO_CLIENTS_PAGE);
			} else if (request.getParameter(Utility.EDITED_STAY_ID) != null
					&& !request.getParameter(Utility.EDITED_STAY_ID).equals(Utility.EMPTY)) {
				LocalDate toDate = LocalDate.parse(request.getParameter(Utility.TO_DATE));
				String notes = request.getParameter(Utility.NOTES);
				int id = Integer.parseInt(request.getParameter(Utility.EDITED_STAY_ID));
				Stay stay = ServiceFactory.getInstance().getStaysService().getStayById(id);
				stay.setToDate(toDate);
				stay.setNotes(notes);
				ServiceFactory.getInstance().getStaysService().updateStay(id, stay);
				response.sendRedirect(Redirect.TO_STAYS_PAGE);
			} else {
				LocalDate fromDate = LocalDate.parse(request.getParameter(Utility.FROM_DATE));
				LocalDate toDate = LocalDate.parse(request.getParameter(Utility.TO_DATE));
				int guestsNumber = Integer.parseInt(request.getParameter(Utility.GUESTS_NUMBER));
				int roomNumber = Integer.parseInt(request.getParameter(Utility.ROOM_NUMBER));
				List<Integer> clients = new ArrayList<>();
				request.getSession().setAttribute(Utility.NEW_STAY_FROM_DATE, fromDate);
				request.getSession().setAttribute(Utility.NEW_STAY_TO_DATE, toDate);
				request.getSession().setAttribute(Utility.NEW_STAY_GUESTS_NUMBER, guestsNumber);
				request.getSession().setAttribute(Utility.NEW_STAY_ROOM_NUMBER, roomNumber);
				request.getSession().setAttribute(Utility.NEW_STAY_GUESTS_TO_ADD, clients);
				response.sendRedirect(Redirect.TO_CLIENTS_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("error while submitting stay", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
