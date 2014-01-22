package com.ah3nong.wd.action.reply;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.security.SecurityContext;
import com.ah3nong.wd.service.KeywordService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.ReplyService;
import com.ah3nong.wd.service.SysdataService;
import com.ah3nong.wd.util.StringHelper;

public class EditReplyAction extends GenericActionSupport<Reply> {

	private static final long serialVersionUID = 8287527356649789271L;

	private ReplyService replyService;
	private KeywordService keywordService;
	private QuestionService questionService;
	private SysdataService sysdataService;
	private Reply reply;
	private String replyContent;

	@Transactional(rollbackFor = Exception.class)
	public String execute() {
		User user = (User) SecurityContext.getUser();
		try {
			reply.setContent(StringHelper.encodeHTML(replyContent));
			
			Reply tmpReply = replyService.findReplyByPrimaryKey(reply.getId());
			int audit = Integer.parseInt(sysdataService.findByName("auditReply").getContent());
			// 1.如果用户选择的为关键字审核
			if (audit == 2) {
				// 如果包含关键字，设置回复状态为审核中
				if (reply.getContent() != null && keywordService.findKeywordInString(reply.getContent())) {
					reply.setStatus(Config.getInt("REPLY_AUDITING"));
					// 否则设置为正常状态
				} else {
					reply.setStatus(Config.getInt("REPLY_UNPROBING"));
				}

			// 2.如果用户选择的为全部审核
			} else if (audit == 1) {
				reply.setStatus(Config.getInt("REPLY_AUDITING"));

			// 3.如果用户选择的为全部不审核
			} else {
				reply.setStatus(Config.getInt("REPLY_UNPROBING"));
			}

			reply.setUpdatedTime(new Date());
			replyService.updateReplyByPrimaryKey(reply, true);
			reply = replyService.findReplyByPrimaryKey(reply.getId());
			request.setAttribute("questionId", reply.getQuestionId());
			if(user!=null){
				request.setAttribute("userId", user.getId());
			}
			
			if((reply.getStatus()==Config.getInt("REPLY_AUDITING") || reply.getStatus()==Config.getInt("REPLY_FAILED_AUDITING") )&& tmpReply.getStatus() ==Config.getInt("REPLY_UNPROBING") ){
				//如果是第一次回答,问题回复数-1
				if (tmpReply.getProbingId() == null ) {
					Question tmpQuestion = questionService.findQuestionByPrimaryKey(tmpReply.getQuestionId());
					Question question = new Question();
					question.setId(tmpReply.getQuestionId());
					question.setReplyNum(tmpQuestion.getReplyNum() - 1);
					questionService.updateQuestionByPrimaryKey(question, true);
				}
			}else if(reply.getStatus()==Config.getInt("REPLY_UNPROBING") && (tmpReply.getStatus() ==Config.getInt("REPLY_AUDITING")||tmpReply.getStatus() ==Config.getInt("REPLY_FAILED_AUDITING")) ){
				if (tmpReply.getProbingId() == null ) {
					Question tmpQuestion = questionService.findQuestionByPrimaryKey(tmpReply.getQuestionId());
					Question question = new Question();
					question.setId(tmpReply.getQuestionId());
					question.setReplyNum(tmpQuestion.getReplyNum() + 1);
					questionService.updateQuestionByPrimaryKey(question, true);
				}
			}
			
			if(reply.getStatus()==Config.getInt("REPLY_AUDITING")){
				request.setAttribute("tips", "成功修改回复，回复将在审核通过后显示在页面上！");
			}else{
				request.setAttribute("tips", "成功修改回复！");
			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setSysdataService(SysdataService sysdataService) {
		this.sysdataService = sysdataService;
	}

}
