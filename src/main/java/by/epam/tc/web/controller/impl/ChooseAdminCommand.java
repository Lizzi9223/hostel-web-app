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
import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

public class ChooseAdminCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.ChooseAdminCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter(Utility.CHOSEN_ADMIN_ID));
			Admin admin = ServiceFactory.getInstance().getUserService().findAdminById(id);
			request.getSession().setAttribute(Utility.CHOSEN_ADMIN, admin);
			request.getSession().setAttribute(Utility.CHOSEN_ADMIN_ID, id);
			request.getSession().setAttribute(Utility.POPUP_VIEW, Utility.OPTIONS);
			response.sendRedirect(Redirect.TO_ADMINS_PAGE);
		} catch (ServiceException e) {
			logger.error("error while choosing admin", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
