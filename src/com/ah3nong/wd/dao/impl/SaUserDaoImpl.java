package com.ah3nong.wd.dao.impl;

import com.ah3nong.wd.bean.SaUser;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.SaUserDao;

public class SaUserDaoImpl extends BaseDao<SaUserDaoImpl> implements SaUserDao {

	public void updateSaUserIsExpert(SaUser saUser) {
		getSqlMapClientTemplate().update("sa_user.updateUserIsExpert", saUser);
	}

	public SaUser findUserByUserId(String userId) {
		return (SaUser) getSqlMapClientTemplate().queryForObject(
				"sa_user.selectByUserId", userId);
	}

	public void updateSaUserLoginData(SaUser user) {
		getSqlMapClientTemplate().update("sa_user.updateUserLoginData", user);
	}
}
