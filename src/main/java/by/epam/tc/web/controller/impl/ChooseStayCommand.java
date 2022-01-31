package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Redirect;
import by.epam.tc.web.controller.constant.Utility;

public class ChooseStayCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter(Utility.CHOSEN_STAY_ID));
		request.getSession().setAttribute(Utility.CHOSEN_STAY_ID, id);
		request.getSession().setAttribute(Utility.POPUP_VIEW, Utility.OPTIONS);
		response.sendRedirect(Redirect.TO_STAYS_PAGE);
	}

}
