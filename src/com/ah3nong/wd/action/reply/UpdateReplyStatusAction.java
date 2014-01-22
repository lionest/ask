package com.ah3nong.wd.action.reply;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Notice;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.NoticeService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.ReplyService;

public class UpdateReplyStatusAction extends GenericActionSupport<Reply> {
	private static final long serialVersionUID = -3727405271706638511L;

	private int id;
	private int status;

	private ReplyService replyService;
	private QuestionService questionService;
	private NoticeService noticeService;

	public String execute() {
		try {
			Reply reply = new Reply();
			reply.setId(id);
			if(status==1){
				Reply tmpReply = replyService.findReplyByPrimaryKey(id);
				
				//如果是第一次回答,问题回复数加1
				if (tmpReply.getProbingId() == null ) {
					Question tmpQuestion = questionService.findQuestionByPrimaryKey(tmpReply.getQuestionId());
					Question question = new Question();
					question.setId(tmpReply.getQuestionId());
					question.setReplyNum(tmpQuestion.getReplyNum() + 1);
					questionService.updateQuestionByPrimaryKey(question, true);
					
					// 增加一个Notice
					Notice notice = new Notice();
					notice.setQuestionId(tmpQuestion.getId());
					notice.setUserId(tmpQuestion.getUser().getId());
					notice.setType(Config.getInt("NOTICE_REPLY"));
					notice.setCreatedTime(new Date());
					// 获得锚点
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("questionId", tmpQuestion.getId());
					List<Reply> replyList = replyService.findReplyByQuestionId(map);
					if (replyList.size() > 0) {
						for (int j = 0; j < replyList.size(); j++) {
							User tmpUser = replyList.get(j).getUser();
							int uid1 = tmpUser.getId();
							int uid2 = tmpReply.getUser().getId();
							if (uid1 == uid2) {
								notice.setAnchor(replyList.get(j).getId());
							}
						}
					}
					noticeService.addNotice(notice);
				}
			}
			reply.setStatus(status);
			replyService.updateReplyByPrimaryKey(reply, true);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

}
