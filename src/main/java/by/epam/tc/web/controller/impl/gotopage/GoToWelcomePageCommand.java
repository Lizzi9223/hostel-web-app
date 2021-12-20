package by.epam.tc.web.controller.impl.gotopage;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.tc.web.controller.Command;

public class GoToWelcomePageCommand implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		if(request.getParameter("logOut")!=null) {
			request.getSession().removeAttribute("role");
			request.getSession().removeAttribute("login");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp");
		dispatcher.forward(request, response);
		
	}

}