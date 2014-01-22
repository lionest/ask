package com.ah3nong.wd.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.QuestionKeyword;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.QuestionKeywordDao;

public class QuestionKeywordDaoImpl extends BaseDao<QuestionKeyword> implements QuestionKeywordDao {

	@Override
	public void addQuestionKeyword(QuestionKeyword questionKeyword) {
		getSqlMapClientTemplate().insert("wd_question_keyword.insert", questionKeyword);
	}

	@Override
	public List<QuestionKeyword> findKeywordsByQuestionId(int questionId) {
		QuestionKeyword qk = new QuestionKeyword();
		qk.setQuestionId(questionId);
		return  getSqlMapClientTemplate().queryForList("wd_question_keyword.selectByQuestionId",qk);
	}

	@Override
	public void deleteQuestionkeywordByQuestionId(int questionId) {
		QuestionKeyword qk = new QuestionKeyword();
		qk.setQuestionId(questionId);
		getSqlMapClientTemplate().delete("wd_question_keyword.deleteByQusetionId", qk);
	}

	@Override
	public List<Map<String,Object>> findHotKeywords(int size) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("size", size);
		return getSqlMapClientTemplate().queryForList("wd_question_keyword.selectHotKeywords",map);
	}

}
