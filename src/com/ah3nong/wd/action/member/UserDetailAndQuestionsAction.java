package com.ah3nong.wd.action.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.QuestionKeywordService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.UserService;

public class UserDetailAndQuestionsAction extends GenericActionSupport<User> {
	private static final long serialVersionUID = -6589575043801154578L;
	// 每页列出问题数
	private static final int COUNT = 20;
	//热门标签数
	private static final int HOTKEYWORD_COUNT = 5;
	// 用户名
	private String username;
	// 问题列表
	List<Map<String, Object>> questionList = new ArrayList<Map<String, Object>>();
	// 问题数
	private int count;
	// 用户提问数
	private int askCount;
	// 回答数
	private int replyCount;
	// 被采纳数
	private int acceptedCount;
	// 采纳率
	private String acceptedPrecent;
	// 是否专家
	private int isExpert = 0;
	// 热门标签
	private List<Map<String, Object>> hotkeywords = new ArrayList<Map<String, Object>>();
	// 擅长领域
	private List<Map<String, Object>> domainList = new ArrayList<Map<String, Object>>();
	
	private User user;
	
	private UserService userService;
	private QuestionService questionService;
	private QuestionKeywordService questionKeywordService;
	private DomainService domainService;

	public String execute() {
		// 获得问题
		try {
			request.getSession().setAttribute("username", username);
			String newT = new String(username.getBytes("iso-8859-1"), "gbk");
			username = newT;
			if (username == null || "".equals(username)) {
				this.addActionError("用户名为空");
				return ERROR;
			}
			user = userService.findUserByUsername(username);
			if (user == null) {
				this.addActionError("用户名不存在");
				return ERROR;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", user.getId());
			params.put("size", COUNT);
			params.put("start", 0);
			count = questionService.countQuestionsByUserId(params);
			// 获得问题列表
			List<Question> questions = questionService.findQuestionsByUserId(params);
			for (int i = 0; i < questions.size(); i++) {
				Question q = questions.get(i);
				HashMap<String, Object> hm = new HashMap<String, Object>();
				hm.put("id", (Integer) q.getId());
				hm.put("subject", (String) q.getSubject());
				hm.put("status", (Integer) q.getStatus());
				hm.put("replyNum", (Integer) q.getReplyNum());
				hm.put("createdTime", (String) q.getCreatedTime().toString());
				questionList.add(hm);
			}

			// 提问数
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("userId", user.getId());
			askCount = questionService.countQuestionsByUserId(params);
			// 回答数
			replyCount = userService.countReplyByUserid(user.getId());
			if (replyCount == 0) {
				// 采纳数
				acceptedCount = 0;
				// 采纳率
				acceptedPrecent = "0%";
			} else {
				acceptedCount = userService.countAcceptedReplyByUserid(user.getId());
				if (acceptedCount == 0) {
					acceptedPrecent = "0%";
				} else {
					acceptedPrecent = (int) ((double) acceptedCount / (double) replyCount * 100) + "%";
				}
			}
			// 热门标签
			hotkeywords = questionKeywordService.findHotKeywords(HOTKEYWORD_COUNT);

			// 如果用户是专家就获得擅长领域
			if (user.getExpert() == 1) {
				domainList = domainService.findDomainByUserId(user.getId());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public List<Map<String, Object>> getQuestionList() {
		return questionList;
	}

	public int getCount() {
		return count;
	}

	public int getAskCount() {
		return askCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public int getAcceptedCount() {
		return acceptedCount;
	}

	public String getAcceptedPrecent() {
		return acceptedPrecent;
	}

	public int getIsExpert() {
		return isExpert;
	}

	public List<Map<String, Object>> getHotkeywords() {
		return hotkeywords;
	}

	public List<Map<String, Object>> getDomainList() {
		return domainList;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setQuestionKeywordService(QuestionKeywordService questionKeywordService) {
		this.questionKeywordService = questionKeywordService;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public User getUser() {
		return user;
	}

}
