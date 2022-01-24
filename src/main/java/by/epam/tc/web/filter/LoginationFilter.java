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

import by.epam.tc.web.controller.constant.Constant;

public class LoginationFilter implements Filter {
	
	private final List<String> availableForGuestCommands = Arrays.asList(
			Constant.Command.GO_TO_WELCOME_PAGE, 
			Constant.Command.GO_TO_LOGINATION_PAGE, 
			Constant.Command.GO_TO_REGISTRATION_PAGE, 
			Constant.Command.GO_TO_ROOMS_PAGE,
			Constant.Command.LOGINATION, 
			Constant.Command.REGISTRATION, 
			Constant.Command.CHANGE_LANGUAGE, 
			Constant.Command.SEARCH_ROOMS);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request; 
	    HttpServletResponse httpResponse = (HttpServletResponse) response;	    
	    String urlQuery = httpRequest.getQueryString();
	    if(urlQuery!=null 
	    		&& !(availableForGuestCommands.stream().anyMatch(urlQuery::contains)) ){
	    	HttpSession session = httpRequest.getSession(false);
		    if (session.getAttribute(Constant.Utility.ROLE)==null || session.getAttribute(Constant.Utility.LOGIN)==null) {
		    	httpResponse.sendRedirect(Constant.Redirect.TO_LOGINATION_PAGE);
		    } else {        
		        chain.doFilter(request, response);
		    }
	    }else {
	        chain.doFilter(request, response);
	    }
	}

}
