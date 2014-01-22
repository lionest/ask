package com.ah3nong.wd.service;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.QuestionScore;

public interface QuestionScoreService {
	// 为满意答案增加分数记录
	public void addQuestionScore(QuestionScore questionScore);

	// 获得用户总分数
	public int countUserScore(int userReplyId);

	// 根据问题id获得问题评分
	public QuestionScore findQuestionScoreByQuestionId(int questionId);

	// 查用户所有被采纳问题分数（包括没有分数记录的
	public List<Map<String, Object>> findAllAcceptQuestionByUserId(int userId);
}
