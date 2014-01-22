package com.ah3nong.wd.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Notice;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.Topic;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.NoticeService;
import com.ah3nong.wd.service.QuestionKeywordService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.TopicService;
import com.ah3nong.wd.service.UserService;

public class IndexAction extends GenericActionSupport<User> {
	private static final long serialVersionUID = 8620324670855120241L;

	//专家被采纳的问题数的排行取前多少条
	private static final int ACCEPTED_EXPERT_COUNT = 8;

	private List<Map<String,Object>> expertAcceptedCountMap;
	private List<Map<String, Object>> imgQuestions;
	private String acceptedPrecent;
	private List<Map<String, Object>> qkList;
	private List<Question> topicQuestions = new ArrayList<Question>();
	private Topic topic;
	
	private TopicService topicService;
	private QuestionService questionService;
	private QuestionKeywordService questionKeywordService;
	private NoticeService noticeService;
	private UserService userService;
	
	private int resolvedCount;
	private int noResolveCount;
	private int todayQuestionCount;
	private int todayReplyCount;

	public String execute() throws ServiceException {
		//获取最热关键字
		qkList = questionKeywordService.findHotKeywords(20);

		//获取推荐专家以及被采纳的问题的数量
		expertAcceptedCountMap = questionService.getUserAcceptCountMap(ACCEPTED_EXPERT_COUNT);
		
		//获取首页图片新闻5条
		imgQuestions = questionService.getRecommendQuestionsWithImage();
		
		//获取专题信息
		topic = topicService.findLatestTopic();
		if(topic!=null){
			String qids = topic.getQuestionIds();
			if(qids!=null&&!"".equals(qids)){
				String[] ids = qids.split("_");
				for(int i=0;i<ids.length;i++){
					Question q = questionService.findQuestionByPrimaryKey(Integer.parseInt(ids[i]));
					topicQuestions.add(q);
				}
			}
		}
		
		//获得所有已解决问题数
		resolvedCount = questionService.countAllQuestion("resolved");
		//获得所有待解决问题数
		noResolveCount = questionService.countAllQuestion("noResolve");
		//获得今日问题数
		todayQuestionCount = questionService.countTodayQuestion();
		//获得今日回答数
		todayReplyCount = questionService.countTodayReply();
		
		User user = (User) request.getSession().getAttribute("user");
		if(user!=null){
			User tmp = userService.findUserByPrimaryKey(user.getId());
			request.getSession().setAttribute("user",tmp);
			//获得用户回复数
			int replyCount = userService.countReplyByUserid(user.getId());
			request.getSession().setAttribute("replyCount", replyCount);
			//获得用户提问数
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("userId",user.getId());
			int askCount = questionService.countQuestionsByUserId(params);
			request.getSession().setAttribute("askCount", askCount);
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

	public List<Map<String, Object>> getImgQuestions() {
		return imgQuestions;
	}

	public String getAcceptedPrecent() {
		return acceptedPrecent;
	}

	public void setAcceptedPrecent(String acceptedPrecent) {
		this.acceptedPrecent = acceptedPrecent;
	}

	public void setQuestionKeywordService(QuestionKeywordService questionKeywordService) {
		this.questionKeywordService = questionKeywordService;
	}

	public List<Map<String, Object>> getQkList() {
		return qkList;
	}

	public List<Question> getTopicQuestions() {
		return topicQuestions;
	}

	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public int getResolvedCount() {
		return resolvedCount;
	}

	public int getNoResolveCount() {
		return noResolveCount;
	}

	public int getTodayQuestionCount() {
		return todayQuestionCount;
	}

	public int getTodayReplyCount() {
		return todayReplyCount;
	}
	
}
