package com.usernet.product.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.usernet.product.entity.Channel;

public class AdminFilter implements Filter {


	public void init(FilterConfig fc) throws ServletException {
	}

	@SuppressWarnings("unused")
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		Channel admin = (Channel) session.getAttribute("admin");
		chain.doFilter(req, res);
	}

	public void destroy() {
	}

}