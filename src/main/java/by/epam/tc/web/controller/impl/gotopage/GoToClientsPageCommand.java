package by.epam.tc.web.controller.impl.gotopage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.CommandName;
import by.epam.tc.web.controller.constant.Forward;
import by.epam.tc.web.controller.constant.Redirect;
import by.epam.tc.web.controller.constant.Utility;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

/** 
 * The class {@code GoToClientsPageCommand} implements {@code Command}
 * 
 * @author Lizzi9223
 *
 */
public class GoToClientsPageCommand implements Command {
	private static final Logger logger = LogManager
			.getLogger(by.epam.tc.web.controller.impl.gotopage.GoToClientsPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Client> clients = new ArrayList<Client>(
					ServiceFactory.getInstance().getUserService().getAllClients());			
			for (Client client : clients) {
				if(ServiceFactory.getInstance().getUserService().isInBlacklist(client.getClientId())) {
					client.setInBlackList(true);
				}
				if(ServiceFactory.getInstance().getUserService().isRegularCustomer(client.getClientId())) {
					client.setRegularCustomer(true);
				}
			}
			clients.sort(Comparator.comparing(Client::isInBlackList)
					.thenComparing(item -> !item.isInBlackList() && !item.isRegularCustomer()));
			String criteria = null;
			String searchData = null;
			if (request.getParameter(Utility.COMMAND).equals(CommandName.SEARCH_CLIENT)) {
				criteria = (String) request.getParameter(Utility.SEARCH_CRITERIA);
				searchData = (String) request.getParameter(Utility.SEARCH_DATA);
				if (searchData != null && !searchData.equals(Utility.EMPTY)) {
					switch (criteria) {
					case Utility.LOGIN:
						for (int i = 0; i < clients.size(); i++) {
							if (clients.get(i).getLogin() == null
									|| !clients.get(i).getLogin().startsWith(searchData)) {
								clients.remove(i--);
							}
						}
						break;
					case Utility.PASSPORT_ID:
						for (int i = 0; i < clients.size(); i++) {
							if (!clients.get(i).getPassportId().startsWith(searchData)) {
								clients.remove(i--);
							}
						}
						break;
					case Utility.SURNAME:
						for (int i = 0; i < clients.size(); i++) {
							if (!clients.get(i).getLastName().startsWith(searchData)) {
								clients.remove(i--);
							}
						}
						break;
					}
				}
			}
			request.setAttribute(Utility.CURRENT_SEARCH_CRITERIA, criteria);
			request.setAttribute(Utility.CURRENT_SEARCH_DATA, searchData);
			request.setAttribute(Utility.CLIENTS, clients);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Forward.TO_CLIENTS_PAGE);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error("error while going to clients page", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
