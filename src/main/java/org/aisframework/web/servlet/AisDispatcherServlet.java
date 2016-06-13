package org.aisframework.web.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AisDispatcherServlet implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
			System.out.print("init");
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		   System.out.print("被拦截了");
		
	}

	public void destroy() {
		
	}

}
