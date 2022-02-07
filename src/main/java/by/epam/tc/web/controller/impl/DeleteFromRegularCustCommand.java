package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Redirect;
import by.epam.tc.web.controller.constant.Utility;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

public class DeleteFromRegularCustCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.DeleteFromRegularCustCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter(Utility.CHOSEN_CLIENT_ID));
			ServiceFactory.getInstance().getUserService().deleteFromRegularCustomers(id);
			response.sendRedirect(Redirect.TO_REGULAR_CUST_PAGE);
		} catch (ServiceException e) {
			logger.error("error while deleting client from regular customers", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
