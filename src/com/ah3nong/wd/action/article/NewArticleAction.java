package com.ah3nong.wd.action.article;

import java.util.List;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Article;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;

public class NewArticleAction extends GenericActionSupport<Article> {
	private static final long serialVersionUID = -2367739041123202612L;

	private DomainService domainService;
	private String domainsStr;

	public String execute() {
		try {
			List<Domain> domains = domainService.findAllDomain();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < domains.size(); i++) {
				Domain d = domains.get(i);
				if (d.getNodePath().length() == 2) {
					sb.append(d.getNodePath() + "0000|");
				} else if (d.getNodePath().length() == 4) {
					sb.append(d.getNodePath() + "00|");
				} else {
					sb.append(d.getNodePath() + "|");
				}
				sb.append(d.getName());
				sb.append(",");
			}
			domainsStr = sb.toString();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getDomainsStr() {
		return domainsStr;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

}
