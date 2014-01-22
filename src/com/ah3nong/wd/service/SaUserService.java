package com.ah3nong.wd.service;

import com.ah3nong.wd.bean.SaUser;
import com.ah3nong.wd.exception.ServiceException;

public interface SaUserService {

	public SaUser findUserByPrimaryKey(String id) throws ServiceException;

	public void updateLoginData(SaUser user) throws ServiceException;
}
