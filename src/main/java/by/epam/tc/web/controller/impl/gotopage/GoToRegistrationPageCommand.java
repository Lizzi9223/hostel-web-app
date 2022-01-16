package by.epam.tc.web.controller.impl.gotopage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Constant;

public class GoToRegistrationPageCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter(Constant.Utility.CREATE) != null && request.getParameter(Constant.Utility.CREATE).equals(Constant.Utility.ADMIN)) {
			request.setAttribute(Constant.Utility.CREATE, Constant.Utility.ADMIN);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.Forward.TO_REGISTRATION_PAGE);
		dispatcher.forward(request, response);		
	}

}
