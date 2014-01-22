package com.ah3nong.wd.dao.impl;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.ReplyDao;

public class ReplyDaoImpl extends BaseDao<ReplyDaoImpl> implements ReplyDao {
	public int countAllReply() {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_reply.countAll");
		return rows;
	}
	
	public int countForPager(Map map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_reply.countPaginationByPageNum", map);
		return rows;
	}
	
	public int delReplyByPrimaryKey(int id) {
		Reply key = new Reply();
		key.setId(id);
		int rows = getSqlMapClientTemplate().delete("wd_reply.deleteByPrimaryKey", key);
		return rows;
	}
	
	public List<Reply> findAllReply() {
		List<Reply> list = getSqlMapClientTemplate().queryForList("wd_reply.selectAll");
		return list;
	}
	
	public void addReply(Reply record) {
		getSqlMapClientTemplate().insert("wd_reply.insert", record);
	}

	
	public List<Reply> queryForPager(Map map) {
		List<Reply> list = getSqlMapClientTemplate().queryForList("wd_reply.selectPaginationByPageNum",map);
		return list;
	}
	
	public Reply findReplyByPrimaryKey(int id) {
		Reply key = new Reply();
		key.setId(id);
		Reply record = (Reply) getSqlMapClientTemplate().queryForObject("wd_reply.selectByPrimaryKey", key);
		return record;
	}
	
	public int updateReplyByPrimaryKey(Reply record) {
		int rows = getSqlMapClientTemplate().update("wd_reply.updateByPrimaryKey", record);
		return rows;
	}
	
	public int updateReplyByPrimaryKeySelective(Reply record) {
		int rows = getSqlMapClientTemplate().update("wd_reply.updateByPrimaryKeySelective", record);
		return rows;
	}

	public Reply findReplyByProbingId(int probingId) {
		Reply key = new Reply();
		key.setProbingId(probingId);
		Reply record = (Reply) getSqlMapClientTemplate().queryForObject("wd_reply.selectByProbingId", key);
		return record;
	}

	@SuppressWarnings("unchecked")
	public List<Reply> findReplyByQuestionId(Map map) {
		List<Reply> list = getSqlMapClientTemplate().queryForList("wd_reply.selectByQuestionId", map);
		return list;
	}
	
	public List<Reply> findReplyByQuestionIdAndStatus(Map map) {
		List<Reply> list = getSqlMapClientTemplate().queryForList("wd_reply.selectByQuestionIdAndStatus", map);
		return list;
	}

	public int delReplyByQuestionId(int questionId) {
		Reply key = new Reply();
		key.setQuestionId(questionId);
		int result = getSqlMapClientTemplate().delete("wd_reply.deleteByQusetionId", key);
		return result;
	}

	public List<Reply> findReplysToAccept(Reply reply) {
		List<Reply> list = getSqlMapClientTemplate().queryForList("wd_reply.selectToAccept",reply);
		return list;
	}

	@Override
	public List<Reply> queryForPagerByStatus(Map<String,Object> map) {
		List<Reply> list = getSqlMapClientTemplate().queryForList("wd_reply.selectByStatus",map);
		return list;
	}

	@Override
	public int countForPagerByStatus(Map<String,Object> map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_reply.countByStatus", map);
		return rows;
	}

	@Override
	public List<Reply> findReplyByQuestionIdAndStatusAndUserId(Map<String, Object> map) {
		List<Reply> list = getSqlMapClientTemplate().queryForList("wd_reply.selectByQuestionIdAndStatusAndUserId",map);
		return list;
	}

}
