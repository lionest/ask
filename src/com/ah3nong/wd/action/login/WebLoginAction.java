package com.ah3nong.wd.action.login;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.SaUser;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.security.SecurityContext;
import com.ah3nong.wd.service.SaUserService;
import com.ah3nong.wd.service.UserService;
import com.ah3nong.wd.util.CommonUtils;

public class WebLoginAction extends GenericActionSupport<SaUser> {

	private static final long serialVersionUID = 8287527356649789871L;
	private User user;
	private UserService userService;
	private int replyCount;
	private String acceptedPrecent;

	private User tmp;

	/*
	 * public void validating() { if (user == null) {
	 * logger.warn("the user  is null while try to login wenda system.");
	 * request.setAttribute("errorMsg", "用户信息不可以为空"); }
	 * 
	 * if (user.getId() == null || user.getId().toString().trim() == "") {
	 * logger.warn("the user id is null while try to login wenda system.");
	 * request.setAttribute("errorMsg", "用户名称不可以为空!"); } if (user.getPassword()
	 * == null || user.getPassword().trim() == "") {
	 * logger.warn("the pwd is null while try to login wenda system.");
	 * request.setAttribute("errorMsg", "密码不可以为空!"); } try { tmp =
	 * userService.findUserByUsername(user.getUsername()); if (tmp == null) {
	 * logger.warn("the user id is invalid while try to login wenda system.");
	 * request.setAttribute("errorMsg", "用户名不存在!"); }
	 * 
	 * String encPwd = CommonUtils.encryptPassword(user.getId() +
	 * user.getPassword());
	 * 
	 * //String encPwd = user.getPassword(); if
	 * (!encPwd.equals(tmp.getPassword())) { request.setAttribute("errorMsg",
	 * "密码输入错误，请重试!"); throw new Exception("the pwd is not correct!"); } } catch
	 * (Exception e) { e.printStackTrace(); request.setAttribute("errorMsg",
	 * "系统正在升级"); } }
	 */

	public String execute() {
		try {

			/*tmp = userService.findUserByUsername(user.getUsername());

			String encPwd = CommonUtils.encryptPassword(user.getId()
					+ user.getPassword());

			String pwd = user.getPassword();
			if (!pwd.equals(tmp.getPassword())) {
				request.setAttribute("errorMsg", "密码输入错误，请重试!");
				throw new Exception("the pwd is not correct!");
			}*/

			// 数据验证
			// validating();
			//logger.debug("user " + user.getId() + " login wenda system suscces");

			// todo 更新用户信息
			// sauser.setLastLoginDate(new Date());
			// sauser.setLogins(sauser.getLogins() + 1);
			// serService.updateLoginData(sauser);

			//tmp = userService.findUserByUsername(user.getUsername());
			
			// 获得用户信息
			user = SecurityContext.getUser();
			
			tmp = user;
			
			request.getSession().setAttribute("lUser", tmp);
			replyCount = userService.countReplyByUserid(tmp.getId());
			request.getSession().setAttribute("replyCount", replyCount);
			if (replyCount == 0) {
				acceptedPrecent = "0%";
			} else {
				int acceptedCount = userService.countAcceptedReplyByUserid(tmp
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
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}

	}

	/**
	 * 用户登出方法
	 * 
	 * @return
	 */
	public String logout() {
		User luser = (User) request.getSession().getAttribute("lUser");
		if (luser == null) {
			logger.info("no use logon no need to logout.");
			return INPUT;
		} else {
			String userid = luser.getUsername();
			request.getSession().removeAttribute("lUser");
			logger.info("user " + userid + " log out success!");
			return SUCCESS;
		}

	}
/*
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/

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

}
