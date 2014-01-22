package com.ah3nong.wd.action.expert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.UserService;
import com.ah3nong.wd.service.impl.ExpertServiceImpl;

public class AddExpertList extends GenericActionSupport<User> {
	/**
	 * 添加专家列表
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = LoggerFactory.getLogger(ExpertServiceImpl.class);
	private UserService userService;
	private int id;

	private Map map = new HashMap();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
  

	public String execute(){
		String method = "ViewExpertAction";
		log.info(method, "is start");
		try {
			map.put("expert", id);
			pager =  (Pager<User>) userService.getUserPagerByParam(map, pageNum,
					10);
		} catch (Exception e) {
			log.error(method, e);
			e.getStackTrace();
		}
		return "success";
	}
	
}
