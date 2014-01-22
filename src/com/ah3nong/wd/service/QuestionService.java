package com.ah3nong.wd.service;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Expert;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionExpert;
import com.ah3nong.wd.bean.QuestionExtraContentHis;
import com.ah3nong.wd.bean.QuestionHis;
import com.ah3nong.wd.bean.QuestionImg;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.exception.ServiceException;

public interface QuestionService {
	public Question findQuestionByPrimaryKey(int id) throws ServiceException;

	// public List<Question> findAllQuestion() throws ServiceException;

	public List<Question> findQuestion(Map<String, Object> params);

	public List<Question> findLatestOpenedQuestion(int count);

	public List<Question> findLatestSolvedQuestion(int count);

	public List<Question> findRecommendedQuestion(int count);

	public List<Question> findQuestion(int domainId, boolean includeSelf, boolean includeChild, int start, int size);

	public int addQuestion(Question question, QuestionExpert questionExpert, List<QuestionImg> questionImgList) throws ServiceException;

	public Pager<Question> getQuestionPagerByParam(Map params, int currentPage, int pageSize) throws ServiceException;

	public int delQuestionByPrimaryKey(int id) throws ServiceException;

	public int updateQuestionByPrimaryKey(Question question, boolean isSelective) throws ServiceException;

	public int countAllQuestion(String terms) throws ServiceException;

	public int updateQuestionImgs(List<QuestionImg> questionImgs, int questionId) throws ServiceException;

	// QuestionImg
	public List<QuestionImg> findImgsByQuestionId(int questionId, int status) throws ServiceException;

	public int delQuestionImgByPrimaryKey(int questionImgId) throws ServiceException;

	public int updateQuestionImgs(QuestionImg questionImg, boolean isSelective) throws ServiceException;

	public QuestionImg findQuestionImgByPrimaryKey(int questionImgId) throws ServiceException;

	public List<QuestionImg> findRecommendQuestionWithImg() throws ServiceException;

	// QuestionExpert
	public Expert findExpertByQuestionId(int questionId) throws ServiceException;

	public List<Map<String, Object>> getExpertAcceptedCountMap(int isAcceptedCount) throws ServiceException; // 获得指定专家被采纳的问题的数量

	public List<Map<String, Object>> getMostDomainNameAndQuestionNum(String terms, int t, int domain) throws ServiceException;

	public List<Question> getAllQuestionsAndReplyNum(int currentPage, int pageSize, String terms, int domain, int t) throws ServiceException;

	// 根据领域的ID 该领域下面所有问题的数量
	public int countAllByDomainId(String terms, int domainId) throws ServiceException;

	// QuestionHis
	public void addQuestionHis(QuestionHis questionHis) throws ServiceException;

	public Pager<Question> getQuestionHisPagerByParam(Map params, int currentPage, int pageSize) throws ServiceException;

	public QuestionHis getQuestionHisByPrimaryKey(int questionHisId) throws ServiceException;

	public int updateQuestionHisSelective(QuestionHis questionHis) throws ServiceException;

	// QuestionExtraContentHis
	public void addQuestionExtraContentHis(QuestionExtraContentHis qExtraContenthis) throws ServiceException;

	public Pager<QuestionExtraContentHis> getQuestionExtraContentHisPagerByParam(Map params, int currentPage, int pageSize) throws ServiceException;

	public QuestionExtraContentHis getQuestionExtraContentHisByPrimaryKey(int qEContentHisId) throws ServiceException;

	public int updateQExHisSelective(QuestionExtraContentHis qExHis) throws ServiceException;

	public List<Map<String, Object>> getQuestionsBuExpertId(int expertId, int start, int size) throws ServiceException;

	public Long getCountQuestionsByExpertId(int expertId) throws ServiceException;

	public List<Map<String, Object>> getQuestionsAskByExpert(int expertId, int start, int size) throws ServiceException;

	public Long getCountQuestionsAskByExpert(int expertId) throws ServiceException;

	public List<Map<String, Object>> searchByTerm(String terms, int start, int size, int status) throws ServiceException;

	public Long CountQuestionsSearchbyTerms(String terms, int status) throws ServiceException;

	public List<Question> findQuestionsByUserId(Map<String, Object> params) throws ServiceException;

	public List<Question> findResolvedQuestionsByUserId(Map<String, Object> params) throws ServiceException;

	public int countQuestionsByUserId(Map<String, Object> params) throws ServiceException;

	public int countResolvedQuestionsByUserId(Map<String, Object> params) throws ServiceException;

	public List<Question> findQuestionsAskForExpert(Map<String, Object> params) throws ServiceException;

	public int countQuestionsAskForExpert(Map<String, Object> params) throws ServiceException;

	public List<Question> findResoveQuestionsAskForExpert(Map<String, Object> params) throws ServiceException;

	public int countResoveQuestionsAskForExpert(Map<String, Object> params) throws ServiceException;

	public List<Question> findReplyQuestionsByUserId(Map<String, Object> params) throws ServiceException;

	public List<Question> findReplyResolvedQuestionsByUserId(Map<String, Object> params) throws ServiceException;

	public int countReplyQuestionsByUserId(Map<String, Object> params) throws ServiceException;

	public int countReplyResolvedQuestionsByUserId(Map<String, Object> params) throws ServiceException;

	public List<Map<String, Object>> getMostDomainNameAndQuestionNumAll(String terms, int t, int domain) throws ServiceException;

	// WLW API
	public String getRecommendQuestionsForApi(int limit);

	public String getHeadQuestionsForApi(int limit);

	public String getQuestionWithImage();

	public List<Map<String, Object>> getQuestionWithRecommendImage();

	// 获得为我推荐的问题
	public List<Map<String, Object>> getQuestionsRecommendToExpert(Map<String, Object> params) throws ServiceException;

	// 获得为我推荐问题条数
	public int getQuestionsRecommendToExpertNum(Map<String, Object> params) throws ServiceException;

	// 获得推荐的五条图片新闻
	public List<Map<String, Object>> getRecommendQuestionsWithImage();

	// 获得某日期之前的所有问题并修改状态
	public void updateStatusByCreatedTime(Map<String, Object> map);

	// 根据标题,创建时间获得问题
	public Question findBySubjectAndCreatedTime(Question question);

	// 根据用户id查用户评论问题
	public List<Question> findCommentQuestionByUserId(Map<String, Object> map) throws ServiceException;

	// 根据用户id查用户评论问题数
	public int countCommentQuestionByUserId(Map<String, Object> map) throws ServiceException;

	// 根据用户id查已解决的用户评论问题
	public List<Question> findCommentQuestionResovedByUserId(Map<String, Object> map) throws ServiceException;

	// 根据用户id查用户评论问题数
	public int countCommentQuestionResovedByUserId(Map<String, Object> map) throws ServiceException;

	// 查用户采纳数排行
	public List<Map<String, Object>> getUserAcceptCountMap(int isAcceptedCount) throws ServiceException;

	// 查用户已有回复 的提问数
	public int countQuestionsHaveReplyByUserId(Map<String, Object> params) throws ServiceException;

	// 获得当天问题数
	public int countTodayQuestion();

	// 获得当天回复数
	public int countTodayReply();

	// 获取浏览量最多问题
	public List<Question> findMostViewCountQuestions(int count);

	// 所有某个人没解决的问题
	public List<Question> findNoResolvedQuestionsByUserId(Map<String, Object> params) throws ServiceException;

	// 所有某个人没解决的问题总数
	public int countNoResolvedQuestionsByUserId(Map<String, Object> params) throws ServiceException;

	// 查询针对具体专家的所有提问
	public List<Question> findNoResoveQuestionsAskForExpert(Map<String, Object> params) throws ServiceException;

	// 计算针对具体专家的没解决提问总数
	public int countNoResoveQuestionsAskForExpert(Map<String, Object> params) throws ServiceException;

	// 根据用户id查未解决的用户评论问题
	public List<Question> findCommentQuestionNoResovedByUserId(Map<String, Object> map) throws ServiceException;

	// 根据用户id查用户没解决的评论问题数
	public int countCommentQuestionNoResovedByUserId(Map<String, Object> map) throws ServiceException;

	// 所有某个人回答的并没解决的问题总数
	public int countReplyNoResolvedQuestionsByUserId(Map<String, Object> params) throws ServiceException;

	// 所有某个人回答的没解决的问题
	public List<Question> findReplyNoResolvedQuestionsByUserId(Map<String, Object> params) throws ServiceException;

	// 获得大分类下的一级分类及一级分类的问题数
	public List<Map<String, Object>> findDomainAndQusetionNumAsk(Map<String, Object> map);

	// 获得大分类下的所有问题
	public List<Question> findQuestionsAsk(Map<String, Object> map);

	// 获得大分类下的所有问题数
	public int countQuestionsAsk(Map<String, Object> map);
	
	// 获得大分类下的前五个图片问题
	public List<Map<String,Object>> findImgQuestionsAsk(Map<String, Object> map);
	
	public List<Map<String, Object>> findImgQuestions(Map<String,Object> map);
	
	public List<Map<String, Object>> findNoImgQuestions(Map<String, Object> map);

	public List<Map<String, Object>> findNoImgQuestionsByParentId(Map<String, Object> map);
	
	public void updateStatusByDomainId(Question question);

}
