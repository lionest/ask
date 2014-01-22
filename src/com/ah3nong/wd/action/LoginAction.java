package com.ah3nong.wd.action;
import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.ah3nong.wd.bean.Admin;
import com.ah3nong.wd.service.AdminService;
import com.ah3nong.wd.service.impl.AdminServiceImpl;

public class LoginAction extends GenericActionSupport<LoginAction> {
	private static final long serialVersionUID = 5194434880475278523L;
	
	private Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
	private String username;
	private String password;
	private String info;
	private AdminService adminService;

	public String login() {
		String method = "loginAction";
		log.info(method, "wenda");
		
		if ("".equals(username) || "".equals(password)) {
			info = "登陆提示：用户名/密码不能为空！";
			return "loginError";

		} else {
			Admin tmp = new Admin();
			tmp.setName(username);
			Admin admin = adminService.findAdminByName(tmp);
			if (admin == null) {
				info = "登陆提示：用户不存在！";
				return "loginError";
			}
			String pwd = admin.getPassword();
			//MD5加密
			Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
			md5PasswordEncoder.setEncodeHashAsBase64(true);
			String md5Password = md5PasswordEncoder.encodePassword(password, admin.getSalt());

			if (pwd.equals(md5Password)) {
				//创建cookie
				Cookie cookie = new Cookie("manager_login_name",admin.getName());
				Cookie timeCookie = new Cookie("manager_login_time",Long.toString( System.currentTimeMillis()));
				//生命周期         
				//cookie.setMaxAge(60*60*24);
				//timeCookie.setMaxAge(60*60*24);
				//设置域
				cookie.setDomain(".gy3nong.com"); 
				timeCookie.setDomain(".gy3nong.com"); 
				response.addCookie(cookie);
				response.addCookie(timeCookie);
				
				request.getSession().setAttribute("ask_admin_name", admin.getName());
				return SUCCESS;
			} else {
				info = "登陆提示：用户名/密码错误！";
				return "loginError";
			}
		}
	}

	public String quit() {
		//删除cookie
		Cookie cookie = new Cookie("manager_login_name", null); 
		Cookie timeCookie = new Cookie("manager_login_time", null); 
		cookie.setMaxAge(0);
		timeCookie.setMaxAge(0);
		cookie.setDomain(".gy3nong.com"); 
		timeCookie.setDomain(".gy3nong.com"); 
		response.addCookie(cookie); 
		response.addCookie(timeCookie); 
		session.remove("ask_admin_name");
		return SUCCESS;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
