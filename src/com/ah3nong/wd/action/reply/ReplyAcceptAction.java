package com.ah3nong.wd.action.reply;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Notice;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionScore;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.NoticeService;
import com.ah3nong.wd.service.QuestionScoreService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.ReplyService;

public class ReplyAcceptAction extends GenericActionSupport<Reply> {

	private static final long serialVersionUID = 8287527356649789271L;
	private ReplyService replyService;
	private QuestionService questionService;
	private QuestionScoreService questionScoreService;
	private NoticeService noticeService;
	
	private int userId;
	private int questionId;
	private int score;
	
	@Transactional(rollbackFor = Exception.class)
	public String execute(){
		try {
			Question currtenQuestion = questionService.findQuestionByPrimaryKey(questionId);
			int questionStatus = currtenQuestion.getStatus();
			if(questionStatus==Config.getInt("QUESTION_RESOLVED")){
				request.setAttribute("tips", "该问题已有满意答案请勿重复设置...");
			}else{
				Reply reply = new Reply();
				reply.setQuestionId(questionId);
				reply.setUserId(userId);
				List<Reply> replyList = new ArrayList<Reply>();
				//查找出需要更改属性为被接受的回复
				replyList = replyService.findReplysToAccept(reply);
				//更新回复为被选为满意答案
				Reply updateReply = new Reply();
				updateReply.setAccepted(Config.getInt("REPLY_ACCEPT"));
				for(Reply reply2:replyList){
					updateReply.setId(reply2.getId());
					replyService.updateReplyByPrimaryKey(updateReply, true);
				}
				//更新问题为已解决
				Question question = new Question();
				question.setId(questionId);
				question.setStatus(Config.getInt("QUESTION_RESOLVED"));
				question.setSolvedTime(new Date());
				questionService.updateQuestionByPrimaryKey(question, true);
				//保存问题分数
				QuestionScore questionScore = new QuestionScore();
				questionScore.setQuestionId(questionId);
				questionScore.setReplyUserId(userId);
				questionScore.setScore(score);
				questionScore.setCreatedTime(new Date());
				questionScore.setExperience(currtenQuestion.getExperience());
				questionScoreService.addQuestionScore(questionScore);
				//增加一个Notice
				Notice notice = new Notice();
				notice.setQuestionId(questionId);
				notice.setUserId(userId);
				notice.setType(Config.getInt("NOTICE_ACCEPT"));
				notice.setCreatedTime(new Date());
				noticeService.addNotice(notice);
				
				request.setAttribute("tips", "成功设置满意回复！");
			}
			request.setAttribute("questionId", questionId);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setQuestionScoreService(QuestionScoreService questionScoreService) {
		this.questionScoreService = questionScoreService;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
}
