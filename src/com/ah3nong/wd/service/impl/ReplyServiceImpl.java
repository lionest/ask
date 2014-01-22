package com.ah3nong.wd.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.bean.ReplyHis;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.dao.ReplyDao;
import com.ah3nong.wd.dao.ReplyHisDao;
import com.ah3nong.wd.dao.UserDao;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.ReplyService;

public class ReplyServiceImpl implements ReplyService {
	
	private Logger log = LoggerFactory.getLogger(ReplyServiceImpl.class);
	
	private ReplyDao replyDao;
	private ReplyHisDao replyHisDao;
	private UserDao userDao;
	
	public void setReplyHisDao(ReplyHisDao replyHisDao) {
		this.replyHisDao = replyHisDao;
	}
	public ReplyDao getReplyDao() {
		return replyDao;
	}
	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	/**
	 * 根据页面参数，查询分页需要的数据
	 * 
	 * @param params 参数
	 * @param currenPage 当前页数
	 * @param pageSize 每页大小
	 * @return
	 * @throws ServiceException
	 */
	
	
	public Pager<Reply> getReplyPagerByParam(Map params, int currentPage,
			int pageSize) throws ServiceException {
		String method = "getReplyPagerByParam";
		if (params == null) {
			log.error(method + ", the params of pager data is null!");
			throw new ServiceException(
					"cannot query any replys since the param is null!");
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
		Pager<Reply> pager = new Pager<Reply>(currentPage, pageSize);
		try {
			pager.setPageRecords(replyDao.queryForPager(params));
			pager.setTotalRecords(replyDao.countForPager(params));
		} catch (Exception e) {
			log.error(method
					+ ",reply dao got exceptions while query pager data."
					+ e);
			throw new ServiceException(
					"reply dao got exceptions while query pager data.Msg:"
							+ e);
		}

		return pager;
	}
	/**
	 * 统计所有的记录数量
	 * @return
	 * @throws ServiceException
	 */
	
	public int countAllReply() throws ServiceException {
		String method = "countAllReply";
		try {
			return replyDao.countAllReply();
		} catch (Exception e) {
			log.error(method
					+ ",reply dao got exceptions while countAll."
					+ e);
			throw new ServiceException(
					"reply dao got exceptions while countAll data.Msg:"
							+ e);
		}
	}
	/**
	 * 根据主键ID删除回答记录
	 * @param id  回答ID
	 * @return
	 * @throws ServiceException
	 */
	
	public int delReplyByPrimaryKey(int id) throws ServiceException {
		String method = "delReplyByPrimaryKey";
		if(id == 0){
			log.error(method + ", the params of delete reply is null!");
			throw new ServiceException(
					"cannot delete reply since the param is null!");
		}
		log.info(method + ", the reply params:" + id);
		try {
			return replyDao.delReplyByPrimaryKey(id);				
		} catch (Exception e) {
			log.error(method
					+ ",reply dao got exceptions while delete reply."
					+ e);
			throw new ServiceException(
					"reply dao got exceptions while delete reply data.Msg:"
							+ e);
		}
	}
	/**
	 * 查找所有的回答记录
	 * @return
	 * @throws ServiceException
	 */
	
	public List<Reply> findAllReply() throws ServiceException {
		String method = "findAllReply";
		try {
			return replyDao.findAllReply();
		} catch (Exception e) {
			log.error(method
					+ ",reply dao got exceptions while findAllReply."
					+ e);
			throw new ServiceException(
					"reply dao got exceptions while findAllReply data.Msg:"
							+ e);
		}
	}
	
	/**
	 * 添加回答记录
	 * @param reply  添加的回答对象
	 * @return
	 * @throws ServiceException
	 */
	
	
	public void addReply(Reply reply) throws ServiceException {
		String method = "addReply";
		if(reply == null){
			log.error(method + ", the params of add reply is null!");
			throw new ServiceException(
					"cannot add reply since the param is null!");
		}
//		log.info(method + ", the reply params:" + reply);
		try {
			replyDao.addReply(reply);	
			//更新用户经验
			User user = userDao.findUserByPrimaryKey(reply.getUserId());
			user.setExperience(user.getExperience()+Config.getInt("ANSWER_EXPERIENCE"));
			userDao.updateUserExperience(user);
		} catch (Exception e) {
			log.error(method
					+ ",reply dao got exceptions while add reply."
					+ e);
			throw new ServiceException(
					"reply dao got exceptions while add reply data.Msg:"
							+ e);
		}
	}
	/**
	 * 根据回答主键ID，查询回答，1：1
	 * @param id  主键ID
	 * @return
	 * @throws ServiceException
	 */
	
	public Reply findReplyByPrimaryKey(int id) throws ServiceException {
		String method = "findReplyByPrimaryKey";
		if(id == 0){
			log.error(method + ", the params of select reply by id is null!");
			throw new ServiceException(
					"cannot select reply by id since the param is null!");
		}
		log.info(method + ", the reply params:" + id);
		try {
			Reply reply =(Reply) replyDao.findReplyByPrimaryKey(id);
			return reply;
		} catch (Exception e) {
			log.error(method
					+ ",reply dao got exceptions while select reply by id."
					+ e);
			throw new ServiceException(
					"reply dao got exceptions while select reply by id data.Msg:"
							+ e);
		}
	}
	
	/**
	 * 根据追问ID，查询回答，1：1
	 * @param probingId  追问ID
	 * @return
	 * @throws ServiceException
	 */
	
	public Reply findReplyByProbingId(int probingId) throws ServiceException {
		String method = "findReplyByProbingId";
		if(probingId == 0){
			log.error(method + ", the params of select reply by probingId is null!");
			throw new ServiceException(
					"cannot select reply by probingId since the param is null!");
		}
		log.info(method + ", the reply params:" + probingId);
		try {
			Reply reply =(Reply) replyDao.findReplyByProbingId(probingId);
			return reply;
		} catch (Exception e) {
			log.error(method
					+ ",reply dao got exceptions while select reply by probingId."
					+ e);
			throw new ServiceException(
					"reply dao got exceptions while select reply by probingId data.Msg:"
							+ e);
		}
	}
	/**
	 * 根据问题ID，查询回答列表，1：N
	 * @param questionId  问题ID
	 * @return
	 * @throws ServiceException
	 */

	
	public List<Reply> findReplyByQuestionId(Map map)
			throws ServiceException {
		String method = "findReplyByQuestionId";
		if((Integer)map.get("questionId") == 0){
			log.error(method + ", the params of select reply by questionId is null!");
			throw new ServiceException(
					"cannot select reply by questionId since the param is null!");
		}
		log.info(method + ", the reply params:" + map);
		try {
			List<Reply> list = replyDao.findReplyByQuestionId(map);
			return list;
		} catch (Exception e) {
			log.error(method
					+ ",reply dao got exceptions while select reply by questionId."
					+ e);
			throw new ServiceException(
					"reply dao got exceptions while select reply by questionId data.Msg:"
							+ e);
		}
	}
	
	public List<Reply> findReplyByQuestionIdAndStatus(Map map)
			throws ServiceException {
		String method = "findReplyByQuestionId";
		if((Integer)map.get("questionId") == 0){
			log.error(method + ", the params of select reply by questionId is null!");
			throw new ServiceException(
					"cannot select reply by questionId since the param is null!");
		}
		log.info(method + ", the reply params:" + map);
		try {
			List<Reply> list = replyDao.findReplyByQuestionIdAndStatus(map);
			return list;
		} catch (Exception e) {
			log.error(method
					+ ",reply dao got exceptions while select reply by questionId."
					+ e);
			throw new ServiceException(
					"reply dao got exceptions while select reply by questionId data.Msg:"
							+ e);
		}
	}
	/**
	 * 修改回答记录
	 * @param reply  修改后的回答对象
	 * @return
	 * @throws ServiceException
	 */
	
	public int updateReplyByPrimaryKey(Reply reply,boolean isSelective) throws ServiceException {
		String method = "updateReplyByPrimaryKey";
		if(reply == null){
			log.error(method + ", the params of update reply by primaryId is null!");
			throw new ServiceException(
					"cannot update reply by primaryId since the param is null!");
		}
		log.info(method + ", the reply params:" + reply.toString());
		try {
			if(isSelective){
				return replyDao.updateReplyByPrimaryKeySelective(reply);
			}else{
				return replyDao.updateReplyByPrimaryKey(reply);
			}
		} catch (Exception e) {
			log.error(method
					+ ",reply dao got exceptions while update reply by primaryId."
					+ e);
			throw new ServiceException(
					"reply dao got exceptions while select update reply by primaryId data.Msg:"
							+ e);
		}
	}
	public void addReplyHis(ReplyHis replyHis) throws ServiceException {
		String method = "addReplyHis";
		if(replyHis == null){
			log.error(method + ", the params of add replyHis is null!");
			throw new ServiceException(
					"cannot add replyHis since the param is null!");
		}
//		log.info(method + ", the reply params:" + reply);
		try {
			replyHisDao.addReplyHis(replyHis);			
		} catch (Exception e) {
			log.error(method
					+ ",reply dao got exceptions while add replyHis."
					+ e);
			throw new ServiceException(
					"reply dao got exceptions while add replyHis data.Msg:"
							+ e);
		}
		
	}
	public Pager<Reply> getReplyHisPagerByParam(Map<String,Object> params, int currentPage,
			int pageSize) throws ServiceException {
		String method = "getReplyHisPagerByParam";
		if (params == null) {
			log.error(method + ", the params of pager data is null!");
			throw new ServiceException(
					"cannot query any ReplyHis since the param is null!");
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
		Pager<Reply> pager = new Pager<Reply>(currentPage, pageSize);
		try {
			pager.setPageRecords(replyDao.queryForPagerByStatus(params));
			pager.setTotalRecords(replyDao.countForPagerByStatus(params));
		} catch (Exception e) {
			log.error(method
					+ ",reply dao got exceptions while query pager data."
					+ e);
			throw new ServiceException(
					"reply dao got exceptions while query pager data.Msg:"
							+ e);
		}

		return pager;
	}
	public ReplyHis findReplyHisByPrimaryKey(int replyHisId)
			throws ServiceException {
		String method = "findReplyHisByPrimaryKey";
		if(replyHisId == 0){
			log.error(method + ", the params of find replyHis by id is null!");
			throw new ServiceException(
					"cannot find replyHis by id since the param is null!");
		}
		log.info(method + ", the reply params:" + replyHisId);
		try {
			
			return replyHisDao.findReplyHisByPrimaryKey(replyHisId);
			
		} catch (Exception e) {
			log.error(method
					+ ",replyHis dao got exceptions while find replyHis by id."
					+ e);
			throw new ServiceException(
					"replyHis dao got exceptions while find replyHis by id data.Msg:"
							+ e);
		}
	}
	public int updateReplyHisSelective(ReplyHis replyHis)
			throws ServiceException {
		String method = "updateReplyHisSelective";
		if(replyHis == null){
			log.error(method + ", the params of update replyHis selective is null!");
			throw new ServiceException(
					"cannot update replyHis selective since the param is null!");
		}
		log.info(method + ", the reply params:" + replyHis);
		try {
			
			return replyHisDao.updateReplyHisByPrimaryKeySelective(replyHis);
			
		} catch (Exception e) {
			log.error(method
					+ ",replyHis dao got exceptions while update replyHis selective."
					+ e);
			throw new ServiceException(
					"replyHis dao got exceptions while update replyHis selective data.Msg:"
							+ e);
		}
	}
	public List<Reply> findReplysToAccept(Reply reply) throws ServiceException {
		String method = "findReplysToAccept";
		if(reply.getQuestionId() == 0){
			log.error(method + ", the questionId of find replylist to accept is null!");
			throw new ServiceException(
					"cannot find replylist to accept since the questionId is null!");
		}
		if(reply.getUserId() == 0){
			log.error(method + ", the userId of find replylist to accept is null!");
			throw new ServiceException(
					"cannot find replylist to accept since the userId is null!");
		}
		log.info(method + ", the reply params:" + reply);
		try {
			
			return replyDao.findReplysToAccept(reply);
			
		} catch (Exception e) {
			log.error(method
					+ ",reply dao got exceptions while find replylist to accept."
					+ e);
			throw new ServiceException(
					"reply dao got exceptions while find replylist to accept data.Msg:"
							+ e);
		}
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public List<Reply> queryForPagerByStatus(Map<String, Object> map) {
		return replyDao.queryForPagerByStatus(map);
	}
	@Override
	public int countForPagerByStatus(Map<String, Object> map) {
		return replyDao.countForPagerByStatus(map);
	}
	@Override
	public List<Reply> findReplyByQuestionIdAndStatusAndUserId(Map<String, Object> map) {
		return replyDao.findReplyByQuestionIdAndStatusAndUserId(map);
	}
	
}
