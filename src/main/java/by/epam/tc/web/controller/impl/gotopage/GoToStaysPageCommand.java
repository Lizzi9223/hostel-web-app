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
import by.epam.tc.web.entity.stay.Stay;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class GoToStaysPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Stay> stays = null;
		try {			
			if(request.getSession().getAttribute("role").toString().equals("ADMIN")) {
				stays = new LinkedList<Stay>(ServiceFactory.getInstance().getStaysService().getAllStays());				
			}
			else {
				String userLogin = (String)request.getSession().getAttribute("login");	
				stays = new LinkedList<Stay>(ServiceFactory.getInstance().getStaysService().getAllUserStays(userLogin));
			}
			Collections.sort(stays, 
				     Comparator.comparing(Stay::getFromDate, Comparator.reverseOrder()));
			request.setAttribute("stays", stays);
		} catch (ServiceException e) {
			// TODO: handle exception
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/stays.jsp");
		dispatcher.forward(request, response);
	}

}
