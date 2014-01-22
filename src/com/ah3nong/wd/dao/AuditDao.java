package com.ah3nong.wd.dao;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Audit;


public interface AuditDao {
	public Audit findByAuditId(int auditId);
	
	public void addAudit(Audit audit);
	
	public int updateAuditByAuditId(Audit audit);
	
	public int updateAuditBySelective(Audit audit);
	
	public List<Audit> queryForPager(Map map);
	
	public int countForPager(Map map);
}
