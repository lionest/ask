package com.ah3nong.wd.action.reply;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Probing;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.ProbingService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.ReplyService;

public class RemoveReplyAction extends GenericActionSupport<Reply> {

	private static final long serialVersionUID = 8287527356649789271L;
	private ReplyService replyService;
	private ProbingService probingService;
	private QuestionService questionService;
	private Reply reply;
	
	@Transactional(rollbackFor = Exception.class)
	public String execute(){
		String method = "remove reply action";
		//更改标记为删除		
		try {
			reply.setStatus(Config.getInt("REPLY_REMOVE"));
			replyService.updateReplyByPrimaryKey(reply, true);
			reply=replyService.findReplyByPrimaryKey(reply.getId());
			//删除恢复则判断这则回复是否是针对追问的
			//如果针对追问则把该追问的状态改为“未回复”，如果不是，则把问题想对应的回复数量-1
			if(reply.getProbingId() == null){
				Question question = questionService.findQuestionByPrimaryKey(reply.getQuestionId());
				//question.setId(reply.getQuestionId());
				question.setReplyNum(question.getReplyNum()-1);
				questionService.updateQuestionByPrimaryKey(question, true);
			}else{
				Probing probing = new Probing();
				probing.setId(reply.getProbingId());
				probing.setStatus(Config.getInt("PROBING_UNREPLIED"));
				probingService.updateProbingByPrimaryKey(probing, true);
				
			}
			request.setAttribute("questionId", reply.getQuestionId());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public ReplyService getReplyService() {
		return replyService;
	}
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	public Reply getReply() {
		return reply;
	}
	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public void setProbingService(ProbingService probingService) {
		this.probingService = probingService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	

}
