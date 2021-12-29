package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.UserService;

public class ChangePasswordCommand implements Command {

	private UserService userService = null;
	
	public ChangePasswordCommand() {
		try {
			userService = ServiceFactory.getInstance().getUserService();
		} catch (Exception e) {
			//TODO
		}
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try {			
			String login = request.getSession().getAttribute("login").toString();
			String password = request.getParameter("initialPassword");
			if(userService.signIn(login, password)!=null) {
				password = request.getParameter("newPassword");
				userService.editPassword(login, password);
				password = null;
			}
			else {
				// TODO !!!!!!!!!!!!!!!!!!!!!!!!!!
			}
			password = null;
			response.sendRedirect("Controller?command=GO_TO_WELCOME_PAGE&logOut=true");
		} catch (ServiceException e) {
			// TODO: handle exception
		}
	}

}
