package com.ah3nong.wd.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.service.DomainService;

public class DomainListTag extends TagSupport {
	private static final long serialVersionUID = -8372621260220788899L;

	private Boolean recommended;
	private String orderBy;
	private String var;

	public int doStartTag() {
		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
		DomainService domainService = (DomainService) applicationContext.getBean("domainService");
		Map<String, Object> params = new HashMap<String, Object>();
		if (recommended != null) {
			params.put("recommended", recommended ? 1 : 0);
		}
		params.put("orderBy", orderBy);
		params.put("var", var);
		if("arcdomains".equals(var)){
			params.put("parentId", -1);
		}
		List<Domain> domains = domainService.findDomain(params);
		pageContext.setAttribute(var, domains);
		return SKIP_BODY;
	}

	public void setRecommended(Boolean recommended) {
		this.recommended = recommended;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void setVar(String var) {
		this.var = var;
	}
}
