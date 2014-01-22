package com.ah3nong.wd.dao;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.UserDomain;

public interface UserDomainDao {

	
	public int countAllUserDomain(int id) ;
	public int countForPager(Map map);
	public List<UserDomain> findAllUserDomain();
	public void deleteUserDomainById(int id);
	public void insertUserDomain(UserDomain userDomain) ;
}
