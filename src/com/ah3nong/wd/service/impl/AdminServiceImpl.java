package com.ah3nong.wd.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.bean.Admin;
import com.ah3nong.wd.dao.AdminDao;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.AdminService;

public class AdminServiceImpl implements AdminService {
	private Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
	private AdminDao adminDao;

	@Override
	public int insertAdmin(Admin admin) {
		String method = "insertAdmin";
		try {
			if (admin == null) {
				log.error(method + ",the param is null");
				throw new ServiceException("cannot insert wd_admin since the param is null!");
			}
			log.info(method + ",the admin param :" + admin);
			return adminDao.insertAdmin(admin);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Admin findAdminByName(Admin admin) {
		String method = "findAdminByName";
		try {
			if (admin == null) {
				log.error(method + ",the param is null");
				throw new ServiceException("cannot find from wd_admin since the param is null");
			}
			log.info(method + ", the admin param :" + admin);
			return adminDao.findAdminByName(admin);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}

	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

}
