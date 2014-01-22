package com.ah3nong.wd.action.domain;

import java.util.List;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.util.StringHelper;

public class EditDomainAction extends GenericActionSupport<Domain> {
	private static final long serialVersionUID = -3729646257086353903L;

	private DomainService domainService;
	private Domain domain;
	private int id;
	private List<Domain> domains;
	private String info;
	private int parentId;
	private int recommended;

	public int getRecommended() {
		return recommended;
	}

	public void setRecommended(int recommended) {
		this.recommended = recommended;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<Domain> getDomains() {
		return domains;
	}

	public void setDomains(List<Domain> domains) {
		this.domains = domains;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String findDomains() {
		logger.info("updateDomain, the domain is:" + id);
		try {
			if (id == 0) {
				logger.error("EditDomainAction,id is null");
			}
			domain = domainService.findDomainByPrimaryKey(id);
			domains = domainService.findAllDomain();
		} catch (ServiceException e) {
			logger.error("addDomain, add domain failed.", e);
			// request.setAttribute("addResult", Config.getString("add_fail"));
		}
		return SUCCESS;
	}

	public String editDomain() {
		logger.info("updateDomain, the domain is:" + id);
		try {
			if (domain == null) {
				logger.error("EditDomainAction,id is null");
			}
			logger.info("editDomain,domian:" + domain.toString());
			domain.setId(id);
			domain.setParentId(parentId);
			domain.setRecommended(recommended == 1);
			domain.setName(StringHelper.encodeHTML(domain.getName()));
			int r = domainService.updateDomainByPrimaryKey(domain);
			if (r == -1) {
				List<Domain> domains = domainService.findAllDomain();
				request.setAttribute("allDomains", domains);
				request.setAttribute("errorMsg", "名称在同级分类已存在");
				request.setAttribute("domain", domain);
				return INPUT;
			}
			info = "修改成功！";
			request.getSession().setAttribute("info", info);
			return SUCCESS;
		} catch (ServiceException e) {
			logger.error("updateDomain, add domain failed.", e);
			info = "修改失败";
			request.getSession().setAttribute("info", info);
			return ERROR;
		}
	}

	// public String recommend() {
	// if (id == 0) {
	// logger.warn("recommend domain error, the domain id is " + id);
	// return "input";
	// }
	// try {
	// Domain domain=domainService.findDomainByPrimaryKey(id);
	// if(domain==null){
	// logger.warn("the domain id is invalid.can not found any domain");
	// return "input";
	// }
	// domain.setRecommended(true);
	// domainService.updateDomainByPrimaryKey(domain);
	//
	// logger.info("recomend domain by id=" + id + " success!");
	// } catch (ServiceException e) {
	// logger.error("execute, find all domains exception.", e);
	// }
	// return SUCCESS;
	// }

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public DomainService getDomainService() {
		return domainService;
	}

}
