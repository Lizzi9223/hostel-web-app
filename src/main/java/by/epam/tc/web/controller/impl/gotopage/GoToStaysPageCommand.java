package by.epam.tc.web.controller.impl.gotopage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import by.epam.tc.web.entity.stay.Stay;
import by.epam.tc.web.entity.user.Role;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

/** 
 * The class {@code GoToStaysPageCommand} implements {@code Command}
 * 
 * @author Lizzi9223
 *
 */
public class GoToStaysPageCommand implements Command {
	private static final Logger logger = LogManager
			.getLogger(by.epam.tc.web.controller.impl.gotopage.GoToStaysPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Stay> stays = null;
		try {
			if (request.getSession().getAttribute(Utility.ROLE).toString().equals(Role.ADMIN.toString())) {
				stays = new ArrayList<Stay>(ServiceFactory.getInstance().getStaysService().getAllStays());
			} else {
				String userLogin = (String) request.getSession().getAttribute(Utility.LOGIN);
				stays = new ArrayList<Stay>(ServiceFactory.getInstance().getStaysService().getAllUserStays(userLogin));
			}
			Collections.sort(stays, Comparator.comparing(Stay::getFromDate, Comparator.reverseOrder()));
			request.setAttribute(Utility.STAYS, stays);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Forward.TO_STAYS_PAGE);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error("error while going to stays page", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
