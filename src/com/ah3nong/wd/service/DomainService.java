package com.ah3nong.wd.service;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.exception.ServiceException;

public interface DomainService {
	/**
	 * 获取所有的领域，按node_path升序排列。
	 * 
	 * @return 领域列表
	 */
	public List<Domain> findAllDomain() throws ServiceException;

	/**
	 * 获取所有推荐的领域，按node_path升序排列。
	 * 
	 * @return 领域列表
	 */
	public List<Domain> findRecommendedDomain();

	public int addDomain(Domain domain) throws ServiceException;

	public Pager<Domain> getDomainPagerDataByParam(Map params, int currentPage,
			int pageSize) throws ServiceException;

	public Domain findDomainByPrimaryKey(int id) throws ServiceException;

	public int delDomainByPrimaryKey(int id) throws ServiceException;
	
	public int delDomainByNodePath(String nodePath) throws ServiceException;

	public int updateDomainByPrimaryKey(Domain domain) throws ServiceException;

	public int countAllDomain() throws ServiceException;
	
	public String convertDomainToJson(List<Domain> allDomains) ;
	
	public List<Domain> findNoParentIdsDomain() throws ServiceException;
	
	public List<Domain> findDomainsByParentId(int parentId) throws ServiceException;

	public List<Domain> findDomain(Map<String, Object> params);
	
	public List<Map<String,Object>> getDomainAndAllExperts() throws ServiceException;
	
	public List<Domain> findDomainsByNodePath(int domainId) throws ServiceException;
	
	public List<Map<String,Object>> findDomainByUserId(int userId) throws ServiceException;
	
	public Domain findDomainByNodePath(String nodePath) throws ServiceException;
	
	public List<Domain> findChildDomain(String parentNodePath)
			throws ServiceException ;
	
	public List<Domain> selectAllChildDomains(Map map) throws ServiceException ;
	
	//获得用户关注领域
	public List<Map<String,Object>> findDomainByUserAskQuestion(Map<String,Object> map);

	public List<Domain> findAllDomainsByParentId(int parentId);
}
