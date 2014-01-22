package com.ah3nong.wd.action.domain;

import java.util.List;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.util.StringHelper;

public class AddDomainAction extends GenericActionSupport<Domain> {
	private static final long serialVersionUID = -3729646257086353903L;

	private DomainService domainService;
	private String name;
	private int recommended;
	private String jpr1;
	private String jci1;
	private String jco1;
	private int parentId;
	
	public String execute() {
		logger.info("addDomain, the domain is:" + name);
		try {
			//获得父级
			if(!"000000".equals(jpr1)){
				if(!"".equals(jco1)){
					parentId = this.findDomainUtil(jco1).getId();
				}else if(!"".equals(jci1)){
					parentId = this.findDomainUtil(jci1).getId();
				}else{
					parentId = this.findDomainUtil(jpr1).getId();
				}
			}
			Domain domain = new Domain();
			domain.setName(StringHelper.encodeHTML(name));
			domain.setRecommended(recommended==1);
			if (parentId == 0) {
				domain.setParentId(null);
			} else {
				domain.setParentId(parentId);
			}
			int r = domainService.addDomain(domain);
			// 名称已存在
			if (r == -1) {
				List<Domain> domains = domainService.findAllDomain();
				request.setAttribute("allDomains", domains);
				request.setAttribute("errorMsg", "名称在同级分类已存在");
				request.setAttribute("domain", domain);
				return INPUT;
			}else{
				request.getSession().setAttribute("info", "领域添加成功...");
			}
			logger.info("addDomain,add domain success with name= "
					+ domain.getName());

		} catch (ServiceException e) {
			logger.error("addDomain, add domain failed.", e);
			// request.setAttribute("addResult", Config.getString("add_fail"));
		}
		return SUCCESS;
	}
	public Domain findDomainUtil(String path){
		Domain domain = new Domain();
		try {
			String nodePath;
			if("0000".equals(path.substring(2, 6))){
				nodePath = path.substring(0,2);
			}else if("00".equals(path.substring(4, 6))){
				nodePath = path.substring(0,4);
			}else{
				nodePath = path;
			};
			domain = domainService.findDomainByNodePath(nodePath);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return domain;
	}
	
	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setRecommended(int recommended) {
		this.recommended = recommended;
	}

	public void setJpr1(String jpr1) {
		this.jpr1 = jpr1;
	}

	public void setJci1(String jci1) {
		this.jci1 = jci1;
	}

	public void setJco1(String jco1) {
		this.jco1 = jco1;
	}
	
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
