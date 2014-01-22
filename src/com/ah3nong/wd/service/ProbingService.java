package com.ah3nong.wd.service;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Probing;
import com.ah3nong.wd.bean.ProbingHis;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.exception.ServiceException;

public interface ProbingService {
	public void addProbing(Probing probing) throws ServiceException;

	public Pager<Probing> getProbingPagerDataByParam(Map params, int currentPage, int pageSize) throws ServiceException;

	public Probing findProbingByPrimaryKey(int id) throws ServiceException;

	public List<Probing> findAllProbing() throws ServiceException;

	public int delProbingByPrimaryKey(int id) throws ServiceException;

	public int updateProbingByPrimaryKey(Probing probing, boolean isSelective) throws ServiceException;

	public int countAllProbing() throws ServiceException;

	public List<Probing> findProbingByQuestionId(Map map) throws ServiceException;

	// Probing_His
	public void addProbingHis(ProbingHis probingHis) throws ServiceException;

	public Pager<ProbingHis> getProbingHisPagerDataByParam(Map params, int currentPage, int pageSize) throws ServiceException;

	public ProbingHis findProbingHisByPrimaryKey(int id) throws ServiceException;

	public int updateProbingHisSelective(ProbingHis probingHis) throws ServiceException;

	public List<Probing> findProbingByStatusPager(Map<String, Object> map);

	public int countProbingByStatusPager(Map<String, Object> map);

	public Pager<Probing> findProbingByStatusPager(Map<String, Object> params, int currentPage, int pageSize) throws ServiceException;
	
	public List<Probing> findProbingByQuestionIdAndStatus(Map<String, Object> map);
}
