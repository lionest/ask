package com.ah3nong.wd.action.audit;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Sysdata;
import com.ah3nong.wd.service.SysdataService;

public class ShowAuditAction extends GenericActionSupport<Sysdata>{
	private static final long serialVersionUID = -1064558716622695535L;
	private SysdataService sysdataService;
	private Sysdata questionSysdata;
	private Sysdata replySysdata;
	private Sysdata probingSysdata;
	
	public String execute(){
		questionSysdata = sysdataService.findByName("auditQuestion");
		replySysdata = sysdataService.findByName("auditReply");
		probingSysdata = sysdataService.findByName("auditProbing");
		
		return SUCCESS;
	}

	public Sysdata getQuestionSysdata() {
		return questionSysdata;
	}

	public Sysdata getReplySysdata() {
		return replySysdata;
	}

	public Sysdata getProbingSysdata() {
		return probingSysdata;
	}

	public void setSysdataService(SysdataService sysdataService) {
		this.sysdataService = sysdataService;
	}

}
