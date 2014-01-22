package com.ah3nong.wd.action.domain;

import java.util.List;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;

public class NewDomainAction extends GenericActionSupport<Domain> {
	private static final long serialVersionUID = -3729646257086353903L;

	private DomainService domainService;
	private String domainsStr;

	public String execute() {
		try {
			logger.info("execute,find all domains for view domain list");
			List<Domain> domains = domainService.findAllDomain();
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<domains.size();i++){
				Domain d = domains.get(i);
				if(d.getNodePath().length()==2){
					sb.append(d.getNodePath()+"0000|");
				}else if(d.getNodePath().length()==4){
					sb.append(d.getNodePath()+"00|");
				}else{
					sb.append(d.getNodePath()+"|");
				}
				sb.append(d.getName());
				sb.append(",");
			}
			sb.append("000000|无父级");
			domainsStr = sb.toString();
		} catch (ServiceException e) {
			logger.error("execute, find all domains exception.", e);
		}

		return SUCCESS;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public String getDomainsStr() {
		return domainsStr;
	}

}
