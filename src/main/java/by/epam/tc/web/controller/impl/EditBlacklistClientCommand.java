package by.epam.tc.web.controller.impl;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Redirect;
import by.epam.tc.web.controller.constant.Utility;
import by.epam.tc.web.entity.user.BlackListClient;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

/** 
 * The class {@code EditBlacklistClientCommand} implements {@code Command}
 * 
 * @author Lizzi9223
 *
 */
public class EditBlacklistClientCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.EditBlacklistClientCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter(Utility.CHOSEN_CLIENT_ID));
			LocalDate sinceDate = LocalDate.parse(request.getParameter(Utility.SINCE_DATE));
			String reason = request.getParameter(Utility.REASON);
			BlackListClient blackListClient = new BlackListClient(id, reason, sinceDate);
			ServiceFactory.getInstance().getUserService().editBlacklistClient(id, blackListClient);
			Client client = ServiceFactory.getInstance().getUserService().findClientById(id);
			blackListClient.setPassportId(client.getPassportId());
			request.getSession().setAttribute(Utility.CHOSEN_CLIENT, blackListClient);
			request.getSession().setAttribute(Utility.CHOSEN_CLIENT_ID, id);
			request.getSession().setAttribute(Utility.POPUP_VIEW, Utility.OPTIONS);
			response.sendRedirect(Redirect.TO_BLACKLIST_PAGE);
		} catch (ServiceException e) {
			logger.error("error while editing blacklist client", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
