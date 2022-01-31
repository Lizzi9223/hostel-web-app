package by.epam.tc.web.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.tc.web.controller.constant.CommandName;
import by.epam.tc.web.controller.constant.Redirect;
import by.epam.tc.web.controller.constant.Utility;

public class LoginationFilter implements Filter {

	private final List<String> availableForGuestCommands = Arrays.asList(CommandName.GO_TO_WELCOME_PAGE,
			CommandName.GO_TO_LOGINATION_PAGE, CommandName.GO_TO_REGISTRATION_PAGE, CommandName.GO_TO_ROOMS_PAGE,
			CommandName.LOGINATION, CommandName.REGISTRATION, CommandName.CHANGE_LANGUAGE, CommandName.SEARCH_ROOMS);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String urlQuery = httpRequest.getQueryString();
		if (urlQuery != null && !(availableForGuestCommands.stream().anyMatch(urlQuery::contains))) {
			HttpSession session = httpRequest.getSession(false);
			if (session.getAttribute(Utility.ROLE) == null || session.getAttribute(Utility.LOGIN) == null) {
				httpResponse.sendRedirect(Redirect.TO_LOGINATION_PAGE);
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

}
