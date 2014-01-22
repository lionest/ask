package com.ah3nong.wd.dao.impl;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.UserDomain;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.UserDomainDao;

public class UserDomainDomainDaoImpl extends BaseDao<UserDomainDomainDaoImpl> implements UserDomainDao {

	
	public int countAllUserDomain(int id) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_user_domain.countAll",id);
		return rows;
	}

	
	public int countForPager(Map map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_user_domain.countPaginationByPageNum", map);
		return rows;
	}

	
	public List<UserDomain> findAllUserDomain() {
		List<UserDomain> list = getSqlMapClientTemplate().queryForList("wd_user_domain.selectAll");
		return list;
	}

	
	public void deleteUserDomainById(int id) {
		UserDomain userDomain = new UserDomain();
		userDomain.setUserId(id);
		getSqlMapClientTemplate().delete("wd_user_domain.deleteByUserId", userDomain);
	}
	
	public void insertUserDomain(UserDomain userDomain) {
		getSqlMapClientTemplate().insert("wd_user_domain.insert", userDomain);
	}

}
