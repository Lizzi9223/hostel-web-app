package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Constant;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class ChangePasswordCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.ChangePasswordCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try {			
			String login = request.getSession().getAttribute(Constant.Utility.LOGIN).toString();
			String password = request.getParameter(Constant.Utility.INITIAL_PASSWORD);
			if(ServiceFactory.getInstance().getUserService().signIn(login, password)!=null) {
				password = request.getParameter(Constant.Utility.NEW_PASSWORD);
				ServiceFactory.getInstance().getUserService().editPassword(login, password);
				password = null;
				response.sendRedirect(Constant.Redirect.LOG_OUT);
			}
			else {
				password = null;
				request.getSession().setAttribute(Constant.Utility.ERROR,Constant.Message.WRONG_PASSWORD);
				response.sendRedirect(Constant.Redirect.TO_ACCOUNT_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("error while changing password", e);
			response.sendRedirect(Constant.Redirect.TO_ERROR_PAGE);
		}
	}

}
