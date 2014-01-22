package com.ah3nong.wd.service;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Expert;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.exception.ServiceException;

public interface ExpertService {
	/**
	 * 根据领域id获取专家。
	 * 
	 * @param domainId
	 *            领域id
	 * @return 专家列表
	 */
	public List<Expert> findExpertByDomainId(int domainId) throws ServiceException;

	public List<Expert> findExpert(Map<String, Object> params);

	/**
	 * 获取推荐的专家。
	 * 
	 * @return 专家列表
	 */

	public Pager<Expert> getExpertPagerByParam(Map params, int currentPage, int pageSize) throws ServiceException;

	public List<Expert> findRecommendedExpert(int count) throws ServiceException;

	public List<Expert> findAllExpert() throws ServiceException;

	public Expert findExpertByPrimaryKey(int id) throws ServiceException;

	public int delExpertByPrimaryKey(int id, User user) throws ServiceException;

	public int updateExpertByPrimaryKey(Expert record, boolean isSelective) throws ServiceException;

	public int countAllExpert() throws ServiceException;

	public List<Expert> queryForPager(Map map) throws ServiceException;

	public void addExpert(Expert expert,User user,String[] domainIds,int userId) throws ServiceException;
  
	public List<Map<String,Object>> getExpertAcceptedCountMapByDomainId(int domainId) throws ServiceException;
	
	public List<Map<String, Object>> getAllExpertsAndReplyNumAndIsAccepteds(int currentPage,int pageSize,int domain)
	throws ServiceException;
	
	public Long countAllExpertByDomainId(int domain) throws ServiceException;
	
	public List<Map<String, Object>> getExpertsByExpertId(int expertId) throws ServiceException;
	
	public void updateExpert(Expert expert, User user, String[] domainIds,
			int userId) throws ServiceException ;
	
	public List<Map<String, Object>> findDomainsByExpertId(Map<String, Object> map) ;
	
	public List<Map<String,Object>> findExpertsByFullname(Map<String,Object> map);
	
	public int countExpertsByFullname(Map<String,Object> map);
	
	public List<Map<String, Object>> getAllExpertsAndReplyNumc(int currentPage, int pageSize, int domain) throws ServiceException;
}
