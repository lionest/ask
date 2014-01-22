package com.ah3nong.wd.service.impl;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.QuestionKeyword;
import com.ah3nong.wd.dao.QuestionKeywordDao;
import com.ah3nong.wd.service.QuestionKeywordService;

public class QuestionKeywordServiceImpl implements QuestionKeywordService {
	private QuestionKeywordDao questionKeywordDao;
	@Override
	public void addQuestionKeyword(QuestionKeyword questionKeyword) {
		questionKeywordDao.addQuestionKeyword(questionKeyword);
	}

	@Override
	public List<QuestionKeyword> findKeywordsByQuestionId(int questionId) {
		return questionKeywordDao.findKeywordsByQuestionId(questionId);
	}

	@Override
	public void deleteQuestionkeywordByQuestionId(int questionId) {
		questionKeywordDao.deleteQuestionkeywordByQuestionId(questionId);
	}

	@Override
	public List<Map<String, Object>> findHotKeywords(int size) {
		return questionKeywordDao.findHotKeywords(size);
	}

	public void setQuestionKeywordDao(QuestionKeywordDao questionKeywordDao) {
		this.questionKeywordDao = questionKeywordDao;
	}

}
