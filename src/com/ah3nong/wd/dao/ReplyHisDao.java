package com.ah3nong.wd.dao;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.ReplyHis;

public interface ReplyHisDao {

	public ReplyHis findReplyHisByPrimaryKey(int id);
	
	public List<ReplyHis> findAllReplyHis();
	
	public int delReplyHisByPrimaryKey(int id);
	
	public void addReplyHis(ReplyHis record);
	
	public int updateReplyHisByPrimaryKey(ReplyHis record);
	
	public int updateReplyHisByPrimaryKeySelective(ReplyHis record);
	
	public int countAllReplyHis();
	
	public List<ReplyHis> queryForPager(Map map);
	
	public int countForPager(Map map);
	//根据问题ID查找回答
	public List<ReplyHis> findReplyHisByQuestionId(int questionId);
	//根据问题ID删除回答
	public int delReplyHisByQuestionId(int questionId);
	//根据追问ID查找回答
	public ReplyHis findReplyHisByProbingId(int probingId);
}
