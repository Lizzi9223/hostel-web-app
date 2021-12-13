package by.epam.tc.web.controller.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.entity.user.User;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.UserService;

public class LoginationCommand implements Command{
	
	private UserService userService = null;
	
	public LoginationCommand() {
		try {
			userService = ServiceFactory.getInstance().getUserService();
		} catch (Exception e) {
			//WHAT TO DO
		}
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		User user = null;
		String errorMessage = "No such login or password. Please check correctness and try again";
		try {
			user = userService.signIn(login, password);
		} catch (ServiceException e) {
			// TODO: handle exception
		}
		
		if(user!=null) {
			request.getSession().setAttribute("role", user.getRole().toString());
			request.setAttribute("login", login);
			request.setAttribute("info", "You signed in!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("Controller?command=GO_TO_LOGINATION_PAGE&errorMessage="+errorMessage);
		}
		
	}
}
