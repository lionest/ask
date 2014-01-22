package com.ah3nong.wd.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AdminOperationInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String admin = (String)request.getSession().getAttribute("ask_admin_name");
		if(admin != null && !"".equals(admin)){
			// 读cookie
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie c = cookies[i];
					if (c.getName().equals("manager_login_name")) {
						
						//重新存储cookie
						Cookie cookie = new Cookie("manager_login_name",c.getValue());
						Cookie timeCookie = new Cookie("manager_login_time",Long.toString( System.currentTimeMillis()));
						//生命周期         
						//cookie.setMaxAge(60*60*24);
						//timeCookie.setMaxAge(60*60*24);
						//设置域
						cookie.setDomain(".gy3nong.com"); 
						timeCookie.setDomain(".gy3nong.com"); 
						response.addCookie(cookie);
						response.addCookie(timeCookie);
						
						return invocation.invoke();
					}
				}
			}
		}
		
		return Action.INPUT;
	}
}
