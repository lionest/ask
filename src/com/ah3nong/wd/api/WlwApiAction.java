package com.ah3nong.wd.api;

import java.io.IOException;
import java.io.Writer;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.service.QuestionService;

/**
 * 物联网 API Action 获取问题API
 */
public class WlwApiAction extends GenericActionSupport<Question> {

	private static final long serialVersionUID = 1L;
	private QuestionService questionService;

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	// 获得头条四个问题
	public void getHeadQuestions() {
		String questionString = questionService.getHeadQuestionsForApi(4);
		if (questionString != null) {
			try {
				response.setContentType("text/html;charset=utf-8");
				Writer write = response.getWriter();
				write.write(questionString);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	// 获得带图片的问题
	public void getImageQuesitons() {
		String questionString = questionService.getQuestionWithImage();
		if (questionString != null) {
			try {
				response.setContentType("text/html;charset=utf-8");
				Writer write = response.getWriter();
				write.write(questionString);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	// 获得最新推荐的问题
	public void getRecommendQuestions() {
		String questionString = questionService.getRecommendQuestionsForApi(12);
		if (questionString != null) {
			try {
				response.setContentType("text/html;charset=utf-8");
				Writer write = response.getWriter();
				write.write(questionString);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
