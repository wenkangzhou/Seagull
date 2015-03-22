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

import com.usernet.product.utils.ProductConfig;

public class EncodingFilter implements Filter {

	public void init(FilterConfig fc) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		request.setCharacterEncoding(ProductConfig.CHARSET);
		response.setCharacterEncoding(ProductConfig.CHARSET);

		if (ProductConfig.realPath == null) {
			ProductConfig.setRealPath(request);
		}
		chain.doFilter(req, res);

	}

	public void destroy() {
	}
}