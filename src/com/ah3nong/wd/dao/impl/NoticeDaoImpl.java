package com.ah3nong.wd.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.bean.Notice;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.NoticeDao;

public class NoticeDaoImpl extends BaseDao<Notice> implements NoticeDao {

	@Override
	public void addNotice(Notice notice) {
		getSqlMapClientTemplate().insert("wd_notice.insert",notice);
	}

	@Override
	public void updateStatusByQuestionIdAndUserId(Notice notice) {
		getSqlMapClientTemplate().update("wd_notice.updateStatusByQuestionIdAndUserId",notice);
	}

	@Override
	public List<Map<String, Object>> findQuestionByUserIdAndType(Notice notice) {
		List<Map<String,Object>> list = getSqlMapClientTemplate().queryForList("wd_notice.selectByUserIdAndType",notice);
		List<Map<String,Object>> questions = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id",(Integer) list.get(i).get("id"));
			map.put("subject",(String) list.get(i).get("subject"));
			map.put("domain",(String) list.get(i).get("domain"));
			map.put("replyNum",(Integer) list.get(i).get("replyNum"));
			if(list.get(i).get("anchor")!=null){
				map.put("anchor",(Integer) list.get(i).get("anchor"));
			}
			Date d = (Date) list.get(i).get("createdTime");
			map.put("createdTime",(String)d.toLocaleString());
			questions.add(map);
		}
		return questions;
	}

	@Override
	public int countByUserIdAndType(Notice notice) {
		return (Integer)getSqlMapClientTemplate().queryForObject("wd_notice.countByUserIdAndType",notice);
	}

}
