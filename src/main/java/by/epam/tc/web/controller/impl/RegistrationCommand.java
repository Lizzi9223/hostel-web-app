package by.epam.tc.web.controller.impl;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.Role;
import by.epam.tc.web.service.*;

public class RegistrationCommand implements Command{	  
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Client client = null;
		Admin admin = null;
		
		String name = "";
		String errorMessage = "Issues while registration. Try again";		
		
		try {
			String login = request.getParameter("login");
			String password = request.getParameter("password");		
			String passportId = request.getParameter("passportId");
			
			if(passportId != null) { 
				name = request.getParameter("name");
				String surname = request.getParameter("surname");
				LocalDate dateOfBith = LocalDate.parse(request.getParameter("dateOfBith"));
				String country = request.getParameter("country");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");				
				client = new Client(login, password, name, surname, passportId, dateOfBith, 
						country, phone, email);
				password = null;
				client = ServiceFactory.getInstance().getUserService().signUp(client);
				request.getSession().setAttribute("role", Role.CLIENT.toString());
				request.getSession().setAttribute("login", login);
			}
			else {
				name = request.getParameter("name");
				String photo = request.getParameter("photo");				
				admin = new Admin(login, password, name, photo);
				password = null;
				admin = ServiceFactory.getInstance().getUserService().signUp(admin);
				request.getSession().setAttribute("role", Role.ADMIN.toString());
				request.getSession().setAttribute("login", login);
			}			
			response.sendRedirect("Controller?command=GO_TO_MY_ACCOUNT_PAGE");
		} catch (ServiceException e) {
			response.sendRedirect("Controller?command=GO_TO_REGISTRATION_PAGE&errorMessage="+errorMessage);
		}
	}
}
