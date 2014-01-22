package com.ah3nong.wd.dao.impl;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Topic;
import com.ah3nong.wd.dao.BaseDao;
import com.ah3nong.wd.dao.TopicDao;

public class TopicDaoImpl extends BaseDao<Topic> implements TopicDao {

	@Override
	public void addTopic(Topic topic) {
		getSqlMapClientTemplate().insert("wd_topic.insert",topic);
	}

	@Override
	public int updateTopicById(Topic topic) {
		return (Integer) getSqlMapClientTemplate().update("wd_topic.updateByPrimaryKey",topic);
	}

	@Override
	public List<Topic> findTopicsByPageNum(Map<String, Integer> map) {
		return getSqlMapClientTemplate().queryForList("wd_topic.selectByPageNum",map);
	}

	@Override
	public Topic findLatestTopic() {
		List<Topic> topicList = getSqlMapClientTemplate().queryForList("wd_topic.selectLatestOne");
		if(topicList.size()>0){
			return topicList.get(0);
		}
		return null;
	}

	@Override
	public List<Topic> findAllTopics() {
		return getSqlMapClientTemplate().queryForList("wd_topic.selectAll");
	}

	@Override
	public Topic findByPrimaryKey(int id) {
		Topic topic = new Topic();
		topic.setId(id);
		return (Topic)getSqlMapClientTemplate().queryForObject("wd_topic.selectByPrimaryKey",topic);
	}

	@Override
	public int countAll() {
		return (Integer)getSqlMapClientTemplate().queryForObject("wd_topic.countAll");
	}

	@Override
	public List<Topic> findTopicsByTitle(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("wd_topic.selectByTitle",map);
	}

	@Override
	public int countTopicsByTitle(Map<String, Object> map) {
		return (Integer)getSqlMapClientTemplate().queryForObject("wd_topic.countByTitle",map);
	}
}
