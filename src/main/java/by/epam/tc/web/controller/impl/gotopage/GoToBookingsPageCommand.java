package by.epam.tc.web.controller.impl.gotopage;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class GoToBookingsPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Booking> bookings = null;
		try {			
			if(request.getSession().getAttribute("role").toString().equals("ADMIN")) {
				bookings = new LinkedList<Booking>(ServiceFactory.getInstance().getStaysService().getAllBookings());				
			}
			else {
				String userLogin = (String)request.getSession().getAttribute("login");	
				bookings = new LinkedList<Booking>(ServiceFactory.getInstance().getStaysService().getAllUserBookings(userLogin));
			}
			Collections.sort(bookings, 
				     Comparator.comparing(Booking::isApproved, Comparator.nullsFirst(Comparator.naturalOrder())));
			request.setAttribute("bookings", bookings);
		} catch (ServiceException e) {
			// TODO: handle exception
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bookings.jsp");
		dispatcher.forward(request, response);
	}

}
