package com.ah3nong.wd.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Probing;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.ProbingDao;

public class ProbingDaoImpl extends BaseDao<ProbingDaoImpl> implements ProbingDao {

	public int countAllProbing() {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_probing.countAll");
		return rows;
	}

	public int countForPager(Map map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_probing.countPaginationByPageNum", map);
		return rows;
	}

	public int delProbingByPrimaryKey(int id) {
		Probing key = new Probing();
		key.setId(id);
		int result = getSqlMapClientTemplate().delete("wd_probing.deleteByPrimaryKey", key);
		return result;
	}

	public int delProbingByQuestionId(int questionId) {
		Probing key = new Probing();
		key.setQuestionId(questionId);
		int result = getSqlMapClientTemplate().delete("wd_probing.deleteByQuestionId", key);
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Probing> findAllProbing() {
		List<Probing> list = getSqlMapClientTemplate().queryForList("wd_probing.selectAll");
		return list;
	}

	public void addProbing(Probing record) {
		getSqlMapClientTemplate().insert("wd_probing.insert", record);
	}

	@SuppressWarnings("unchecked")
	public List<Probing> queryForPager(Map map) {
		List<Probing> list = getSqlMapClientTemplate().queryForList("wd_probing.selectPaginationByPageNum", map);
		return list;
	}

	public Probing findProbingByPrimaryKey(int id) {
		Probing key = new Probing();
		key.setId(id);
		Probing record = (Probing) getSqlMapClientTemplate().queryForObject("wd_probing.selectByPrimaryKey", key);
		return record;
	}

	public int updateProbingByPrimaryKey(Probing record) {
		int rows = getSqlMapClientTemplate().update("wd_probing.updateByPrimaryKey", record);
		return rows;
	}

	public int updateProbingByPrimaryKeySelective(Probing record) {
		int rows = getSqlMapClientTemplate().update("wd_probing.updateByPrimaryKeySelective", record);
		return rows;
	}

	@SuppressWarnings("unchecked")
	public List<Probing> findProbingByQuestionId(Map map) {
		List<Map> list = getSqlMapClientTemplate().queryForList("wd_probing.selectByQuestionId", map);
		Map hashMap = new HashMap();
		List<Probing> probings = new ArrayList<Probing>();
		Probing probing = null;
		Reply reply = null;
		for (int i = 0; i < list.size(); i++) {
			hashMap = (HashMap) list.get(i);
			probing = new Probing();
			probing.setId((Integer) hashMap.get("id"));
			probing.setContent(hashMap.get("content").toString());
			probing.setCreatedTime((Date) hashMap.get("createdTime"));
			probing.setQuestionId((Integer) hashMap.get("questionId"));
			probing.setStatus((Integer) hashMap.get("status"));
			probing.setUpdatedTime((Date) hashMap.get("updateTime"));
			reply = new Reply();
			reply.setId((Integer) hashMap.get("replyId"));
			reply.setUserId((Integer) hashMap.get("userId"));
			probing.setReply(reply);
			probings.add(probing);
		}
		return probings;
	}

	public List<Probing> findProbingByStatusPager(Map<String, Object> map) {
		List<Probing> list = getSqlMapClientTemplate().queryForList("wd_probing.selectProbingByStatusPager", map);
		return list;
	}

	public int countProbingByStatusPager(Map<String, Object> map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_probing.countProbingByStatusPager", map);
		return rows;
	}

	@Override
	public List<Probing> findProbingByQuestionIdAndStatus(Map<String, Object> map) {
		List<Probing> list = getSqlMapClientTemplate().queryForList("wd_probing.selectByQuestionIdAndStatus", map);
		return list;
	}
}
