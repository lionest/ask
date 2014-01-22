package com.ah3nong.wd.action.audit;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.ReplyService;

public class ReplyHisDetailAction extends GenericActionSupport<Reply> {

	private static final long serialVersionUID = 8287527356649789271L;
	private ReplyService replyService;
	private QuestionService questionService;
	
	private int id;
	private Reply reply;
	private Question question;
	
	public String execute(){
		try {
			reply = replyService.findReplyByPrimaryKey(id);
			question = questionService.findQuestionByPrimaryKey(reply.getQuestionId());
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	public Reply getReply() {
		return reply;
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
