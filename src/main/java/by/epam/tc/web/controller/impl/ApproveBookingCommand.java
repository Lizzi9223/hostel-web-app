package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class ApproveBookingCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("bookingId")!=null) {
			int id = Integer.parseInt(request.getParameter("bookingId"));
			boolean isApproved = Boolean.parseBoolean(request.getParameter("approve"));
			try {
				ServiceFactory.getInstance().getStaysService().approveBooking(id, isApproved);
			}
			catch (ServiceException e) {
				// TODO: handle exception
			}			
		}
		response.sendRedirect("Controller?command=GO_TO_BOOKINGS_PAGE");
	}

}
