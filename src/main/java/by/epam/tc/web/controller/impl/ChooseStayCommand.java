package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Constant;

public class ChooseStayCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter(Constant.Utility.CHOSEN_STAY_ID));			
		request.getSession().setAttribute(Constant.Utility.CHOSEN_STAY_ID, id);
		request.getSession().setAttribute(Constant.Utility.POPUP_VIEW, Constant.Utility.OPTIONS);
		response.sendRedirect(Constant.Redirect.TO_STAYS_PAGE);
	}

}
