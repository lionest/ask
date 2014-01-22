package com.ah3nong.wd.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Expert;
import com.ah3nong.wd.bean.UserDomain;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.ExpertDao;

public class ExpertDaoImpl extends BaseDao<ExpertDaoImpl> implements ExpertDao {
	public List<Expert> findExpertByDomainId(int domainId) {
		List<UserDomain> list = getSqlMapClientTemplate().queryForList(
				"wd_user_domain.selectByDomainId", domainId);
		List<Expert> expertList = new ArrayList();
		for (UserDomain userDomain : list) {
			expertList.add(findExpertByID(userDomain.getUserId()));
		}
		return expertList;
	}

	public List<Expert> findExpert(Map<String, Object> params) {
		List<Map> list = getSqlMapClientTemplate().queryForList(
				"wd_expert.selectByParams", params);
		List<Expert> experts = new ArrayList<Expert>();
		Map hashMap = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			Expert expert = new Expert();
			hashMap = (HashMap) list.get(i);
		    expert.setFullName((String)hashMap.get("fullName"));
		    expert.setAvatar((String)hashMap.get("avatar"));
		    expert.setId((Integer)hashMap.get("id"));
		    expert.setEmail((String)hashMap.get("email"));
		    expert.setNickname((String)hashMap.get("nickname"));
		    expert.setOrganization((String)hashMap.get("organization"));
		    expert.setPhotoUrl((String)hashMap.get("photourl"));
		    expert.setRecommended((Integer)hashMap.get("recommended"));
		    expert.setResume((String)hashMap.get("resume"));
		    expert.setSex(hashMap.get("sex")+"");
		    expert.setSummary((String)hashMap.get("summary"));
		    expert.setUsername((String)hashMap.get("username"));
		    experts.add(expert);
		}
		return experts;
	}

	public List<Expert> findRecommendedExpert(int count) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("recommended", 1);
		map.put("count", count);
		List<Expert> list = getSqlMapClientTemplate().queryForList(
				"wd_expert.selectRecommendedExpert", map);
		return list;
	}

	public int countAllExpert() {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject(
				"wd_expert.countAll");
		return rows;
	}

	public int countForPager(Map map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject(
				"wd_expert.countPaginationByPageNum", map);
		return rows;
	}

	public int delExpertByPrimaryKey(int id) {
		Expert key = new Expert();
		key.setId(id);
		int rows = getSqlMapClientTemplate().delete(
				"wd_expert.deleteByPrimaryKey", key);
		return rows;
	}

	public List<Expert> findAllExpert() {
		List<Expert> list = getSqlMapClientTemplate().queryForList(
				"wd_expert.selectAll");
		return list;
	}

	public List<Expert> queryForPager(Map map) {
		List<Map> list = getSqlMapClientTemplate().queryForList(
				"wd_expert.selectPaginationByPageNum", map);
		Map hashMap = new HashMap();
		List<Expert> experts = new ArrayList<Expert>();
		for (int i = 0; i < list.size(); i++) {
			Expert expert = new Expert();
			hashMap = (HashMap) list.get(i);
			expert.setId((Integer) hashMap.get("userid"));
			expert.setOrganization((String) hashMap.get("organization"));
			expert.setUsername((String) hashMap.get("username"));
			expert.setNickname((String) hashMap.get("nickname"));
			expert.setFullName((String) hashMap.get("full_name"));
			expert.setRecommended((Integer) hashMap.get("recommended"));
			expert.setSex(String.valueOf(hashMap.get("sex")));
			expert.setSummary(String.valueOf(hashMap.get("summary")));
			expert.setResume(String.valueOf(hashMap.get("resume")));
			experts.add(expert);
		}
		return experts;
	}

	public Expert findExpertByPrimaryKey(int id) {
		Expert key = new Expert();
		key.setId(id);
		Expert record = (Expert) getSqlMapClientTemplate().queryForObject(
				"wd_expert.selectByPrimaryKey", key);
		return record;
	}

	public int updateExpertByPrimaryKey(Expert record) {
		int rows = getSqlMapClientTemplate().update(
				"wd_expert.updateByPrimaryKey", record);
		return rows;
	}

	public int updateExpertByPrimaryKeySelective(Expert record) {
		int rows = getSqlMapClientTemplate().update(
				"wd_expert.updateByPrimaryKeySelective", record);
		return rows;
	}

	public void inserExpertByPrams(Expert expert) {
		getSqlMapClientTemplate().insert("wd_expert.insert", expert);

	}
	
	public void updateExpertByPrams(Expert expert) {
		getSqlMapClientTemplate().insert("wd_expert.updateByPrimaryKeySelective", expert);

	}

	@SuppressWarnings("unchecked")
	public Expert findExpertByID(int expertId) {
		Map<Object, Object> map = (Map) getSqlMapClientTemplate()
				.queryForObject("wd_expert.selectByExpertId", expertId);
		// Map hashMap = new HashMap();
		if(map!=null){
			Expert expert = new Expert();
			expert.setId(expertId);
			expert.setOrganization((String) map.get("organization"));
			expert.setFullName((String) map.get("fullName"));
			if(expert.getFullName()==null||"".equals(expert.getFullName())){
				expert.setFullName((String) map.get("username"));
			}
			expert.setResume((String) map.get("resume"));
			expert.setSummary((String) map.get("summary"));
			expert.setSex( map.get("sex").toString());
			expert.setRecommended((Integer) map.get("recommended"));
			return expert;
		}else{
			return null;
		}
	}

	public List<Map<String, Object>> findExpertsByDomainId(
			Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList(
				"wd_expert.selectExpertsByDomainId", map);
		Map hashMap = new HashMap();
		List<Map<String, Object>> experts = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("id", (Integer) hashMap.get("id"));
			p.put("fullName", (String) hashMap.get("name"));
			if(p.get("fullName")==null||"".equals(p.get("fullName"))){
				p.put("fullName", (String) hashMap.get("username"));
			}
			p.put("count", (Long) hashMap.get("c"));
			experts.add(p);
		}
		return experts;
	}

	public List<Map<String, Object>> findAllExpertsAndReplyNumAndIsAccepteds(
			Map m) {
		List<Map> list = getSqlMapClientTemplate().queryForList(
				"wd_expert.selectAllExpertsAndReplyNumAndIsAccepteds", m);
		Map hashMap = new HashMap();
		List<Map<String, Object>> experts = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("id", (Integer) hashMap.get("id"));
			p.put("fullName", (String) hashMap.get("name"));
			if(p.get("fullName")==null||"".equals(p.get("fullName"))){
				p.put("fullName", (String) hashMap.get("username"));
			}
			p.put("username", (String) hashMap.get("username"));
			p.put("count", (Long) hashMap.get("count"));
			p.put("acceptNum", (Long) hashMap.get("acceptNum"));
			// java.text.DecimalFormat a = new java.text.DecimalFormat("#");
			p.put("isAcceptedCount", (BigDecimal) hashMap
					.get("isAcceptedCount")
					+ "");
			p.put("photoUrl", (String) hashMap.get("photoUrl"));
			p.put("summary", (String) hashMap.get("summary"));
			p.put("resume", (String) hashMap.get("resume"));
			Map map = new HashMap();
			map.put("expertId", (Integer) hashMap.get("id"));
			p.put("domains", this.findDomainsByExpertId(map));
			experts.add(p);
		}
		return experts;
	}
	
	public List<Map<String, Object>> findAllExpertsAndReplyNumc(
			Map m) {
		List<Map> list = getSqlMapClientTemplate().queryForList(
				"wd_expert.selectAllExpertsAndReplyNumc", m);
		Map hashMap = new HashMap();
		List<Map<String, Object>> experts = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("id", (Integer) hashMap.get("id"));
			p.put("fullName", (String) hashMap.get("name"));
			if(p.get("fullName")==null||"".equals(p.get("fullName"))){
				p.put("fullName", (String) hashMap.get("username"));
			}
			p.put("username", (String) hashMap.get("username"));
			p.put("count", (Long) hashMap.get("count"));
			p.put("acceptNum", (Long) hashMap.get("acceptNum"));
			// java.text.DecimalFormat a = new java.text.DecimalFormat("#");
			p.put("isAcceptedCount", (BigDecimal) hashMap
					.get("isAcceptedCount")
					+ "");
			p.put("photoUrl", (String) hashMap.get("photoUrl"));
			p.put("summary", (String) hashMap.get("summary"));
			p.put("resume", (String) hashMap.get("resume"));
			Map map = new HashMap();
			map.put("expertId", (Integer) hashMap.get("id"));
			p.put("domains", this.findDomainsByExpertId(map));
			experts.add(p);
		}
		return experts;
	}
	
	public List<Map<String, Object>> findDomainsByExpertId(
			Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList(
				"wd_expert.selectDomainsByexpertId", map);
		Map hashMap = new HashMap();
		List<Map<String, Object>> domains = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("id", (String) hashMap.get("id"));
			p.put("name", (String) hashMap.get("name"));
			domains.add(p);
		}
		return domains;
	}

	public List<Map<String, Object>> findExpertAndReplyNumByDomainId(Map m) {
		List<Map> list = getSqlMapClientTemplate().queryForList(
				"wd_expert.selectExpertAndReplyNumBydomainId", m);
		Map hashMap = new HashMap();
		List<Map<String, Object>> experts = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("id", (Integer) hashMap.get("id"));
			p.put("fullName", (String) hashMap.get("name"));
			if(p.get("fullName")==null||"".equals(p.get("fullName"))){
				p.put("fullName", (String) hashMap.get("username"));
			}
			p.put("username", (String) hashMap.get("username"));
			p.put("count", (Long) hashMap.get("count"));
			p.put("acceptNum", (Long) hashMap.get("acceptNum"));
			// java.text.DecimalFormat a = new java.text.DecimalFormat("#");
			p.put("isAcceptedCount", (BigDecimal) hashMap
					.get("isAcceptedCount")
					+ "");
			p.put("photoUrl", (String) hashMap.get("photoUrl"));
			p.put("summary", (String) hashMap.get("summary"));
			p.put("resume", (String) hashMap.get("resume"));
			Map map = new HashMap();
			map.put("expertId", (Integer) hashMap.get("id"));
			p.put("domains", this.findDomainsByExpertId(map));
			experts.add(p);
		}
		return experts;
	}

	public Long findCountExpertBydomainId(Map map) {
		List<Map> list = getSqlMapClientTemplate().queryForList(
				"wd_expert.selectCountExpertBydomainId", map);
		Map hashMap = new HashMap();
		Long t = null;
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			t = (Long) hashMap.get("count");
		}
		return t;
	}

	public List<Map<String, Object>> findExpertsByExpertId(
			Map<String, Object> map) {
		List<Map> list = getSqlMapClientTemplate().queryForList(
				"wd_expert.selectExpertsByExpertId", map);
		Map hashMap = new HashMap();
		List<Map<String, Object>> expert = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", (Integer) hashMap.get("id"));
			m.put("photoUrl", (String) hashMap.get("photoUrl"));
			m.put("resume", (String) hashMap.get("resume"));
			m.put("organization", (String) hashMap.get("organization"));
			m.put("summary", (String) hashMap.get("summary"));
			m.put("fullName", (String) hashMap.get("fullName"));
			if(m.get("fullName")==null||"".equals(m.get("fullName"))){
				m.put("fullName", (String) hashMap.get("username"));
			}
			m.put("username", (String) hashMap.get("username"));
			m.put("count", (Long) hashMap.get("count"));
			m.put("isAcceptedExpertsNum", (BigDecimal) hashMap
					.get("isAcceptedExpertsNum")
					+ "");
			Map ma = new HashMap();
			ma.put("expertId", (Integer) hashMap.get("id"));
			m.put("domains", this.findDomainsByExpertId(ma));
			expert.add(m);
		}
		return expert;
	}

	@Override
	public List<Map<String, Object>> findExpertsByFullname(Map<String, Object> map) {
		List<Map<String, Object>> list = getSqlMapClientTemplate().queryForList(
				"wd_expert.selectExpertsByFullname", map);
		return list;
	}

	@Override
	public int countExpertsByFullname(Map<String, Object> map) {
		return (Integer)getSqlMapClientTemplate().queryForObject("wd_expert.countExpertsByFullname", map);
	}
}
