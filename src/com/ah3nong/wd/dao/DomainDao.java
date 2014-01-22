package com.ah3nong.wd.dao;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Domain;

public interface DomainDao {
	/**
	 * 获取所有的领域，按node_path升序排列。
	 * 
	 * @return 领域列表
	 */
	public List<Domain> findAllDomain();

	/**
	 * 获取所有推荐的领域，按node_path升序排列。
	 * 
	 * @return 领域列表
	 */
	public List<Domain> findRecommendedDomain();

	public Domain findDomainByPrimaryKey(int id);

	public List<Domain> findDomainByName(Map map);

	public List<Domain> findDomainByNameForFirst(Map map);

	public int delDomainByPrimaryKey(int id);

	public int addDomain(Domain record);

	public int updateDomainByPrimaryKey(Domain record);

	public int updateDomainByPrimaryKeySelective(Domain record);

	public int countAllDomain();

	public List<Domain> queryForPager(Map map);

	public int countForPager(Map map);

	public List<Domain> findAllDomains(Map map);

	public String getMaxNodePath(int parentId);

	public List<Domain> findDomain(Map<String, Object> params);

	public List<Map<String, Object>> getDomainAndAllExperts();

	public Domain findDomainByNodePath(String nodePath);

	public List<Map<String, Object>> findDomainByUserId(int userId);

	public int delDomainByNodePath(String nodePath);

	public List<Domain> selectChildDomain(Map map);

	public List<Domain> selectAllChildDomains(Map map);

	// 获得用户关注领域
	public List<Map<String, Object>> findDomainByUserAskQuestion(Map<String, Object> map);

	public List<Domain> findAllDomainsByParentId(int parentId);
}
