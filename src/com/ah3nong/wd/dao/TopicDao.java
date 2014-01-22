package com.ah3nong.wd.dao;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Topic;

public interface TopicDao {
	// 增加1个
	public void addTopic(Topic topic);

	// 根据id修改
	public int updateTopicById(Topic topic);

	// 按页查
	public List<Topic> findTopicsByPageNum(Map<String, Integer> map);

	// 查最新1个
	public Topic findLatestTopic();

	// 查全部
	public List<Topic> findAllTopics();

	// 根据id查
	public Topic findByPrimaryKey(int id);

	// 查全部
	public int countAll();

	// 按标题包含字搜
	public List<Topic> findTopicsByTitle(Map<String, Object> map);

	// 查按标题包含字搜出的专题数
	public int countTopicsByTitle(Map<String, Object> map);
}
