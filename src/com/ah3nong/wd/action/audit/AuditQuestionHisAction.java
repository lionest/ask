package com.ah3nong.wd.action.audit;

import java.util.Date;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Audit;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionHis;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.AuditService;
import com.ah3nong.wd.service.QuestionService;

public class AuditQuestionHisAction extends GenericActionSupport {

	private static final long serialVersionUID = -3729646257086353903L;
	private QuestionService questionService;
	private AuditService auditService;
	private QuestionHis questionHis;
	private Question question;
	private Audit audit;
	
	private String flag;
	
	public String execute(){
		String method = "audit pass action!";
		try {
			//questionHis = questionService.getQuestionHisByPrimaryKey(questionHis.getId());
			if(flag.equals("pass")){
				questionHis.setStatus(Config.getInt("AUDIT_PASSED"));
				questionService.updateQuestionHisSelective(questionHis);
				questionHis = questionService.getQuestionHisByPrimaryKey(questionHis.getId());
				Question question = new Question();
				if(questionHis.getQuestionId()!=null){
					//说明是对问题的修改
					question.setId(questionHis.getQuestionId());
					question.setContent(questionHis.getContent());
					questionService.updateQuestionByPrimaryKey(question, true);
				}else{
					//说明是新加问题，审核通过后把记录添加到问题表中
					question.setContent(questionHis.getContent());
					question.setCreatedTime(questionHis.getCreatedTime());
					question.setDomainId(questionHis.getDomain().getId());
					question.setRecommended(Config.getInt("NOT_RECOMMEND"));
					question.setReplyNum(0);
					question.setViewCount(0);
					question.setStatus(Config.getInt("QUESTION_UNRESOLVED"));
					question.setSubject(questionHis.getSubject());
					question.setUserId(questionHis.getUser().getId());
					questionService.addQuestion(question, null, null);
					//question.setContent(questionHis.get)
				}
			}else{
				questionHis.setStatus(Config.getInt("AUDIT_NOT_PASS"));
				questionService.updateQuestionHisSelective(questionHis);
			}

			audit.setCreatedTime(new Date());
			audit.setUserId(1001);
			audit.setType(Config.getInt("QUESTION_HIS"));
			audit.setRecordId(questionHis.getId());
			audit.setStatus(Config.getInt("AUDIT_NORMAL"));
			auditService.addAudit(audit);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public QuestionHis getQuestionHis() {
		return questionHis;
	}
	public void setQuestionHis(QuestionHis questionHis) {
		this.questionHis = questionHis;
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
	
	

}
