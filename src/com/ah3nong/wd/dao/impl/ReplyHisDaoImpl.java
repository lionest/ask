package com.ah3nong.wd.dao.impl;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.ReplyHis;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.ReplyHisDao;

public class ReplyHisDaoImpl extends BaseDao<ReplyHisDaoImpl> implements ReplyHisDao {

	
	public int countAllReplyHis() {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_reply_his.countAll");
		return rows;
	}

	
	public int countForPager(Map map) {
		int rows = (Integer) getSqlMapClientTemplate().queryForObject("wd_reply_his.countPaginationByPageNum", map);
		return rows;
	}

	
	public int delReplyHisByPrimaryKey(int id) {
		ReplyHis key = new ReplyHis();
		key.setId(id);
		int rows = getSqlMapClientTemplate().delete("wd_reply_his.deleteByPrimaryKey", key);
		return rows;
	}

	
	public List<ReplyHis> findAllReplyHis() {
		List<ReplyHis> list = getSqlMapClientTemplate().queryForList("wd_reply_his.selectAll");
		return list;
	}

	
	public void addReplyHis(ReplyHis record) {
		getSqlMapClientTemplate().insert("wd_reply_his.insert", record);
	}

	
	public List<ReplyHis> queryForPager(Map map) {
		List<ReplyHis> list = getSqlMapClientTemplate().queryForList("wd_reply_his.selectPaginationByPageNum",map);
		return list;
	}

	
	public ReplyHis findReplyHisByPrimaryKey(int id) {
		ReplyHis key = new ReplyHis();
		key.setId(id);
		ReplyHis record = (ReplyHis) getSqlMapClientTemplate().queryForObject("wd_reply_his.selectByPrimaryKey", key);
		return record;
	}

	
	public int updateReplyHisByPrimaryKey(ReplyHis record) {
		int rows = getSqlMapClientTemplate().update("wd_reply_his.updateByPrimaryKey", record);
		return rows;
	}

	
	public int updateReplyHisByPrimaryKeySelective(ReplyHis record) {
		int rows = getSqlMapClientTemplate().update("wd_reply_his.updateByPrimaryKeySelective", record);
		return rows;
	}

	
	public ReplyHis findReplyHisByProbingId(int probingId) {
		ReplyHis key = new ReplyHis();
		key.setProbingId(probingId);
		ReplyHis record = (ReplyHis) getSqlMapClientTemplate().queryForObject("wd_reply_his.selectByProbingId", key);
		return record;
	}

	@SuppressWarnings("unchecked")
	
	public List<ReplyHis> findReplyHisByQuestionId(int questionId) {
		ReplyHis key = new ReplyHis();
		key.setQuestionId(questionId);
		List<ReplyHis> list = getSqlMapClientTemplate().queryForList("wd_reply_his.selectByQuestionId", key);
		return list;
	}

	public int delReplyHisByQuestionId(int questionId) {
		ReplyHis key = new ReplyHis();
		key.setQuestionId(questionId);
		int result = getSqlMapClientTemplate().delete("wd_reply_his.deleteByQusetionId", key);
		return result;
	}

}
