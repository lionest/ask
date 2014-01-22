package com.ah3nong.wd.dao.impl;

import com.ah3nong.wd.bean.Admin;
import com.ah3nong.wd.dao.AdminDao;
import com.ah3nong.wd.dao.BaseDao;

public class AdminDaoImpl extends BaseDao<Admin> implements AdminDao {

	@Override
	public int insertAdmin(Admin admin) {
		int id = (Integer) getSqlMapClientTemplate().insert("wd_admin.insert", admin);
		return id;
	}

	@Override
	public Admin findAdminByName(Admin admin) {
		return (Admin)getSqlMapClientTemplate().queryForObject("wd_admin.selectByAdminName",admin);
	}

}
