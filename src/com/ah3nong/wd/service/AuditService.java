package com.ah3nong.wd.service;

import java.util.Map;

import com.ah3nong.wd.bean.Audit;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.exception.ServiceException;

public interface AuditService {

	public void addAudit(Audit audit) throws ServiceException;
	public Pager<Audit> getAuditPagerByParam(Map params, int currentPage, int pageSize) throws ServiceException;
	public Audit findAuditByPrimaryKey(int auditId) throws ServiceException;
}
