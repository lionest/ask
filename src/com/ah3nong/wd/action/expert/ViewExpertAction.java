package com.ah3nong.wd.action.expert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Expert;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.service.ExpertService;
import com.ah3nong.wd.service.UserService;
import com.ah3nong.wd.service.impl.ExpertServiceImpl;

public class ViewExpertAction extends GenericActionSupport{
	private static final long serialVersionUID = 1L;
	private Logger log = LoggerFactory.getLogger(ExpertServiceImpl.class);
	private Map map = new HashMap();
	private ExpertService expertService;
	private UserService userService;
	private List<Expert> experts;
	private List<User> users;
	private int id;
    
    

	public List<Expert> getExperts() {
		return experts;
	}

	public void setExperts(List<Expert> experts) {
		this.experts = experts;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ExpertService getExpertService() {
		return expertService;
	}

	public void setExpertService(ExpertService expertService) {
		this.expertService = expertService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String execute() {
		String method = "ViewExpertAction";
		log.info(method, "is start");
		if (id == 0) {
			log.error(method + ", id is null");
		}
		try {
			map.put("expert", id);
			pager = (Pager<Expert>) expertService.getExpertPagerByParam(map, pageNum,
					10);
//			pager = (Pager<User>) userService.getUserPagerByParam(map, pageNum,
//					10);
		} catch (Exception e) {
			log.error(method, e);
			e.getStackTrace();
		}
		return "success";
	}
}
