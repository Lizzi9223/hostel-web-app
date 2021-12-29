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
import by.epam.tc.web.service.*;

public class RegistrationCommand implements Command{
	
	private UserService userService = null;
	
	public RegistrationCommand() {
		try {
			userService = ServiceFactory.getInstance().getUserService();
		} catch (Exception e) {
			//TODO
		}
	}
	  
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
				client = userService.signUp(client);
				//request.getSession().setAttribute("client", client);
				request.getSession().setAttribute("role", "CLIENT");
				request.getSession().setAttribute("login", login);
				request.getSession().setAttribute("name", client.getFirstName());
				request.getSession().setAttribute("surname", client.getLastName());
				request.getSession().setAttribute("passportId", client.getPassportId());
				request.getSession().setAttribute("dateOfBith", client.getBirthDate());
				request.getSession().setAttribute("country", client.getCountry());
				request.getSession().setAttribute("phone", client.getPhoneNumber());
				request.getSession().setAttribute("email", client.getEmail());
			}
			else {
				name = request.getParameter("name");
				String photo = request.getParameter("photo");				
				admin = new Admin(login, password, name, photo);
				password = null;
				admin = userService.signUp(admin);
				request.getSession().setAttribute("role", "ADMIN");
				request.getSession().setAttribute("login", login);
				request.getSession().setAttribute("name", admin.getName());
				request.getSession().setAttribute("photo", admin.getPhotoPath());
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/myAccount.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			response.sendRedirect("Controller?command=GO_TO_REGISTRATION_PAGE&errorMessage="+errorMessage);
		}
	}
}
