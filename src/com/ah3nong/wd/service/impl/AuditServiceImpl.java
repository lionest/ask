package com.ah3nong.wd.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.bean.Audit;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.dao.AuditDao;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.AuditService;

public class AuditServiceImpl implements AuditService {
	
	private Logger log = LoggerFactory.getLogger(AuditServiceImpl.class);
	private AuditDao auditDao;
	

	public void setAuditDao(AuditDao auditDao) {
		this.auditDao = auditDao;
	}

	public void addAudit(Audit audit) throws ServiceException {
		String method = "addAudit";
		if (audit == null) {
			log
					.error(method
							+ ", the params of add audit is null!");
			throw new ServiceException(
					"cannot add audit since the param is null!");
		}
		//log.info(method + ", the probing params:" + probing.toString());
		try {
			auditDao.addAudit(audit);
		} catch (Exception e) {
			log.error(method + ",audit dao got exceptions while add audit."
					+ e.getLocalizedMessage());
			throw new ServiceException(
					"audit dao got exceptions while add audit data.Msg:"
							+ e.getLocalizedMessage());
		}

	}

	public Pager<Audit> getAuditPagerByParam(Map params, int currentPage,
			int pageSize) throws ServiceException {
		String method = "getAuditPagerByParam";
		if (params == null) {
			log.error(method + ", the params of pager data is null!");
			throw new ServiceException(
					"cannot query any Audit with the param is null!");
		}
		if (currentPage <= 0) {
			log.error(method + ", the current page is invalid!");
			throw new ServiceException("the current page is invalid!");
		}
		if (pageSize <= 0) {
			log.error(method + ", the pageSize is invalid!");
			throw new ServiceException("the pageSizeis invalid!");
		}
		log.info(method + ", the params:" + params.toString() + ",currentPage:"
				+ currentPage + ",pagesize:" + pageSize);

		params.put("start", (currentPage - 1) * pageSize);
		params.put("size", pageSize);
		Pager<Audit> pager = new Pager<Audit>(currentPage, pageSize);
		try {
			pager.setPageRecords(auditDao.queryForPager(params));
			pager.setTotalRecords(auditDao.countForPager(params));
		} catch (Exception e) {
			log.error(method
					+ ",audit dao got exceptions while query pager data."
					+ e.getLocalizedMessage());
			throw new ServiceException(
					"audit dao got exceptions while query pager data.Msg:"
							+ e.getLocalizedMessage());
		}

		return pager;
	}

	public Audit findAuditByPrimaryKey(int auditId) throws ServiceException {
		String method = "findAuditByPrimaryKey";
		if (auditId == 0) {
			log.error(method+ ", the params of find audit by id is null!");
			throw new ServiceException(
					"cannot find audit by id since the param is null!");
		}
		//log.info(method + ", the probing params:" + probing.toString());
		try {
			return auditDao.findByAuditId(auditId);
		} catch (Exception e) {
			log.error(method + ",audit dao got exceptions while find audit by id."
					+ e.getLocalizedMessage());
			throw new ServiceException(
					"audit dao got exceptions while find audit by id data.Msg:"
							+ e.getLocalizedMessage());
		}
	}

}
