package com.ah3nong.wd.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionService;

public class ListNewQuestionsAction extends GenericActionSupport<Question> {
	private static final long serialVersionUID = 163755058584315915L;
	private static final int COUNT = 12;
	private QuestionService questionService;

	private List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();

	public String execute() {
		try {
			List<Question> questionList;
			//获得所有分类12条最新问题
			questionList = questionService.getAllQuestionsAndReplyNum(1, COUNT, "status", 0, 1);
			for (int i = 0; i < questionList.size(); i++) {
				Question q = questionList.get(i);
				HashMap<String, Object> hm = new HashMap<String, Object>();
				hm.put("id", (Integer) q.getId());
				hm.put("subject", (String) q.getSubject());
				hm.put("domain", (String) q.getDomain().getName());
				questions.add(hm);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public List<Map<String, Object>> getQuestions() {
		return questions;
	}

}
