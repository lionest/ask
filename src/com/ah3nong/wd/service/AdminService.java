package com.ah3nong.wd.service;

import com.ah3nong.wd.bean.Admin;

public interface AdminService {
	public int insertAdmin(Admin admin);
	public Admin findAdminByName(Admin admin);
}
