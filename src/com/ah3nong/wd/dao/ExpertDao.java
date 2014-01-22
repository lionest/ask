package com.ah3nong.wd.dao;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Expert;


public interface ExpertDao {
	/**
	 * 根据领域id获取专家。
	 * @param domainId 领域id
	 * @return 专家列表
	 */
	public List<Expert> findExpertByDomainId(int domainId);
	
	/**
	 * 获取推荐的专家。
	 * @return 专家列表
	 */
	public List<Expert> findRecommendedExpert(int count);
	
	public List<Expert> findAllExpert();
	
	public Expert findExpertByPrimaryKey(int id);
	
	public int delExpertByPrimaryKey(int id);
	
	public int updateExpertByPrimaryKey(Expert record);
	
	public int updateExpertByPrimaryKeySelective(Expert record);
	
	public int countAllExpert();
	
	public List<Expert> queryForPager(Map map);
	
	public int countForPager(Map map);
	
	public void inserExpertByPrams(Expert expert);
	
	public Expert findExpertByID(int expertId);

	public List<Expert> findExpert(Map<String, Object> params);
	
	public List<Map<String,Object>> findExpertsByDomainId(Map<String, Object> map);
	
	public List<Map<String,Object>> findAllExpertsAndReplyNumAndIsAccepteds(Map map);
	
	public List<Map<String,Object>> findDomainsByExpertId(Map<String,Object> map);
	
	public List<Map<String,Object>> findExpertAndReplyNumByDomainId(Map m);
	
	public Long findCountExpertBydomainId(Map map);
	
	public List<Map<String,Object>> findExpertsByExpertId(Map<String,Object> map);
	
	public void updateExpertByPrams(Expert expert);
	
	public List<Map<String,Object>> findExpertsByFullname(Map<String,Object> map);
	
	public int countExpertsByFullname(Map<String,Object> map);
	
	public List<Map<String, Object>> findAllExpertsAndReplyNumc(Map m);
}
