package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class DeleteStayCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("stayId"));			
		try {
			ServiceFactory.getInstance().getStaysService().deleteStay(id);
		}
		catch (ServiceException e) {
			// TODO: handle exception
		}	
		
		response.sendRedirect("Controller?command=GO_TO_STAYS_PAGE");
	}

}
