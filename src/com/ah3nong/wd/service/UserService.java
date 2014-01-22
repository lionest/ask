package com.ah3nong.wd.service;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.exception.ServiceException;

public interface UserService {

	public Pager<User> getUserPagerByParam(Map params, int currentPage,
			int pageSize) throws ServiceException;
	
	public User findUserByPrimaryKey(int id) throws ServiceException;
	
	public User findUserByUsername(String username) throws ServiceException;
	
	public int addUser(User u);
	
	public List<User> findAllUser() throws ServiceException;
	
	public List<User> findExperts(int id) throws ServiceException;
	
	public int countAllUser(int id) throws ServiceException;
	
	public int countReplyByUserid(int id) throws ServiceException;
	
	public int countAcceptedReplyByUserid(int id) throws ServiceException;
	
	public int updateUserExperience(User user);
	
	public int updateUserByPrimarykey(User user);
	
}
