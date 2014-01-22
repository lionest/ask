package com.ah3nong.wd.action.link;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Link;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.service.LinkService;

public class ViewLinkAction extends GenericActionSupport<Link> {
	private static final long serialVersionUID = -2159035589477995386L;
	
	private LinkService linkService;
	private String status;
	
	public String execute(){
		if (pageNum == 0) {
			pageNum = 1;
		}
		if (numPerPage == 0) {
			numPerPage = Config.getInt("PAGESIZE");
		}
		if(status==null||"".equals(status)){
			status = (String) request.getSession().getAttribute("currentLinkStatus");
			if(status==null||"".equals(status)){
				status="0,1,2";
			}
		}
		request.getSession().setAttribute("currentLinkStatus",status);
		pager = linkService.findLinksByStatusAndPageNum(status,pageNum,numPerPage);
		return SUCCESS;
	}

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
