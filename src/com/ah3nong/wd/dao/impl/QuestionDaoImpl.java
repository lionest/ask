package com.ah3nong.wd.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionExpert;
import com.ah3nong.wd.bean.QuestionImg;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.QuestionDao;

public class QuestionDaoImpl extends BaseDao<QuestionDaoImpl> implements QuestionDao {
	// Question表
	public int addQuestion(Question question) {
		return (Integer) getSqlMapClientTemplate().insert("wd_question.insert", question);
	}

	public int countAllQuestion(Map map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_question.countAll", map);
		return rows;
	}

	public int countAllByDomainId(Map map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_question.countAllByDomainId", map);
		return rows;
	}

	public int countForPager(Map map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_question.countPaginationByPageNum", map);
		return rows;
	}

	public int delQuestionByPrimaryKey(int id) {
		Question key = new Question();
		key.setId(id);
		int rows = getSqlMapClientTemplate().delete("wd_question.deleteByPrimaryKey", key);
		return rows;
	}

	public List<Question> queryForPager(Map map) {
		List<Question> list = getSqlMapClientTemplate().queryForList("wd_question.selectPaginationByPageNum", map);
		return list;
	}

	public List<Question> findAllQuestion() {
		List<Question> list = getSqlMapClientTemplate().queryForList("wd_question.selectAll");
		return list;
	}

	public List<Question> findQuestion(Map<String, Object> params) {
		List<Question> list = getSqlMapClientTemplate().queryForList("wd_question.selectByParams", params);
		return list;
	}

	public List<Question> findLatestOpenedQuestion(int count) {
		List<Question> list = getSqlMapClientTemplate().queryForList("wd_question.selectLatestOpened", count);
		return list;
	}

	public List<Question> findLatestSolvedQuestion(int count) {
		List<Question> list = getSqlMapClientTemplate().queryForList("wd_question.selectLatestSolved", count);
		return list;
	}

	public List<Question> findRecommendedQuestion(int count) {
		List<Question> list = getSqlMapClientTemplate().queryForList("wd_question.selectRecommended", count);
		return list;
	}

	public Question findQuestionByPrimaryKey(int id) {
		Question key = new Question();
		key.setId(id);
		Question record = (Question) getSqlMapClientTemplate().queryForObject("wd_question.selectByPrimaryKey", key);
		return record;
	}

	public int updateQuestionByPrimaryKey(Question record) {
		int rows = getSqlMapClientTemplate().update("wd_question.updateByPrimaryKey", record);
		return rows;
	}

	public int updateQuestionByPrimaryKeySelective(Question record) {
		int rows = getSqlMapClientTemplate().update("wd_question.updateByPrimaryKeySelective", record);
		return rows;
	}

	// QuestionExpert表

	public void addQuestionExpert(QuestionExpert questionExpert) {
		getSqlMapClientTemplate().insert("wd_question_expert.insert", questionExpert);
	}

	public List<QuestionExpert> findQuestionExpertByQuestionId(int questionId) {
		QuestionExpert key = new QuestionExpert();
		key.setQuestionId(questionId);
		List<QuestionExpert> list = getSqlMapClientTemplate().queryForList("wd_question_expert.selectByQuestionId", key);
		return list;
	}

	public int delQuestionExpertByQuestionId(int questionId) {
		QuestionExpert key = new QuestionExpert();
		key.setQuestionId(questionId);
		return getSqlMapClientTemplate().delete("wd_question_expert.deleteByQuestionId", key);
	}

	// QuestionImg表

	public void addQuestionImg(QuestionImg questionImg) {
		getSqlMapClientTemplate().insert("wd_question_img.insert", questionImg);
	}

	public List<QuestionImg> findQuestionImgByQuestionId(int questionId, int status) {
		QuestionImg key = new QuestionImg();
		key.setQuestionId(questionId);
		key.setStatus(status);
		List<QuestionImg> list = getSqlMapClientTemplate().queryForList("wd_question_img.selectByQuestionId", key);
		return list;
	}

	public int delQuestionImgByQuestionId(int questionId) {
		QuestionImg key = new QuestionImg();
		key.setQuestionId(questionId);
		return getSqlMapClientTemplate().delete("wd_question_img.deleteByQuestionId", key);
	}

	public int delQuestionImgByPrimaryKey(int id) {
		QuestionImg key = new QuestionImg();
		key.setId(id);
		return getSqlMapClientTemplate().delete("wd_question_img.deleteByPrimaryKey", key);
	}

	public int updateQuestionImgByPrimaryKey(QuestionImg questionImg, boolean isSelective) {
		int rows = 0;
		if (isSelective) {
			rows = getSqlMapClientTemplate().update("wd_question_img.updateByPrimaryKeySelective", questionImg);
		} else {
			rows = getSqlMapClientTemplate().update("wd_question_img.updateByPrimaryKey", questionImg);
		}

		return rows;
	}

	public QuestionImg findQuestionImgById(int id) {
		QuestionImg key = new QuestionImg();
		key.setId(id);
		QuestionImg record = (QuestionImg) getSqlMapClientTemplate().queryForObject("wd_question_img.selectByPrimaryKey", key);
		return record;
	}

	public List<Map<String, Object>> selectCountByParams(Map map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectCountByParams", map);
		Map hashMap = new HashMap();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> m = new HashMap<String, Object>();
			hashMap = (HashMap) list.get(i);
			m.put("id", (Integer) hashMap.get("id"));
			if(hashMap.get("fullName")==null||"".equals(hashMap.get("fullName"))){
				m.put("fullName", (String) hashMap.get("username"));
			}else{
				m.put("fullName", (String) hashMap.get("fullName"));
			}
			m.put("count", (Long) hashMap.get("count"));
			params.add(m);
		}
		return params;
	}
	public List<Map<String, Object>> selectUserAcceptCountByParams(Map map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectUserAcceptCountByParams", map);
		Map hashMap = new HashMap();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> m = new HashMap<String, Object>();
			hashMap = (HashMap) list.get(i);
			m.put("id", (Integer) hashMap.get("id"));
			if(hashMap.get("fullName")==null||"".equals(hashMap.get("fullName"))){
				m.put("fullName", (String) hashMap.get("username"));
			}else{
				m.put("fullName", (String) hashMap.get("fullName"));
			}
			m.put("count", (Long) hashMap.get("count"));
			params.add(m);
		}
		return params;
	}	
	

	public List<Map<String, Object>> selectDomainNameAndQuestionNum(String nodePath) {
		Map<String,Object> p = new HashMap<String,Object>();
		p.put("nodePath", nodePath);
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectAllDomainsAndQuestions", p);
		Map hashMap = new HashMap();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map map = new HashMap();
			hashMap = (HashMap) list.get(i);
			map.put("id", (Integer) hashMap.get("id"));
			map.put("name", (String) hashMap.get("name"));
			map.put("nodePath", (String) hashMap.get("nodePath"));
			map.put("count", (Long) hashMap.get("c"));
			params.add(map);
		}
		return params;
	}

	/**
	 * @deprecated
	 */
	public List<Question> findAllQuestionsByParams(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectAllQuestions", map);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}

	@Override
	public List<Question> findQuestion(String domainNodePath, boolean includeSelf, boolean includeChild, String type,  int start, int size) {
		if (!(includeSelf || includeChild)) {
			throw new java.lang.IllegalArgumentException("参数不正确。");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nodePath", domainNodePath);
		map.put("start", start);
		map.put("size", size);
		map.put("type",type);

		String sqlId;
		if (includeChild) {
			sqlId = "wd_question.findQuestionByNodePathIncludeChild";
		} else {
			sqlId = "wd_question.findQuestionByNodePathNotIncludeChild";
		}

		List<Map<String,Object>> list = getSqlMapClientTemplate().queryForList(sqlId, map);
		Map<String,Object> hashMap = new HashMap<String,Object>();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap<String,Object>) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			if(hashMap.get("experience")!=null){
				question.setExperience((Integer) hashMap.get("experience"));
			}
			questions.add(question);
		}
		return questions;
	}
	
	public List<Question> findQuestion(String type,int start,int size){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("size", size);
		map.put("type",type);

		String sqlId = "wd_question.findQuestionByType";

		List<Map<String,Object>> list = getSqlMapClientTemplate().queryForList(sqlId, map);
		Map<String,Object> hashMap = new HashMap<String,Object>();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap<String,Object>) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			if(hashMap.get("experience")!=null){
				question.setExperience((Integer) hashMap.get("experience"));
			}
			questions.add(question);
		}
		return questions;
	}

	public List<Question> findAllNoReplyQuestions(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectNoReplyQuestions", map);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}

	public List<Question> findAllNoResolveQuestions(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectNoResolveQuestions", map);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}

	public List<Question> findAllResolvedQuestions(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectResolveQuestions", map);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}

	public List<Question> findAllRecommendedQuestions(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectRecommendedQuestions", map);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}

	// 所有问题为零回答的问题的数量
	public List<Map<String, Object>> findNoReplyQuestionsNum() {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectNoReplyQuestionsNum");
		Map hashMap = new HashMap();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map map = new HashMap();
			hashMap = (HashMap) list.get(i);
			map.put("id", (Integer) hashMap.get("id"));
			map.put("name", (String) hashMap.get("name"));
			map.put("nodePath", (String) hashMap.get("nodePath"));
			map.put("count", (Long) hashMap.get("c"));
			params.add(map);
		}
		return params;
	}

	// 所有问题待解决的问题的数量
	public List<Map<String, Object>> findNoResolveQuestionsNum() {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectNoResolveQuestionsNum");
		Map hashMap = new HashMap();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map map = new HashMap();
			hashMap = (HashMap) list.get(i);
			map.put("id", (Integer) hashMap.get("id"));
			map.put("name", (String) hashMap.get("name"));
			map.put("nodePath", (String) hashMap.get("nodePath"));
			map.put("count", (Long) hashMap.get("c"));
			params.add(map);
		}
		return params;
	}

	// 所有问题解决的问题的数量
	public List<Map<String, Object>> findResolvedQuestionsNum() {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectResolveQuestionsNum");
		Map hashMap = new HashMap();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map map = new HashMap();
			hashMap = (HashMap) list.get(i);
			map.put("id", (Integer) hashMap.get("id"));
			map.put("name", (String) hashMap.get("name"));
			map.put("nodePath", (String) hashMap.get("nodePath"));
			map.put("count", (Long) hashMap.get("c"));
			params.add(map);
		}
		return params;
	}

	// 所有问题解决的问题的数量
	public List<Map<String, Object>> findRecommendedQuestionsNum() {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectRecommendedQuestionsNum");
		Map hashMap = new HashMap();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map map = new HashMap();
			hashMap = (HashMap) list.get(i);
			map.put("id", (Integer) hashMap.get("id"));
			map.put("name", (String) hashMap.get("name"));
			map.put("nodePath", (String) hashMap.get("nodePath"));
			map.put("count", (Long) hashMap.get("c"));
			params.add(map);
		}
		return params;
	}

	// 所有问题的数量
	public List<Map<String, Object>> selectAllDomainNameAndQuestionNum() {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectAllDomainQuestionsNum");
		Map hashMap = new HashMap();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map map = new HashMap();
			hashMap = (HashMap) list.get(i);
			map.put("id", (Integer) hashMap.get("id"));
			map.put("name", (String) hashMap.get("name"));
			map.put("nodePath", (String) hashMap.get("nodePath"));
			map.put("count", (Long) hashMap.get("c"));
			params.add(map);
		}
		return params;
	}
	
	/**
	 * @deprecated
	 */
	public List<Question> findQuestionsByDomainId(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectQuestionsByDomainId", map);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}

	public List<Question> findQuestionsByDomainId(int domainId, int start, int size) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("domainId", domainId);
		map.put("start", start);
		map.put("size", size);
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectQuestionsByDomainId", map);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}

	public List<Question> findNoReplyQuestionsByDomainId(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectNoReplyQuestionsByDomainId", map);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}

	public List<Question> findNoResolveQuestionsByDomainId(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectNoResolveQuestionsByDomainId", map);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}

	public List<Question> findResolveQuestionsByDomainId(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectResolveQuestionsByDomainId", map);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}

	public List<Question> findRecommendQuestionsByDomainId(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectRecommendQuestionsByDomainId", map);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}

	public List<Map<String, Object>> findQuestionsByExpertId(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectQuestionsByExpertId", map);
		Map hashMap = new HashMap();
		List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			Map m = new HashMap();
			m.put("questionId", (Integer) hashMap.get("questionId"));
			m.put("id", (Integer) hashMap.get("id"));
			m.put("status", (Integer) hashMap.get("qstatus"));
			m.put("name", (String) hashMap.get("name"));
			m.put("subject", (String) hashMap.get("subject"));
			questions.add(m);
		}
		return questions;
	}

	public Long countQuestionsByExpertId(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.CountQuestionsByExpertId", map);
		Map hashMap = new HashMap();
		Long l = null;
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			l = (Long) hashMap.get("count");
		}
		return l;
	}

	public List<Map<String, Object>> searchByTerm(Map map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectSearchByTerms", map);
		Map hashMap = new HashMap();
		List<Map<String, Object>> m = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			Map p = new HashMap();
			p.put("id", (Integer) hashMap.get("id"));
			p.put("subject", (String) hashMap.get("subject"));
			p.put("replyNum", (Integer) hashMap.get("replyNum"));
			p.put("content", (String) hashMap.get("content"));
			p.put("createdTime", (Date) hashMap.get("createdTime"));
			p.put("domainName", (String) hashMap.get("domainName"));
			p.put("status", (Integer) hashMap.get("status"));
			m.add(p);
		}
		return m;
	}

	public Long CountQuestionsSearchByTerms(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.CountQuestions", map);
		Map hashMap = new HashMap();
		Long l = null;
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			l = (Long) hashMap.get("count");
		}
		return l;
	}

	// Search

	public List<Question> findQuestionsByUserId(Map<String, Object> params) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectQuestionsByUserId", params);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			question.setStatus((Integer) hashMap.get("status"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			questions.add(question);
		}
		return questions;
	}

	public List<Question> findResolvedQuestionsByUserId(Map<String, Object> params) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectResolvedQuestionsByUserId", params);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}
	
	public List<Question> findNoResolvedQuestionsByUserId(Map<String, Object> params) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectNoResolvedQuestionsByUserId", params);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}
	
	public int countNoResolvedQuestionsByUserId(Map<String, Object> map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_question.countNoResolvedQuestionsByUserId", map);
		return rows;
	}

	public int countQuestionsByUserId(Map<String, Object> map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_question.countQuestionsByUserId", map);
		return rows;
	}
	
	public int countQuestionsHaveReplyByUserId(Map<String, Object> map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_question.countQuestionsHaveReplyByUserId", map);
		return rows;
	}

	public int countResolvedQuestionsByUserId(Map<String, Object> map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_question.countResolvedQuestionsByUserId", map);
		return rows;
	}

	public List<QuestionImg> findRecomendQuestionWithImgs() {
		List<QuestionImg> qImgList = getSqlMapClientTemplate().queryForList("wd_question_img.selectRecommendQuestionWithImg");
		return qImgList;
	}

	// 根据以及领域的Id获得该领域下的所有子领域以及该领域下的问题数量
	public List<Map<String, Object>> getDomainNameById(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.SelectDomainNameById", map);
		Map hashMap = new HashMap();
		List<Map<String, Object>> domainIds = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("name", hashMap.get("name"));
			m.put("id", hashMap.get("id"));
			m.put("count", hashMap.get("c"));
			domainIds.add(m);
		}
		return domainIds;
	}

	public List<Question> findReplyQuestionsByUserId(Map<String, Object> params) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectReplyQuestionsByUserId", params);
		Map hashMap = new HashMap();
		if (list == null) {
			return null;
		}
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			User user = new User();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			question.setStatus((Integer) hashMap.get("status"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			if((Integer) hashMap.get("isAccepted")==1){
				question.setUserId((Integer) hashMap.get("userId"));
			}
			user.setId((Integer) hashMap.get("userId"));
			question.setUser(user);
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			questions.add(question);
		}
		return questions;
	}

	public List<Question> findReplyResolvedQuestionsByUserId(Map<String, Object> params) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectReplyResolvedQuestionsByUserId", params);
		Map hashMap = new HashMap();
		if (list == null) {
			return null;
		}
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			User user = new User();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			user.setId((Integer) hashMap.get("userId"));
			question.setUser(user);
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}
	
	public List<Question> findReplyNoResolvedQuestionsByUserId(Map<String, Object> params) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectReplyNoResolvedQuestionsByUserId", params);
		Map hashMap = new HashMap();
		if (list == null) {
			return null;
		}
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			User user = new User();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			user.setId((Integer) hashMap.get("userId"));
			question.setUser(user);
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}

	public int countReplyQuestionsByUserId(Map<String, Object> map) {
		Object result = getSqlMapClientTemplate().queryForObject("wd_question.countReplyQuestionsByUserId", map);
		if (result == null) {
			return 0;
		}
		return (Integer) result;
	}

	public int countReplyResolvedQuestionsByUserId(Map<String, Object> map) {
		Object result = getSqlMapClientTemplate().queryForObject("wd_question.countReplyResolvedQuestionsByUserId", map);
		if (result == null) {
			return 0;
		}
		return (Integer) result;
	}
	
	public int countReplyNoResolvedQuestionsByUserId(Map<String, Object> map) {
		Object result = getSqlMapClientTemplate().queryForObject("wd_question.countReplyNoResolvedQuestionsByUserId", map);
		if (result == null) {
			return 0;
		}
		return (Integer) result;
	}

	public int countQuestionsAskForExpert(Map<String, Object> params) {
		int result = (Integer) getSqlMapClientTemplate().queryForObject("wd_question.countQuestionsAskForExpert", params);
		return result;
	}

	public List<Question> findQuestionsAskForExpert(Map<String, Object> params) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectQuestionsAskForExpert", params);
		Map hashMap = new HashMap();
		if (list == null) {
			return null;
		}
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setStatus((Integer) hashMap.get("status"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}

	public int countResoveQuestionsAskForExpert(Map<String, Object> params) {
		int result = (Integer) getSqlMapClientTemplate().queryForObject("wd_question.countResolveQuestionsAskForExpert", params);
		return result;
	}
	
	public int countNoResoveQuestionsAskForExpert(Map<String, Object> params) {
		int result = (Integer) getSqlMapClientTemplate().queryForObject("wd_question.countNoResolveQuestionsAskForExpert", params);
		return result;
	}

	public List<Question> findResoveQuestionsAskForExpert(Map<String, Object> params) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectResolveQuestionsAskForExpert", params);
		Map hashMap = new HashMap();
		if (list == null) {
			return null;
		}
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}
	
	public List<Question> findNoResoveQuestionsAskForExpert(Map<String, Object> params) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectNoResolveQuestionsAskForExpert", params);
		Map hashMap = new HashMap();
		if (list == null) {
			return null;
		}
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			questions.add(question);
		}
		return questions;
	}

	public Long countQuestionsAskByExpert(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.CountQuestionsAskByExpert", map);
		Map hashMap = new HashMap();
		Long l = null;
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			l = (Long) hashMap.get("count");
		}
		return l;
	}

	public List<Map<String, Object>> findQuestionsAskByExpert(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectQuestionsAskByExpert", map);
		Map hashMap = new HashMap();
		List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			Map m = new HashMap();
			m.put("questionId", (Integer) hashMap.get("id"));
			m.put("status", (Integer) hashMap.get("status"));
			m.put("name", (String) hashMap.get("name"));
			m.put("subject", (String) hashMap.get("subject"));
			questions.add(m);
		}
		return questions;
	}

	@Override
	public List<Question> getRecommendQuestionsForApi(int limit) {
		return getSqlMapClientTemplate().queryForList(
				"wd_question.selectRecommendedForApi", limit);
	}

	@Override
	public List<Question> getHeadQuestionsForApi(int limit) {
		return getSqlMapClientTemplate().queryForList(
				"wd_question.selectHeadsForApi", limit);
	}

	@Override
	public List<Map<String, Object>> getQuestionWithImage() {
		List<Map> list = getSqlMapClientTemplate().queryForList(
				"wd_question.selectRecommendedImageForApi");
		Map hashMap = new HashMap();
		List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			Map m = new HashMap();
			m.put("id", (Integer) hashMap.get("id"));
			m.put("subject", (String) hashMap.get("subject"));
			m.put("url", (String) hashMap.get("url"));
			questions.add(m);
		}
		return questions;
	}
	
	@Override
	public List<Map<String, Object>> getQuestionsRecommendToExpert(Map<String, Object> params) {
		List<Map> list = getSqlMapClientTemplate().queryForList(
				"wd_question.selectQuestionsRecommendToExpert", params);
		Map hashMap = new HashMap();
		List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			Map m = new HashMap();
			m.put("id", (Integer) hashMap.get("id"));
			m.put("subject", (String) hashMap.get("subject"));
			m.put("createdTime",  hashMap.get("createdTime").toString());
			m.put("replyNum", (Integer) hashMap.get("replyNum"));
			questions.add(m);
		}
		return questions;
	}
	public int getQuestionsRecommendToExpertNum(Map<String, Object> map) {
		Object result = getSqlMapClientTemplate().queryForObject("wd_question.selectQuestionsRecommendToExpertNum", map);
		if (result == null) {
			return 0;
		}
		return (Integer) result;
	}

	@Override
	public List<Map<String, Object>> getRecommendQuestionsWithImage() {
		List<Map> list = getSqlMapClientTemplate().queryForList(
				"wd_question.selectRecommendedImageQuestions");
		Map hashMap = new HashMap();
		List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			Map m = new HashMap();
			m.put("id", (Integer) hashMap.get("id"));
			m.put("subject", (String) hashMap.get("subject"));
			m.put("url", (String) hashMap.get("url"));
			questions.add(m);
		}
		return questions;
	}
	
	@Override
	public List<Map<String, Object>> findImgQuestions(Map<String,Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList(
				"wd_question.selectImgQuestions",map);
		Map hashMap = new HashMap();
		List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			Map m = new HashMap();
			m.put("id", (Integer) hashMap.get("id"));
			m.put("subject", (String) hashMap.get("subject"));
			m.put("url", (String) hashMap.get("url"));
			questions.add(m);
		}
		return questions;
	}

	@Override
	public void updateStatusByCreatedTime(Map<String, Object> map) {
		getSqlMapClientTemplate().update("wd_question.updateStatusByCreatedTime", map);
	}

	@Override
	public Question findBySubjectAndCreatedTime(Question question) {
		return (Question) getSqlMapClientTemplate().queryForObject("wd_question.selectBySubjectAndCreatedTime",question);
	}

	@Override
	public List<Question> findCommentQuestionByUserId(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectCommentQuestionsByUserId", map);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			User user = new User();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			question.setStatus((Integer) hashMap.get("status"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			user.setId((Integer) hashMap.get("userId"));
			question.setUser(user);
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			questions.add(question);
		}
		return questions;
	}

	@Override
	public int countCommentQuestionByUserId(Map<String, Object> map) {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("wd_question.countCommentQuestionsByUserId", map);
		return count;
	}

	@Override
	public List<Question> findCommentQuestionResovedByUserId(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectCommentQuestionsResovedByUserId", map);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			User user = new User();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			question.setStatus((Integer) hashMap.get("status"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			user.setId((Integer) hashMap.get("userId"));
			question.setUser(user);
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			questions.add(question);
		}
		return questions;
	}

	@Override
	public int countCommentQuestionResovedByUserId(Map<String, Object> map) {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("wd_question.countCommentQuestionsResovedByUserId", map);
		return count;
	}
	
	@Override
	public List<Question> findCommentQuestionNoResovedByUserId(Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_question.selectCommentQuestionsNoResovedByUserId", map);
		Map hashMap = new HashMap();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			User user = new User();
			hashMap = (HashMap) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			question.setStatus((Integer) hashMap.get("status"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			user.setId((Integer) hashMap.get("userId"));
			question.setUser(user);
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			questions.add(question);
		}
		return questions;
	}
	
	@Override
	public int countCommentQuestionNoResovedByUserId(Map<String, Object> map) {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("wd_question.countCommentQuestionsNoResovedByUserId", map);
		return count;
	}
	
	@Override
	public int countTodayQuestion() {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("wd_question.todayQuestionCount");
		return count;
	}
	
	@Override
	public int countTodayReply() {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("wd_question.todayReplyCount");
		return count;
	}

	@Override
	public List<Question> findMostViewCountQuestions(int count) {
		return getSqlMapClientTemplate().queryForList("wd_question.selectMostViewCountQuestions",count);
	}

	@Override
	public List<Map<String, Object>> findDomainAndQusetionNumAsk(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("wd_question.selectDomainAndQusetionNumAsk",map);
	}

	@Override
	public List<Question> findQuestionsAsk(Map<String, Object> map) {
		List<Map<String,Object>> list = getSqlMapClientTemplate().queryForList("wd_question.selectQuestionsAsk", map);
		Map<String,Object> hashMap = new HashMap<String,Object>();
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < list.size(); i++) {
			Question question = new Question();
			Domain domain = new Domain();
			hashMap = (HashMap<String,Object>) list.get(i);
			question.setId((Integer) hashMap.get("id"));
			question.setSubject((String) hashMap.get("subject"));
			domain.setName((String) hashMap.get("name"));
			question.setDomain(domain);
			question.setCreatedTime((Date) hashMap.get("createdTime"));
			question.setReplyNum((Integer) hashMap.get("replyNum"));
			if(hashMap.get("experience")!=null){
				question.setExperience((Integer) hashMap.get("experience"));
			}
			questions.add(question);
		}
		return questions;
	}

	@Override
	public int countQuestionsAsk(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject("wd_question.countQuestionsAsk", map);
	}

	@Override
	public List<Map<String,Object>> findImgQuestionsAsk(Map<String, Object> map) {
		List<Map<String,Object>> list = getSqlMapClientTemplate().queryForList("wd_question.selectImgQuestionsAsk", map);
		return list;
	}

	@Override
	public List<Map<String, Object>> findNoImgQuestions(Map<String, Object> map) {
		List<Map<String,Object>> list = getSqlMapClientTemplate().queryForList("wd_question.selectNoImgQuestions", map);
		return list;
	}

	@Override
	public List<Map<String, Object>> findNoImgQuestionsByParentId(Map<String, Object> map) {
		List<Map<String,Object>> list = getSqlMapClientTemplate().queryForList("wd_question.selecNoImgQuestionsByParentId", map);
		return list;
	}

	@Override
	public void updateStatusByDomainId(Question question) {
		getSqlMapClientTemplate().update("wd_question.updateStatusByDomainId",question);
	}
}