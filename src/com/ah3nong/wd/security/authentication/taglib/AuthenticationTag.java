package com.ah3nong.wd.security.authentication.taglib;

import javax.servlet.jsp.tagext.TagSupport;

import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.security.SecurityContext;

public class AuthenticationTag extends TagSupport {
	private static final long serialVersionUID = -6446820161477363281L;

	private boolean inverse;
	private String var;

	public int doStartTag() {
		//User user = SecurityContext.getUser(pageContext.getSession());
		User user = SecurityContext.getUser();
		boolean authenticated = user != null;
		if (inverse == authenticated) {
			return SKIP_BODY;
		}
		if (authenticated && var != null) {
			pageContext.setAttribute(var, user);
		}
		return EVAL_PAGE;
	}

	public void setInverse(boolean inverse) {
		this.inverse = inverse;
	}

	public void setVar(String var) {
		this.var = var;
	}
}