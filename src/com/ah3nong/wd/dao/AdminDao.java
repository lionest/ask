package com.ah3nong.wd.dao;

import com.ah3nong.wd.bean.Admin;

public interface AdminDao {
	public int insertAdmin(Admin admin);
	public Admin findAdminByName(Admin admin);
}
