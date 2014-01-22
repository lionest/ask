package com.ah3nong.wd.action.question;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.QuestionImg;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionService;

public class RemoveQuestionImgAction extends GenericActionSupport {

	private static final long serialVersionUID = 2373740238689012088L;
	private QuestionService questionService;
	private QuestionImg questionImg;
	
	@Transactional(rollbackFor = Exception.class)
	public String execute(){
		String method = "remove questionImg Action!";
		try {
			questionImg.setStatus(Config.getInt("QUESTIONIMG_REMOVE"));
			questionService.updateQuestionImgs(questionImg, true);
			questionImg = questionService.findQuestionImgByPrimaryKey(questionImg.getId());
			request.setAttribute("questionId", questionImg.getQuestionId());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public QuestionImg getQuestionImg() {
		return questionImg;
	}
	public void setQuestionImg(QuestionImg questionImg) {
		this.questionImg = questionImg;
	}
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
}
