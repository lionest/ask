package com.ah3nong.wd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.bean.Expert;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.bean.UserDomain;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.dao.DomainDao;
import com.ah3nong.wd.dao.ExpertDao;
import com.ah3nong.wd.dao.UserDao;
import com.ah3nong.wd.dao.UserDomainDao;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.ExpertService;

public class ExpertServiceImpl implements ExpertService {
	private Logger log = LoggerFactory.getLogger(ExpertServiceImpl.class);
	private ExpertDao expertDao;
	private UserDao userDao;
	private UserDomainDao userDomainDao;
	private DomainDao domainDao;

	public void setUserDomainDao(UserDomainDao userDomainDao) {
		this.userDomainDao = userDomainDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setExpertDao(ExpertDao expertDao) {
		this.expertDao = expertDao;
	}

	public void setDomainDao(DomainDao domainDao) {
		this.domainDao = domainDao;
	}

	public Pager<Expert> getExpertPagerByParam(Map params, int currentPage, int pageSize) throws ServiceException {
		String method = "getExpertPagerByParam";
		if (params == null) {
			log.error(method + ", the params of pager data is null!");
			throw new ServiceException("cannot query any expert since the param is null!");
		}
		if (currentPage <= 0) {
			log.error(method + ", the current page is invalid!");
			throw new ServiceException("the current page is invalid!");
		}
		if (pageSize <= 0) {
			log.error(method + ", the pageSize is invalid!");
			throw new ServiceException("the pageSize is invalid!");
		}
		log.info(method + ", the params:" + params.toString() + ",currentPage:" + currentPage + ",pagesize:" + pageSize);

		params.put("start", (currentPage - 1) * pageSize);
		params.put("size", pageSize);
		Pager<Expert> pager = new Pager<Expert>(currentPage, pageSize);
		try {
			pager.setPageRecords(expertDao.queryForPager(params));
			pager.setTotalRecords(expertDao.countForPager(params));
		} catch (Exception e) {
			log.error(method + ",expert dao got exceptions while query pager data." + e);
			throw new ServiceException("expert dao got exceptions while query pager data.Msg:" + e);
		}

		return pager;
	}

	public List<Expert> findExpertByDomainId(int domainId) throws ServiceException {
		String method = "findExpertByDomainId";
		if (domainId == 0) {
			log.error(method + ", the params of find expert by domainId is null!");
			throw new ServiceException("cannot find expert by domainId since the param is null!");
		}
		log.info(method + ", the params:" + domainId);
		try {
			return expertDao.findExpertByDomainId(domainId);
		} catch (Exception e) {
			log.error(method + ",expert dao got exceptions while find expert by domainId." + e);
			throw new ServiceException("expert dao got exceptions while find expert by domainId data.Msg:" + e);
		}
	}

	public List<Expert> findExpert(Map params) {
		return expertDao.findExpert(params);
	}

	public List<Expert> findRecommendedExpert(int count) throws ServiceException {
		String method = "findRecommendedExpert";
		try {
			return expertDao.findRecommendedExpert(count);
		} catch (Exception e) {
			log.error(method + ",expert dao got exceptions while find recommended expert." + e);
			throw new ServiceException("expert dao got exceptions while find recommended expert data.Msg:" + e);
		}
	}

	public int countAllExpert() throws ServiceException {
		String method = "countAllExpert";
		try {
			return expertDao.countAllExpert();
		} catch (Exception e) {
			log.error(method + ",expert dao got exceptions while count all expert." + e);
			throw new ServiceException("expert dao got exceptions while count all expert data.Msg:" + e);
		}
	}

	public Long countAllExpertByDomainId(int domain) throws ServiceException {
		String method = "countAllExpert";
		try {
			Map map = new HashMap();
			map.put("expertID", domain);
			map.put("expertId", "0" + domain);
			return expertDao.findCountExpertBydomainId(map);
		} catch (Exception e) {
			log.error(method + ",expert dao got exceptions while count all expert." + e);
			throw new ServiceException("expert dao got exceptions while count all expert data.Msg:" + e);
		}
	}

	public int delExpertByPrimaryKey(int id, User user) throws ServiceException {
		String method = "delExpertByPrimaryKey";
		if (id == 0) {
			log.error(method + ", the params of delete expert by primaryKey is null!");
			throw new ServiceException("cannot delete expert by primaryKey since the param is null!");
		}
		log.info(method + ", the params:" + id);
		try {
			userDao.updateUserIsExpert(user);
			userDomainDao.deleteUserDomainById(id);
			return expertDao.delExpertByPrimaryKey(id);

		} catch (Exception e) {
			log.error(method + ",expert dao got exceptions while delete expert by primaryKey." + e);
			throw new ServiceException("expert dao got exceptions while delete expert by primaryKey data.Msg:" + e);
		}
	}

	public List<Expert> findAllExpert() throws ServiceException {
		String method = "findAllExpert";
		try {
			return expertDao.findAllExpert();
		} catch (Exception e) {
			log.error(method + ",expert dao got exceptions while find all expert." + e);
			throw new ServiceException("expert dao got exceptions while find all expert data.Msg:" + e);
		}
	}

	public Expert findExpertByPrimaryKey(int id) throws ServiceException {
		String method = "findExpertByPrimaryKey";
		if (id == 0) {
			log.error(method + ", the params of find expert by primaryKey is null!");
			throw new ServiceException("cannot find expert by primaryKey since the param is null!");
		}
		log.info(method + ", the params:" + id);
		try {
			return expertDao.findExpertByPrimaryKey(id);
		} catch (Exception e) {
			log.error(method + ",expert dao got exceptions while find expert by primaryKey." + e);
			throw new ServiceException("expert dao got exceptions while find expert by primaryKey data.Msg:" + e);
		}
	}

	public int updateExpertByPrimaryKey(Expert expert, boolean isSelective) throws ServiceException {
		String method = "updateExpertByPrimaryKey";
		if (expert == null) {
			log.error(method + ", the params of update expert is null!");
			throw new ServiceException("cannot update expert since the param is null!");
		}
		log.info(method + ", the params:" + expert.toString());
		try {
			if (isSelective) {
				return expertDao.updateExpertByPrimaryKeySelective(expert);
			} else {
				return expertDao.updateExpertByPrimaryKey(expert);
			}
		} catch (Exception e) {
			log.error(method + ",expert dao got exceptions while update expert." + e);
			throw new ServiceException("expert dao got exceptions while update expert data.Msg:" + e);
		}
	}

	public List<Expert> queryForPager(Map map) throws ServiceException {
		String method = "queryForPager";
		if (map == null) {
			log.error(method + ", the params of update expert is null!");
			throw new ServiceException("cannot list expert since the param is null!");
		}
		log.info(method + ",the map is" + map.toString());
		return expertDao.queryForPager(map);
	}

	public void addExpert(Expert expert, User user, String[] domainIds, int userId) throws ServiceException {
		String method = "addExpert";
		if (expert == null) {
			log.error(method + ", the params of add expert is null!");
			throw new ServiceException("cannot add expert since the param is null!");
		}
		if (user == null) {
			log.error(method + ", the params of add expert is null!");
			throw new ServiceException("cannot add expert since the param is null!");
		}
		log.info(method + ", the expert params:" + expert.toString());
		try {

			expertDao.inserExpertByPrams(expert);
			userDao.updateUserIsExpert(user);

			for (int i = 0; i < domainIds.length; i++) {
				UserDomain userDomain = new UserDomain();
				if (domainIds[i] != null) {
					userDomain.setDomainId(Integer.parseInt(domainIds[i]));
					userDomain.setUserId(userId);
					userDomainDao.insertUserDomain(userDomain);
				}
			}
		} catch (Exception e) {
			log.error(method + ",question dao got expert while add question." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got expert while add question data.Msg:" + e);
		}
	}

	public void updateExpert(Expert expert, User user, String[] domainIds, int userId) throws ServiceException {
		String method = "addExpert";
		if (expert == null) {
			log.error(method + ", the params of update expert is null!");
			throw new ServiceException("cannot add expert since the param is null!");
		}
		if (user == null) {
			log.error(method + ", the params of update expert is null!");
			throw new ServiceException("cannot update expert since the param is null!");
		}
		log.info(method + ", the expert params:" + expert.toString());
		try {
			// 更新专家列表里的信息
			expertDao.updateExpertByPrams(expert);
			// userDao.updateUserIsExpert(user);

			// 先把原有的专家擅长领域删掉，然后再新添加进去
			userDomainDao.deleteUserDomainById(userId);
			for (int i = 0; i < domainIds.length; i++) {
				UserDomain userDomain = new UserDomain();
				if (domainIds[i] != null) {
					userDomain.setDomainId(Integer.parseInt(domainIds[i]));
					userDomain.setUserId(userId);
					userDomainDao.insertUserDomain(userDomain);
				}
			}
		} catch (Exception e) {
			log.error(method + ",question dao got expert while add question." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got expert while add question data.Msg:" + e);
		}
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public UserDomainDao getUserDomainDao() {
		return userDomainDao;
	}

	public List<Map<String, Object>> getExpertAcceptedCountMapByDomainId(int domainId) throws ServiceException {
		String method = "getExpertAcceptedCountMapByDomainId";
		if (domainId == 0) {
			log.error(method + ", the params of getExpertAcceptedCountMapByDomainId is null!");
			throw new ServiceException("cannot get expert since the param is null!");
		}
		log.info(method + "get expert since the param" + domainId);
		List<Map<String, Object>> experts = new ArrayList<Map<String, Object>>();
		try {
			Map map = new HashMap();
			map.put("domainId", domainId);
			experts = expertDao.findExpertsByDomainId(map);
		} catch (Exception e) {
			log.error(method + ",question dao got expert while add question." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got expert while add question data.Msg:" + e);
		}

		return experts;
	}

	public List<Map<String, Object>> getAllExpertsAndReplyNumAndIsAccepteds(int currentPage, int pageSize, int domain) throws ServiceException {
		String method = "getAllExpertsAndReplyNumAndIsAccepteds";
		List<Map<String, Object>> expertsAndReplys = new ArrayList<Map<String, Object>>();
		try {
			Map map = new HashMap();
			map.put("start", currentPage);
			map.put("size", pageSize);
			if (domain != 0) {
				String nodePath = domainDao.findDomainByPrimaryKey(domain).getNodePath().substring(0,2);
				map.put("expertId", nodePath);
				map.put("expertID", domain);
				expertsAndReplys = expertDao.findExpertAndReplyNumByDomainId(map);
			} else {
				expertsAndReplys = expertDao.findAllExpertsAndReplyNumAndIsAccepteds(map);
			}
		} catch (Exception e) {
			log.error(method + ",question dao got expert while add question." + e.getLocalizedMessage());
			e.printStackTrace();
			throw new ServiceException("question dao got expert while add question data.Msg:" + e.getLocalizedMessage());
		}
		return expertsAndReplys;
	}
	
	public List<Map<String, Object>> getAllExpertsAndReplyNumc(int currentPage, int pageSize, int domain) throws ServiceException {
		String method = "getAllExpertsAndReplyNumAndIsAccepteds";
		List<Map<String, Object>> expertsAndReplys = new ArrayList<Map<String, Object>>();
		try {
			Map map = new HashMap();
			map.put("start", currentPage);
			map.put("size", pageSize);
			if (domain != 0) {
				String nodePath = domainDao.findDomainByPrimaryKey(domain).getNodePath().substring(0,2);
				map.put("expertId", nodePath);
				map.put("expertID", domain);
				expertsAndReplys = expertDao.findExpertAndReplyNumByDomainId(map);
			} else {
				expertsAndReplys = expertDao.findAllExpertsAndReplyNumc(map);
			}
		} catch (Exception e) {
			log.error(method + ",question dao got expert while add question." + e.getLocalizedMessage());
			e.printStackTrace();
			throw new ServiceException("question dao got expert while add question data.Msg:" + e.getLocalizedMessage());
		}
		return expertsAndReplys;
	}


	public List<Map<String, Object>> getExpertsByExpertId(int expertId) throws ServiceException {
		String method = "getExpertsByExpertId";
		if (expertId == 0) {
			log.error(method + "the expertId is null");
			throw new ServiceException("the expertId is null");
		}
		List<Map<String, Object>> expert = new ArrayList<Map<String, Object>>();
		try {
			Map map = new HashMap();
			map.put("isAccepted", Config.getInt("REPLY_ACCEPT"));
			//map.put("recommendExpert", Config.getInt("RECOMMEND"));
			map.put("expertId", expertId);
			expert = expertDao.findExpertsByExpertId(map);
		} catch (Exception e) {
			log.error(method + ",question dao got expert while add question." + e.getLocalizedMessage());
			e.printStackTrace();
			throw new ServiceException("question dao got expert while add question data.Msg:" + e.getLocalizedMessage());
		}
		return expert;
	}

	public List<Map<String, Object>> findDomainsByExpertId(Map<String, Object> map) {
		String method = "findDomainsByExpertId";
		List<Map<String, Object>> expertDomain = new ArrayList<Map<String, Object>>();
		try {
			if (map.get("expertId") == null) {
				log.error(method + "the expertId is null");
				throw new ServiceException("the expertId is null");
			}
			expertDomain = expertDao.findDomainsByExpertId(map);
			return expertDomain;
		} catch (ServiceException e) {
			log.error(method + ",question dao got expert while add question." + e.getLocalizedMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> findExpertsByFullname(Map<String, Object> map) {
		return expertDao.findExpertsByFullname(map);
	}

	@Override
	public int countExpertsByFullname(Map<String, Object> map) {
		return expertDao.countExpertsByFullname(map);
	}
}
