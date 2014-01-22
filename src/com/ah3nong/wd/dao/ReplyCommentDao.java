package com.ah3nong.wd.dao;

import java.util.List;

import com.ah3nong.wd.bean.ReplyComment;

public interface ReplyCommentDao {
	// 增加评论
	public void addReplyComment(ReplyComment replyComment);

	// 根据id修改状态
	public int updateReplyCommentStatusById(int id);

	// 根据回复id获得评论
	public List<ReplyComment> findReplyCommentByReplyId(int replyId);
	
	// 根据回复id获得评论数
	public int findCommentNumByReplyId(int replyId);
}
