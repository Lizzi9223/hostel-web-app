package by.epam.tc.web.controller.impl.gotopage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Forward;
import by.epam.tc.web.controller.constant.Redirect;
import by.epam.tc.web.controller.constant.Utility;
import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.Role;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

public class GoToMyAccountPageCommand implements Command{
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.gotopage.GoToMyAccountPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute(Utility.ROLE) != null) {
			Role role = Role.valueOf(request.getSession().getAttribute(Utility.ROLE).toString());			
			String login = request.getSession().getAttribute(Utility.LOGIN).toString();
			try {
				if(role == Role.CLIENT) {
					Client client = ServiceFactory.getInstance().getUserService().findClientByLogin(login);
					request.setAttribute(Utility.CLIENT, client);
				}else {
					Admin admin = ServiceFactory.getInstance().getUserService().findAdminByLogin(login);
					request.setAttribute(Utility.ADMIN, admin);
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher(Forward.TO_ACCOUNT_PAGE);
				dispatcher.forward(request, response);
			}
			catch (ServiceException e) {
				request.getSession().removeAttribute(Utility.ROLE);
				request.getSession().removeAttribute(Utility.LOGIN);
				logger.error("error while going to account page", e);
				response.sendRedirect(Redirect.TO_ERROR_PAGE);
			}
		}else {
			response.sendRedirect(Redirect.TO_LOGINATION_PAGE);
		}
	}
}
