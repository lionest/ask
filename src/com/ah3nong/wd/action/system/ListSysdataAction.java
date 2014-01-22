package com.ah3nong.wd.action.system;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Sysdata;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.service.SysdataService;

public class ListSysdataAction extends GenericActionSupport<Sysdata>{
	private static final long serialVersionUID = 3210544621513644394L;
	private SysdataService sysdataService;
	
	public String execute() {

		if (pageNum == 0) {
			pageNum = 1;
		}
		if (numPerPage == 0) {
			numPerPage = Config.getInt("PAGESIZE");
		}
		pager = sysdataService.findSystemsByPageNum(pageNum, numPerPage);
		return SUCCESS;
	}

	public void setSysdataService(SysdataService sysdataService) {
		this.sysdataService = sysdataService;
	}
	
}
