package com.ah3nong.wd.action.expertquestion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.QuestionKeywordService;
import com.ah3nong.wd.service.QuestionScoreService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.UserService;

public class UserDetailAction extends GenericActionSupport<User> {
	private static final long serialVersionUID = 8074611472973938672L;

	private static final int HOTKEYWORD_COUNT = 5;

	private UserService userService;
	private QuestionService questionService;
	private QuestionScoreService questionScoreService;
	private QuestionKeywordService questionKeywordService;
	private DomainService domainService;

	private String username;

	private int askCount;
	private int replyCount;
	private int acceptedCount;
	private String acceptedPrecent;
	private int userScore;
	private boolean isExpert = false;
	private List<Map<String, Object>> hotkeywords;
	private List<Map<String, Object>> domainList;

	public String execute() {
		try {
			if (username == null || "".equals(username)) {
				this.addActionError("用户名为空");
				return ERROR;
			}
			User user = userService.findUserByUsername(username);
			if (user == null) {
				this.addActionError("用户名不存在");
				return ERROR;
			}
			// 提问数
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", user.getId());
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
			// 积分
			userScore = questionScoreService.countUserScore(user.getId());

			// 热门标签
			hotkeywords = questionKeywordService.findHotKeywords(HOTKEYWORD_COUNT);

			// 如果用户是专家就获得擅长领域
			if (user.getExpert() == 1) {
				isExpert = true;
				domainList = domainService.findDomainByUserId(user.getId());
			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
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

	public void setQuestionScoreService(QuestionScoreService questionScoreService) {
		this.questionScoreService = questionScoreService;
	}

	public void setQuestionKeywordService(QuestionKeywordService questionKeywordService) {
		this.questionKeywordService = questionKeywordService;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
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

	public int getUserScore() {
		return userScore;
	}

	public List<Map<String, Object>> getHotkeywords() {
		return hotkeywords;
	}

	public List<Map<String, Object>> getDomainList() {
		return domainList;
	}

	public boolean isExpert() {
		return isExpert;
	}

}
