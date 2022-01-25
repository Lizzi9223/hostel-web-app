package by.epam.tc.web.controller.impl;

import java.io.IOException;
import java.time.LocalDate;

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

public class SubmitBookingCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.SubmitBookingCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate fromDate = LocalDate.parse(request.getParameter(Utility.FROM_DATE));
		LocalDate toDate = LocalDate.parse(request.getParameter(Utility.TO_DATE));
		int guestsNumber = Integer.parseInt(request.getParameter(Utility.GUESTS_NUMBER));
		int roomNumber = -1;
		if(request.getParameter(Utility.ROOM_NUMBER)!=null) {
			roomNumber = Integer.parseInt(request.getParameter(Utility.ROOM_NUMBER));
		}
		try {
			if(request.getParameter(Utility.EDITED_BOOKING_ID)!=null 
					&& !request.getParameter(Utility.EDITED_BOOKING_ID).equals(Utility.EMPTY)) {
				int id = Integer.parseInt(request.getParameter(Utility.EDITED_BOOKING_ID));
				Booking booking = ServiceFactory.getInstance().getStaysService().getBookingById(id);
				booking.setFromDate(fromDate);
				booking.setToDate(toDate);
				booking.setGuestsCount(guestsNumber);
				if(roomNumber>0) {
					booking.setRoomNumber(roomNumber);
				}
				booking.setApproved(true);
				booking.setApproveDate(LocalDate.now());
				ServiceFactory.getInstance().getStaysService().updateBooking(id, booking);
			}else {
				String userLogin = (String)request.getSession().getAttribute(Utility.LOGIN);
				ServiceFactory.getInstance().getStaysService().addBooking(
						userLogin, fromDate, toDate, guestsNumber, roomNumber);
			}	
			response.sendRedirect(Redirect.TO_BOOKINGS_PAGE);
		} catch (ServiceException e) {
			logger.error("error while submitting booking", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}	
		
	}
	
}
