package by.epam.tc.web.controller.impl;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.CommandName;
import by.epam.tc.web.controller.constant.Redirect;
import by.epam.tc.web.controller.constant.Utility;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.RegularClient;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

public class AddToRegularCustCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.AddToRegularCustCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter(Utility.CHOSEN_CLIENT_ID));
			if(request.getParameter(Utility.COMMAND).equals(CommandName.SUBMIT_ADD_TO_REGULAR_CUSTOMERS)) {				
				LocalDate sinceDate = LocalDate.parse(request.getParameter(Utility.SINCE_DATE));
				int discount = Integer.parseInt(request.getParameter(Utility.DISCOUNT));
				String notes = request.getParameter(Utility.NOTES);
				RegularClient regularClient = new RegularClient(id, sinceDate, discount, notes);
				ServiceFactory.getInstance().getUserService().addRegularClient(regularClient);
				Client client = ServiceFactory.getInstance().getUserService().findClientById(id);
				regularClient.setPassportId(client.getPassportId());
				request.getSession().setAttribute(Utility.CHOSEN_CLIENT, regularClient);				
				request.getSession().setAttribute(Utility.POPUP_VIEW, Utility.OPTIONS);
			}else if(request.getParameter(Utility.COMMAND).equals(CommandName.ADD_TO_REGULAR_CUSTOMERS)){
				Client client = ServiceFactory.getInstance().getUserService().findClientById(id); 
				request.getSession().setAttribute(Utility.CHOSEN_CLIENT, client);	
				request.getSession().setAttribute(Utility.POPUP_VIEW, CommandName.ADD_TO_REGULAR_CUSTOMERS);				
			}
			request.getSession().setAttribute(Utility.CHOSEN_CLIENT_ID, id);
			response.sendRedirect(Redirect.TO_REGULAR_CUST_PAGE);
		} catch (ServiceException e) {
			logger.error("error while adding regular client", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
