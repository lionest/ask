package com.ah3nong.wd.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.dao.UserDao;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.UserService;

public class UserServiceImpl implements UserService {

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Pager<User> getUserPagerByParam(Map params, int currentPage,
			int pageSize) throws ServiceException {
		String method = "getUserPagerByParam";
		if (params == null) {
			log.error(method + ", the params of pager data is null!");
			throw new ServiceException(
					"cannot query any users since the param is null!");
		}
		if (currentPage <= 0) {
			log.error(method + ", the current page is invalid!");
			throw new ServiceException("the current page is invalid!");
		}
		if (pageSize <= 0) {
			log.error(method + ", the pageSize is invalid!");
			throw new ServiceException("the pageSizeis invalid!");
		}
		log.info(method + ", the params:" + params.toString() + ",currentPage:"
				+ currentPage + ",pagesize:" + pageSize);

		params.put("start", (currentPage - 1) * pageSize);
		params.put("size", pageSize);
		System.out.println(params.toString());
		Pager<User> pager = new Pager<User>(currentPage, pageSize);
		try {
			pager.setPageRecords(userDao.queryForPager(params));
			pager.setTotalRecords(userDao.countForPager(params));
		} catch (Exception e) {
			log.error(method
					+ ",user dao got exceptions while query pager data." + e);
			throw new ServiceException(
					"user dao got exceptions while query pager data.Msg:" + e);
		}

		return pager;
	}

	public int countAllUser(int id) throws ServiceException {
		String method = "countAllUser";
		try {
			return userDao.countAllUser(id);
		} catch (Exception e) {
			log.error(method
					+ ",user dao got exceptions while count all users." + e);
			throw new ServiceException(
					"user dao got exceptions while count all users data.Msg:"
							+ e);
		}
	}

	public List<User> findAllUser() throws ServiceException {
		String method = "findAllUser";
		try {
			return userDao.findAllUser();
		} catch (Exception e) {
			log.error(method + ",user dao got exceptions while find users." + e);
			throw new ServiceException(
					"user dao got exceptions while find users data.Msg:" + e);
		}
	}

	public List<User> findExperts(int id) throws ServiceException {
		String method = "findExperts";
		try {
			return userDao.findExperts(id);
		} catch (Exception e) {
			log.error(method + ",user dao got exceptions while find experts."
					+ e);
			throw new ServiceException(
					"user dao got exceptions while find experts data.Msg:" + e);
		}
	}

	public User findUserByPrimaryKey(int id) throws ServiceException {
		String method = "findUserByPrimaryKey";
		if (id == 0) {
			log.error(method + ", the params of find user is null!");
			throw new ServiceException(
					"cannot find user since the param is null!");
		}
		log.info(method + ", the user params:" + id);
		try {
			return userDao.findUserByPrimaryKey(id);
		} catch (Exception e) {
			log.error(method + ",user dao got exceptions while find user." + e);
			throw new ServiceException(
					"user dao got exceptions while find user data.Msg:" + e);
		}
	}

	/**
	 * 根据用户USERID 查询用户信息
	 */
	public User findUserByUsername(String username) throws ServiceException {
		String method = "findUserByUsername";
		if (username == null) {
			log.error(method + ", the params of find user is null!");
			throw new ServiceException(
					"cannot find user since the param is null!");
		}
		log.info(method + ", the user params:" + username);
		try {
			return userDao.findUserByUsername(username);
		} catch (Exception e) {
			log.error(method + ",user dao got exceptions while find user." + e);
			throw new ServiceException(
					"user dao got exceptions while find user data.Msg:" + e);
		}
	}

	/**
	 * 根据用户ID来获得用户的回答数量
	 */
	public int countAcceptedReplyByUserid(int id) throws ServiceException {
		String method = "countAcceptedReplyByUserid";
		if (id == 0) {
			log.error(method + ", the params of find user count is zero!");
			throw new ServiceException(
					"cannot find user since the param is zero!");
		}
		log.info(method + ", the user id params:" + id);
		try {
			return userDao.findCountOfReplyAccepted(id);
		} catch (Exception e) {
			log.error(method
					+ ",user dao got exceptions while find the count of reply."
					+ e);
			throw new ServiceException(
					"user dao got exceptions while find the count of reply data.Msg:"
							+ e);
		}
	}

	/**
	 * 根据用户ID来获得用户的被采纳的回答数量
	 */
	public int countReplyByUserid(int id) throws ServiceException {
		String method = "countAcceptedReplyByUserid";
		if (id == 0) {
			log.error(method + ", the params of find user count is zero!");
			throw new ServiceException(
					"cannot find user since the param is zero!");
		}
		log.info(method + ", the user id params:" + id);
		try {
			return userDao.findCountOfReply(id);
		} catch (Exception e) {
			log.error(method
					+ ",user dao got exceptions while find the count of reply which accepted."
					+ e);
			throw new ServiceException(
					"user dao got exceptions while find the count of reply data which accepted.Msg:"
							+ e);
		}
	}

	@Override
	public int addUser(User u) {
		return userDao.insertUser(u);
	}

	@Override
	public int updateUserExperience(User user) {
		int rows = userDao.updateUserExperience(user);
		return rows;
	}

	@Override
	public int updateUserByPrimarykey(User user) {
		int rows = userDao.updateUserByPrimarykey(user);
		return rows;
	}

}
