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
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.Role;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class GoToClientsPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.gotopage.GoToClientsPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
			if(request.getSession().getAttribute(Constant.Utility.ROLE).toString().equals(Role.ADMIN.toString())) {
				List<Client> clients = new LinkedList<Client>(ServiceFactory.getInstance().getUserService().getAllClients());
				Collections.sort(clients, 
					     Comparator.comparing(Client::getClientId, Comparator.naturalOrder()));
				request.setAttribute(Constant.Utility.CLIENTS, clients);
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.Forward.TO_CLIENTS_PAGE);
				dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			logger.error("error while going to clients page", e);
			response.sendRedirect(Constant.Redirect.TO_ERROR_PAGE);
		}
	}

}
