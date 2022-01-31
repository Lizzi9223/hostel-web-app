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
import by.epam.tc.web.controller.constant.CommandName;
import by.epam.tc.web.controller.constant.Forward;
import by.epam.tc.web.controller.constant.Redirect;
import by.epam.tc.web.controller.constant.Utility;
import by.epam.tc.web.entity.stay.Stay;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.exception.ServiceException;

public class EditStayCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.EditStayCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int stayId = Integer.parseInt(request.getParameter(Utility.STAY_ID));
			Stay stay = ServiceFactory.getInstance().getStaysService().getStayById(stayId);
			if (request.getParameter(Utility.COMMAND).equals(CommandName.EDIT_STAY)) {
				request.getSession().setAttribute(Utility.STAY, stay);
				request.getSession().setAttribute(Utility.POPUP_VIEW, CommandName.EDIT_STAY);
				response.sendRedirect(Redirect.TO_STAYS_PAGE);
			} else if (request.getParameter(Utility.COMMAND).equals(CommandName.EDIT_STAY_CHECK)) {
				// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				LocalDate toDate = LocalDate.parse(request.getParameter(Utility.TO_DATE));
				if (ServiceFactory.getInstance().getStaysService().areAvailablePlaces(stay.getRoomNumber(),
						stay.getFromDate(), toDate, 1, 0, stayId)) {
					request.setAttribute(Utility.TO_DATE, toDate);
					request.setAttribute(Utility.EDITED_NOTES, request.getParameter(Utility.NOTES));
					request.setAttribute(Utility.CHECK_RESULT, true);
					request.setAttribute(Utility.EDITED_STAY_ID, stayId);
				} else {
					request.setAttribute(Utility.CHECK_RESULT, false);
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher(Forward.TO_STAYS_PAGE);
				dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			logger.error("error while editing stay", e);
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		}
	}

}
