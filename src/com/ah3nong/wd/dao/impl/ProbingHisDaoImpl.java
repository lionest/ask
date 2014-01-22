package com.ah3nong.wd.dao.impl;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Probing;
import com.ah3nong.wd.bean.ProbingHis;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.ProbingHisDao;

public class ProbingHisDaoImpl extends BaseDao<ProbingHisDaoImpl> implements
		ProbingHisDao {

	public int countAllProbingHis() {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject(
				"wd_probing_his.countAll");
		return rows;
	}

	public int countForPager(Map map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject(
				"wd_probing_his.countPaginationByPageNum", map);
		return rows;
	}

	public int delProbingHisByPrimaryKey(int id) {
		Probing key = new Probing();
		key.setId(id);
		int result = getSqlMapClientTemplate().delete(
				"wd_probing_his.deleteByPrimaryKey", key);
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<ProbingHis> findAllProbingHis() {
		List<ProbingHis> list = getSqlMapClientTemplate().queryForList(
				"wd_probing_his.selectAll");
		return list;
	}

	public void addProbingHis(ProbingHis record) {
		getSqlMapClientTemplate().insert("wd_probing_his.insert", record);
	}

	@SuppressWarnings("unchecked")
	public List<ProbingHis> queryForPager(Map map) {
		List<ProbingHis> list = getSqlMapClientTemplate().queryForList(
				"wd_probing_his.selectPaginationByPageNum", map);
		return list;
	}

	public ProbingHis findProbingHisByPrimaryKey(int id) {
		ProbingHis key = new ProbingHis();
		key.setId(id);
		ProbingHis record = (ProbingHis) getSqlMapClientTemplate().queryForObject(
				"wd_probing_his.selectByPrimaryKey", key);
		return record;
	}

	public int updateProbingHisByPrimaryKey(ProbingHis record) {
		int rows = getSqlMapClientTemplate().update(
				"wd_probing_his.updateByPrimaryKey", record);
		return rows;
	}

	public int updateProbingHisByPrimaryKeySelective(ProbingHis record) {
		int rows = getSqlMapClientTemplate().update(
				"wd_probing_his.updateByPrimaryKeySelective", record);
		return rows;
	}

}
