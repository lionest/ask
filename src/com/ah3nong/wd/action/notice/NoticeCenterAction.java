package com.ah3nong.wd.action.notice;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Notice;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.security.SecurityContext;
import com.ah3nong.wd.service.NoticeService;

public class NoticeCenterAction  extends GenericActionSupport<Notice>{
	private static final long serialVersionUID = -740260890976587904L;
	
	private NoticeService noticeService;
	private int type;
	
	public String execute(){
		User user = (User)SecurityContext.getUser();
		//获得用户提醒问题
		if(type==0){
			type=1;
		}
		Notice notice = new Notice();
		notice.setUserId(user.getId());
		notice.setType(type);
		List<Map<String,Object>> noticeQuestions = noticeService.findQuestionByUserIdAndType(notice);
		
		//消除用户该类问题新消息提醒
		if(user!=null){
			Notice tmp = new Notice();
			tmp.setUserId(user.getId());
			for(int i=0;i<noticeQuestions.size();i++){
				notice.setQuestionId((Integer)noticeQuestions.get(i).get("id"));
				notice.setStatus(Config.getInt("NOTICE_READED"));
				notice.setType(type);
				noticeService.updateStatusByQuestionIdAndUserId(notice);
			}
			
			//获得notice数
			Notice notice2 = new Notice();
			notice2.setUserId(user.getId());
			//回复提醒数
			notice2.setType(Config.getInt("NOTICE_REPLY"));
			int noticeReplyCount = noticeService.countByUserIdAndType(notice2);
			request.getSession().setAttribute("noticeReplyCount",noticeReplyCount);
			//采纳数
			notice2.setType(Config.getInt("NOTICE_ACCEPT"));
			int noticeAcceptCount = noticeService.countByUserIdAndType(notice2);
			request.getSession().setAttribute("noticeAcceptCount",noticeAcceptCount);
			//评论数
			notice2.setType(Config.getInt("NOTICE_COMMENT"));
			int noticeCommentCount = noticeService.countByUserIdAndType(notice2);
			request.getSession().setAttribute("noticeCommentCount",noticeCommentCount);
			//追问数
			notice2.setType(Config.getInt("NOTICE_PROBING"));
			int noticeProbingCount = noticeService.countByUserIdAndType(notice2);
			request.getSession().setAttribute("noticeProbingCount",noticeProbingCount);
			//审核未通过数
			notice2.setType(Config.getInt("NOTICE_AUDIT_FAIL"));
			int auditFailedCount = noticeService.countByUserIdAndType(notice2);
			request.getSession().setAttribute("auditFailedCount",auditFailedCount);
			
		}
		
		request.setAttribute("noticeQuestions", noticeQuestions);
		request.setAttribute("t1", "n");
		return SUCCESS;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

}
