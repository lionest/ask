package com.ah3nong.wd.service;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.bean.ReplyHis;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.exception.ServiceException;

public interface ReplyService {
	public Pager<Reply> getReplyPagerByParam(Map params, int currentPage, int pageSize) throws ServiceException;

	public Reply findReplyByPrimaryKey(int id) throws ServiceException;

	public List<Reply> findAllReply() throws ServiceException;

	public int delReplyByPrimaryKey(int id) throws ServiceException;

	public void addReply(Reply record) throws ServiceException;

	public int updateReplyByPrimaryKey(Reply record, boolean isSelective) throws ServiceException;

	public int countAllReply() throws ServiceException;

	// 根据问题ID查找回答
	public List<Reply> findReplyByQuestionId(Map map) throws ServiceException;

	// 根据追问ID查找回答
	public Reply findReplyByProbingId(int probingId) throws ServiceException;

	// 根据questionId和userId来锁定确认为满意答案的回复
	public List<Reply> findReplysToAccept(Reply reply) throws ServiceException;

	// Reply_His
	public void addReplyHis(ReplyHis replyHis) throws ServiceException;

	public Pager<Reply> getReplyHisPagerByParam(Map<String,Object> params, int currentPage, int pageSize) throws ServiceException;

	public int updateReplyHisSelective(ReplyHis replyHis) throws ServiceException;

	public ReplyHis findReplyHisByPrimaryKey(int replyHisId) throws ServiceException;

	public List<Reply> findReplyByQuestionIdAndStatus(Map map) throws ServiceException;
	
	public List<Reply> queryForPagerByStatus(Map<String,Object> map);

	public int countForPagerByStatus(Map<String,Object> map);
	
	public List<Reply> findReplyByQuestionIdAndStatusAndUserId(Map<String,Object> map);
}
