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
import by.epam.tc.web.entity.stay.Stay;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.ServiceFactory;

public class SubmitStayCommand implements Command {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.controller.impl.SubmitStayCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getParameter(Constant.Utility.EDITED_STAY_ID)!=null
					&& !request.getParameter(Constant.Utility.EDITED_STAY_ID).equals(Constant.Utility.EMPTY)) {
				LocalDate toDate = LocalDate.parse(request.getParameter(Constant.Utility.TO_DATE));
				String notes = request.getParameter(Constant.Utility.NOTES);
				int id = Integer.parseInt(request.getParameter(Constant.Utility.EDITED_STAY_ID));
				Stay stay = ServiceFactory.getInstance().getStaysService().getStayById(id);
				stay.setToDate(toDate);
				stay.setNotes(notes);
				ServiceFactory.getInstance().getStaysService().updateStay(id, stay);
				response.sendRedirect(Constant.Redirect.TO_STAYS_PAGE);
			}else {
				LocalDate fromDate = LocalDate.parse(request.getParameter(Constant.Utility.FROM_DATE));
				LocalDate toDate = LocalDate.parse(request.getParameter(Constant.Utility.TO_DATE));
				int guestsNumber = Integer.parseInt(request.getParameter(Constant.Utility.GUESTS_NUMBER));
				int roomNumber = Integer.parseInt(request.getParameter(Constant.Utility.ROOM_NUMBER));
				request.getSession().setAttribute(Constant.Utility.NEW_STAY_FROM_DATE, fromDate);
				request.getSession().setAttribute(Constant.Utility.NEW_STAY_TO_DATE, toDate);
				request.getSession().setAttribute(Constant.Utility.NEW_STAY_GUESTS_NUMBER, guestsNumber);
				request.getSession().setAttribute(Constant.Utility.NEW_STAY_ROOM_NUMBER, roomNumber);
				response.sendRedirect(Constant.Redirect.TO_CLIENTS_PAGE);
			}			
		} catch (ServiceException e) {
			logger.error("error while submitting stay", e);
			response.sendRedirect(Constant.Redirect.TO_ERROR_PAGE);
		}	
	}

}
