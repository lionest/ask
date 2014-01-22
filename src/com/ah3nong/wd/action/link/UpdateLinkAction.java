package com.ah3nong.wd.action.link;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Link;
import com.ah3nong.wd.service.LinkService;

public class UpdateLinkAction extends GenericActionSupport<Link> {
	private static final long serialVersionUID = -797212475550295687L;
	private Link link;
	private LinkService linkService;
	private String info;
	
	public String execute(){
		linkService.updateLink(link);
		info="修改成功";
		return SUCCESS;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}

	public String getInfo() {
		return info;
	}

}
