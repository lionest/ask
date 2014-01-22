package com.ah3nong.wd.dao;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionExpert;
import com.ah3nong.wd.bean.QuestionExtraContentHis;
import com.ah3nong.wd.bean.QuestionHis;
import com.ah3nong.wd.bean.QuestionImg;

public interface QuestionHisDao {
	//QuestionHis表
	public void addQuestionHis(QuestionHis question);	
	public QuestionHis findQuestionHisByPrimaryKey(int id);	
	public List<QuestionHis> findAllQuestionHis();	
	public int delQuestionHisByPrimaryKey(int id);	
	public int updateQuestionHisByPrimaryKey(QuestionHis record);	
	public int updateQuestionHisByPrimaryKeySelective(QuestionHis record);	
	public int countAllQuestionHis();	
	public List<Question> queryForPager(Map map);	
	public int countForPager(Map map);
	//QuestionExpert表
	public void addQuestionExpert(QuestionExpert questionExpert);
	public List<QuestionExpert> findQuestionExpertByQuestionId(int id);
	public int delQuestionExpertByQuestionId(int questionId);
	//QuestionImg表
	public void addQuestionImg(QuestionImg questionImg);
	public List<QuestionImg> findQuestionImgByQuestionId(int id);
	public int delQuestionImgByQuestionId(int questionId);
	public int delQuestionImgByPrimaryKey(int id);
	public int updateQuestionImgByPrimaryKey(QuestionImg questionImg);
	public QuestionImg findQuestionImgById(int id);
	//QuestionExtraContent_HIS表
	public void addQuestionExtraContentHis(QuestionExtraContentHis qExtraContentHis);
	public List<QuestionExtraContentHis> queryExtraContentForPager(Map map);
	public int countExtraContentForPager(Map map);
	public QuestionExtraContentHis findQEContentHisByPrimaryKey(int id);
	public int updateQExHisSelective(QuestionExtraContentHis qExHis);
}
