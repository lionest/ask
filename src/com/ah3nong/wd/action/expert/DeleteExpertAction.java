package com.ah3nong.wd.action.expert;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Expert;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.ExpertService;
import com.ah3nong.wd.service.impl.ExpertServiceImpl;

public class DeleteExpertAction extends GenericActionSupport<Expert>{
	/**
	 *  删除专家
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = LoggerFactory.getLogger(ExpertServiceImpl.class);
    private ExpertService expertService;
    private User user = new User();
    private int id;
    private String username;
    private String newStr;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ExpertService getExpertService() {
		return expertService;
	}

	public void setExpertService(ExpertService expertService) {
		this.expertService = expertService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute(){
		try {
			newStr = new String(username.getBytes("iso-8859-1"),"utf-8");
			username = newStr;
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String method = "DeleteExpertAction";
		if(id == 0){
			log.info(method+",expert is null");
		}
		if(user == null){
			log.info(method+", user is null");
		}
		log.info(method,"expert:"+id+"   user:"+user.toString());
		user.setExpert(0);
		user.setUsername(username);
		try{
			expertService.delExpertByPrimaryKey(id,user);
		}catch(ServiceException e) {
			log.error(method, e);
			e.getStackTrace();
		}
		return "success";
	}
}
