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

public class LoginationFilter implements Filter {
	
	private final List<String> availableForGuestCommands = Arrays.asList(
			"GO_TO_WELCOME_PAGE", "GO_TO_LOGINATION_PAGE", "GO_TO_REGISTRATION_PAGE", "GO_TO_ROOMS_PAGE",
			"Logination", "Registration", "ChangeLanguage", "SearchRooms");

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request; 
	    HttpServletResponse httpResponse = (HttpServletResponse) response;
	    
	    String urlQuery = httpRequest.getQueryString(); 
	    //urlQuery!=null && urlQuery.contains("GO_TO_MY_ACCOUNT_PAGE")
	    //urlQuery!=null && needAuthorizationCommands.stream().anyMatch(urlQuery::contains)
	    if(urlQuery!=null 
	    		&& !(availableForGuestCommands.stream().anyMatch(urlQuery::contains)) ){
	    	HttpSession session = httpRequest.getSession(false);
		    if (session.getAttribute("role")==null || session.getAttribute("login")==null) {
		    	httpResponse.sendRedirect("Controller?command=GO_TO_LOGINATION_PAGE");
		    } else {        
		        chain.doFilter(request, response);
		    }
	    }else {
	        chain.doFilter(request, response);
	    }
	}

}
