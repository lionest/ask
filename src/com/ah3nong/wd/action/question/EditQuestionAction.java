package com.ah3nong.wd.action.question;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionKeyword;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.KeywordService;
import com.ah3nong.wd.service.QuestionKeywordService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.SysdataService;
import com.ah3nong.wd.util.StringHelper;

public class EditQuestionAction extends GenericActionSupport<Question> {

	private static final long serialVersionUID = 8287527356649789271L;

	private QuestionService questionService;
	private KeywordService keywordService;
	private QuestionKeywordService questionKeywordService;
	private SysdataService sysdataService;
	private Question question;

	@Transactional(rollbackFor = Exception.class)
	public String execute() throws ServiceException {
		question.setContent(StringHelper.encodeHTML(question.getContent()));
		question.setExtraContent(StringHelper.encodeHTML(question.getExtraContent()));
		
		int audit = Integer.parseInt(sysdataService.findByName("auditQuestion").getContent());
		// 1.如果用户选择的为关键字审核
		if(audit==2){
			//如果包含关键字，设置问题状态为审核中
			if (question.getContent() != null && keywordService.findKeywordInString(question.getContent())) {
				question.setStatus(Config.getInt("QUESTION_AUDITING"));
			} else if (question.getExtraContent() != null && keywordService.findKeywordInString(question.getExtraContent())) {
				question.setStatus(Config.getInt("QUESTION_AUDITING"));
			//否则设置为正常状态
			}else{
				question.setStatus(Config.getInt("QUESTION_UNRESOLVED"));
			}
			
		// 2.如果用户选择的为全部审核
		}else if(audit==1){
			question.setStatus(Config.getInt("QUESTION_AUDITING"));
			
		// 3.如果用户选择的为全部不审核
		}else{
			question.setStatus(Config.getInt("QUESTION_UNRESOLVED"));
		}
		
		questionService.updateQuestionByPrimaryKey(question, true);
		// 把问题关键字存入问题-关键字表
		String qkw = question.getKeywords();
		if (qkw != null && !"".equals(qkw)) {
			// 先删除原来的问题关键字
			questionKeywordService.deleteQuestionkeywordByQuestionId(question.getId());
			// 增加新问题关键字
			String[] qkws = qkw.split(",");
			for (int r = 0; r < qkws.length; r++) {
				QuestionKeyword qkwo = new QuestionKeyword();
				qkwo.setKeyword(qkws[r]);
				qkwo.setQuestionId(question.getId());
				questionKeywordService.addQuestionKeyword(qkwo);
			}
		}
		request.setAttribute("questionId", question.getId());
		if(question.getStatus()==Config.getInt("QUESTION_AUDITING")){
			request.setAttribute("auding",1);
			request.setAttribute("tips", "问题修改成功，审核通过后将会在页面问题列表显示！");
		}else{
			request.setAttribute("tips", "问题补充成功！");
		}
		return SUCCESS;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}

	public void setQuestionKeywordService(QuestionKeywordService questionKeywordService) {
		this.questionKeywordService = questionKeywordService;
	}

	public void setSysdataService(SysdataService sysdataService) {
		this.sysdataService = sysdataService;
	}

}
