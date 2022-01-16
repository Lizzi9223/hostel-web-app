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
import by.epam.tc.web.controller.constant.Constant;
import by.epam.tc.web.entity.room.Room;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class GoToRoomsPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.gotopage.GoToRoomsPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			BigDecimal minCost = ServiceFactory.getInstance().getRoomService().getMinCost();
			BigDecimal maxCost = ServiceFactory.getInstance().getRoomService().getMaxCost();
			int minCapacity = ServiceFactory.getInstance().getRoomService().getMinCapacity();
			int maxCapacity = ServiceFactory.getInstance().getRoomService().getMaxCapacity();
			
			request.setAttribute(Constant.Utility.MIN_COST, minCost);
			request.setAttribute(Constant.Utility.MAX_COST, maxCost);
			request.setAttribute(Constant.Utility.MIN_CAPACITY, minCapacity);
			request.setAttribute(Constant.Utility.MAX_CAPACITY, maxCapacity);
			
			List<Room> rooms = new ArrayList<Room>(ServiceFactory.getInstance().getRoomService().getAllRooms());
			List<Room> roomsToRemove = new ArrayList<Room>();
			if(request.getParameter(Constant.Utility.PRICE_LEFT) != null) {
				for (Room room : rooms) {
					BigDecimal priceLeft = new BigDecimal(request.getParameter(Constant.Utility.PRICE_LEFT));
					BigDecimal priceRight = new BigDecimal(request.getParameter(Constant.Utility.PRICE_RIGHT));
					boolean priceLeftCompare = room.getCost().compareTo(priceLeft) < 0;
					boolean priceRightCompare = room.getCost().compareTo(priceRight) > 0;
					if(priceLeftCompare || priceRightCompare
							|| room.getCapacity() < Integer.parseInt(request.getParameter(Constant.Utility.CAPACITY_LEFT))
							|| room.getCapacity() > Integer.parseInt(request.getParameter(Constant.Utility.CAPACITY_RIGHT))) {
						roomsToRemove.add(room);
					}					
				}
				request.setAttribute(Constant.Utility.CURRENT_MIN_COST, request.getParameter(Constant.Utility.PRICE_LEFT));
				request.setAttribute(Constant.Utility.CURRENT_MAX_COST, request.getParameter(Constant.Utility.PRICE_RIGHT));
				request.setAttribute(Constant.Utility.CURRENT_MIN_CAPACITY, request.getParameter(Constant.Utility.CAPACITY_LEFT));
				request.setAttribute(Constant.Utility.MAX_CAPACITY, request.getParameter(Constant.Utility.CAPACITY_RIGHT));
			}
			else {
				request.setAttribute(Constant.Utility.CURRENT_MIN_COST, minCost);
				request.setAttribute(Constant.Utility.CURRENT_MAX_COST, maxCost);
				request.setAttribute(Constant.Utility.CURRENT_MIN_CAPACITY, minCapacity);
				request.setAttribute(Constant.Utility.MAX_CAPACITY, maxCapacity);
			}
			rooms.removeAll(roomsToRemove);
			roomsToRemove.clear();
			
			if(request.getParameter(Constant.Utility.SEARCH_GENDER) != null) {
				for (Room room : rooms) {
					if((!room.getGender().equals("")) && (!room.getGender().equals(request.getParameter(Constant.Utility.SEARCH_GENDER)))) {
						roomsToRemove.add(room);
					}					
				}
				request.setAttribute(Constant.Utility.CURRENT_GENDER, request.getParameter(Constant.Utility.SEARCH_GENDER));
				rooms.removeAll(roomsToRemove);
				roomsToRemove.clear();
			}
			
			if(request.getParameter(Constant.Utility.SEARCH_BATHROOM) != null) {
				for (Room room : rooms) {
					if(!room.isBathroomInRoom()) {
						roomsToRemove.add(room);
					}					
				}
				request.setAttribute(Constant.Utility.CURRENT_IS_BATHROOM, true);
				rooms.removeAll(roomsToRemove);
				roomsToRemove.clear();
			}
			
			request.setAttribute(Constant.Utility.SELECTED_ROOMS, rooms);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.Forward.TO_ROOMS_PAGE);
			dispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			logger.error("error while going to rooms page", e);
			response.sendRedirect(Constant.Redirect.TO_ERROR_PAGE);
		}	
	}

}
