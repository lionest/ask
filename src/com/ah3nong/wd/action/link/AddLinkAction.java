package com.ah3nong.wd.action.link;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Link;
import com.ah3nong.wd.service.LinkService;

public class AddLinkAction extends GenericActionSupport<Link> {
	private static final long serialVersionUID = -5689857784496272627L;
	private LinkService linkService;
	private String name;
	private String url;
	private String info;
	private int status;
	public String execute(){
		Link tmp = linkService.findLinksByUrl(url);
		if(tmp==null){
			Link link = new Link();
			link.setName(name);
			link.setUrl(url);
			link.setStatus(status);
			linkService.addLink(link);
			info="提交成功";
			return SUCCESS;
		}else{
			info="该链接已经存在，请勿重复申请。";
			return INPUT;
		}
		
	}
	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getInfo() {
		return info;
	}
	
}
