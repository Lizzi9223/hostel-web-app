package by.epam.tc.web.controller.impl.gotopage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Forward;
import by.epam.tc.web.controller.constant.Redirect;
import by.epam.tc.web.controller.constant.Utility;
import by.epam.tc.web.entity.room.Room;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

public class GoToRoomsPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.gotopage.GoToRoomsPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			BigDecimal minCost = ServiceFactory.getInstance().getRoomService().getMinCost();
			BigDecimal maxCost = ServiceFactory.getInstance().getRoomService().getMaxCost();
			int minCapacity = ServiceFactory.getInstance().getRoomService().getMinCapacity();
			int maxCapacity = ServiceFactory.getInstance().getRoomService().getMaxCapacity();
			
			request.setAttribute(Utility.MIN_COST, minCost);
			request.setAttribute(Utility.MAX_COST, maxCost);
			request.setAttribute(Utility.MIN_CAPACITY, minCapacity);
			request.setAttribute(Utility.MAX_CAPACITY, maxCapacity);
			
			List<Room> rooms = new ArrayList<Room>(ServiceFactory.getInstance().getRoomService().getAllRooms());
			List<Room> roomsToRemove = new ArrayList<Room>();
			if(request.getParameter(Utility.PRICE_LEFT) != null) {
				for (Room room : rooms) {
					BigDecimal priceLeft = new BigDecimal(request.getParameter(Utility.PRICE_LEFT));
					BigDecimal priceRight = new BigDecimal(request.getParameter(Utility.PRICE_RIGHT));
					boolean priceLeftCompare = room.getCost().compareTo(priceLeft) < 0;
					boolean priceRightCompare = room.getCost().compareTo(priceRight) > 0;
					if(priceLeftCompare || priceRightCompare
							|| room.getCapacity() < Integer.parseInt(request.getParameter(Utility.CAPACITY_LEFT))
							|| room.getCapacity() > Integer.parseInt(request.getParameter(Utility.CAPACITY_RIGHT))) {
						roomsToRemove.add(room);
					}					
				}
				request.setAttribute(Utility.CURRENT_MIN_COST, request.getParameter(Utility.PRICE_LEFT));
				request.setAttribute(Utility.CURRENT_MAX_COST, request.getParameter(Utility.PRICE_RIGHT));
				request.setAttribute(Utility.CURRENT_MIN_CAPACITY, request.getParameter(Utility.CAPACITY_LEFT));
				request.setAttribute(Utility.MAX_CAPACITY, request.getParameter(Utility.CAPACITY_RIGHT));
			}
			else {
				request.setAttribute(Utility.CURRENT_MIN_COST, minCost);
				request.setAttribute(Utility.CURRENT_MAX_COST, maxCost);
				request.setAttribute(Utility.CURRENT_MIN_CAPACITY, minCapacity);
				request.setAttribute(Utility.MAX_CAPACITY, maxCapacity);
			}
			rooms.removeAll(roomsToRemove);
			roomsToRemove.clear();
			
			if(request.getParameter(Utility.SEARCH_GENDER) != null) {
				for (Room room : rooms) {
					if((!room.getGender().equals("")) && (!room.getGender().equals(request.getParameter(Utility.SEARCH_GENDER)))) {
						roomsToRemove.add(room);
					}					
				}
				request.setAttribute(Utility.CURRENT_GENDER, request.getParameter(Utility.SEARCH_GENDER));
				rooms.removeAll(roomsToRemove);
				roomsToRemove.clear();
			}
			
			if(request.getParameter(Utility.SEARCH_BATHROOM) != null) {
				for (Room room : rooms) {
					if(!room.isBathroomInRoom()) {
						roomsToRemove.add(room);
					}					
				}
				request.setAttribute(Utility.CURRENT_IS_BATHROOM, true);
				rooms.removeAll(roomsToRemove);
				roomsToRemove.clear();
			}
			
			request.setAttribute(Utility.SELECTED_ROOMS, rooms);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(Forward.TO_ROOMS_PAGE);
			dispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			logger.error("error while going to rooms page", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}	
	}

}
