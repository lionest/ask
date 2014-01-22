package com.ah3nong.wd.service;

import java.util.List;
import com.ah3nong.wd.bean.Link;
import com.ah3nong.wd.common.Pager;

public interface LinkService {
	// 增加
	public void addLink(Link link);

	// 修改状态
	public void updateLink(Link link);

	// 按状态查找
	public List<Link> findLinksByStatus(int status);

	// 按页数和状态查
	public Pager<Link> findLinksByStatusAndPageNum(String status, int pageNum, int size);

	// 按状态查数量
	public int countByStatus(String status);
	
	// 按url查找
	public Link findLinksByUrl(String url);
	
	public Link findById(int id);
}
