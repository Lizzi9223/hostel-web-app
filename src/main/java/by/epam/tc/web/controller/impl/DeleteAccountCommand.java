package by.epam.tc.web.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.service.ServiceFactory;

public class DeleteAccountCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String login = (String)request.getSession().getAttribute("login");
			ServiceFactory.getInstance().getUserService().deleteAccount(login);
			request.getSession().removeAttribute("role");
			request.getSession().removeAttribute("login");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
