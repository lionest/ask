package com.ah3nong.wd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.bean.Topic;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.dao.TopicDao;
import com.ah3nong.wd.service.TopicService;

public class TopicServiceImpl implements TopicService {
	private TopicDao topicDao;
	
	@Override
	public void addTopic(Topic topic) {
		topicDao.addTopic(topic);
	}

	@Override
	public int updateTopicById(Topic topic) {
		return topicDao.updateTopicById(topic);
	}

	@Override
	public Pager<Topic> findTopicsByPageNum(int pageNum,int numPerPage) {
		Pager<Topic> pager = new Pager<Topic>(pageNum,numPerPage);
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("start", (pageNum-1)*numPerPage);
		map.put("size", numPerPage);
		pager.setPageRecords(topicDao.findTopicsByPageNum(map));
		pager.setTotalRecords(topicDao.countAll());
		return  pager;
	}

	@Override
	public Topic findLatestTopic() {
		return topicDao.findLatestTopic();
	}

	@Override
	public List<Topic> findAllTopics() {
		return topicDao.findAllTopics();
	}

	@Override
	public Topic findByPrimaryKey(int id) {
		return topicDao.findByPrimaryKey(id);
	}
	
	@Override
	public List<Topic> findTopicListByPageNum(int pageNum, int numPerPage) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("start", (pageNum-1)*numPerPage);
		map.put("size", numPerPage);
		return topicDao.findTopicsByPageNum(map);
	}
	
	@Override
	public int countAll() {
		return topicDao.countAll();
	}

	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}

	@Override
	public List<Topic> findTopicsByTitle(Map<String, Object> map) {
		return topicDao.findTopicsByTitle(map);
	}

	@Override
	public int countTopicsByTitle(Map<String, Object> map) {
		return topicDao.countTopicsByTitle(map);
	}

}
