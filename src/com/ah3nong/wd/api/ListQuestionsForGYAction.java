package com.ah3nong.wd.api;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.system.SystemDataConfig;
import com.ah3nong.wd.util.JsonUtil;

public class ListQuestionsForGYAction extends GenericActionSupport<Question> {
	private static final long serialVersionUID = -7075092411481722854L;
	private int count;
	private int haveImg;
	private int domain;
	
	private QuestionService questionService;

	public void list() {
		List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
		
		if(count == 0){
			count = 8;
		}
		
		int parentId = 0;
		if(domain != 0){
			parentId = Integer.parseInt("-" + domain);
		}
		
		List<Question> qlist = new ArrayList<Question>();
		Map<String, Object> tmpParams = new HashMap<String, Object>();
		tmpParams.put("parentId", parentId);
		tmpParams.put("start", 0);
		tmpParams.put("size", (Integer)count);
		if(haveImg == 0){
			if(domain == 0){
				tmpParams.put("count", count);
				tmpParams.put("orderBy", "created_time desc");
				qlist = questionService.findQuestion(tmpParams);
			} else {
				tmpParams.put("type", "status");
				qlist = questionService.findQuestionsAsk(tmpParams);
			}
			if(qlist.size() > 0){
				for(int i = 0;i<qlist.size();i++){
					Map<String, Object> question = new HashMap<String, Object>();
					question.put("id", qlist.get(i).getId());
					question.put("subject", qlist.get(i).getSubject());
					questions.add(question);
				}
			}
		} else if(haveImg == 1){
			if(domain == 0){
				questions = questionService.findImgQuestions(tmpParams);
			} else {
				questions = questionService.findImgQuestionsAsk(tmpParams);
			}
			
			if(questions.size() > 0 ){
				for(int j=0;j<questions.size();j++){
					questions.get(j).put("url", "http://"+ SystemDataConfig.getSiteUrl() + "/question/" + questions.get(j).get("url") );
				}
			}
		} else{
			List<Map<String, Object>> qlists = new ArrayList<Map<String, Object>>();
			if(domain == 0){
				qlists = questionService.findNoImgQuestions(tmpParams);
			} else {
				qlists = questionService.findNoImgQuestionsByParentId(tmpParams);
			}
			if(qlists.size() > 0){
				for(int i = 0;i<qlists.size();i++){
					Map<String, Object> question = new HashMap<String, Object>();
					question.put("id", qlists.get(i).get("id"));
					question.put("subject",qlists.get(i).get("subject"));
					questions.add(question);
				}
			}
		}
		
		String questionsStr = "{\"result\":\"success\",\"data\":{\"questions\":" + JsonUtil.list2json(questions) + "}}";
		
		if (questionsStr != null) {
			try {
				response.setContentType("text/html;charset=utf-8");
				Writer write = response.getWriter();
				write.write(questionsStr);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setHaveImg(int haveImg) {
		this.haveImg = haveImg;
	}

	public void setDomain(int domain) {
		this.domain = domain;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
}
