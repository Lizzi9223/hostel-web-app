package by.epam.tc.web.controller.impl.gotopage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Forward;
import by.epam.tc.web.controller.constant.Utility;

/** 
 * The class {@code GoToWelcomePageCommand} implements {@code Command}
 * 
 * @author Lizzi9223
 *
 */
public class GoToWelcomePageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter(Utility.LOG_OUT) != null) {
			request.getSession().removeAttribute(Utility.ROLE);
			request.getSession().removeAttribute(Utility.LOGIN);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(Forward.TO_WELCOME_PAGE);
		dispatcher.forward(request, response);

	}

}
