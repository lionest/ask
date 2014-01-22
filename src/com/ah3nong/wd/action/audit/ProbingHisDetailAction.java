package com.ah3nong.wd.action.audit;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Probing;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.ProbingService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.ReplyService;

public class ProbingHisDetailAction extends GenericActionSupport<Probing> {

	private static final long serialVersionUID = 8287527356649789271L;
	private ProbingService probingService;
	private QuestionService questionService;
	private ReplyService replyService;
	private int id;
	private Probing probing;
	private Question question;
	private Reply reply;
	private int status ;
	
	public String execute(){
		try {
			probing = probingService.findProbingByPrimaryKey(id);
			question = questionService.findQuestionByPrimaryKey(probing.getQuestionId());
			reply = replyService.findReplyByPrimaryKey(probing.getReplyId());
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public Probing getProbing() {
		return probing;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Question getQuestion() {
		return question;
	}

	public Reply getReply() {
		return reply;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProbingService(ProbingService probingService) {
		this.probingService = probingService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
}
