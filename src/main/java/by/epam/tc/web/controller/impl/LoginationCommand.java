package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.Role;
import by.epam.tc.web.entity.user.User;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.UserService;

public class LoginationCommand implements Command{
	
	private UserService userService = null;
	
	public LoginationCommand() {
		try {
			userService = ServiceFactory.getInstance().getUserService();
		} catch (ServiceException e) {
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
			password = null;
		} catch (ServiceException e) {
			// TODO: handle exception
		}  
		
		if(user!=null) {
			request.getSession().setAttribute("role", user.getRole().toString());
			request.getSession().setAttribute("login", login);
			if(user.getRole() == Role.CLIENT) {
				try {
					Client client = userService.findClientByLogin(login);
					request.getSession().setAttribute("name", client.getFirstName());
					request.getSession().setAttribute("surname", client.getLastName());
					request.getSession().setAttribute("passportId", client.getPassportId());
					request.getSession().setAttribute("dateOfBith", client.getBirthDate());
					request.getSession().setAttribute("country", client.getCountry());
					request.getSession().setAttribute("phone", client.getPhoneNumber());
					request.getSession().setAttribute("email", client.getEmail());
				} catch (ServiceException e) {
					// TODO: handle exception
				}				
			}else {
				try {
					Admin admin = userService.findAdminByLogin(login);
					request.getSession().setAttribute("name", admin.getName());
					request.getSession().setAttribute("photo", admin.getPhotoPath());
				} catch (ServiceException e) {
					// TODO: handle exception
				}
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/myAccount.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("Controller?command=GO_TO_LOGINATION_PAGE&errorMessage="+errorMessage);
		}
		
	}
}
