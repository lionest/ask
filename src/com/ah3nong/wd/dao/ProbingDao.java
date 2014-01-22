package com.ah3nong.wd.dao;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Probing;

public interface ProbingDao {
	public List<Probing> findAllProbing();

	public void addProbing(Probing record);

	public Probing findProbingByPrimaryKey(int id);

	public int delProbingByPrimaryKey(int id);

	public int updateProbingByPrimaryKey(Probing record);

	public int updateProbingByPrimaryKeySelective(Probing record);

	public int countAllProbing();

	public List<Probing> queryForPager(Map map);

	public int countForPager(Map map);
	
	public int delProbingByQuestionId(int questionId);
	
	public List<Probing> findProbingByQuestionId(Map map);
	
	public List<Probing> findProbingByStatusPager(Map<String, Object> map);

	public int countProbingByStatusPager(Map<String, Object> map) ;
	
	public List<Probing> findProbingByQuestionIdAndStatus(Map<String, Object> map);

}
