package com.ah3nong.wd.action.link;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Link;
import com.ah3nong.wd.service.LinkService;

public class UpdateLinkStatusAction extends GenericActionSupport<Link> {
	private static final long serialVersionUID = 6417704013899593648L;
	private int id;
	private int status;
	private LinkService linkService;
	public String execute(){
		Link link = new Link();
		link.setId(id);
		link.setStatus(status);
		linkService.updateLink(link);
		return SUCCESS;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}

}
