package com.ah3nong.wd.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Link;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.LinkDao;

public class LinkDaoImpl extends BaseDao<Link> implements LinkDao {

	@Override
	public void addLink(Link link) {
		getSqlMapClientTemplate().insert("wd_link.insert",link);
	}

	@Override
	public void updateLink(Link link) {
		getSqlMapClientTemplate().update("wd_link.updateByPrimaryKey",link);
	}

	@Override
	public List<Link> findLinksByStatus(int status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status",status);
		return getSqlMapClientTemplate().queryForList("wd_link.selectByStatus",map);
	}
	
	@Override
	public List<Link> findLinksByStatusAndPageNum(String status, int start, int size) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status",status);
		map.put("start",start);
		map.put("size", size);
		return (List<Link>)getSqlMapClientTemplate().queryForList("wd_link.selectByStatusAndPageNum",map);
	}

	@Override
	public int countByStatus(String status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status",status);
		return (Integer)getSqlMapClientTemplate().queryForObject("wd_link.countByStatus",map);
	}

	@Override
	public Link findLinksByUrl(String url) {
		Link link = new Link();
		link.setUrl(url);
		return (Link) getSqlMapClientTemplate().queryForObject("wd_link.selectByUrl",link);
	}

	@Override
	public Link findById(int id) {
		Link link = new Link();
		link.setId(id);
		return (Link) getSqlMapClientTemplate().queryForObject("wd_link.selectById",link);
	}

}
