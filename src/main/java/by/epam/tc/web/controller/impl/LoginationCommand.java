package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Message;
import by.epam.tc.web.controller.constant.Redirect;
import by.epam.tc.web.controller.constant.Utility;
import by.epam.tc.web.entity.user.User;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

public class LoginationCommand implements Command{
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.LoginationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(Utility.LOGIN);
		String password = request.getParameter(Utility.PASSWORD);
		User user = null;
		try {
			user = ServiceFactory.getInstance().getUserService().signIn(login, password);
			password = null;
			if(user!=null) {
				request.getSession().setAttribute(Utility.ROLE, user.getRole().toString());
				request.getSession().setAttribute(Utility.LOGIN, login);
				response.sendRedirect(Redirect.TO_ACCOUNT_PAGE);
			}
			else {
				request.getSession().setAttribute(Utility.ERROR, Message.LOGINATION);
				response.sendRedirect(Redirect.TO_LOGINATION_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("error while authorization", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}
}
