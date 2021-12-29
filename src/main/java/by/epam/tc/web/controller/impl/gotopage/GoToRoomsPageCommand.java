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
import by.epam.tc.web.service.ServiceFactory;

public class GoToRoomsPageCommand implements Command {
	
	private RoomService roomService = null;
	
	public GoToRoomsPageCommand() {
		try {
			roomService = ServiceFactory.getInstance().getRoomService();
		} catch (Exception e) {
			//TODO
		}
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Room> rooms = roomService.getAllRooms();
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
		}
		rooms.removeAll(roomsToRemove);
		roomsToRemove.clear();
		if(request.getParameter("searchGender") != null) {
			for (Room room : rooms) {
				if((!room.getGender().equals("")) && (!room.getGender().equals(request.getParameter("searchGender")))) {
					roomsToRemove.add(room);
				}					
			}
			rooms.removeAll(roomsToRemove);
			roomsToRemove.clear();
		}
		if(request.getParameter("searchBathroom") != null) {
			for (Room room : rooms) {
				if(!room.isBathroomInRoom()) {
					roomsToRemove.add(room);
				}					
			}
			rooms.removeAll(roomsToRemove);
			roomsToRemove.clear();
		}
		request.setAttribute("selected_rooms", rooms);
		
		request.setAttribute("minCost", roomService.getMinCost());
		request.setAttribute("maxCost", roomService.getMaxCost());
		request.setAttribute("minCapacity", roomService.getMinCapacity());
		request.setAttribute("maxCapacity", roomService.getMaxCapacity());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rooms.jsp");
		dispatcher.forward(request, response);		
	}

}
