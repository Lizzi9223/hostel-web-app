package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Utility;

public class ChangeLanguageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String language = request.getParameter(Utility.LANGUAGE);
		request.getSession().setAttribute(Utility.LANGUAGE, language);
		String url = (String) request.getSession().getAttribute(Utility.URL);
		response.sendRedirect(url);
	}

}
