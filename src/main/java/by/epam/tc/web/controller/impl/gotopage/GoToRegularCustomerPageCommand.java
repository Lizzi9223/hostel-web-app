package by.epam.tc.web.controller.impl.gotopage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import by.epam.tc.web.entity.user.RegularClient;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

public class GoToRegularCustomerPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.gotopage.GoToRegularCustomerPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<RegularClient> regularClients = new ArrayList<RegularClient>(
					ServiceFactory.getInstance().getUserService().getAllRegularClients());
			request.setAttribute(Utility.REGULAR_CLIENTS, regularClients);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Forward.TO_REGULAR_CUSTOMERS_PAGE);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error("error while going to regular customers page", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
