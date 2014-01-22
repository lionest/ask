package com.ah3nong.wd.action.domain;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.QuestionService;

public class RemoveDomainAction extends GenericActionSupport<Domain> {
	private static final long serialVersionUID = -3729646257086353903L;

	private DomainService domainService;
	private QuestionService questionService;

	private int id;

	public void setId(int id) {
		this.id = id;
	}
	@Transactional(rollbackFor = ServiceException.class)
	public String execute() {
		if (id == 0) {
			logger.warn("remove domain error, the domain id is " + id);
			return "input";
		}
		try {
			//获得选中domain的nodePath然后进行级联删除
			Domain domain = domainService.findDomainByPrimaryKey(id);
			String nodePath = domain.getNodePath();
			
			List<Domain> childDomains = domainService.findChildDomain(nodePath);
			if(childDomains != null && childDomains.size() > 0){
				request.getSession().setAttribute("info", "请删除该分类全部子分类后再进行删除操作...");
				return INPUT;
			}
			Question params = new Question();
			params.setDomainId(id);
			params.setStatus(Config.getInt("QUESTION_REMOVED"));
			questionService.updateStatusByDomainId(params);
			domainService.delDomainByNodePath(nodePath);
			
			logger.info("remove domain by id="+id+" success!");
		} catch (ServiceException e) {
			logger.error("execute, find all domains exception.", e);
		}
		request.getSession().setAttribute("info", "删除成功...");
		return SUCCESS;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

}
