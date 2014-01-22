package com.ah3nong.wd.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.bean.SaUser;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.dao.SaUserDao;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.SaUserService;

public class SaUserServiceImpl implements SaUserService {

	private Logger log = LoggerFactory.getLogger(SaUserServiceImpl.class);
	private SaUserDao saUserDao;

	/**
	 * 根据主键ID，查询SAUSER
	 */
	public SaUser findUserByPrimaryKey(String id) throws ServiceException {
		String method = "findUserByPrimaryKey";
		if (id == null) {
			log.error(method + ", the params of find user is null!");
			throw new ServiceException(
					"cannot find sa user since the param is null!");
		}
		log.info(method + ", the sa user params:" + id);
		try {
			return saUserDao.findUserByUserId(id);
		} catch (Exception e) {
			log.error(method
					+ ",sa user dao got exceptions while find sa user." + e);
			throw new ServiceException(
					"user dao got exceptions while find sa user data.Msg:" + e);
		}
	}

	/**
	 * SAUSER用户登录后，更新SAUSER数据
	 */
	public void updateLoginData(SaUser user) throws ServiceException {
		String method = "updateLoginData";
		if (user == null) {
			log.error(method + ", the params of update sa user is null!");
			throw new ServiceException(
					"cannot update reply by primaryId since the sa user is null!");
		}
		log.info(method + ", the reply params:" + user.toString());
		try {
			saUserDao.updateSaUserLoginData(user);
		} catch (Exception e) {
			log
					.error(method
							+ ",sa user dao got exceptions while update sauser by primaryId."
							+ e);
			throw new ServiceException(
					"sauser dao got exceptions while update sauser by primaryId data.Msg:"
							+ e);
		}
	}

	public void setSaUserDao(SaUserDao saUserDao) {
		this.saUserDao = saUserDao;
	}

}
