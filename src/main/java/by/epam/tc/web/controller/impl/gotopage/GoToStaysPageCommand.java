package by.epam.tc.web.controller.impl.gotopage;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Constant;
import by.epam.tc.web.entity.stay.Stay;
import by.epam.tc.web.entity.user.Role;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class GoToStaysPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.gotopage.GoToStaysPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Stay> stays = null;
		try {			
			if(request.getSession().getAttribute(Constant.Utility.ROLE).toString().equals(Role.ADMIN.toString())) {
				stays = new LinkedList<Stay>(ServiceFactory.getInstance().getStaysService().getAllStays());				
			}
			else {
				String userLogin = (String)request.getSession().getAttribute(Constant.Utility.LOGIN);	
				stays = new LinkedList<Stay>(ServiceFactory.getInstance().getStaysService().getAllUserStays(userLogin));
			}
			Collections.sort(stays, 
				     Comparator.comparing(Stay::getFromDate, Comparator.reverseOrder()));
			request.setAttribute(Constant.Utility.STAYS, stays);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.Forward.TO_STAYS_PAGE);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error("error while going to stays page", e);
			response.sendRedirect(Constant.Redirect.TO_ERROR_PAGE);
		}		
	}

}
