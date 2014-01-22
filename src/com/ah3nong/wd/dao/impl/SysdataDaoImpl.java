package com.ah3nong.wd.dao.impl;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Sysdata;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.SysdataDao;

public class SysdataDaoImpl extends BaseDao<Sysdata> implements SysdataDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Sysdata> findAll() {
		return getSqlMapClientTemplate().queryForList("wd_sysdata.selectAll");
	}

	@Override
	public void add(Sysdata sysdata) {
		getSqlMapClientTemplate().insert("wd_sysdata.insert",sysdata);
	}

	@Override
	public void updateByPrimarykey(Sysdata sysdata) {
		getSqlMapClientTemplate().update("wd_sysdata.updateByPrimaryKey",sysdata);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sysdata> findByPager(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("wd_sysdata.selectByPager",map);
	}

	@Override
	public int countAll() {
		return (Integer) getSqlMapClientTemplate().queryForObject("wd_sysdata.countAll");
	}

	@Override
	public Sysdata findByPrimarykey(int id) {
		Sysdata sysdata = new Sysdata();
		sysdata.setId(id);
		return (Sysdata) getSqlMapClientTemplate().queryForObject("wd_sysdata.selectByPrimarykey",sysdata);
	}

}
