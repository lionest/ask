package com.ah3nong.wd.interceptor;

import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.security.SecurityContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class UserOperationInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		/*
		 * ActionContext actionContext = invocation.getInvocationContext(); Map session = actionContext.getSession(); if (session != null && session.get(USER_SESSION_KEY) != null) { return
		 * invocation.invoke(); } return Action.INPUT;
		 */

		User user = SecurityContext.getUser();
		if (user == null) {
			return Action.LOGIN;
		} else {
			return invocation.invoke();
		}
	}
}
/*
 * ActionContext actionContext = invocation.getInvocationContext(); Map session = actionContext.getSession(); if (session != null && session.get(USER_SESSION_KEY) != null) { return
 * invocation.invoke(); } return Action.INPUT;
 */

