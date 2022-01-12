package by.epam.tc.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request; 
	    HttpServletResponse httpResponse = (HttpServletResponse) response;

	    HttpSession session = httpRequest.getSession(false);

	    if (session!=null && (session.getAttribute("role")==null || session.getAttribute("login")==null)) {
	    	httpResponse.sendRedirect("Controller?command=GO_TO_LOGINATION_PAGE");
	    } else {        
	        chain.doFilter(request, response);
	    }
	}

}
