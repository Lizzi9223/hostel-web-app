package by.epam.tc.web.controller.impl;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Constant;
import by.epam.tc.web.entity.stay.Stay;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class EditStayCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.EditStayCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int stayId = Integer.parseInt(request.getParameter(Constant.Utility.STAY_ID));
			Stay stay = ServiceFactory.getInstance().getStaysService().getStayById(stayId);
			if(request.getParameter(Constant.Utility.COMMAND).equals(Constant.Command.EDIT_STAY)) {				
				request.getSession().setAttribute(Constant.Utility.STAY, stay);
				request.getSession().setAttribute(Constant.Utility.POPUP_VIEW, Constant.Command.EDIT_STAY);
				response.sendRedirect(Constant.Redirect.TO_STAYS_PAGE);
			}else if(request.getParameter(Constant.Utility.COMMAND).equals(Constant.Command.EDIT_STAY_CHECK)) {
				//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				LocalDate toDate = LocalDate.parse(request.getParameter(Constant.Utility.TO_DATE));
				if(ServiceFactory.getInstance().getStaysService().areAvailablePlaces(
						stay.getRoomNumber(), stay.getFromDate(), toDate, 1, 0, stayId)) {					
					request.setAttribute(Constant.Utility.TO_DATE, toDate);
					request.setAttribute(Constant.Utility.NOTES, request.getParameter(Constant.Utility.NOTES));
					request.setAttribute(Constant.Utility.CHECK_RESULT, true);
					request.setAttribute(Constant.Utility.EDITED_STAY_ID, stayId);
				}else {
					request.setAttribute(Constant.Utility.CHECK_RESULT, false);
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.Forward.TO_STAYS_PAGE);
				dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			logger.error("error while editing stay", e);
			response.sendRedirect(Constant.Redirect.TO_ERROR_PAGE);
		}
	}

}
