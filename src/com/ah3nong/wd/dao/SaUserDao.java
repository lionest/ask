package com.ah3nong.wd.dao;

import com.ah3nong.wd.bean.SaUser;

public interface SaUserDao {
	public void updateSaUserIsExpert(SaUser saUser);
	public SaUser findUserByUserId(String userId);
	public void updateSaUserLoginData(SaUser user);
}
