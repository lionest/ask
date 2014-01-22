package com.ah3nong.wd.service.impl;

import java.util.List;

import com.ah3nong.wd.bean.Link;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.dao.LinkDao;
import com.ah3nong.wd.service.LinkService;

public class LinkServiceImpl implements LinkService {
	private LinkDao linkDao;
	@Override
	public void addLink(Link link) {
		linkDao.addLink(link);
	}

	@Override
	public void updateLink(Link link) {
		linkDao.updateLink(link);
	}

	@Override
	public List<Link> findLinksByStatus(int status) {
		return linkDao.findLinksByStatus(status);
	}

	@Override
	public Pager<Link> findLinksByStatusAndPageNum(String status, int pageNum, int size) {
		Pager<Link> pager = new Pager<Link>(pageNum,size);
		int start = (pageNum-1)*size;
		
		pager.setPageRecords(linkDao.findLinksByStatusAndPageNum(status, start, size));
		pager.setTotalRecords(linkDao.countByStatus(status));
		return pager;
	}

	@Override
	public int countByStatus(String status) {
		return linkDao.countByStatus(status);
	}

	public void setLinkDao(LinkDao linkDao) {
		this.linkDao = linkDao;
	}

	@Override
	public Link findLinksByUrl(String url) {
		return linkDao.findLinksByUrl(url);
	}

	@Override
	public Link findById(int id) {
		return linkDao.findById(id);
	}

}
