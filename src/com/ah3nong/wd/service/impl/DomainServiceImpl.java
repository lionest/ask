package com.ah3nong.wd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.dao.DomainDao;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.util.StringHelper;

public class DomainServiceImpl implements DomainService {
	private DomainDao domainDao;
	private Logger log = LoggerFactory.getLogger(DomainServiceImpl.class);

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

	public Pager<Domain> getDomainPagerDataByParam(Map params, int currentPage,
			int pageSize) throws ServiceException {
		String method = "getDomainPagerDataByParam";
		if (params == null) {
			log.error(method + ", the params of pager data is null!");
			throw new ServiceException(
					"cannot query any domains with the param is null!");
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
		Pager<Domain> pager = new Pager<Domain>(currentPage, pageSize);
		try {
			pager.setPageRecords(domainDao.queryForPager(params));
			pager.setTotalRecords(domainDao.countForPager(params));
		} catch (Exception e) {
			log.error(method
					+ ",domain dao got exceptions while query pager data." + e);
			throw new ServiceException(
					"domain dao got exceptions while query pager data.Msg:" + e);
		}

		return pager;
	}

	/**
	 * 添加领域分类
	 * 
	 * @param domain
	 *            领域分类对象
	 * 
	 */
	public int addDomain(Domain domain) throws ServiceException {
		String method = "addDomain";
		int domainId = 0;
		if (domain == null) {
			log
					.error(method
							+ ", the params of add domain、domainExpert、domainExpert is null!");
			throw new ServiceException(
					"cannot add domain、domainExpert、domainExpert since the param is null!");
		}
		log.info(method + ", the domain params:" + domain.toString());
		try {
			Map map = new HashMap<String, Object>();
			map.put("name", domain.getName());
			List<Domain> m = null;
			if (domain.getParentId() == null || domain.getParentId() < 0) {
				m = domainDao.findDomainByNameForFirst(map);
			} else {
				m = domainDao.findDomainByName(map);
			}
			Domain tmp = null;
			if (m != null && m.size() > 0) {
				tmp = m.get(0);
				if (!tmp.getId().equals(domain.getId())) {
					log.error(method + ",sorry the name  exists!");
					return -1;
				}
			}
			String maxNodePath = "";
			if (domain.getParentId() == null || domain.getParentId() < 0) {
				maxNodePath = domainDao.getMaxNodePath(0);
			} else {
				maxNodePath = domainDao.getMaxNodePath(domain.getParentId());				
			}
			String nexNodePath=null;
			if(maxNodePath == null){
				Domain ParentDomain = domainDao.findDomainByPrimaryKey(domain.getParentId());
				nexNodePath = ParentDomain.getNodePath()+"01";
			}else{
				nexNodePath = StringHelper.findMaxNextValue(maxNodePath);
			}
			
			domain.setNodePath(nexNodePath);
			if (domain.getParentId() == null) {
				domain.setParentId(null);
			}
			
			//通过nodePath找出他的父类，如果有就把父类的has_child设为1
			String parentNodePath = nexNodePath.substring(0,nexNodePath.length()-2);
			Domain parentDomain = domainDao.findDomainByNodePath(parentNodePath);
			if(parentDomain!=null){
				parentDomain.setHasChild(1);
				domainDao.updateDomainByPrimaryKeySelective(parentDomain);
			}
			
			domainId = domainDao.addDomain(domain);
		} catch (Exception e) {
			log.error(method + ",domain dao got exceptions while add domain."
					+ e);
			e.printStackTrace();
			return domainId;
		}
		return domainId;
	}

	/**
	 * 查询所有的领域分类总数
	 * 
	 */
	public int countAllDomain() throws ServiceException {
		String method = "countAllDomain";
		try {
			return domainDao.countAllDomain();
		} catch (Exception e) {
			log.error(method
					+ ",domain dao got exceptions while countAllDomain." + e);
			throw new ServiceException(
					"domain dao got exceptions while countAllDomain data.Msg:"
							+ e);
		}
	}

	/**
	 * 根据领域分类ID删除领域分类
	 */
	public int delDomainByPrimaryKey(int id) throws ServiceException {
		String method = "delDomainByPrimaryKey";
		if (id == 0) {
			log.error(method + ", the params of delete domain is null!");
			throw new ServiceException(
					"cannot delete domain since the param is null!");
		}
		log.info(method + ", the params:" + id);
		try {
			int result = domainDao.delDomainByPrimaryKey(id);
			return result;
		} catch (Exception e) {
			log.error(method
					+ ",domain dao got exceptions while delete domain." + e);
			throw new ServiceException(
					"domain dao got exceptions while delete domain data.Msg:"
							+ e);
		}
	}
	
	public int delDomainByNodePath(String nodePath) throws ServiceException {
		String method = "delDomainByPrimaryKey";
		if (nodePath == null || "".equals(nodePath)) {
			log.error(method + ", the params of delete domain is null!");
			throw new ServiceException(
					"cannot delete domain since the param is null!");
		}
		log.info(method + ", the params:" + nodePath);
		try {
			int result = domainDao.delDomainByNodePath(nodePath);
			
			//判断有没有父类，如果有,判断父类还有没有子类，修改has_child选项
			String parentNodePath = nodePath.substring(0,nodePath.length()-2);
			Domain parentDomain = domainDao.findDomainByNodePath(parentNodePath);
			Map map = new HashMap();
			map.put("nodePath", parentNodePath);
			List<Domain> domainList = domainDao.selectChildDomain(map);
			if(domainList.size()==0){
				parentDomain.setHasChild(0);
				domainDao.updateDomainByPrimaryKeySelective(parentDomain);
			}
			return result;
		} catch (Exception e) {
			log.error(method
					+ ",domain dao got exceptions while delete domain." + e);
			throw new ServiceException(
					"domain dao got exceptions while delete domain data.Msg:"
							+ e);
		}
	}

	/**
	 * 查询数据库所有的领域分类
	 * 
	 */
	public List<Domain> findAllDomain() throws ServiceException {
		String method = "findAllDomain";
		try {
			return domainDao.findAllDomain();
		} catch (Exception e) {
			log.error(method + ",domain dao got exceptions while selectAll."
					+ e);
			throw new ServiceException(
					"domain dao got exceptions while selectAll data.Msg:" + e);
		}
	}

	public List<Domain> findDomain(Map<String, Object> params) {
		return domainDao.findDomain(params);
	}

	/**
	 * 根据领域分类ID查询领域分类对象
	 */
	public Domain findDomainByPrimaryKey(int id) throws ServiceException {
		String method = "findDomainByPrimaryKey";
		if (id == 0) {
			log.error(method
					+ ", the params of query domain by primaryKey is null!");
			throw new ServiceException(
					"cannot query domain by primaryKey since the id is null!");
		}
		log.info(method + ", the params:" + id);
		try {
			return domainDao.findDomainByPrimaryKey(id);
		} catch (Exception e) {
			log
					.error(method
							+ ",domain dao got exceptions while query domain by primaryKey."
							+ e);
			throw new ServiceException(
					"domain dao got exceptions while query domain by primaryKey data.Msg:"
							+ e);
		}
	}

	/**
	 * 根据领域分类ID来更新领域分类
	 */
	public int updateDomainByPrimaryKey(Domain domain) throws ServiceException {
		String method = "updateDomainByPrimaryKey";
		if (domain == null) {
			log.error(method
					+ ", the params of update domain by domain is null!");
			throw new ServiceException(
					"cannot update domain by primaryKey since the domain is null!");
		}
		log.info(method + ", the params:" + domain.toString());
		try {
			String maxNodePath = domainDao.getMaxNodePath(domain.getParentId());
			Domain td = domainDao.findDomainByPrimaryKey(domain.getId());
			Domain tmp = null;
			Map map = new HashMap<String, Object>();
			map.put("name", domain.getName());
			List<Domain> m = null;
			if (domain.getParentId() == null || domain.getParentId() == 0 || domain.getParentId() < 0) {
				m = domainDao.findDomainByNameForFirst(map);
			} else {
				m = domainDao.findDomainByName(map);
			}
			if (m != null && m.size() > 0) {
				tmp = m.get(0);
				if (!tmp.getId().equals(domain.getId())) {
					log.error(method + ",sorry the name  exists!");
					return -1;
				}
			}
			if (domain.getParentId() == 0) {
				domain.setParentId(null);
			}
			if(domain.getNodePath()== null || domain.getNodePath() == ""){
				if (td.getParentId() != domain.getParentId()) {
					String nexNodePath = StringHelper.findMaxNextValue(maxNodePath);
					domain.setNodePath(nexNodePath);
				}
			}

			return domainDao.updateDomainByPrimaryKey(domain);
		} catch (Exception e) {
			log
					.error(method
							+ ",domain dao got exceptions while update domain by primaryKey."
							+ e);
			throw new ServiceException(
					"domain dao got exceptions while update domain by primaryKey data.Msg:"
							+ e);
		}
	}

	/**
	 * 转换领域分类为JSON格式用于前台页面显示 [{'id':'1','parentId':'0','name':'粮食'},{...},...]
	 * 
	 * @param allDomains
	 * @return
	 */
	public String convertDomainToJson(List<Domain> allDomains) {
		String method = "convertDomainToJson";
		if (allDomains == null) {
			log.error(method + ", the domain list is null!");
			return null;
		}
		StringBuffer str = new StringBuffer();
		str.append("[");
		for (int i = 0; i < allDomains.size(); i++) {
			Domain d = allDomains.get(i);
			if (d.getParentId() == null || d.getParentId()<0) {
				d.setParentId(0);
			}
			str.append("{");
			str.append("'id':'" + d.getId() + "','parentId':'"
					+ d.getParentId() + "','name':'" + d.getName() + "'");
			str.append("}");
			if (i != allDomains.size() - 1) {
				str.append(",");
			}
		}
		str.append("]");
		return str.toString();
	}

	public List<Domain> findRecommendedDomain() {
		return domainDao.findRecommendedDomain();
	}

	public void setDomainDao(DomainDao domainDao) {
		this.domainDao = domainDao;
	}

	/**
	 * 获得一级问答分类
	 * 
	 * @throws ServiceException
	 */
	public List<Domain> findNoParentIdsDomain() throws ServiceException {
		String method = "findNoParentIdsDomain";
		List<Domain> domains = new ArrayList<Domain>();
		try {
			List<Domain> domain = domainDao.findAllDomain();
			log.info(method + ", the params:" + domain.toString());
			for (int i = 0; i < domain.size(); i++) {
				if (domain.get(i).getParentId() == null) {
					domains.add(domain.get(i));
				}
			}
		} catch (Exception e) {
			log
					.error(method
							+ ",domain dao got exceptions while update domain by primaryKey."
							+ e);
			throw new ServiceException(
					"domain dao got exceptions while update domain by primaryKey data.Msg:"
							+ e);
		}
		return domains;
	}

	/**
	 * 根据一级问答分类获得二级问答分类
	 * 
	 * @throws ServiceException
	 */
	public List<Domain> findDomainsByParentId(int parentId)
			throws ServiceException {
		String method = "findDomainsByParentId";
		List<Domain> domains = new ArrayList<Domain>();
		if (parentId == 0) {
			log.error(method + ", the domain list is null!");
			return null;
		}
		try {
			Map map = new HashMap();
			map.put("parentId", parentId);
			map.put("recommended", 1);
			domains = domainDao.findAllDomains(map);
		} catch (Exception e) {
			log
					.error(method
							+ ",domain dao got exceptions while update domain by primaryKey."
							+ e);
			throw new ServiceException(
					"domain dao got exceptions while update domain by primaryKey data.Msg:"
							+ e);
		}
		return domains;
	}
	
	public List<Domain> findChildDomain(String parentNodePath)
			throws ServiceException {
		String method = "selectChildDomain";
		List<Domain> domains = new ArrayList<Domain>();
		if (parentNodePath == null) {
			log.error(method + ", the domain list is null!");
			return null;
		}
		try {
			Map map = new HashMap();
			map.put("nodePath", parentNodePath);
			domains = domainDao.selectChildDomain(map);
		} catch (Exception e) {
			log
					.error(method
							+ ",domain dao got exceptions while update domain by primaryKey."
							+ e);
			throw new ServiceException(
					"domain dao got exceptions while update domain by primaryKey data.Msg:"
							+ e);
		}
		return domains;
	}

	public List<Map<String, Object>> getDomainAndAllExperts()
			throws ServiceException {
		String method = "getDomainAndAllExperts";
		List<Map<String, Object>> domainAndAllExperts = new ArrayList<Map<String, Object>>();
		try {
			domainAndAllExperts = domainDao.getDomainAndAllExperts();
		} catch (Exception e) {
			log
					.error(method
							+ ",domain dao getDomainAndAllExperts while update domain by primaryKey."
							+ e.getLocalizedMessage());
			throw new ServiceException(
					"domain dao getDomainAndAllExperts while update domain by primaryKey data.Msg:"
							+ e.getLocalizedMessage());
		}

		return domainAndAllExperts;
	}

	public List<Domain> findDomainsByNodePath(int domainId)
			throws ServiceException {
		String method = "findDomainsByNodePath";
		List<Domain> domains = new ArrayList<Domain>();
		if (domainId == 0) {
			log.error(method + ", the domain list is null!");
			return null;
		}
		try {
			Domain domain = domainDao.findDomainByPrimaryKey(domainId);
			domains.add(0,domain);
			if(domain.getNodePath()!=null && !domain.getNodePath().equals("")){				
				
				String nodePath = domain.getNodePath().substring(0,domain.getNodePath().length()-2);								
				while(nodePath.length()>0){					
					domains.add(0,domainDao.findDomainByNodePath(nodePath));					
					nodePath = nodePath.substring(0,nodePath.length()-2);
				}
//				Domain temp =new Domain();
//				for(int i=0;i<domains.size()/2;i++){
//					temp = domains.get(i);
//					domains.add(i, domains.get(domains.size()-1-i));
//					domains.add(domains.size()-1-i,temp);  
//				}
			}
			
			
		} catch (Exception e) {
			log
					.error(method
							+ ",domain dao got exceptions while find domains by nodePath."
							+ e);
			throw new ServiceException(
					"domain dao got exceptions while find domains by nodePath data.Msg:"
							+ e);
		}
		return domains;
	}

	public List<Map<String, Object>> findDomainByUserId(int userId)
			throws ServiceException {
		String method = "findDomainByUserId";
		if (userId == 0) {
			log.error(method + ", the domain list is null!");
			return null;
		}
		try {
			List<Map<String,Object>> list = domainDao.findDomainByUserId(userId);						
			if(list.size()>0){
				return list;
			}
		} catch (Exception e) {
			log
					.error(method
							+ ",domain dao got exceptions while find domains by userId."
							+ e);
			throw new ServiceException(
					"domain dao got exceptions while find domains by userId data.Msg:"
							+ e);
		}
		return null;
	}
	
	public Domain findDomainByNodePath(String nodePath) throws ServiceException{
		String method = "findDomainByNodePath";
		if (nodePath == null) {
			log.error(method + ", the domain is null!");
			return null;
		}
		try {
			Domain domain = domainDao.findDomainByNodePath(nodePath);
			if(domain != null){
				return domain;
			}
		} catch (Exception e) {
			log.error(method + ",domain dao got exceptions while find domains by userId." + e);
			throw new ServiceException( "domain dao got exceptions while find domains by userId data.Msg:"+ e);
		}
		return null;
	}
	
	public List<Domain> selectAllChildDomains(Map map) throws ServiceException {
		String method = "selectAllChildDomains";
		try {
			return domainDao.selectAllChildDomains(map);
		} catch (Exception e) {
			log.error(method + ",domain dao got exceptions while selectAllChildDomains."
					+ e);
			throw new ServiceException(
					"domain dao got exceptions while selectAllChildDomains data.Msg:" + e);
		}
	}

	@Override
	public List<Map<String, Object>> findDomainByUserAskQuestion(Map<String, Object> map) {
		return domainDao.findDomainByUserAskQuestion(map);
	}

	@Override
	public List<Domain> findAllDomainsByParentId(int parentId) {
		return domainDao.findAllDomainsByParentId(parentId);
	}
}
