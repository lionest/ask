package com.ah3nong.wd.action.notice;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Notice;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.security.SecurityContext;
import com.ah3nong.wd.service.NoticeService;

public class ShowNoticeCountAction extends GenericActionSupport<Notice>{
	private static final long serialVersionUID = -8697675985240582839L;
	private NoticeService noticeService;
	
	private int noticeReplyCount;
	private int noticeAcceptCount;
	private int noticeCommentCount;
	private int noticeProbingCount;
	private int auditFailedCount ;
	
	public String execute(){
		User user = SecurityContext.getUser();
		if(user!=null){
			//获得notice数
			Notice notice = new Notice();
			notice.setUserId(user.getId());
			//回复提醒数
			notice.setType(Config.getInt("NOTICE_REPLY"));
			noticeReplyCount = noticeService.countByUserIdAndType(notice);
			request.getSession().setAttribute("noticeReplyCount",noticeReplyCount);
			//采纳数
			notice.setType(Config.getInt("NOTICE_ACCEPT"));
			noticeAcceptCount = noticeService.countByUserIdAndType(notice);
			request.getSession().setAttribute("noticeAcceptCount",noticeAcceptCount);
			//评论数
			notice.setType(Config.getInt("NOTICE_COMMENT"));
			noticeCommentCount = noticeService.countByUserIdAndType(notice);
			request.getSession().setAttribute("noticeCommentCount",noticeCommentCount);
			//追问数
			notice.setType(Config.getInt("NOTICE_PROBING"));
			noticeProbingCount = noticeService.countByUserIdAndType(notice);
			request.getSession().setAttribute("noticeProbingCount",noticeProbingCount);
			//审核未通过数
			notice.setType(Config.getInt("NOTICE_AUDIT_FAIL"));
			auditFailedCount = noticeService.countByUserIdAndType(notice);
			request.getSession().setAttribute("auditFailedCount",auditFailedCount);
			return SUCCESS;
		}else{
			return ERROR;
		}
	}

	public int getNoticeReplyCount() {
		return noticeReplyCount;
	}

	public int getNoticeAcceptCount() {
		return noticeAcceptCount;
	}

	public int getNoticeCommentCount() {
		return noticeCommentCount;
	}

	public int getNoticeProbingCount() {
		return noticeProbingCount;
	}

	public int getAuditFailedCount() {
		return auditFailedCount;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
}
