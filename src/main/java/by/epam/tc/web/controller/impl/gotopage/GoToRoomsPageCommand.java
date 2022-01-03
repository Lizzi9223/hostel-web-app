package by.epam.tc.web.controller.impl.gotopage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.entity.room.Room;
import by.epam.tc.web.service.RoomService;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class GoToRoomsPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int minCost = ServiceFactory.getInstance().getRoomService().getMinCost();
			int maxCost = ServiceFactory.getInstance().getRoomService().getMaxCost();
			int minCapacity = ServiceFactory.getInstance().getRoomService().getMinCapacity();
			int maxCapacity = ServiceFactory.getInstance().getRoomService().getMaxCapacity();
			
			request.setAttribute("minCost", minCost);
			request.setAttribute("maxCost", maxCost);
			request.setAttribute("minCapacity", minCapacity);
			request.setAttribute("maxCapacity", maxCapacity);
			
			List<Room> rooms = new ArrayList<Room>(ServiceFactory.getInstance().getRoomService().getAllRooms());
			List<Room> roomsToRemove = new ArrayList<Room>();
			if(request.getParameter("price-left") != null) {
				for (Room room : rooms) {
					if(room.getCost() < Integer.parseInt(request.getParameter("price-left")) 
							|| room.getCost() > Integer.parseInt(request.getParameter("price-right"))
							|| room.getCapacity() < Integer.parseInt(request.getParameter("capacity-left"))
							|| room.getCapacity() > Integer.parseInt(request.getParameter("capacity-right"))) {
						roomsToRemove.add(room);
					}					
				}
				request.setAttribute("currentMinCost", request.getParameter("price-left"));
				request.setAttribute("currentMaxCost", request.getParameter("price-right"));
				request.setAttribute("currentMinCapacity", request.getParameter("capacity-left"));
				request.setAttribute("currentMaxCapacity", request.getParameter("capacity-right"));
			}
			else {
				request.setAttribute("currentMinCost", minCost);
				request.setAttribute("currentMaxCost", maxCost);
				request.setAttribute("currentMinCapacity", minCapacity);
				request.setAttribute("currentMaxCapacity", maxCapacity);
			}
			rooms.removeAll(roomsToRemove);
			roomsToRemove.clear();
			
			if(request.getParameter("searchGender") != null) {
				for (Room room : rooms) {
					if((!room.getGender().equals("")) && (!room.getGender().equals(request.getParameter("searchGender")))) {
						roomsToRemove.add(room);
					}					
				}
				request.setAttribute("currentGender", request.getParameter("searchGender"));
				rooms.removeAll(roomsToRemove);
				roomsToRemove.clear();
			}
			
			if(request.getParameter("searchBathroom") != null) {
				for (Room room : rooms) {
					if(!room.isBathroomInRoom()) {
						roomsToRemove.add(room);
					}					
				}
				request.setAttribute("currentIsBathroom", true);
				rooms.removeAll(roomsToRemove);
				roomsToRemove.clear();
			}
			
			request.setAttribute("selected_rooms", rooms);
			
		} catch (ServiceException e) {
			// TODO: handle exception
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rooms.jsp");
		dispatcher.forward(request, response);	
	}

}
