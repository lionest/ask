package com.ah3nong.wd.action.audit;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionHis;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionService;

public class QuestionHisDetailAction extends GenericActionSupport<QuestionHis> {

	private static final long serialVersionUID = 8287527356649789271L;
	private QuestionService questionService;
	private Question question;
	private int id;
	
	public String execute(){
		try {
			question = questionService.findQuestionByPrimaryKey(id);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public Question getQuestion() {
		return question;
	}

	public void setId(int id) {
		this.id = id;
	}

}
