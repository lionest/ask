package com.ah3nong.wd.dao.impl;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Audit;
import com.ah3nong.wd.dao.AuditDao;
import com.ah3nong.wd.dao.BaseDao;

public class AuditDaoImpl extends BaseDao implements AuditDao {

	
	public void addAudit(Audit audit) {
		getSqlMapClientTemplate().insert("wd_audit.insert", audit);
	}

	
	public Audit findByAuditId(int auditId) {
		Audit key = new Audit();
		key.setId(auditId);		
		return (Audit)getSqlMapClientTemplate().queryForObject("wd_audit.selectByPrimaryKey", key);
	}
	
	
	public int updateAuditByAuditId(Audit audit) {		
		return getSqlMapClientTemplate().update("wd_audit.updateByPrimaryKey", audit);
	}

	
	public int updateAuditBySelective(Audit audit) {
		return getSqlMapClientTemplate().update("wd_audit.updateByPrimaryKeySelective", audit);
	}

	
	public List<Audit> queryForPager(Map map) {
		List<Audit> list = getSqlMapClientTemplate().queryForList("wd_audit.selectPaginationByPageNum", map);
		return list;
	}

	
	public int countForPager(Map map) {
		int rows =(Integer) getSqlMapClientTemplate().queryForObject("wd_audit.countPaginationByPageNum",map);
		return rows;
	}
	
	

}
