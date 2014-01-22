package com.ah3nong.wd.action.experience;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionScoreService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.UserService;

public class CountAllUsersExperienceAction extends GenericActionSupport<User> {
	private static final long serialVersionUID = 5712320573410418957L;

	private UserService userService;
	private QuestionService questionService;
	private QuestionScoreService questionScoreService;

	public String execute() {
		try {
			List<User> userList = userService.findAllUser();
			for (int i = 0; i < userList.size(); i++) {
				User user = userList.get(i);
				// 获得用户提问数
				Map<String, Object> params = new HashMap<String, Object>();
				if(user.getId()!=0){
					params.put("userId", user.getId());
					int askExp = questionService.countQuestionsByUserId(params) * Config.getInt("ASK_EXPERIENCE");
					int replyExp = userService.countReplyByUserid(user.getId()) * Config.getInt("ANSWER_EXPERIENCE");
					int acceptExp = 0;
					List<Map<String, Object>> scoreList = questionScoreService.findAllAcceptQuestionByUserId(user.getId());
					for (int j = 0; j < scoreList.size(); j++) {
						if (scoreList.get(j).get("score") != null ) {
							int tmp = (Integer) scoreList.get(j).get("score");
							acceptExp = acceptExp + tmp;
						} else {
							acceptExp = acceptExp + Config.getInt("QUESTION_DEFAULT_SCORE");
						}
					}
					User newUser = new User();
					newUser.setId(user.getId());
					newUser.setExperience(askExp + replyExp + acceptExp);
					userService.updateUserExperience(newUser);
				}
			}
		} catch (ServiceException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
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

}
