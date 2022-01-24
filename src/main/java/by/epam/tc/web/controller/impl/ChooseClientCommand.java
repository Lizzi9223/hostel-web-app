package by.epam.tc.web.controller.impl;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Constant;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class ChooseClientCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.ChooseClientCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter(Constant.Utility.CHOSEN_CLIENT_ID));
			if(request.getSession().getAttribute(Constant.Utility.NEW_STAY_GUESTS_NUMBER)!=null 
					&& (int)request.getSession().getAttribute(Constant.Utility.NEW_STAY_GUESTS_NUMBER)>0) {
				ServiceFactory.getInstance().getStaysService().addStay(id,
						(LocalDate)request.getSession().getAttribute(Constant.Utility.NEW_STAY_FROM_DATE),
						(LocalDate)request.getSession().getAttribute(Constant.Utility.NEW_STAY_TO_DATE),
						(int)request.getSession().getAttribute(Constant.Utility.NEW_STAY_ROOM_NUMBER), "");
				int guestsNumber = (int)request.getSession().getAttribute(Constant.Utility.NEW_STAY_GUESTS_NUMBER);
				request.getSession().setAttribute(Constant.Utility.NEW_STAY_GUESTS_NUMBER, --guestsNumber);				
				if(guestsNumber==0) {
					request.getSession().removeAttribute(Constant.Utility.NEW_STAY_FROM_DATE);
					request.getSession().removeAttribute(Constant.Utility.NEW_STAY_TO_DATE);
					request.getSession().removeAttribute(Constant.Utility.NEW_STAY_GUESTS_NUMBER);
					request.getSession().removeAttribute(Constant.Utility.NEW_STAY_ROOM_NUMBER);
					response.sendRedirect(Constant.Redirect.TO_STAYS_PAGE);
				}else {
					response.sendRedirect(Constant.Redirect.TO_CLIENTS_PAGE);
				}				
			}else {
				request.getSession().setAttribute(Constant.Utility.CHOSEN_CLIENT_ID, id);
				request.getSession().setAttribute(Constant.Utility.POPUP_VIEW, Constant.Utility.OPTIONS);				
				response.sendRedirect(Constant.Redirect.TO_CLIENTS_PAGE);
			}	
		}catch (ServiceException e) {
			logger.error("error while choosing client", e);
			response.sendRedirect(Constant.Redirect.TO_ERROR_PAGE);
		}
	}

}
