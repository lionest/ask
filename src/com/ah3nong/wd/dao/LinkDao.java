package com.ah3nong.wd.dao;

import java.util.List;

import com.ah3nong.wd.bean.Link;

public interface LinkDao {
	// 增加
	public void addLink(Link link);
	
	public Link findById(int id);

	// 修改状态
	public void updateLink(Link link);

	// 按状态查找
	public List<Link> findLinksByStatus(int status);
	
	// 按url查找
	public Link findLinksByUrl(String url);

	// 按页数和状态查
	public List<Link> findLinksByStatusAndPageNum(String status, int start, int size);

	// 按状态查数量
	public int countByStatus(String status);
}
