package com.ah3nong.wd.dao;

import java.util.List;
import java.util.Map;


import com.ah3nong.wd.bean.Keyword;




public interface KeywordDao {

	public List<Keyword> findAllKeyword();
	public List<Keyword> findByKeyword(String word);
	public Keyword findKeywordByPrimaryKey(int id);
	
	public int delKeywordByPrimaryKey(int id);
	
	public void addKeyword(Keyword record);
	
	public int updateKeywordByPrimaryKey(Map map);
	
	public int updateKeywordByPrimaryKeySelective(Keyword record);
	
	public int countAllKeyword();
	
	public List<Keyword> queryForPager(Map map);
	
	public int countForPager(Map map);
}
