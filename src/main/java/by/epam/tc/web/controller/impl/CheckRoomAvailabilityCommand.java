package by.epam.tc.web.controller.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.entity.room.Room;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class CheckRoomAvailabilityCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate fromDate = LocalDate.parse(request.getParameter("fromDate"));
		LocalDate toDate = LocalDate.parse(request.getParameter("toDate"));
		int guestsNumber = Integer.parseInt(request.getParameter("guestsNumber"));
		int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
		List<Room> availableRooms = new ArrayList<Room>();
		Room room = null;
		try {			
			if(request.getParameter("checkAmongAllRooms")!=null) {
				availableRooms = ServiceFactory.getInstance().getStaysService().areAvailablePlaces(fromDate, toDate, guestsNumber);				
			}
			else {				
				if(ServiceFactory.getInstance().getStaysService().areAvailablePlaces(roomNumber, fromDate, toDate, guestsNumber)) {					
					availableRooms.add(ServiceFactory.getInstance().getRoomService().getRoomByNumber(roomNumber));
				}
			}
			room = ServiceFactory.getInstance().getRoomService().getRoomByNumber(roomNumber);
			request.setAttribute("room", room);
		} catch (ServiceException e) {
			// TODO: handle exception
		}
		if(availableRooms.size()>0) {
			request.setAttribute("availableRooms", availableRooms);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bookRoom.jsp");
			request.setAttribute("fromDate", fromDate);
			request.setAttribute("toDate", toDate);
			request.setAttribute("guestsNumber", guestsNumber);			
			request.setAttribute("checkResult", true);
			dispatcher.forward(request, response);	
		}
		else {
			request.setAttribute("checkResult", false);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bookRoom.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
