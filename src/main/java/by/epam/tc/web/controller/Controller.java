package by.epam.tc.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final CommandProvider provider = new CommandProvider(); 

    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String commandName = request.getParameter("command");
		if(!commandName.equals("ChangeLanguage")) {
			HttpSession session = request.getSession();
			StringBuilder urlBuilder = new StringBuilder();
			urlBuilder.append(request.getRequestURI());
			Enumeration<String> parameterNames = request.getParameterNames();
			if(parameterNames.hasMoreElements()) {
				urlBuilder.append("?");
			}
	        while (parameterNames.hasMoreElements()) { 
	            String paramName = parameterNames.nextElement();
	            String[] paramValues = request.getParameterValues(paramName);
	            for (int i = 0; i < paramValues.length; i++) {
	            	if(urlBuilder.charAt(urlBuilder.length()-1)!='?') {
	            		urlBuilder.append("&");
	            	}
	                String paramValue = paramValues[i];
	                urlBuilder.append(paramName);
	                urlBuilder.append("=");
	                urlBuilder.append(paramValue);
	            }
	        }
			session.setAttribute("url", urlBuilder.toString());
		}
		Command command = provider.getCommand(commandName);		
		command.execute(request, response);
	}

}
