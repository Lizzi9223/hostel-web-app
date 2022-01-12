package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.Role;
import by.epam.tc.web.entity.user.User;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class LoginationCommand implements Command{
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.LoginationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		User user = null;
		String errorMessage = "No such login or password. Please check correctness and try again";
		try {
			user = ServiceFactory.getInstance().getUserService().signIn(login, password);
			password = null;
		} catch (ServiceException e) {
			//logger.error(e); //?? DO NOT LOG
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}  
		
		if(user!=null) {
			request.getSession().setAttribute("role", user.getRole().toString());
			request.getSession().setAttribute("login", login);
			response.sendRedirect("Controller?command=GO_TO_MY_ACCOUNT_PAGE");
		}
		else {
			response.sendRedirect("Controller?command=GO_TO_LOGINATION_PAGE&errorMessage="+errorMessage);
		}
		
	}
}
