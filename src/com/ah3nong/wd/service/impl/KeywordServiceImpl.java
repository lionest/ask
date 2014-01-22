package com.ah3nong.wd.service.impl;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.bean.Keyword;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.dao.KeywordDao;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.KeywordService;

public class KeywordServiceImpl implements KeywordService {

	private Logger log = LoggerFactory.getLogger(KeywordServiceImpl.class);
	private KeywordDao keywordDao;

	public KeywordDao getKeywordDao() {
		return keywordDao;
	}

	public void setKeywordDao(KeywordDao keywordDao) {
		this.keywordDao = keywordDao;
	}

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

	public Pager<Keyword> getKeywordPagerByParam(Map params, int currentPage,
			int pageSize) throws ServiceException {
		String method = "getQuestionPagerByParam";
		if (params == null) {
			log.error(method + ", the params of pager data is null!");
			throw new ServiceException(
					"cannot query any keyword since the param is null!");
		}
		if (currentPage <= 0) {
			log.error(method + ", the current page is invalid!");
			throw new ServiceException("the current page is invalid!");
		}
		if (pageSize <= 0) {
			log.error(method + ", the pageSize is invalid!");
			throw new ServiceException("the pageSize is invalid!");
		}
		log.info(method + ", the params:" + params.toString() + ",currentPage:"
				+ currentPage + ",pagesize:" + pageSize);

		params.put("start", (currentPage - 1) * pageSize);
		params.put("size", pageSize);
		Pager<Keyword> pager = new Pager<Keyword>(currentPage, pageSize);
		try {
			pager.setPageRecords(keywordDao.queryForPager(params));
			pager.setTotalRecords(keywordDao.countForPager(params));
		} catch (Exception e) {
			log
					.error(method
							+ ",keyword dao got exceptions while query pager data."
							+ e);
			throw new ServiceException(
					"keyword dao got exceptions while query pager data.Msg:"
							+ e);
		}

		return pager;
	}

	/**
	 * 添加关键字记录
	 * 
	 * @param keyword
	 *            关键字对象
	 * @return
	 * @throws ServiceException
	 */

	public int addKeyword(Keyword keyword) throws ServiceException {
		String method = "addKeyword";
		if (keyword == null) {
			log.error(method + ", the params of add keyword is null!");
			throw new ServiceException(
					"cannot add keyword since the param is null!");
		}
		log.info(method + ", the params:" + keyword.toString());
		try {
			Keyword key = null;
			List<Keyword> list = keywordDao.findByKeyword(keyword.getContent());
			if (list != null && list.size() > 0) {
				key = list.get(0);
				if (!key.getId().equals(keyword.getId())) {
					log.error(method + ",the keyword exists already!");
					return -1;
				}
			}
			keywordDao.addKeyword(keyword);
		} catch (Exception e) {
			log.error(method + ",keyword dao got exceptions while add keyword."
					+ e);
			throw new ServiceException(
					"keyword dao got exceptions while add keyword data.Msg:"
							+ e);
		}
		return 0;

	}

	public int countAllKeyword() throws ServiceException {
		String method = "countAllKeyword";
		try {
			return keywordDao.countAllKeyword();
		} catch (Exception e) {
			log.error(method
					+ ",keyword dao got exceptions while countAllKeyword." + e);
			throw new ServiceException(
					"keyword dao got exceptions while countAllKeyword data.Msg:"
							+ e);
		}
	}

	public int delKeywordByPrimaryKey(int id) throws ServiceException {
		String method = "delKeywordByPrimaryKey";
		if (id == 0) {
			log.error(method
					+ ", the params of delete keyword by primaryKey is null!");
			throw new ServiceException(
					"cannot delete keyword by primaryKey since the param is null!");
		}
		log.info(method + ", the params:" + id);
		try {
			return keywordDao.delKeywordByPrimaryKey(id);
		} catch (Exception e) {
			log
					.error(method
							+ ",keyword dao got exceptions while delete keyword by primaryKey."
							+ e);
			throw new ServiceException(
					"keyword dao got exceptions while delete keyword by primaryKey data.Msg:"
							+ e);
		}
	}

	public List<Keyword> findAllKeyword() throws ServiceException {
		String method = "findAllKeyword";
		try {
			return keywordDao.findAllKeyword();
		} catch (Exception e) {
			log
					.error(method
							+ ",keyword dao got exceptions while find all keyword."
							+ e);
			throw new ServiceException(
					"keyword dao got exceptions while find all keyword data.Msg:"
							+ e);
		}
	}

	public Keyword findKeywordByPrimaryKey(int id) throws ServiceException {
		String method = "findKeywordByPrimaryKey";
		if (id == 0) {
			log.error(method
					+ ", the params of find keyword by primaryKey is null!");
			throw new ServiceException(
					"cannot delete keyword by find since the param is null!");
		}
		log.info(method + ", the params:" + id);
		try {
			return keywordDao.findKeywordByPrimaryKey(id);
		} catch (Exception e) {
			log
					.error(method
							+ ",keyword dao got exceptions while find keyword by primaryKey."
							+ e);
			throw new ServiceException(
					"keyword dao got exceptions while find keyword by primaryKey data.Msg:"
							+ e);
		}
	}

	public int updateKeywordByPrimaryKey(Keyword keyword, boolean isSelective,
			Map map) throws ServiceException {
		String method = "updateKeywordByPrimaryKey";
		if (keyword == null) {
			log.error(method + ", the params of update keyword is null!");
			throw new ServiceException(
					"cannot update keyword find since the param is null!");
		}
		log.info(method + ", the params:" + keyword.toString());

		try {

			List<Keyword> list = keywordDao.findByKeyword(keyword.getContent());
			Keyword key = null;
			if (list != null && list.size() > 0) {
				key = list.get(0);
				if (!key.getId().equals(keyword.getId())) {
					log.error(method + ",the keyword exists already!");
					return -1;
				}
			}
			if (isSelective) {
				return keywordDao.updateKeywordByPrimaryKeySelective(keyword);
			} else {
				return keywordDao.updateKeywordByPrimaryKey(map);
			}
		} catch (Exception e) {
			log.error(method
					+ ",keyword dao got exceptions while update keyword." + e);
			throw new ServiceException(
					"keyword dao got exceptions while update keywords data.Msg:"
							+ e);
		}
	}

	public boolean findKeywordInString(String str) throws ServiceException {
		String method = "findKeywordInString";
		if (str == null) {
			log.error(method
					+ ", the params of find keyword in String is null!");
			throw new ServiceException(
					"cannot delete keyword by find keyword in String is null!");
		}
		log.info(method + ", the params:" + str);
		try {
			List<Keyword> keywords = keywordDao.findAllKeyword();
			// String s = "中国|天空";
			Pattern p = null;
			Matcher m = null;
			for (Keyword keyword : keywords) {
				p = Pattern.compile("(" + keyword.getContent() + ")+");
				m = p.matcher(str);
				if (m.find()) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			log
					.error(method
							+ ",keyword dao got exceptions while find keyword in String."
							+ e);
			throw new ServiceException(
					"keyword dao got exceptions while find keyword in String data.Msg:"
							+ e);
		}
	}

}
