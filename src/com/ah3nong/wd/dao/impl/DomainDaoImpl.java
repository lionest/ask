package com.ah3nong.wd.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.DomainDao;

public class DomainDaoImpl extends BaseDao<DomainDaoImpl> implements DomainDao {
	@SuppressWarnings("unchecked")
	public List<Domain> findAllDomain() {
		List<Domain> list = getSqlMapClientTemplate().queryForList(
				"wd_domain.selectAll");
		return list;
	}

	public List<Domain> findDomain(Map<String, Object> params) {
		List<Domain> list = getSqlMapClientTemplate().queryForList(
				"wd_domain.selectByParams", params);
		return list;
	}

	public List<Domain> findRecommendedDomain() {
		return getSqlMapClientTemplate().queryForList(
				"wd_domain.selectRecommended");
	}
	
	public List<Domain> selectChildDomain(Map map) {
		return getSqlMapClientTemplate().queryForList(
				"wd_domain.selectChildDomain" ,map);
	}

	public int countAllDomain() {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject(
				"wd_domain.countAll");
		return rows;
	}

	public int countForPager(Map map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject(
				"wd_domain.countAll", map);
		return rows;
	}

	public int delDomainByPrimaryKey(int id) {
		Domain key = new Domain();
		key.setId(id);
		int rows = getSqlMapClientTemplate().delete(
				"wd_domain.deleteByPrimaryKey", key);
		return rows;
	}
	
	public int delDomainByNodePath(String nodePath) {
		Domain key = new Domain();
		key.setNodePath(nodePath);
		int rows = getSqlMapClientTemplate().delete(
				"wd_domain.deleteByNodePath", key);
		return rows;
	}

	public int addDomain(Domain record) {
		return (Integer) getSqlMapClientTemplate().insert("wd_domain.insert",
				record);

	}

	public Domain findDomainByPrimaryKey(int id) {
		Domain key = new Domain();
		key.setId(id);
		Domain record = (Domain) getSqlMapClientTemplate().queryForObject(
				"wd_domain.selectByPrimaryKey", key);
		return record;
	}

	public List<Domain> queryForPager(Map map) {
		List<Domain> list = getSqlMapClientTemplate().queryForList(
				"wd_domain.selectPaginationByPageNum", map);
		return list;
	}

	public int updateDomainByPrimaryKey(Domain record) {
		int rows = getSqlMapClientTemplate().update(
				"wd_domain.updateByPrimaryKey", record);
		return rows;
	}

	public int updateDomainByPrimaryKeySelective(Domain record) {
		int rows = getSqlMapClientTemplate().update(
				"wd_domain.updateByPrimaryKeySelective", record);
		return rows;
	}

	public String getMaxNodePath(int parentId) {
		Object nodePath = null;
		if (parentId == 0) {
			nodePath = getSqlMapClientTemplate().queryForObject(
					"wd_domain.selectFirstMaxNodePath");
		} else {
			nodePath = getSqlMapClientTemplate().queryForObject(
					"wd_domain.selectMaxNodePath", parentId);
		}
		if (nodePath == null) {
			return null;
		} else {
			return nodePath.toString();
		}
	}

	public List<Domain> findAllDomains(Map map) {
		return getSqlMapClientTemplate().queryForList(
				"wd_domain.selectAllByPageNum", map);
	}
	
	public List<Map<String,Object>> getDomainAndAllExperts(){
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_domain.selectDomainAndAllExperts");
		Map hashMap = new HashMap();
		 List<Map<String,Object>> DomainAndAllExperts = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			Map map = new HashMap();
			map.put("id", (Integer)hashMap.get("id"));
			map.put("name", (String)hashMap.get("name"));
			map.put("count", (Long)hashMap.get("count"));
			DomainAndAllExperts.add(map);
         
		}
		return DomainAndAllExperts;
	}

	public List<Domain> findDomainByName(Map map) {
		return getSqlMapClientTemplate().queryForList(
				"wd_domain.selectDomainByName", map);
	}

	public List<Domain> findDomainByNameForFirst(Map map) {
		return getSqlMapClientTemplate().queryForList(
				"wd_domain.selectDomainByNameForFirst", map);
	}

	public Domain findDomainByNodePath(String nodePath) {
		Domain key = new Domain();
		key.setNodePath(nodePath);
		Domain domain = (Domain) getSqlMapClientTemplate().queryForObject("wd_domain.selectByNodePath", key);
		return domain;
	}

	public List<Map<String, Object>> findDomainByUserId(int userId) {
		List<Map<String,Object>> list = getSqlMapClientTemplate().queryForList("wd_user_domain.selectByUserId", userId);
		return list;
	}
	
	public List<Domain> selectAllChildDomains(Map map) {
		List<Domain> list = getSqlMapClientTemplate().queryForList(
				"wd_domain.selectAllChildDomains", map);
		return list;
	}

	@Override
	public List<Map<String, Object>> findDomainByUserAskQuestion(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("wd_domain.selectDomainByUserAskQuestion",map);
	}

	@Override
	public List<Domain> findAllDomainsByParentId(int parentId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parentId", parentId);
		return getSqlMapClientTemplate().queryForList("wd_domain.selectAllDomainsByParentId",map);
	}
}
