package com.ah3nong.wd.dao.impl;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.QuestionScore;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.QuestionScoreDao;

public class QuestionScoreDaoImpl extends BaseDao<QuestionScore> implements QuestionScoreDao {

	@Override
	public void addQuestionScore(QuestionScore questionScore) {
		getSqlMapClientTemplate().insert("wd_question_score.insert", questionScore);
	}

	@Override
	public int countUserScore(int userReplyId) {
		QuestionScore questionScore = new QuestionScore();
		questionScore.setReplyUserId(userReplyId);
		return (Integer) getSqlMapClientTemplate().queryForObject("wd_question_score.countUserScore",questionScore);
	}

	@Override
	public QuestionScore findQuestionScoreByQuestionId(int questionId) {
		QuestionScore questionScore = new QuestionScore();
		questionScore.setQuestionId(questionId);
		return (QuestionScore) getSqlMapClientTemplate().queryForObject("wd_question_score.selectByQuestionId", questionScore);
	}

	@Override
	public List<Map<String,Object>> findAllAcceptQuestionByUserId(int userId) {
		return getSqlMapClientTemplate().queryForList("wd_question_score.selectAcceptQuestionByUserId",userId);
	}

}
