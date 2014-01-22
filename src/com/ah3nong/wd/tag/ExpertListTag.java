package com.ah3nong.wd.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ah3nong.wd.bean.Expert;
import com.ah3nong.wd.service.ExpertService;

public class ExpertListTag extends TagSupport {
	private static final long serialVersionUID = -7860812577939505278L;

	private Integer count;
	private Boolean recommended;
	private String orderBy;
	private String var;

	public int doStartTag() {
		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
		ExpertService expertService = (ExpertService) applicationContext.getBean("expertService");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("count", count);
		if (recommended != null) {
			params.put("recommended", recommended ? 1 : 0);
		}
		params.put("orderBy", orderBy);
		params.put("var", var);
		List<Expert> experts = expertService.findExpert(params);
		pageContext.setAttribute(var, experts);
		return SKIP_BODY;
	}

	public void setCount(Integer count) {
		this.count = count;
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
