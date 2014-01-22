package com.ah3nong.wd.action.audit;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.QuestionExtraContentHis;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionService;

public class QExHisDetailAction extends GenericActionSupport {

	private static final long serialVersionUID = 8287527356649789271L;
	private QuestionService questionService;
	private QuestionExtraContentHis qExHis;
	private int id;
	
	public String execute(){
		String method = "show  questionExtraContentHis details action!";
		try {
			qExHis=questionService.getQuestionExtraContentHisByPrimaryKey(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public QuestionExtraContentHis getQExHis() {
		return qExHis;
	}


	public void setQExHis(QuestionExtraContentHis exHis) {
		qExHis = exHis;
	}


	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	

}
