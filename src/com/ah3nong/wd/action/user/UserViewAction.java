package com.ah3nong.wd.action.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.ExpertAttachment;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.security.SecurityContext;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.ExpertAttachmentService;
import com.ah3nong.wd.service.ExpertService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.UserService;

/**
 * 用户信息页面
 */
public class UserViewAction extends GenericActionSupport<User> {

	private static final long serialVersionUID = 1L;
	private static final int ACCEPTED_EXPERT_COUNT = 8;
	private static final int MOST_VIEWCOUNT_QUESTION_COUNT = 8;
	private static final int USER_DOMAIN_COUNT = 5;
	private static final int PAGE_SIZE = 16; // 问答分类列表页的问题列表默认显示条数

	private QuestionService questionService;
	private UserService userService;
	private DomainService domainService;
	private ExpertService expertService;

	private List<Map<String, Object>> expertAcceptedCountMap;
	private List<Map<String, Object>> questionByUser;
	private List<Map<String, Object>> userDomains = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> expertReplyNumMap;
	private int viewUserId;
	private int count;
	private int countSize;
	private int currentPage = 1;
	private String s = null;
	private int askCount;
	private String sovedPercent;
	private int replyCount;
	private String acceptedPrecent;
	private User viewUser;
	private Map<String, Object> expert;
	private List<Question> mostViewCountQuestions = new ArrayList<Question>();
	private List<Question> experienceQuestions = new ArrayList<Question>();
	private ExpertAttachmentService expertAttachmentService;
	private List<ExpertAttachment> attachments;

	public String execute() {
		try {
			User user = SecurityContext.getUser();

			if (user != null) {
				if (user.getId() == viewUserId) {
					return "toMyCenter";
				}
			}

			viewUser = userService.findUserByPrimaryKey(viewUserId);

			if (viewUser.getExpert() == 1) {
				List<Map<String, Object>> expertList = expertService.getExpertsByExpertId(viewUserId);
				if (expertList.size() > 0) {
					expert = expertList.get(0);
				}
			}

			// 获得用户关注领域
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", viewUserId);
			map.put("count", USER_DOMAIN_COUNT);
			userDomains = domainService.findDomainByUserAskQuestion(map);

			// 获得用户提问数
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", viewUserId);
			askCount = questionService.countQuestionsByUserId(params);

			// 结帖率
			int questionsHaveReplyCount = questionService.countQuestionsHaveReplyByUserId(params);
			if (questionsHaveReplyCount == 0) {
				sovedPercent = "0%";
			} else {
				int askResovedCount = questionService.countResolvedQuestionsByUserId(params);
				if (askResovedCount == 0) {
					sovedPercent = "0%";
				} else {
					sovedPercent = (int) ((double) askResovedCount / (double) questionsHaveReplyCount * 100) + "%";
				}
			}

			// 获得用户回复数和采纳率
			replyCount = userService.countReplyByUserid(viewUserId);
			request.getSession().setAttribute("replyCount", replyCount);
			if (replyCount == 0) {
				acceptedPrecent = "0%";
			} else {
				int acceptedCount = userService.countAcceptedReplyByUserid(viewUserId);
				if (acceptedCount == 0) {
					acceptedPrecent = "0%";
				} else {
					acceptedPrecent = (int) ((double) acceptedCount / (double) replyCount * 100) + "%";
				}
			}

			// 查询时的开始条数 （因为service里面有 -1 所以在这 +1）
			int start = (currentPage - 1) * PAGE_SIZE + 1;
			
			//用户问题列表，图片，视频
			if(s == null || "".equals(s) || "q".equals(s)){
				// 根据用户ID获得用户回答的 问题
				questionByUser = questionService.getQuestionsBuExpertId(viewUserId, start, PAGE_SIZE);
				count = new Long(questionService.getCountQuestionsByExpertId(viewUserId)).intValue();
				s = "q";
			} else if ("a".equals(s)){
				// 根据用户ID获得 用户提问问题
				questionByUser = questionService.getQuestionsAskByExpert(viewUserId, start, PAGE_SIZE);
				count = new Long(questionService.getCountQuestionsAskByExpert(viewUserId)).intValue();
			} else if ("pic".equals(s) || "movie".equals(s)){
				//获得专家附件
				attachments = expertAttachmentService.findByUserId(viewUserId);
			}
			
			
			if (count % PAGE_SIZE == 0) {
				countSize = count / PAGE_SIZE;
			} else {
				countSize = (count + PAGE_SIZE - 1) / PAGE_SIZE;
			}
			// 获得点击率最多问题
			mostViewCountQuestions = questionService.findMostViewCountQuestions(MOST_VIEWCOUNT_QUESTION_COUNT);
			// 获得悬赏问题
			experienceQuestions = questionService.getAllQuestionsAndReplyNum(1, MOST_VIEWCOUNT_QUESTION_COUNT, "experience", 0, 0);

			// 获取推荐专家-按采纳数排序
			expertAcceptedCountMap = expertService.getAllExpertsAndReplyNumAndIsAccepteds(0, ACCEPTED_EXPERT_COUNT, 0);

			// 按回答数排序专家
			expertReplyNumMap = expertService.getAllExpertsAndReplyNumc(0, ACCEPTED_EXPERT_COUNT, 0);
			
		} catch (ServiceException e) {
			e.printStackTrace();
			return ERROR;
		}

		return SUCCESS;
	}

	public List<Map<String, Object>> getExpertAcceptedCountMap() {
		return expertAcceptedCountMap;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Map<String, Object>> questionByUser() {
		return questionByUser;
	}

	public int getCount() {
		return count;
	}

	public int getCountSize() {
		return countSize;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public List<Map<String, Object>> getQuestionByUser() {
		return questionByUser;
	}

	public int getAskCount() {
		return askCount;
	}

	public String getSovedPercent() {
		return sovedPercent;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public String getAcceptedPrecent() {
		return acceptedPrecent;
	}

	public User getViewUser() {
		return viewUser;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setViewUserId(int viewUserId) {
		this.viewUserId = viewUserId;
	}

	public List<Map<String, Object>> getUserDomains() {
		return userDomains;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public Map<String, Object> getExpert() {
		return expert;
	}

	public void setExpertService(ExpertService expertService) {
		this.expertService = expertService;
	}

	public List<Question> getMostViewCountQuestions() {
		return mostViewCountQuestions;
	}

	public List<Question> getExperienceQuestions() {
		return experienceQuestions;
	}

	public List<Map<String, Object>> getExpertReplyNumMap() {
		return expertReplyNumMap;
	}

	public void setExpertAttachmentService(ExpertAttachmentService expertAttachmentService) {
		this.expertAttachmentService = expertAttachmentService;
	}

	public List<ExpertAttachment> getAttachments() {
		return attachments;
	}

}
