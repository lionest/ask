package com.ah3nong.wd.action.audit;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Audit;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.bean.ReplyHis;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.AuditService;
import com.ah3nong.wd.service.ReplyService;


public class AuditReplyHisAction extends GenericActionSupport {

	private static final long serialVersionUID = -3729646257086353903L;
	private ReplyService replyService;
	private AuditService auditService;
	
	private ReplyHis replyHis;

	private Audit audit;
	
	private String flag;
	
	@Transactional(rollbackFor = Exception.class)
	public String execute(){
		String method = "audit pass action!";
		try {
			
			if(flag.equals("pass")){
				replyHis.setStatus(Config.getInt("AUDIT_PASSED"));
				replyService.updateReplyHisSelective(replyHis);
				replyHis = replyService.findReplyHisByPrimaryKey(replyHis.getId());
				Reply reply = new Reply();
				
				//对追问的修改
				reply.setId(replyHis.getReplyId());
				reply.setContent(replyHis.getContent());
				replyService.updateReplyByPrimaryKey(reply, true);
				
			}else{
				replyHis.setStatus(Config.getInt("AUDIT_NOT_PASS"));
				replyService.updateReplyHisSelective(replyHis);
			}
			//添加新的审核记录
			audit.setCreatedTime(new Date());
			audit.setUserId(1001);
			audit.setType(Config.getInt("REPLY_HIS"));
			audit.setRecordId(replyHis.getId());
			audit.setStatus(Config.getInt("AUDIT_NORMAL"));
			auditService.addAudit(audit);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}


	public ReplyHis getReplyHis() {
		return replyHis;
	}


	public void setReplyHis(ReplyHis replyHis) {
		this.replyHis = replyHis;
	}


	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
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
	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}
	
	
	
	

}
