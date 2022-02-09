package by.epam.tc.web.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.tc.web.controller.constant.CommandName;
import by.epam.tc.web.controller.constant.Redirect;
import by.epam.tc.web.controller.constant.Utility;

/** 
 * Servlet that processes requests and returns the processing result
 * 
 * The class {@code Controller} implements {@code HttpServlet}
 * 
 * @author Lizzi9223
 *
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CommandProvider provider = new CommandProvider();
	private final String QUESTION_MARK = "?";
	private final String AMPERSAND = "&";
	private final String EQUALS = "=";

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (getServletContext().getAttribute(Utility.ERROR) != null) {
			response.sendRedirect(Redirect.TO_ERROR_PAGE);
		} else {
			String commandName = request.getParameter(Utility.COMMAND);
			if (!commandName.equals(CommandName.CHANGE_LANGUAGE)) {
				HttpSession session = request.getSession();
				StringBuilder urlBuilder = new StringBuilder();
				urlBuilder.append(request.getRequestURI());
				Enumeration<String> parameterNames = request.getParameterNames();
				if (parameterNames.hasMoreElements()) {
					urlBuilder.append(QUESTION_MARK);
				}
				while (parameterNames.hasMoreElements()) {
					String paramName = parameterNames.nextElement();
					String[] paramValues = request.getParameterValues(paramName);
					for (int i = 0; i < paramValues.length; i++) {
						if (urlBuilder.charAt(urlBuilder.length() - 1) != '?') {
							urlBuilder.append(AMPERSAND);
						}
						String paramValue = paramValues[i];
						urlBuilder.append(paramName);
						urlBuilder.append(EQUALS);
						urlBuilder.append(paramValue);
					}
				}
				session.setAttribute(Utility.URL, urlBuilder.toString());
			}
			Command command = provider.getCommand(commandName);
			command.execute(request, response);
		}
	}

}
