package com.ah3nong.wd.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Notice;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.security.SecurityContext;
import com.ah3nong.wd.service.NoticeService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.UserService;

public class SearchLoginUserAction extends GenericActionSupport<User> {
	private static final long serialVersionUID = 8620324670855120241L;

	private List<Map<String,Object>> expertAcceptedCountMap;
	private Map<String, Object> imgQuestion;
	private int replyCount;
	private UserService userService; 
	private QuestionService questionService;
	private NoticeService noticeService;

	public String execute() throws ServiceException {
		User user =  userService.findUserByPrimaryKey(SecurityContext.getUser().getId());
		request.getSession().setAttribute("user", user);
		if(user!=null){
			//获得用户提问数
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("userId",user.getId());
			int askCount = questionService.countQuestionsByUserId(params);
			request.getSession().setAttribute("askCount", askCount);
			
			//结帖率
			int questionsHaveReplyCount = questionService.countQuestionsHaveReplyByUserId(params);
			String sovedPercent ;
			if (questionsHaveReplyCount == 0) {
				sovedPercent = "0%";
			} else {
				int askResovedCount = questionService.countResolvedQuestionsByUserId(params);
				if (askResovedCount == 0) {
					sovedPercent = "0%";
				} else {
					sovedPercent = (int) ((double) askResovedCount
							/ (double) questionsHaveReplyCount * 100)
							+ "%";
				}
			}
			request.getSession().setAttribute("sovedPercent", sovedPercent);
			
			//获得用户回复数和采纳率
			replyCount = userService.countReplyByUserid(user.getId());
			request.getSession().setAttribute("replyCount", replyCount);
			String acceptedPrecent ;
			if (replyCount == 0) {
				acceptedPrecent = "0%";
			} else {
				int acceptedCount = userService.countAcceptedReplyByUserid(user
						.getId());
				if (acceptedCount == 0) {
					acceptedPrecent = "0%";
				} else {
					acceptedPrecent = (int) ((double) acceptedCount
							/ (double) replyCount * 100)
							+ "%";
				}
			}
			request.setAttribute("replyCount", replyCount);
			request.getSession().setAttribute("acceptedPrecent",
					acceptedPrecent);
			//获得notice数
			Notice notice = new Notice();
			notice.setUserId(user.getId());
			//回复提醒数
			notice.setType(Config.getInt("NOTICE_REPLY"));
			int noticeReplyCount = noticeService.countByUserIdAndType(notice);
			request.getSession().setAttribute("noticeReplyCount",noticeReplyCount);
			//采纳数
			notice.setType(Config.getInt("NOTICE_ACCEPT"));
			int noticeAcceptCount = noticeService.countByUserIdAndType(notice);
			request.getSession().setAttribute("noticeAcceptCount",noticeAcceptCount);
			//评论数
			notice.setType(Config.getInt("NOTICE_COMMENT"));
			int noticeCommentCount = noticeService.countByUserIdAndType(notice);
			request.getSession().setAttribute("noticeCommentCount",noticeCommentCount);
			//追问数
			notice.setType(Config.getInt("NOTICE_PROBING"));
			int noticeProbingCount = noticeService.countByUserIdAndType(notice);
			request.getSession().setAttribute("noticeProbingCount",noticeProbingCount);
			//审核未通过数
			notice.setType(Config.getInt("NOTICE_AUDIT_FAIL"));
			int auditFailedCount = noticeService.countByUserIdAndType(notice);
			request.getSession().setAttribute("auditFailedCount",auditFailedCount);
		}
		return SUCCESS;
	}
	
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	public List<Map<String, Object>> getExpertAcceptedCountMap() {
		return expertAcceptedCountMap;
	}

	public Map<String, Object> getImgQuestion() {
		return imgQuestion;
	}

	public void setImgQuestion(Map<String, Object> imgQuestion) {
		this.imgQuestion = imgQuestion;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
}
