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
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.UserService;

public class EditCommand implements Command {

	private UserService userService = null;
	
	public EditCommand() {
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
		String errorMessage = "Issues while edit. Try again";		
		
		try {
			String login = request.getParameter("login");	
			String passportId = request.getParameter("passportId");
			
			if(passportId != null) { 
				name = request.getParameter("name");
				String surname = request.getParameter("surname");
				LocalDate dateOfBith = LocalDate.parse(request.getParameter("dateOfBith"));
				String country = request.getParameter("country");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");				
				client = new Client(login, name, surname, passportId, dateOfBith, 
						country, phone, email);				
				client = userService.edit(client, 
						request.getSession().getAttribute("login").toString(),
						request.getSession().getAttribute("passportId").toString());				
				request.getSession().setAttribute("login", login);
				request.getSession().setAttribute("name", name);
				request.getSession().setAttribute("surname", surname);
				request.getSession().setAttribute("passportId", passportId);
				request.getSession().setAttribute("dateOfBith", dateOfBith);
				request.getSession().setAttribute("country", country);
				request.getSession().setAttribute("phone", phone);
				request.getSession().setAttribute("email", email);
			}
			else {
				name = request.getParameter("name");
				String photo = request.getParameter("photo");				
				admin = new Admin(login, name, photo);				
				admin = userService.edit(admin, 
						request.getSession().getAttribute("login").toString());
				request.getSession().setAttribute("login", login);
				request.getSession().setAttribute("name", name);
				request.getSession().setAttribute("photo", photo);
			}			
		} catch (ServiceException e) {
			// TODO: handle exception
		}
		
		if(client!=null || admin!=null) {			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/myAccount.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("Controller?command=GO_TO_MY_ACCOUNT_PAGE&errorMessage="+errorMessage);
		}
	}

}
