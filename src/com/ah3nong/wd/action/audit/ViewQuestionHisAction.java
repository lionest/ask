package com.ah3nong.wd.action.audit;

import java.util.HashMap;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionService;

public class ViewQuestionHisAction extends GenericActionSupport<Question> {

	private static final long serialVersionUID = -3729646257086353903L;
	private QuestionService questionService;
	private String status;
	
	public String execute(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", status);
			
			pager = questionService.getQuestionHisPagerByParam(params, pageNum, 10);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
