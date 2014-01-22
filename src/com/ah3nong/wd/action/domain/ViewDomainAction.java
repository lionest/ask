package com.ah3nong.wd.action.domain;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.QuestionService;

public class ViewDomainAction extends GenericActionSupport<Domain> {
	private static final long serialVersionUID = -3729646257086353903L;

	private DomainService domainService;
	private QuestionService questionService;
	//页面分类列表
	private List<Map<String, Object>> mostDomainAndQuestionsNum;
	//导航分类
	private List<Domain> domains;
	//传来的分类ID
	private int domain;

	public String execute() {
		try {
			int t = 1;
			domains = domainService.findDomainsByNodePath(domain);
			if (domain == 0) {
				mostDomainAndQuestionsNum = questionService.getMostDomainNameAndQuestionNumAll("", t, domain);
				
			} else {
				mostDomainAndQuestionsNum = questionService.getMostDomainNameAndQuestionNum("", t, domain);
			}

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public List<Map<String, Object>> getMostDomainAndQuestionsNum() {
		return mostDomainAndQuestionsNum;
	}

	public List<Domain> getDomains() {
		return domains;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setDomain(int domain) {
		this.domain = domain;
	}

}
