package com.ah3nong.wd.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.validation.Assertion;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.security.authentication.LoginServiceImpl;

/**
 * 自动根据单点登录系统的信息设置本系统的用户信息
 * 
 */
public class AutoSetUserAdapterFilter implements Filter {

	/**
	 * 
	 */
	private LoginServiceImpl loginService;

	/**
	 * Default constructor.
	 */
	public AutoSetUserAdapterFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * 过滤逻辑：首先判断单点登录的账户是否已经存在本系统中， 如果不存在使用用户查询接口查询出用户对象并设置在Session中
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		// _const_cas_assertion_是CAS中存放登录用户名的session标志
		Object object = httpRequest.getSession().getAttribute("_const_cas_assertion_");

		if (object != null) {
			WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
			loginService = applicationContext.getBean(LoginServiceImpl.class);
			Assertion assertion = (Assertion) object;

			if (httpRequest.getSession().getAttribute("user") == null) {
				User user = (User) loginService.loadUserByUsername(assertion.getPrincipal().getName());
				httpRequest.getSession().setAttribute("user", user);
			} else {
				// 如果用户不为空，则判断当前session用户名与cas服务器端传过来的用户名是否一致，若不一致则用新用户替换session中的用户
				User u = (User) httpRequest.getSession().getAttribute("user");
				if (!u.getUsername().equals(assertion.getPrincipal().getName())) {
					httpRequest.getSession().removeAttribute("user");
					User user = (User) loginService.loadUserByUsername(assertion.getPrincipal().getName());
					httpRequest.getSession().setAttribute("user", user);
				}
			}
		} else {
			httpRequest.getSession().removeAttribute("user");
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public LoginServiceImpl getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginServiceImpl loginService) {
		this.loginService = loginService;
	}

}