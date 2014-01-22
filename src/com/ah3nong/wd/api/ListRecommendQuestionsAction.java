package com.ah3nong.wd.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.service.QuestionService;

public class ListRecommendQuestionsAction extends GenericActionSupport<Question> {
	private static final long serialVersionUID = -7075092411481722854L;
	private static final int COUNT = 11;
	private QuestionService questionService;

	private List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();

	public String execute() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("count", COUNT);
		params.put("recommended", 1);
		params.put("orderBy", "created_time desc");
		// 获得所有分类11条最新推荐问题
		List<Question> questionList = questionService.findQuestion(params);
		for (int i = 0; i < questionList.size(); i++) {
			Question q = questionList.get(i);
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("id", (Integer) q.getId());
			hm.put("subject", (String) q.getSubject());
			questions.add(hm);
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
