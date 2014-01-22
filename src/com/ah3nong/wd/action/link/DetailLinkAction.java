package com.ah3nong.wd.action.link;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Link;
import com.ah3nong.wd.service.LinkService;

public class DetailLinkAction extends GenericActionSupport<Link> {
	private static final long serialVersionUID = 5635721456936525552L;
	
	private LinkService linkService;
	private int id;
	private Link link;
	
	public String execute(){
		link = linkService.findById(id);
		return SUCCESS;
	}

	public Link getLink() {
		return link;
	}

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
