package com.ah3nong.wd.security;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.ah3nong.wd.bean.User;
import com.opensymphony.xwork2.ActionContext;

public class SecurityContext {
	public static User getUser() {
		return (User)ActionContext.getContext().getSession().get("user"); 
		//return getUser(SecurityContextHolder.getContext());
	}

	public static User getUser(HttpSession session) {
		Object contextFromSession = session
				.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		if (!(contextFromSession instanceof org.springframework.security.core.context.SecurityContext)) {
			return null;
		}
		return getUser((org.springframework.security.core.context.SecurityContext) contextFromSession);
	}

	private static User getUser(org.springframework.security.core.context.SecurityContext securityContext) {
		if (securityContext == null) {
			return null;
		}

		Authentication authentication = securityContext.getAuthentication();
		if (authentication == null) {
			return null;
		}

		Object principal = authentication.getPrincipal();
		if (principal instanceof User) {
			return (User) principal;
		}

		return null;
	}
}
