package com.ah3nong.wd.action;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.security.SecurityContext;

/**
 * 登陆过滤器
 */
public class LoginFilter implements Filter {
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		User user = SecurityContext.getUser();
		if (user != null) {
			HttpServletRequest re = (HttpServletRequest) request;
			HttpSession session = re.getSession();
			session.setAttribute("username", user.getUsername());
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
