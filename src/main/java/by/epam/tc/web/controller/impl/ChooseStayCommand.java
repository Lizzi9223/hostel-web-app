package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.entity.stay.Stay;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class ChooseStayCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("chosenStayId"));			
		try {
			Stay stay = ServiceFactory.getInstance().getStaysService().getStayById(id);
			request.getSession().setAttribute("chosenStayId", id);
			request.getSession().setAttribute("popUpView", "options");
		}
		catch (ServiceException e) {
			// TODO: handle exception
		}	
		
		response.sendRedirect("Controller?command=GO_TO_STAYS_PAGE");
	}

}
