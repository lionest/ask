package com.ah3nong.wd.action.audit;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Audit;
import com.ah3nong.wd.bean.Probing;
import com.ah3nong.wd.bean.ProbingHis;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.AuditService;
import com.ah3nong.wd.service.ProbingService;


public class AuditProbingHisAction extends GenericActionSupport {

	private static final long serialVersionUID = -3729646257086353903L;
	private ProbingService probingService;
	private AuditService auditService;
	
	private ProbingHis probingHis;

	private Audit audit;
	
	private String flag;
	
	@Transactional(rollbackFor = Exception.class)
	public String execute(){
		String method = "audit pass action!";
		try {
			
			if(flag.equals("pass")){
				probingHis.setStatus(Config.getInt("AUDIT_PASSED"));
				probingService.updateProbingHisSelective(probingHis);
				probingHis = probingService.findProbingHisByPrimaryKey(probingHis.getId());
				Probing probing = new Probing();
				
				//对追问的修改
				probing.setId(probingHis.getProbingId());
				probing.setContent(probingHis.getContent());
				probingService.updateProbingByPrimaryKey(probing, true);
				
			}else{
				probingHis.setStatus(Config.getInt("AUDIT_NOT_PASS"));
				probingService.updateProbingHisSelective(probingHis);
			}
			//添加新的审核记录
			audit.setCreatedTime(new Date());
			audit.setUserId(1001);
			audit.setType(Config.getInt("PROBING_HIS"));
			audit.setRecordId(probingHis.getId());
			audit.setStatus(Config.getInt("AUDIT_NORMAL"));
			auditService.addAudit(audit);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public ProbingHis getProbingHis() {
		return probingHis;
	}

	public void setProbingHis(ProbingHis probingHis) {
		this.probingHis = probingHis;
	}

	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setProbingService(ProbingService probingService) {
		this.probingService = probingService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}
	
	
	
	

}
