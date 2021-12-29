package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class BookRoomCommand implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
		try {
			request.setAttribute("room", ServiceFactory.getInstance().getRoomService().getRoomByNumber(roomNumber));
			request.getSession().setAttribute("allRooms", ServiceFactory.getInstance().getRoomService().getAllRooms());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bookRoom.jsp");
		dispatcher.forward(request, response);	
	}

}
