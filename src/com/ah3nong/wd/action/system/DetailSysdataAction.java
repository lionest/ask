package com.ah3nong.wd.action.system;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Sysdata;
import com.ah3nong.wd.service.SysdataService;

public class DetailSysdataAction extends GenericActionSupport<Sysdata>{
	private static final long serialVersionUID = 6807778379832653216L;
	private int id;
	private Sysdata sysdata;
	private SysdataService sysdataService;
	
	public String execute(){
		sysdata = sysdataService.findByPrimarykey(id);
		return SUCCESS;
	}

	public Sysdata getSysdata() {
		return sysdata;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSysdataService(SysdataService sysdataService) {
		this.sysdataService = sysdataService;
	}
	
}

