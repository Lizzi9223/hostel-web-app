package by.epam.tc.web.controller.impl.gotopage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.service.ServiceFactory;

public class GoToBookingsPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Booking> bookings = new ArrayList<Booking>();
		try {			
			if(request.getSession().getAttribute("role").toString().equals("ADMIN")) {
				bookings = ServiceFactory.getInstance().getStaysService().getAllBookings();
			}
			else {
				String userLogin = (String)request.getSession().getAttribute("login");
				bookings = ServiceFactory.getInstance().getStaysService().getAllUserBookings(userLogin);				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute("bookings", bookings);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bookings.jsp");
		dispatcher.forward(request, response);
	}

}
