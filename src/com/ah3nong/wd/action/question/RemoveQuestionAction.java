package com.ah3nong.wd.action.question;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionService;

public class RemoveQuestionAction extends GenericActionSupport<Question> {

	private static final long serialVersionUID = 8287527356649789271L;
	
	private QuestionService questionService;
	private Question question;
	
	@Transactional(rollbackFor = Exception.class)
	public String execute(){
		String method = "remove the question Action!";
		try {
			question.setStatus(Config.getInt("QUESTION_REMOVED"));
			questionService.updateQuestionByPrimaryKey(question, true);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	
}
