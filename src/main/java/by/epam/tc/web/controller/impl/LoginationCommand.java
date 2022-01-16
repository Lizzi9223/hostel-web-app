package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Constant;
import by.epam.tc.web.entity.user.User;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class LoginationCommand implements Command{
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.LoginationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(Constant.Utility.LOGIN);
		String password = request.getParameter(Constant.Utility.PASSWORD);
		User user = null;
		try {
			user = ServiceFactory.getInstance().getUserService().signIn(login, password);
			password = null;
			if(user!=null) {
				request.getSession().setAttribute(Constant.Utility.ROLE, user.getRole().toString());
				request.getSession().setAttribute(Constant.Utility.LOGIN, login);
				response.sendRedirect(Constant.Redirect.TO_ACCOUNT_PAGE);
			}
			else {
				request.getSession().setAttribute(Constant.Utility.ERROR, Constant.Message.LOGINATION);
				response.sendRedirect(Constant.Redirect.TO_LOGINATION_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("error while authorization", e);
			response.sendRedirect(Constant.Redirect.TO_ERROR_PAGE);
		}
	}
}
