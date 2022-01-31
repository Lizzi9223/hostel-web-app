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
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

public class ChangePasswordCommand implements Command {
	private static final Logger logger = LogManager
			.getLogger(by.epam.tc.web.controller.impl.ChangePasswordCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String login = request.getSession().getAttribute(Utility.LOGIN).toString();
			String password = request.getParameter(Utility.INITIAL_PASSWORD);
			if (ServiceFactory.getInstance().getUserService().signIn(login, password) != null) {
				password = request.getParameter(Utility.NEW_PASSWORD);
				if (ServiceFactory.getInstance().getUserService().editPassword(login, password)) {
					password = null;
					response.sendRedirect(Redirect.LOG_OUT);
				} else {
					password = null;
					request.getSession().setAttribute(Utility.ERROR, Message.VALIDATION);
					response.sendRedirect(Redirect.TO_ACCOUNT_PAGE);
				}
			} else {
				password = null;
				logger.info("Validation failed while changing password registration");
				request.getSession().setAttribute(Utility.ERROR, Message.WRONG_PASSWORD);
				response.sendRedirect(Redirect.TO_ACCOUNT_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("error while changing password", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
