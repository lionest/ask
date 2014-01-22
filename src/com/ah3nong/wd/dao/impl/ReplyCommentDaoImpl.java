package com.ah3nong.wd.dao.impl;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.ReplyComment;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.ReplyCommentDao;

public class ReplyCommentDaoImpl extends BaseDao<ReplyComment> implements ReplyCommentDao {

	@Override
	public void addReplyComment(ReplyComment replyComment) {
		getSqlMapClientTemplate().insert("wd_reply_comment.insert", replyComment);
	}

	@Override
	public int updateReplyCommentStatusById(int id) {
		ReplyComment replyComment = new ReplyComment();
		replyComment.setId(id);
		replyComment.setStatus(2);
		return getSqlMapClientTemplate().update("wd_reply_comment.updateStatusByPrimaryKey", replyComment);
	}

	@Override
	public List<ReplyComment> findReplyCommentByReplyId(int replyId) {
		ReplyComment replyComment = new ReplyComment();
		replyComment.setReplyId(replyId);
		return getSqlMapClientTemplate().queryForList("wd_reply_comment.selectByReplyId", replyComment);
	}

	@Override
	public int findCommentNumByReplyId(int replyId) {
		ReplyComment replyComment = new ReplyComment();
		replyComment.setReplyId(replyId);
		return (Integer) getSqlMapClientTemplate().queryForObject("wd_reply_comment.selectCommentNumByReplyId", replyComment);
	}

}
