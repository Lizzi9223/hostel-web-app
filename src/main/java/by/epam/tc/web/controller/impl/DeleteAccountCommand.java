package by.epam.tc.web.controller.impl;

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
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

public class DeleteAccountCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.DeleteAccountCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String login = (String)request.getSession().getAttribute(Utility.LOGIN);
			ServiceFactory.getInstance().getUserService().deleteAccount(login);
			request.getSession().removeAttribute(Utility.ROLE);
			request.getSession().removeAttribute(Utility.LOGIN);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Forward.TO_WELCOME_PAGE);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error("error while deleting account", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
