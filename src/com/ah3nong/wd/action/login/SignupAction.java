package com.ah3nong.wd.action.login;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.service.UserService;

public class SignupAction extends GenericActionSupport<User> {

	private static final long serialVersionUID = 8287527356649789871L;
	private UserService userService;
	private User user;
	private int replyCount;
	private String acceptedPrecent;

	public void validating() {
		if (user == null) {
			logger.warn("the user  is null while try to signup wenda system.");
			request.setAttribute("errorMsg", "用户信息不可以为空");
		}

		if (user.getEmail() == null || user.getEmail().trim() == "") {
			request.setAttribute("errorMsg", "EMAIL不可以为空!");
		}
		if (user.getUsername() == null || user.getUsername().trim() == "") {
			request.setAttribute("errorMsg", "username不可以为空!");
		}
	}

	public String execute() {
		// 数据验证
		validating();
		try {
			user.setFullName(user.getUsername());
			user.setExpert(0);
			user.setNickname(user.getUsername());
			userService.addUser(user);
			
			request.getSession().setAttribute("lUser", user);
			//
			replyCount = userService.countReplyByUserid(user.getId());
			request.getSession().setAttribute("replyCount", replyCount);
			if (replyCount == 0) {
				acceptedPrecent = "0%";
			} else {
				int acceptedCount = userService.countAcceptedReplyByUserid(user
						.getId());
				if (acceptedCount == 0) {
					acceptedPrecent = "0%";
				} else {
					acceptedPrecent = (acceptedCount / replyCount * 100) + "%";
				}
			}
			// request.setAttribute("replyCount", replyCount);
			request.getSession().setAttribute("acceptedPrecent",
					acceptedPrecent);
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public String getAcceptedPrecent() {
		return acceptedPrecent;
	}

	public void setAcceptedPrecent(String acceptedPrecent) {
		this.acceptedPrecent = acceptedPrecent;
	}

	public UserService getUserService() {
		return userService;
	}

}
