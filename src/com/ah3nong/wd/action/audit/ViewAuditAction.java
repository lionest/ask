package com.ah3nong.wd.action.audit;

import java.util.HashMap;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Audit;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.AuditService;

public class ViewAuditAction extends GenericActionSupport {

	private static final long serialVersionUID = -3729646257086353903L;
	private AuditService auditService;
	private Audit audit;
	
	private Map<String,Integer> typeMap;
	
	public String execute(){
		String method = "query audits by page action!";
		
		try {
			typeMap = new HashMap<String,Integer>();
			typeMap.put("QUESTION_HIS", Config.getInt("QUESTION_HIS"));
			typeMap.put("QUESTION_EX_HIS", Config.getInt("QUESTION_EX_HIS"));
			typeMap.put("PROBING_HIS", Config.getInt("PROBING_HIS"));
			typeMap.put("REPLY_HIS", Config.getInt("REPLY_HIS"));
			
			Map<String, Object> params = new HashMap<String, Object>();
			pager = auditService.getAuditPagerByParam(params, pageNum, 10);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public Audit getAudit() {
		return audit;
	}
	public void setAudit(Audit audit) {
		this.audit = audit;
	}
	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public Map<String, Integer> getTypeMap() {
		return typeMap;
	}

	public void setTypeMap(Map<String, Integer> typeMap) {
		this.typeMap = typeMap;
	}

	


}
