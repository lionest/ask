package com.ah3nong.wd.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.bean.Probing;
import com.ah3nong.wd.bean.ProbingHis;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.dao.ProbingDao;
import com.ah3nong.wd.dao.ProbingHisDao;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.ProbingService;

@SuppressWarnings("unchecked")
public class ProbingServiceImpl implements ProbingService {
	private ProbingDao probingDao;
	private ProbingHisDao probingHisDao;
	
	public void setProbingHisDao(ProbingHisDao probingHisDao) {
		this.probingHisDao = probingHisDao;
	}

	public void setProbingDao(ProbingDao probingDao) {
		this.probingDao = probingDao;
	}

	private Logger log = LoggerFactory.getLogger(ProbingServiceImpl.class);

	/**
	 * 根据页面参数，查询分页需要的数据
	 * 
	 * @param params
	 *            参数
	 * @param currenPage
	 *            当前页数
	 * @param pageSize
	 *            每页大小
	 * @return
	 * @throws ServiceException
	 */

	public Pager<Probing> getProbingPagerDataByParam(Map params, int currentPage,
			int pageSize) throws ServiceException {
		String method = "getProbingPagerDataByParam";
		if (params == null) {
			log.error(method + ", the params of pager data is null!");
			throw new ServiceException(
					"cannot query any probings with the param is null!");
		}
		if (currentPage <= 0) {
			log.error(method + ", the current page is invalid!");
			throw new ServiceException("the current page is invalid!");
		}
		if (pageSize <= 0) {
			log.error(method + ", the pageSize is invalid!");
			throw new ServiceException("the pageSizeis invalid!");
		}
		log.info(method + ", the params:" + params.toString() + ",currentPage:"
				+ currentPage + ",pagesize:" + pageSize);

		params.put("start", (currentPage - 1) * pageSize);
		params.put("size", pageSize);
		Pager<Probing> pager = new Pager<Probing>(currentPage, pageSize);
		try {
			pager.setPageRecords(probingDao.queryForPager(params));
			pager.setTotalRecords(probingDao.countForPager(params));
		} catch (Exception e) {
			log.error(method
					+ ",probing dao got exceptions while query pager data."
					+ e);
			throw new ServiceException(
					"probing dao got exceptions while query pager data.Msg:"
							+ e);
		}

		return pager;
	}

	/**
	 * 添加追问
	 * 
	 * @param probing
	 *            追问对象
	 * 
	 */
	public void addProbing(Probing probing) throws ServiceException {
		String method = "addProbing";
		if (probing == null) {
			log
					.error(method
							+ ", the params of add probing、probingExpert、probingExpert is null!");
			throw new ServiceException(
					"cannot add probing、probingExpert、probingExpert since the param is null!");
		}
		//log.info(method + ", the probing params:" + probing.toString());
		try {
			probingDao.addProbing(probing);
		} catch (Exception e) {
			log.error(method + ",probing dao got exceptions while add probing."
					+ e);
			throw new ServiceException(
					"probing dao got exceptions while add probing data.Msg:"
							+ e);
		}
	}

	/**
	 * 查询所有的追问总数
	 * 
	 */
	public int countAllProbing() throws ServiceException {
		String method = "countAllProbing";
		try {
			return probingDao.countAllProbing();
		} catch (Exception e) {
			log.error(method + ",probing dao got exceptions while countAllProbing."
					+ e);
			throw new ServiceException(
					"probing dao got exceptions while countAllProbing data.Msg:"
							+ e);
		}
	}

	/**
	 * 根据追问ID删除追问
	 */
	public int delProbingByPrimaryKey(int id) throws ServiceException {
		String method = "delProbingByPrimaryKey";
		if (id == 0) {
			log.error(method + ", the params of delete probing is null!");
			throw new ServiceException(
					"cannot delete probing since the param is null!");
		}
		log.info(method + ", the params:" + id);
		try {
			int result = probingDao.delProbingByPrimaryKey(id);
			return result;
		} catch (Exception e) {
			log.error(method
					+ ",probing dao got exceptions while delete probing."
					+ e);
			throw new ServiceException(
					"probing dao got exceptions while delete probing data.Msg:"
							+ e);
		}
	}

	/**
	 * 查询数据库所有的追问
	 * 
	 */
	public List<Probing> findAllProbing() throws ServiceException {
		String method = "findAllProbing";
		try {
			return probingDao.findAllProbing();
		} catch (Exception e) {
			log.error(method + ",probing dao got exceptions while selectAll."
					+ e);
			throw new ServiceException(
					"probing dao got exceptions while selectAll data.Msg:"
							+ e);
		}
	}

	/**
	 * 根据追问ID查询追问对象
	 */
	public Probing findProbingByPrimaryKey(int id) throws ServiceException {
		String method = "findProbingByPrimaryKey";
		if (id == 0) {
			log.error(method
					+ ", the params of query probing by primaryKey is null!");
			throw new ServiceException(
					"cannot query probing by primaryKey since the id is null!");
		}
		log.info(method + ", the params:" + id);
		try {
			return probingDao.findProbingByPrimaryKey(id);
		} catch (Exception e) {
			log
					.error(method
							+ ",probing dao got exceptions while query probing by primaryKey."
							+ e);
			throw new ServiceException(
					"probing dao got exceptions while query probing by primaryKey data.Msg:"
							+ e);
		}
	}

	/**
	 * 根据追问ID来更新追问
	 */
	public int updateProbingByPrimaryKey(Probing probing,boolean isSelective) throws ServiceException {
		String method = "updateProbingByPrimaryKey";
		if (probing == null) {
			log.error(method
					+ ", the params of update probing by probing is null!");
			throw new ServiceException(
					"cannot update probing by primaryKey since the probing is null!");
		}
		log.info(method + ", the params:" + probing.toString());
		try {
			if(isSelective){
				return probingDao.updateProbingByPrimaryKeySelective(probing);
			}else{
				return probingDao.updateProbingByPrimaryKey(probing);
			}			
		} catch (Exception e) {
			log
					.error(method
							+ ",probing dao got exceptions while update probing by primaryKey."
							+ e);
			throw new ServiceException(
					"probing dao got exceptions while update probing by primaryKey data.Msg:"
							+ e);
		}
	}

	public List<Probing> findProbingByQuestionId(Map map)
			throws ServiceException {
		String method = "findProbingByQuestionId";
		if (map.get("questionId") == null) {
			log.error(method + ", the params of find Probing by questionId is null!");
			throw new ServiceException(
					"cannot find Probing by questionId since the param is null!");
		}
		log.info(method + ", the params:" + map);
		try {
			List<Probing> result = probingDao.findProbingByQuestionId(map);
			return result;
		} catch (Exception e) {
			log.error(method
					+ ",probing dao got exceptions while find Probing by questionId."
					+ e);
			throw new ServiceException(
					"probing dao got exceptions while find Probing by questionId data.Msg:"
							+ e);
		}
	}

	public void addProbingHis(ProbingHis probingHis) throws ServiceException {
		String method = "addProbingHis";
		if (probingHis == null) {
			log
					.error(method
							+ ", the params of add probingHis is null!");
			throw new ServiceException(
					"cannot add probingHis since the param is null!");
		}
		//log.info(method + ", the probing params:" + probing.toString());
		try {
			probingHisDao.addProbingHis(probingHis);
		} catch (Exception e) {
			log.error(method + ",probingHis dao got exceptions while add probingHis."
					+ e);
			throw new ServiceException(
					"probingHis dao got exceptions while add probingHis data.Msg:"
							+ e);
		}
	}

	public Pager<ProbingHis> getProbingHisPagerDataByParam(Map params,
			int currentPage, int pageSize) throws ServiceException {
		String method = "getProbingHisPagerDataByParam";
		if (params == null) {
			log.error(method + ", the params of pager data is null!");
			throw new ServiceException(
					"cannot query any probingHis with the param is null!");
		}
		if (currentPage <= 0) {
			log.error(method + ", the current page is invalid!");
			throw new ServiceException("the current page is invalid!");
		}
		if (pageSize <= 0) {
			log.error(method + ", the pageSize is invalid!");
			throw new ServiceException("the pageSizeis invalid!");
		}
		log.info(method + ", the params:" + params.toString() + ",currentPage:"
				+ currentPage + ",pagesize:" + pageSize);

		params.put("start", (currentPage - 1) * pageSize);
		params.put("size", pageSize);
		Pager<ProbingHis> pager = new Pager<ProbingHis>(currentPage, pageSize);
		try {
			pager.setPageRecords(probingHisDao.queryForPager(params));
			pager.setTotalRecords(probingHisDao.countForPager(params));
		} catch (Exception e) {
			log.error(method
					+ ",probingHis dao got exceptions while query pager data."
					+ e);
			throw new ServiceException(
					"probingHis dao got exceptions while query pager data.Msg:"
							+ e);
		}

		return pager;
	}

	public ProbingHis findProbingHisByPrimaryKey(int id)
			throws ServiceException {
		String method = "findProbingHisByPrimaryKey";
		if (id == 0) {
			log.error(method
					+ ", the params of query probingHis by primaryKey is null!");
			throw new ServiceException(
					"cannot query probingHis by primaryKey since the id is null!");
		}
		log.info(method + ", the params:" + id);
		try {
			return probingHisDao.findProbingHisByPrimaryKey(id);
		} catch (Exception e) {
			log.error(method
							+ ",probingHis dao got exceptions while query probingHis by primaryKey."
							+ e);
			throw new ServiceException(
					"probingHis dao got exceptions while query probingHis by primaryKey data.Msg:"
							+ e);
		}
	}

	public int updateProbingHisSelective(ProbingHis probingHis)
			throws ServiceException {
		String method = "updateProbingHisSelective";
		if (probingHis == null) {
			log.error(method
					+ ", the params of update probingHis selective is null!");
			throw new ServiceException(
					"cannot update probingHis selective since the id is null!");
		}
		log.info(method + ", the params:" + probingHis);
		try {
			return probingHisDao.updateProbingHisByPrimaryKeySelective(probingHis);
		} catch (Exception e) {
			log.error(method
							+ ",probingHis dao got exceptions while update probingHis selective."
							+ e);
			throw new ServiceException(
					"probingHis dao got exceptions while update probingHis selective data.Msg:"
							+ e);
		}
	}

	@Override
	public List<Probing> findProbingByStatusPager(Map<String, Object> map) {
		return probingDao.findProbingByStatusPager(map);
	}

	@Override
	public int countProbingByStatusPager(Map<String, Object> map) {
		return probingDao.countProbingByStatusPager(map);
	}
	
	public Pager<Probing> findProbingByStatusPager(Map<String,Object> params, int currentPage,
			int pageSize) throws ServiceException {
		String method = "getProbingPagerDataByParam";
		if (params == null) {
			log.error(method + ", the params of pager data is null!");
			throw new ServiceException(
					"cannot query any probings with the param is null!");
		}
		if (currentPage <= 0) {
			log.error(method + ", the current page is invalid!");
			throw new ServiceException("the current page is invalid!");
		}
		if (pageSize <= 0) {
			log.error(method + ", the pageSize is invalid!");
			throw new ServiceException("the pageSizeis invalid!");
		}
		log.info(method + ", the params:" + params.toString() + ",currentPage:"
				+ currentPage + ",pagesize:" + pageSize);

		params.put("start", (currentPage - 1) * pageSize);
		params.put("size", pageSize);
		Pager<Probing> pager = new Pager<Probing>(currentPage, pageSize);
		try {
			pager.setPageRecords(probingDao.findProbingByStatusPager(params));
			pager.setTotalRecords(probingDao.countProbingByStatusPager(params));
		} catch (Exception e) {
			log.error(method
					+ ",probing dao got exceptions while query pager data."
					+ e);
			throw new ServiceException(
					"probing dao got exceptions while query pager data.Msg:"
							+ e);
		}

		return pager;
	}

	@Override
	public List<Probing> findProbingByQuestionIdAndStatus(Map<String, Object> map) {
		return probingDao.findProbingByQuestionIdAndStatus(map);
	}

}
