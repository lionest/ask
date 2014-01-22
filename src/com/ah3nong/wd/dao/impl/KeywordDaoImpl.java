package com.ah3nong.wd.dao.impl;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Keyword;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.KeywordDao;

public class KeywordDaoImpl extends BaseDao<KeywordDaoImpl> implements KeywordDao {


	public int countAllKeyword() {
		int rows =(Integer) getSqlMapClientTemplate().queryForObject("wd_keyword.countAll");
		return rows;
	}


	public int countForPager(Map map) {
		int rows =(Integer) getSqlMapClientTemplate().queryForObject("wd_keyword.countPaginationByPageNum",map);
		return rows;
	}


	public int delKeywordByPrimaryKey(int id) {
		Keyword key = new Keyword();
		key.setId(id);
		int rows = getSqlMapClientTemplate().delete("wd_keyword.deleteByPrimaryKey", key);
		return rows;
	}


	public List<Keyword> findAllKeyword() {
		List<Keyword> list = getSqlMapClientTemplate().queryForList("wd_keyword.selectByStatus");
		return list;
	}


	public void addKeyword(Keyword record) {
		getSqlMapClientTemplate().insert("wd_keyword.insert", record);
	}


	public Keyword findKeywordByPrimaryKey(int id) {
		Keyword key = new Keyword();
		key.setId(id);
		Keyword record = (Keyword) getSqlMapClientTemplate().queryForObject("wd_keyword.selectByPrimaryKey",key);
		return record;
	}


	public List<Keyword> queryForPager(Map map) {
		List<Keyword> list = getSqlMapClientTemplate().queryForList("wd_keyword.selectPaginationByPageNum", map);
		return list;
	}


	public int updateKeywordByPrimaryKey(Map map) {
		int rows = getSqlMapClientTemplate().update("wd_keyword.updateByPrimaryKey", map);
		return rows;
	}


	public int updateKeywordByPrimaryKeySelective(Keyword record) {
		int rows = getSqlMapClientTemplate().update("wd_keyword.updateByPrimaryKeySelective", record);
		return rows;
	}


	
	public List<Keyword> findByKeyword(String keyword) {	
		List<Keyword> list = getSqlMapClientTemplate().queryForList("wd_keyword.selectByKeyword",keyword
		);
	return list;
	}

}
