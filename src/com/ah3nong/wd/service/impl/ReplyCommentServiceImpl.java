package com.ah3nong.wd.service.impl;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.ReplyComment;
import com.ah3nong.wd.dao.ReplyCommentDao;
import com.ah3nong.wd.service.ReplyCommentService;

public class ReplyCommentServiceImpl implements ReplyCommentService {
	private ReplyCommentDao replyCommentDao;
	@Override
	public void addReplyComment(ReplyComment replyComment) {
		replyCommentDao.addReplyComment(replyComment);
	}

	@Override
	public int updateReplyCommentStatusById(int id) {
		return replyCommentDao.updateReplyCommentStatusById(id);
	}

	@Override
	public List<ReplyComment> findReplyCommentByReplyId(int replyId) {
		return replyCommentDao.findReplyCommentByReplyId(replyId);
	}

	@Override
	public int findCommentNumByReplyId(int replyId) {
		return replyCommentDao.findCommentNumByReplyId(replyId);
	}
	
	public void setReplyCommentDao(ReplyCommentDao replyCommentDao) {
		this.replyCommentDao = replyCommentDao;
	}
	
}
