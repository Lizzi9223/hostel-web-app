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
import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

/** 
 * The class {@code GoToAdminsPageCommand} implements {@code Command}
 * 
 * @author Lizzi9223
 *
 */
public class GoToAdminsPageCommand implements Command {
	private static final Logger logger = LogManager
			.getLogger(by.epam.tc.web.controller.impl.gotopage.GoToAdminsPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Admin> admins = new ArrayList<Admin>(
					ServiceFactory.getInstance().getUserService().getAllAdmins());
			Collections.sort(admins, Comparator.comparing(Admin::getUserId, Comparator.naturalOrder()));
			request.setAttribute(Utility.ADMINS, admins);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Forward.TO_ADMINS_PAGE);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error("error while going to admins page", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
