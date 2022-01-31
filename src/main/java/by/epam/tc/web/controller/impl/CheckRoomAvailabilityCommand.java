package by.epam.tc.web.controller.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.CommandName;
import by.epam.tc.web.controller.constant.Forward;
import by.epam.tc.web.controller.constant.Redirect;
import by.epam.tc.web.controller.constant.Utility;
import by.epam.tc.web.entity.room.Room;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

public class CheckRoomAvailabilityCommand implements Command {
	private static final Logger logger = LogManager
			.getLogger(by.epam.tc.web.controller.impl.CheckRoomAvailabilityCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			LocalDate fromDate = LocalDate.parse(request.getParameter(Utility.FROM_DATE));
			LocalDate toDate = LocalDate.parse(request.getParameter(Utility.TO_DATE));
			int guestsNumber = Integer.parseInt(request.getParameter(Utility.GUESTS_NUMBER));
			int roomNumber = -1;
			if (request.getParameter(Utility.ROOM_NUMBER) != null) {
				roomNumber = Integer.parseInt(request.getParameter(Utility.ROOM_NUMBER));
				Room room = ServiceFactory.getInstance().getRoomService().getRoomByNumber(roomNumber);
				request.setAttribute(Utility.ROOM, room);
			}
			int bookingId = 0;
			if (request.getParameter(Utility.BOOKING_ID) != null) {
				bookingId = Integer.parseInt(request.getParameter(Utility.BOOKING_ID));
				request.setAttribute(Utility.EDITED_BOOKING_ID, request.getParameter(Utility.BOOKING_ID));
			}

			List<Room> availableRooms = new ArrayList<Room>();
			if (request.getParameter(Utility.CHECK_AMONG_ALL_ROOMS) != null) {
				availableRooms = ServiceFactory.getInstance().getStaysService().areAvailablePlaces(fromDate, toDate,
						guestsNumber, bookingId, 0);
			} else {
				if (ServiceFactory.getInstance().getStaysService().areAvailablePlaces(roomNumber, fromDate, toDate,
						guestsNumber, bookingId, 0)) {
					availableRooms.add(ServiceFactory.getInstance().getRoomService().getRoomByNumber(roomNumber));
				}
			}

			if (availableRooms.size() > 0) {
				request.setAttribute(Utility.AVAILABLE_ROOMS, availableRooms);
				request.setAttribute(Utility.FROM_DATE, fromDate);
				request.setAttribute(Utility.TO_DATE, toDate);
				request.setAttribute(Utility.GUESTS_NUMBER, guestsNumber);
				request.setAttribute(Utility.CHECK_RESULT, true);
			} else {
				request.setAttribute(Utility.CHECK_RESULT, false);
			}

			if (request.getParameter(Utility.COMMAND).equals(CommandName.ADD_BOOKING)
					|| request.getParameter(Utility.COMMAND).equals(CommandName.EDIT_BOOKING_CHECK)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(Forward.TO_BOOKINGS_PAGE);
				dispatcher.forward(request, response);
			} else if (request.getParameter(Utility.COMMAND).equals(CommandName.ADD_STAY)) {
				request.setAttribute(Utility.CREATE, true);
				RequestDispatcher dispatcher = request.getRequestDispatcher(Forward.TO_STAYS_PAGE);
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(Forward.TO_BOOK_ROOM_PAGE);
				dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			logger.error("error while checking room availability", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
