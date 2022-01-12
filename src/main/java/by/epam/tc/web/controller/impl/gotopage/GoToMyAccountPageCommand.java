package by.epam.tc.web.controller.impl.gotopage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.Role;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class GoToMyAccountPageCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("role") != null) {
			Role role = Role.valueOf(request.getSession().getAttribute("role").toString());			
			String login = request.getSession().getAttribute("login").toString();
			try {
				if(role == Role.CLIENT) {
					Client client = ServiceFactory.getInstance().getUserService().findClientByLogin(login);
					request.setAttribute("client", client);
				}else {
					Admin admin = ServiceFactory.getInstance().getUserService().findAdminByLogin(login);
					request.setAttribute("admin", admin);
				}
			}
			catch (ServiceException e) {
				request.getSession().removeAttribute("role");
				request.getSession().removeAttribute("login");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/myAccount.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect("Controller?command=GO_TO_LOGINATION_PAGE");
		}
	}
}
