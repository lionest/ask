package com.ah3nong.wd.action.freemaker;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.bean.Expert;
import com.ah3nong.wd.bean.Link;
import com.ah3nong.wd.bean.Notice;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.Topic;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.ExpertService;
import com.ah3nong.wd.service.LinkService;
import com.ah3nong.wd.service.NoticeService;
import com.ah3nong.wd.service.QuestionKeywordService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.TopicService;
import com.ah3nong.wd.service.UserService;
import com.ah3nong.wd.util.DateUtil;
import com.ah3nong.wd.util.FreeMarkerUtil;

public class IndexFmAction extends GenericActionSupport<Question> {
	private static final long serialVersionUID = -9176609514603548202L;
	private DomainService domainService;
	private QuestionService questionService;
	private QuestionKeywordService questionKeywordService;
	private ExpertService expertService;
	private UserService userService;
	private NoticeService noticeService;
	private TopicService topicService;
	private LinkService linkService;

	public String execute() {
		try {
			Map<String, Object> root = new HashMap<String, Object>();
			String bathPath = request.getContextPath();
			// 分类
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("recommended", 1);
			params.put("orderBy", "node_path");
			List<Domain> domains = domainService.findDomain(params);
			// 热门标签
			List<Map<String, Object>> qkList = questionKeywordService.findHotKeywords(20);
			// 幻灯片问题
			List<Map<String, Object>> imgQuestions = questionService.getRecommendQuestionsWithImage();
			// 推荐问题
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("count", 6);
			params1.put("recommended", 1);
			params1.put("orderBy", "created_time desc");
			Date now = new Date();
			List<Question> recommendedQuestions = questionService.findQuestion(params1);
			List<Integer> askForExpertList0 = new ArrayList<Integer>();
			List<Integer> newQuestions0 = new ArrayList<Integer>();
			for (Question question : recommendedQuestions) {
				// 看是否专家
				if (questionService.findExpertByQuestionId(question.getId()) != null) {
					askForExpertList0.add((Integer) question.getId());
				} else {
					// 看是否是最新问题
					if (now.getTime() - question.getCreatedTime().getTime() < (1000 * 60 * 60 * 6)) {
						newQuestions0.add((Integer) question.getId());
					}
				}
			}
			// 最新问题
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("count", 8);
			params2.put("orderBy", "created_time desc");
			List<Question> latestOpenedQuestions = questionService.findQuestion(params2);
			if (latestOpenedQuestions.size() > 0) {
				for (int i = 0; i < latestOpenedQuestions.size(); i++) {
					String timeStr = latestOpenedQuestions.get(i).getCreatedTime().toLocaleString();
					DateUtil.getTime(timeStr);
					latestOpenedQuestions.get(i).setExtraContent(DateUtil.getTime(timeStr));
				}
			}
			List<Integer> askForExpertList = new ArrayList<Integer>();
			List<Integer> newQuestions = new ArrayList<Integer>();
			for (Question question : latestOpenedQuestions) {
				// 看是否专家

				if (questionService.findExpertByQuestionId(question.getId()) != null) {
					askForExpertList.add((Integer) question.getId());
				} else {
					// 看是否是最新问题
					if (now.getTime() - question.getCreatedTime().getTime() < (1000 * 60 * 60 * 6)) {
						newQuestions.add((Integer) question.getId());
					}
				}

			}
			// 专家团队
			Map<String, Object> params3 = new HashMap<String, Object>();
			params3.put("count", 6);
			params3.put("recommended", 1);
			List<Expert> recommendedExperts = expertService.findExpert(params3);
			// 已解决问题
			Map<String, Object> params4 = new HashMap<String, Object>();
			params4.put("count", 8);
			params4.put("status", 2);
			params4.put("orderBy", "solved_time desc");
			List<Question> latestSolvedQuestions = questionService.findQuestion(params4);
			if (latestSolvedQuestions.size() > 0) {
				for (int i = 0; i < latestSolvedQuestions.size(); i++) {
					String timeStr = latestSolvedQuestions.get(i).getCreatedTime().toLocaleString();
					DateUtil.getTime(timeStr);
					latestSolvedQuestions.get(i).setExtraContent(DateUtil.getTime(timeStr));
				}
			}
			List<Integer> askForExpertList1 = new ArrayList<Integer>();
			List<Integer> newQuestions1 = new ArrayList<Integer>();
			for (Question question : latestSolvedQuestions) {
				// 看是否专家
				try {
					if (questionService.findExpertByQuestionId(question.getId()) != null) {
						askForExpertList1.add((Integer) question.getId());
					} else {
						// 看是否是最新问题
						if (now.getTime() - question.getCreatedTime().getTime() < (1000 * 60 * 60 * 6)) {
							newQuestions1.add((Integer) question.getId());
						}
					}
				} catch (ServiceException e) {
					e.printStackTrace();
				}
			}
			// 获得所有已解决问题数
			int resolvedCount = questionService.countAllQuestion("resolved");
			// 获得所有待解决问题数
			int noResolveCount = questionService.countAllQuestion("noResolve");
			// 获得今日问题数
			int todayQuestionCount = questionService.countTodayQuestion();
			// 获得今日回答数
			int todayReplyCount = questionService.countTodayReply();

			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {
				root.put("user", user);
				// 获得用户回复数
				int replyCount = userService.countReplyByUserid(user.getId());
				// 获得用户提问数
				Map<String, Object> params5 = new HashMap<String, Object>();
				params5.put("userId", user.getId());
				int askCount = questionService.countQuestionsByUserId(params5);
				// 获得notice
				Notice notice = new Notice();
				notice.setUserId(user.getId());
				// 回复提醒数
				notice.setType(Config.getInt("NOTICE_REPLY"));
				int noticeReplyCount = noticeService.countByUserIdAndType(notice);
				// 采纳数
				notice.setType(Config.getInt("NOTICE_ACCEPT"));
				int noticeAcceptCount = noticeService.countByUserIdAndType(notice);
				// 评论数
				notice.setType(Config.getInt("NOTICE_COMMENT"));
				int noticeCommentCount = noticeService.countByUserIdAndType(notice);
				// 追问数
				notice.setType(Config.getInt("NOTICE_PROBING"));
				int noticeProbingCount = noticeService.countByUserIdAndType(notice);
				root.put("replyCount", replyCount);
				root.put("askCount", askCount);
				root.put("noticeReplyCount", noticeReplyCount);
				root.put("noticeAcceptCount", noticeAcceptCount);
				root.put("noticeCommentCount", noticeCommentCount);
				root.put("noticeProbingCount", noticeProbingCount);
			}
			// 获取推荐专家以及被采纳的问题的数量
			List<Map<String, Object>> expertAcceptedCountMap = questionService.getUserAcceptCountMap(8);
			// 获得知识专题
			Topic topic = topicService.findLatestTopic();
			List<Question> topicQuestions = new ArrayList<Question>();
			if (topic != null) {
				String qids = topic.getQuestionIds();
				if (qids != null && !"".equals(qids)) {
					String[] ids = qids.split("_");
					for (int i = 0; i < ids.length; i++) {
						Question q = questionService.findQuestionByPrimaryKey(Integer.parseInt(ids[i]));
						topicQuestions.add(q);
					}
				}
			}
			// 待解决问题
			Map<String, Object> params5 = new HashMap<String, Object>();
			params5.put("count", 8);
			params5.put("status", 1);
			params5.put("orderBy", "created_time desc");
			List<Question> noSolvedQuestions = questionService.findQuestion(params5);
			// 友链
			List<Link> linkList = linkService.findLinksByStatus(1);

			// 存入参数
			root.put("domains", domains);
			root.put("qkList", qkList);
			root.put("imgQuestions", imgQuestions);
			root.put("recommendedQuestions", recommendedQuestions);
			root.put("askForExpertList0", askForExpertList0);
			root.put("newQuestions0", newQuestions0);
			root.put("latestOpenedQuestions", latestOpenedQuestions);
			root.put("askForExpertList", askForExpertList);
			root.put("newQuestions", newQuestions);
			root.put("recommendedExperts", recommendedExperts);
			root.put("latestSolvedQuestions", latestSolvedQuestions);
			root.put("askForExpertList1", askForExpertList1);
			root.put("newQuestions1", newQuestions1);
			root.put("resolvedCount", resolvedCount);
			root.put("noResolveCount", noResolveCount);
			root.put("todayQuestionCount", todayQuestionCount);
			root.put("todayReplyCount", todayReplyCount);
			root.put("expertAcceptedCountMap", expertAcceptedCountMap);
			root.put("topic", topic);
			root.put("topicQuestions", topicQuestions);
			root.put("noSolvedQuestions", noSolvedQuestions);
			root.put("linkList", linkList);
			root.put("bathPath", bathPath);
			root.put("request", ServletActionContext.getRequest());

			FreeMarkerUtil.createHtml(ServletActionContext.getServletContext(), root, "/ftl", "/html", "index.ftl", "index.html");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setQuestionKeywordService(QuestionKeywordService questionKeywordService) {
		this.questionKeywordService = questionKeywordService;
	}

	public void setExpertService(ExpertService expertService) {
		this.expertService = expertService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}

}
