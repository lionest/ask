package com.ah3nong.wd.tag;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ah3nong.wd.bean.Sysdata;
import com.ah3nong.wd.service.SysdataService;

public class SysdataListTag extends TagSupport{
	private static final long serialVersionUID = -3153499275694064270L;
	private String name;
	private String var;
	public int doStartTag() {
		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
		SysdataService sysdataService = (SysdataService) applicationContext.getBean("sysdataService");
		List<Sysdata> sysdataList = sysdataService.findAll();
		Sysdata sysdata = null;
		for(int i=0;i<sysdataList.size();i++){
			if(name.equals(sysdataList.get(i).getName())){
				sysdata = sysdataList.get(i);
			}
		}
		pageContext.setAttribute(var, sysdata);
		return SKIP_BODY;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVar(String var) {
		this.var = var;
	}

}
