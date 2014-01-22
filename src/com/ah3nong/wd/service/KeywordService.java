package com.ah3nong.wd.service;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Keyword;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.exception.ServiceException;

public interface KeywordService {
	
	public Pager<Keyword> getKeywordPagerByParam(Map params, int currentPage,
			int pageSize) throws ServiceException;
	
	public List<Keyword> findAllKeyword() throws ServiceException;
	
	public Keyword findKeywordByPrimaryKey(int id) throws ServiceException;
	
	public int delKeywordByPrimaryKey(int id) throws ServiceException;
	
	public int addKeyword(Keyword record) throws ServiceException;
	
	public int updateKeywordByPrimaryKey(Keyword keyword,boolean isSelective,Map map)
	throws ServiceException;
	
	public int countAllKeyword() throws ServiceException;
	
	public boolean findKeywordInString(String str) throws ServiceException;
	
}
