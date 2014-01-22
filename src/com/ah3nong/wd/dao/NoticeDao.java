package com.ah3nong.wd.dao;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Notice;

public interface NoticeDao {
	//增加一条记录
	public void addNotice(Notice notice);
	
	//修改记录状态
	public void updateStatusByQuestionIdAndUserId(Notice notice);
	
	//根据用户Id,类型查找问题
	public List<Map<String,Object>> findQuestionByUserIdAndType(Notice notice);
	
	//根据用户id,类型查问题数
	public int countByUserIdAndType(Notice notice);
}
