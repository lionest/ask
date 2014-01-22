package com.ah3nong.wd.action.replycomment;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Notice;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.bean.ReplyComment;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.NoticeService;
import com.ah3nong.wd.service.ReplyCommentService;
import com.ah3nong.wd.service.ReplyService;
import com.ah3nong.wd.util.StringHelper;

public class AddReplyCommentAction extends GenericActionSupport<ReplyComment>{
	private static final long serialVersionUID = 2061622777989643784L;
	private ReplyCommentService replyCommentService;
	private ReplyService replyService;
	private NoticeService noticeService;
	private int replyId ;
	private String replyCommentContent;
	private Map<String,Object> newComment = new HashMap<String,Object>();
	
	public String execute(){
		ReplyComment replyComment = new ReplyComment();
		replyComment.setReplyId(replyId);
		replyComment.setContent(StringHelper.encodeHTML(replyCommentContent));
		replyComment.setUserId(((User)request.getSession().getAttribute("user")).getId());
		replyComment.setCreatedTime(new Date());
		replyCommentService.addReplyComment(replyComment);
		newComment.put("username", ((User)request.getSession().getAttribute("user")).getScreenName());
		newComment.put("content",replyCommentContent);
		newComment.put("createdTime",new Date().toLocaleString());
		try {
			//增加一个Notice
			Reply tmp = replyService.findReplyByPrimaryKey(replyId);
			Notice notice = new Notice();
			notice.setQuestionId(tmp.getQuestionId());
			notice.setUserId(tmp.getUser().getId());
			notice.setType(Config.getInt("NOTICE_COMMENT"));
			notice.setCreatedTime(new Date());
			noticeService.addNotice(notice);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public void setReplyCommentService(ReplyCommentService replyCommentService) {
		this.replyCommentService = replyCommentService;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public void setReplyCommentContent(String replyCommentContent) {
		this.replyCommentContent = replyCommentContent;
	}

	public Map<String, Object> getNewComment() {
		return newComment;
	}

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
}
