package com.ah3nong.wd.dao;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Reply;

public interface ReplyDao {

	public Reply findReplyByPrimaryKey(int id);

	public List<Reply> findAllReply();

	public int delReplyByPrimaryKey(int id);

	public void addReply(Reply record);

	public int updateReplyByPrimaryKey(Reply record);

	public int updateReplyByPrimaryKeySelective(Reply record);

	public int countAllReply();

	public List<Reply> queryForPager(Map map);

	public int countForPager(Map map);
	
	public List<Reply> queryForPagerByStatus(Map<String,Object> map);

	public int countForPagerByStatus(Map<String,Object> map);

	// 根据问题ID查找回答
	public List<Reply> findReplyByQuestionId(Map map);

	// 根据问题ID删除回答
	public int delReplyByQuestionId(int questionId);

	// 根据追问ID查找回答
	public Reply findReplyByProbingId(int probingId);

	// 根据questionId和userId来锁定确认为满意答案的回复
	public List<Reply> findReplysToAccept(Reply reply);

	public List<Reply> findReplyByQuestionIdAndStatus(Map map);
	
	public List<Reply> findReplyByQuestionIdAndStatusAndUserId(Map<String,Object> map);
}
