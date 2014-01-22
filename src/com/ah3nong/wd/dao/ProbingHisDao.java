package com.ah3nong.wd.dao;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Probing;
import com.ah3nong.wd.bean.ProbingHis;



public interface ProbingHisDao {
	public List<ProbingHis> findAllProbingHis();
	
	public void addProbingHis(ProbingHis record);
	
	public ProbingHis findProbingHisByPrimaryKey(int id);
	
	public int delProbingHisByPrimaryKey(int id);
	
	public int updateProbingHisByPrimaryKey(ProbingHis record);
	
	public int updateProbingHisByPrimaryKeySelective(ProbingHis record);
	
	public int countAllProbingHis();
	
	public List<ProbingHis> queryForPager(Map map);
	
	public int countForPager(Map map);

}
