package com.ah3nong.wd.tag;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ah3nong.wd.bean.Link;
import com.ah3nong.wd.service.LinkService;

public class LinkListTag extends TagSupport {
	private static final long serialVersionUID = -7613858879601666804L;
	private int status;
	private String var;
	
	public int doStartTag() {
		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
		LinkService linkService = (LinkService) applicationContext.getBean("linkService");
		List<Link> links = linkService.findLinksByStatus(status);
		pageContext.setAttribute(var, links);
		return SKIP_BODY;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setVar(String var) {
		this.var = var;
	}
}
