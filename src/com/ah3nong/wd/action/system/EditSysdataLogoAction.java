package com.ah3nong.wd.action.system;

import java.io.File;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Sysdata;
import com.ah3nong.wd.service.SysdataService;

public class EditSysdataLogoAction extends GenericActionSupport<Sysdata>{
	private static final long serialVersionUID = 6807778379832653216L;
	private Sysdata sysdata;
	private File syslogo;
	private String syslogoFileName;
	private SysdataService sysdataService;
	
	public String execute(){
		
		sysdataService.updateByPrimarykey(sysdata);
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

	public void setSyslogo(File syslogo) {
		this.syslogo = syslogo;
	}

	public void setSyslogoFileName(String syslogoFileName) {
		this.syslogoFileName = syslogoFileName;
	}
	
}

