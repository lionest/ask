package com.ah3nong.wd.service.impl;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.QuestionScore;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.dao.QuestionScoreDao;
import com.ah3nong.wd.dao.UserDao;
import com.ah3nong.wd.service.QuestionScoreService;

public class QuestionScoreServiceImpl implements QuestionScoreService {
	private QuestionScoreDao questionScoreDao ;
	private UserDao userDao;
	
	@Override
	public void addQuestionScore(QuestionScore questionScore) {
		questionScoreDao.addQuestionScore(questionScore);
		//更新用户经验
		User user = userDao.findUserByPrimaryKey(questionScore.getReplyUserId());
		if(questionScore.getExperience()!=0){
			user.setExperience(user.getExperience()+questionScore.getExperience()*questionScore.getScore()/5+Config.getInt("QUESTION_SCORE_EXPERIENCE")*questionScore.getScore());
		}else{
			user.setExperience(user.getExperience()+Config.getInt("QUESTION_SCORE_EXPERIENCE")*questionScore.getScore());
		}
		userDao.updateUserExperience(user);
	}

	@Override
	public int countUserScore(int userReplyId) {
		return questionScoreDao.countUserScore(userReplyId);
	}
	
	@Override
	public QuestionScore findQuestionScoreByQuestionId(int questionId) {
		return questionScoreDao.findQuestionScoreByQuestionId(questionId);
	}

	public void setQuestionScoreDao(QuestionScoreDao questionScoreDao) {
		this.questionScoreDao = questionScoreDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<Map<String,Object>> findAllAcceptQuestionByUserId(int userId) {
		return questionScoreDao.findAllAcceptQuestionByUserId(userId);
	}
	
}
