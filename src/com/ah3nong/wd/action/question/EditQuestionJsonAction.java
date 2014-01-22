package com.ah3nong.wd.action.question;

import java.util.Date;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Notice;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.NoticeService;
import com.ah3nong.wd.service.QuestionService;

public class EditQuestionJsonAction extends GenericActionSupport<Question> {

	private static final long serialVersionUID = 8287527356649789271L;
	private int id;
	private int status;
	private String column;
	private QuestionService questionService;
	private NoticeService noticeService;
	
	public String execute() {
		try {
			Question question = new Question();
			question.setId(id);
			if(column.equals("recommended")){
				question.setRecommended(status);
			}else if (column.equals("status")){
				question.setStatus(status);
				if(status==Config.getInt("QUESTION_FAILED_AUDITING")){
					Question q = questionService.findQuestionByPrimaryKey(id);
					// 增加一个Notice
					Notice notice = new Notice();
					notice.setQuestionId(id);
					notice.setUserId(q.getUser().getId());
					notice.setType(Config.getInt("NOTICE_AUDIT_FAIL"));
					notice.setCreatedTime(new Date());
					noticeService.addNotice(notice);
				}
			}else{
				question.setHead(status);
			}
			questionService.updateQuestionByPrimaryKey(question, true);
			return SUCCESS;
		} catch (ServiceException e) {
			e.printStackTrace();
			return ERROR;	
		}
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

}
