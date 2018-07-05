package com.nasaproject.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This Filter has a role purpose of validating whether or not the add user.
 * @author Kami Hassanzadeh
 *
 */
@WebFilter("/user/add")
public class AuthFilter implements Filter {

	@Override
	public void destroy() {}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;

		String username = servletRequest.getParameter("username");
		String password = servletRequest.getParameter("password");
		

		if(this.inputMatches(username) && this.inputMatches(password)) {
			filterChain.doFilter(request, response);
		}
		else {
			RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/index?error=Only%20characters%20allowed!");

			servletResponse.setContentType("text/html");
			requestDispatcher.forward(servletRequest, servletResponse);
		}
	}

	
	public boolean inputMatches(String input) {
		Pattern pattern = Pattern.compile("[A-Za-z]");
		Matcher matcher = pattern.matcher(input);
		if(matcher.find()) {
			return true;
		}
		else return false;
	}

}
