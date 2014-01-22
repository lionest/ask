package com.ah3nong.wd.dao;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionExpert;
import com.ah3nong.wd.bean.QuestionImg;
import com.ah3nong.wd.exception.ServiceException;

public interface QuestionDao {
	// Question表
	public int addQuestion(Question question);

	public Question findQuestionByPrimaryKey(int id);

	public List<Question> findAllQuestion();

	/**
	 * 获取最新的打开的问题。
	 * @param count 条数
	 * @return 问题列表
	 */
	public List<Question> findLatestOpenedQuestion(int count);

	/**
	 * 获取最近的已解决的问题。
	 * @param count 条数
	 * @return 问题列表
	 */
	public List<Question> findLatestSolvedQuestion(int count);

	/**
	 * 获取推荐的问题。
	 * @param count 条数
	 * @return 问题列表
	 */
	public List<Question> findRecommendedQuestion(int count);

	public int delQuestionByPrimaryKey(int id);

	public int updateQuestionByPrimaryKey(Question record);

	public int updateQuestionByPrimaryKeySelective(Question record);

	public int countAllQuestion(Map map);

	public List<Question> queryForPager(Map map);

	public int countForPager(Map map);

	// QuestionExpert表
	public void addQuestionExpert(QuestionExpert questionExpert);

	public List<QuestionExpert> findQuestionExpertByQuestionId(int id);

	public int delQuestionExpertByQuestionId(int questionId);

	// QuestionImg表
	public void addQuestionImg(QuestionImg questionImg);

	public List<QuestionImg> findQuestionImgByQuestionId(int id, int status);

	public int delQuestionImgByQuestionId(int questionId);

	public int delQuestionImgByPrimaryKey(int id);

	public int updateQuestionImgByPrimaryKey(QuestionImg questionImg, boolean isSelective);

	public QuestionImg findQuestionImgById(int id);

	public List<Map<String, Object>> selectCountByParams(Map map); // 查找指定专家被采纳的问题的数量

	public List<QuestionImg> findRecomendQuestionWithImgs();

	// 查询最高级的领域以及该领域下的所有问题数量
	public List<Map<String, Object>> selectDomainNameAndQuestionNum(String nodePath);

	// 获得所有的问题以及该问题的创建时间以及回答数
	/**
	 * 
	 * @param map
	 * @return
	 * @deprecated
	 */
	public List<Question> findAllQuestionsByParams(Map<String, Object> map);

	// public List<Question> findQuestion(int domainId,boolean includeSelf, boolean includeChild, int start,int size);
	public List<Question> findQuestion(String domainNodePath, boolean includeSelf, boolean includeChild, String type, int start, int size);

	public List<Question> findQuestion(String type, int start, int size);

	// 获得全部问题中没有被回答的问题
	public List<Question> findAllNoReplyQuestions(Map<String, Object> map);

	// 获得全部问题中没有解决的问题
	public List<Question> findAllNoResolveQuestions(Map<String, Object> map);

	// 获得全部问题中已经解决的问题
	public List<Question> findAllResolvedQuestions(Map<String, Object> map);

	// 获得推荐的问题列表
	public List<Question> findAllRecommendedQuestions(Map<String, Object> map);

	// 所有问题为零回答的问题数量
	public List<Map<String, Object>> findNoReplyQuestionsNum();

	// 所有问题待解决的问题的数量
	public List<Map<String, Object>> findNoResolveQuestionsNum();

	// 所有问题解决的问题的数量
	public List<Map<String, Object>> findResolvedQuestionsNum();

	// 所有问题解决的问题的数量
	public List<Map<String, Object>> findRecommendedQuestionsNum();

	public List<Question> findQuestion(Map<String, Object> params);

	// 所有某个人的问题
	public List<Question> findQuestionsByUserId(Map<String, Object> params);

	// 所有某个人已解决的问题
	public List<Question> findResolvedQuestionsByUserId(Map<String, Object> params);

	// 所有某个人没解决的问题
	public List<Question> findNoResolvedQuestionsByUserId(Map<String, Object> params);

	// 所有某个人没解决的问题总数
	public int countNoResolvedQuestionsByUserId(Map<String, Object> params);

	// 所有某个人的问题总数
	public int countQuestionsByUserId(Map<String, Object> params);

	// 所有某个人已解决的问题总数
	public int countResolvedQuestionsByUserId(Map<String, Object> params);

	// 所有某个人回答的问题
	public List<Question> findReplyQuestionsByUserId(Map<String, Object> params);

	// 所有某个人回答的已解决的问题
	public List<Question> findReplyResolvedQuestionsByUserId(Map<String, Object> params);

	// 所有某个人回答的没解决的问题
	public List<Question> findReplyNoResolvedQuestionsByUserId(Map<String, Object> params);

	// 查询针对具体专家的所有提问
	public List<Question> findQuestionsAskForExpert(Map<String, Object> params);

	// 查询针对具体专家的所有提问
	public List<Question> findResoveQuestionsAskForExpert(Map<String, Object> params);

	// 查询针对具体专家的所有未解决提问
	public List<Question> findNoResoveQuestionsAskForExpert(Map<String, Object> params);

	// 计算针对具体专家的提问总数
	public int countQuestionsAskForExpert(Map<String, Object> params);

	// 计算针对具体专家的提问总数
	public int countResoveQuestionsAskForExpert(Map<String, Object> params);

	// 计算针对具体专家的没解决提问总数
	public int countNoResoveQuestionsAskForExpert(Map<String, Object> params);

	// 所有某个人回答的问题总数
	public int countReplyQuestionsByUserId(Map<String, Object> params);

	// 所有某个人回答的并已解决的问题总数
	public int countReplyResolvedQuestionsByUserId(Map<String, Object> params);

	// 所有某个人回答的并没解决的问题总数
	public int countReplyNoResolvedQuestionsByUserId(Map<String, Object> params);

	// 根据一级领域的ID 查找该领域下的所有问题
	public List<Question> findQuestionsByDomainId(Map<String, Object> map);

	public List<Question> findQuestionsByDomainId(int domainId, int start, int size);

	// 根据一级领域的ID 查找该领域下的所有零回答的问题
	public List<Question> findNoReplyQuestionsByDomainId(Map<String, Object> map);

	// 根据一级领域的ID 查找该领域下的所有待解决的问题
	public List<Question> findNoResolveQuestionsByDomainId(Map<String, Object> map);

	// 根据一级领域的ID 查找该领域下的所有已解决的问题
	public List<Question> findResolveQuestionsByDomainId(Map<String, Object> map);

	// 根据一级领域的ID 查找该领域下的所有推荐的问题
	public List<Question> findRecommendQuestionsByDomainId(Map<String, Object> map);

	public int countAllByDomainId(Map map);

	public List<Map<String, Object>> findQuestionsByExpertId(Map<String, Object> map);

	public Long countQuestionsByExpertId(Map<String, Object> map);

	public List<Map<String, Object>> findQuestionsAskByExpert(Map<String, Object> map);

	public Long countQuestionsAskByExpert(Map<String, Object> map);

	public List<Map<String, Object>> searchByTerm(Map map);

	public Long CountQuestionsSearchByTerms(Map<String, Object> map);

	// 根据以及领域的Id获得该领域下的所有子领域以及该领域下的问题数量
	public List<Map<String, Object>> getDomainNameById(Map<String, Object> map);

	public List<Map<String, Object>> selectAllDomainNameAndQuestionNum();

	public List<Question> getRecommendQuestionsForApi(int limit);

	public List<Question> getHeadQuestionsForApi(int limit);

	public List<Map<String, Object>> getQuestionWithImage();

	// 获得为我推荐的问题
	public List<Map<String, Object>> getQuestionsRecommendToExpert(Map<String, Object> params);

	// 获得为我推荐的问题条数
	public int getQuestionsRecommendToExpertNum(Map<String, Object> map);

	// 获得推荐的五条图片新闻
	public List<Map<String, Object>> getRecommendQuestionsWithImage();

	// 获得某日期之前的所有问题并修改状态
	public void updateStatusByCreatedTime(Map<String, Object> map);

	// 根据标题,创建时间获得问题
	public Question findBySubjectAndCreatedTime(Question question);

	// 根据用户id查用户评论问题
	public List<Question> findCommentQuestionByUserId(Map<String, Object> map);

	// 根据用户id查用户评论问题数
	public int countCommentQuestionByUserId(Map<String, Object> map);

	// 根据用户id查已解决的用户评论问题
	public List<Question> findCommentQuestionResovedByUserId(Map<String, Object> map);

	// 根据用户id查用户评论问题数
	public int countCommentQuestionResovedByUserId(Map<String, Object> map);

	// 根据用户id查未解决的用户评论问题
	public List<Question> findCommentQuestionNoResovedByUserId(Map<String, Object> map);

	// 根据用户id查用户没解决的评论问题数
	public int countCommentQuestionNoResovedByUserId(Map<String, Object> map);

	// 查用户采纳数排行
	public List<Map<String, Object>> selectUserAcceptCountByParams(Map map);

	// 查用户已有回复的提问数
	public int countQuestionsHaveReplyByUserId(Map<String, Object> params) throws ServiceException;

	// 获得当天问题数
	public int countTodayQuestion();

	// 获得当天回复数
	public int countTodayReply();

	// 获取浏览量最多问题
	public List<Question> findMostViewCountQuestions(int count);

	// 获得大分类下的一级分类及一级分类的问题数
	public List<Map<String, Object>> findDomainAndQusetionNumAsk(Map<String, Object> map);

	// 获得大分类下的所有问题
	public List<Question> findQuestionsAsk(Map<String, Object> map);

	// 获得大分类下的所有问题数
	public int countQuestionsAsk(Map<String, Object> map);

	// 获得大分类下的前五个图片问题
	public List<Map<String, Object>> findImgQuestionsAsk(Map<String, Object> map);

	public List<Map<String, Object>> findImgQuestions(Map<String, Object> map);

	public List<Map<String, Object>> findNoImgQuestions(Map<String, Object> map);

	public List<Map<String, Object>> findNoImgQuestionsByParentId(Map<String, Object> map);
	
	public void updateStatusByDomainId(Question question);
}
