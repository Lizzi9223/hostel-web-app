package by.epam.tc.web.controller.impl.gotopage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.web.controller.Command;
import by.epam.tc.web.controller.constant.Forward;

/** 
 * The class {@code GoToContactsPageCommand} implements {@code Command}
 * 
 * @author Lizzi9223
 *
 */
public class GoToContactsPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(Forward.TO_CONTACTS_PAGE);
		dispatcher.forward(request, response);
	}

}
