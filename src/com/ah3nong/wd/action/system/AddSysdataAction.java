package com.ah3nong.wd.action.system;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Sysdata;
import com.ah3nong.wd.service.SysdataService;

public class AddSysdataAction extends GenericActionSupport<Sysdata>{
	private static final long serialVersionUID = 6807778379832653216L;
	private Sysdata sysdata;
	private SysdataService sysdataService;
	
	public String execute(){
		sysdataService.add(sysdata);
		return SUCCESS;
	}

	public void setSysdata(Sysdata sysdata) {
		this.sysdata = sysdata;
	}

	public Sysdata getSysdata() {
		return sysdata;
	}

	public void setSysdataService(SysdataService sysdataService) {
		this.sysdataService = sysdataService;
	}
	
}

