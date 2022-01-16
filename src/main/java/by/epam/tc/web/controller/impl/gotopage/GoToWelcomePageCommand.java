package by.epam.tc.web.controller.impl.gotopage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Constant;

public class GoToWelcomePageCommand implements Command{	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter(Constant.Utility.LOG_OUT)!=null) {
			request.getSession().removeAttribute(Constant.Utility.ROLE);
			request.getSession().removeAttribute(Constant.Utility.LOGIN);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.Forward.TO_WELCOME_PAGE);
		dispatcher.forward(request, response);
		
	}

}
