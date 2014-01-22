package com.ah3nong.wd.dao.impl;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionExpert;
import com.ah3nong.wd.bean.QuestionExtraContentHis;
import com.ah3nong.wd.bean.QuestionHis;
import com.ah3nong.wd.bean.QuestionImg;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.QuestionHisDao;

public class QuestionHisDaoImpl extends BaseDao<QuestionHisDaoImpl> implements QuestionHisDao {
	//Question表
	public void addQuestionHis(QuestionHis question) {
		getSqlMapClientTemplate().insert("wd_question_his.insert",question);
	}

	
	public int countAllQuestionHis() {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_question_his.countAll");
		return rows;
	}

	
	public int countForPager(Map map) {
		int rows =(Integer) getSqlMapClientTemplate().queryForObject("wd_question_his.countPaginationByPageNum",map);
		return rows;
	}

	
	public int delQuestionHisByPrimaryKey(int id) {
		QuestionHis key = new QuestionHis();
		key.setId(id);
		int rows = getSqlMapClientTemplate().delete("wd_question_his.deleteByPrimaryKey",key);
		return rows;
	}

	
	public List<Question> queryForPager(Map map) {
		List<Question> list = getSqlMapClientTemplate().queryForList("wd_question_his.selectPaginationByPageNum", map);
		return list;
	}

	
	public List<QuestionHis> findAllQuestionHis() {
		List<QuestionHis> list = getSqlMapClientTemplate().queryForList("wd_question_his.selectAll");
		return list;
	}

	
	public QuestionHis findQuestionHisByPrimaryKey(int id) {
		QuestionHis key = new QuestionHis();
		key.setId(id);
		QuestionHis record = (QuestionHis) getSqlMapClientTemplate().queryForObject("wd_question_his.selectByPrimaryKey",key);
		return record;
	}

	
	public int updateQuestionHisByPrimaryKey(QuestionHis record) {
		int rows = getSqlMapClientTemplate().update("wd_question_his.updateByPrimaryKey", record);
		return rows;
	}

	
	public int updateQuestionHisByPrimaryKeySelective(QuestionHis record) {
		int rows = getSqlMapClientTemplate().update("wd_question_his.updateByPrimaryKeySelective", record);
		return rows;
	}
	
	//QuestionExpert表
	
	public void addQuestionExpert(QuestionExpert questionExpert) {
		getSqlMapClientTemplate().insert("wd_question_his_expert.insert", questionExpert);
	}
	
	
	public List<QuestionExpert> findQuestionExpertByQuestionId(int questionId) {
		QuestionExpert key = new QuestionExpert();
		key.setQuestionId(questionId);
		List<QuestionExpert> list = getSqlMapClientTemplate().queryForList("wd_question_his_expert.selectByQuestionId", key);
		return list;
	}

	
	public int delQuestionExpertByQuestionId(int questionId) {
		QuestionExpert key = new QuestionExpert();
		key.setQuestionId(questionId);		 
		return getSqlMapClientTemplate().delete("wd_question_expert.deleteByQuestionId",key);
	}
	
	//QuestionImg表
	
	public void addQuestionImg(QuestionImg questionImg) {
		getSqlMapClientTemplate().insert("wd_question_img.insert", questionImg);
	}
	
	
	public List<QuestionImg> findQuestionImgByQuestionId(int questionId) {
		QuestionImg key = new QuestionImg();
		key.setQuestionId(questionId);
		List<QuestionImg> list = getSqlMapClientTemplate().queryForList("wd_question_img.selectByQuestionId", key);
		return list;
	}

	
	public int delQuestionImgByQuestionId(int questionId) {
		QuestionImg key = new QuestionImg();
		key.setQuestionId(questionId);
		return getSqlMapClientTemplate().delete("wd_question_img.deleteByQuestionId",key);
	}

	
	public int delQuestionImgByPrimaryKey(int id) {
		QuestionImg key = new QuestionImg();
		key.setId(id);
		return getSqlMapClientTemplate().delete("wd_question_img.deleteByPrimaryKey",key);
	}

	
	public int updateQuestionImgByPrimaryKey(QuestionImg questionImg) {
		int rows = getSqlMapClientTemplate().update("wd_question_img.updateByPrimaryKey", questionImg);
		return rows;
	}

	
	public QuestionImg findQuestionImgById(int id) {
		QuestionImg key = new QuestionImg();
		key.setId(id);
		QuestionImg record = (QuestionImg) getSqlMapClientTemplate().queryForObject("wd_question_img.selectByPrimaryKey",key);
		return record;
	}


	public void addQuestionExtraContentHis(
			QuestionExtraContentHis extraContentHis) {
		getSqlMapClientTemplate().insert("wd_question_ex_his.insert",extraContentHis);
		
	}


	public int countExtraContentForPager(Map map) {
		int rows =(Integer) getSqlMapClientTemplate().queryForObject("wd_question_ex_his.countPaginationByPageNum",map);
		return rows;
	}


	public List<QuestionExtraContentHis> queryExtraContentForPager(Map map) {
		List<QuestionExtraContentHis> list = getSqlMapClientTemplate().queryForList("wd_question_ex_his.selectPaginationByPageNum", map);
		return list;
	}


	public QuestionExtraContentHis findQEContentHisByPrimaryKey(int id) {
		QuestionExtraContentHis record = new QuestionExtraContentHis();
		record.setId(id);
		
		return (QuestionExtraContentHis) getSqlMapClientTemplate().queryForObject("wd_question_ex_his.selectByPrimaryKey",record);
	}


	public int updateQExHisSelective(QuestionExtraContentHis exHis) {
		int rows = getSqlMapClientTemplate().update("wd_question_ex_his.updateByPrimaryKeySelective", exHis);
		return rows;
	}
}
