package com.ah3nong.wd.action.audit;

import java.util.Date;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Audit;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionExtraContentHis;
import com.ah3nong.wd.bean.QuestionHis;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.AuditService;
import com.ah3nong.wd.service.QuestionService;

public class AuditQExHisAction extends GenericActionSupport {

	private static final long serialVersionUID = -3729646257086353903L;
	private QuestionService questionService;
	private AuditService auditService;
	private Question question;
	private Audit audit;
	
	private String flag;
	private int id;

	
	public String execute(){
		String method = "audit QExHis  action!";
		try {
			QuestionExtraContentHis qExHis = new QuestionExtraContentHis();		
			if(flag.equals("pass")){
				qExHis.setId(id);
				qExHis.setStatus(Config.getInt("AUDIT_PASSED"));
				questionService.updateQExHisSelective(qExHis);
				qExHis = questionService.getQuestionExtraContentHisByPrimaryKey(id);
				//通过问题ID查找问题记录，并修改其问题补充字段
				Question question = new Question();
				question.setId(qExHis.getQuestionId());
				question.setExtraContent(qExHis.getExtraContent());
				question.setStatus(Config.getInt("QUESTION_UNRESOLVED"));
				questionService.updateQuestionByPrimaryKey(question, true);
			}else{
				qExHis.setId(id);
				qExHis.setStatus(Config.getInt("AUDIT_NOT_PASS"));
				questionService.updateQExHisSelective(qExHis);
			}

			audit.setCreatedTime(new Date());
			audit.setUserId(1001);
			audit.setType(Config.getInt("QUESTION_EX_HIS"));
			audit.setRecordId(qExHis.getId());
			audit.setStatus(Config.getInt("AUDIT_NORMAL"));
			auditService.addAudit(audit);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
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

	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	

}
