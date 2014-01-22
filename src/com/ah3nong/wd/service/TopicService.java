package com.ah3nong.wd.service;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Topic;
import com.ah3nong.wd.common.Pager;

public interface TopicService {
	// 增加1个
	public void addTopic(Topic topic);

	// 根据id修改
	public int updateTopicById(Topic topic);

	// 按页查
	public Pager<Topic> findTopicsByPageNum(int pageNum, int numPerPage);

	// 按页查
	public List<Topic> findTopicListByPageNum(int pageNum, int numPerPage);

	// 查最新1个
	public Topic findLatestTopic();

	// 查全部
	public List<Topic> findAllTopics();

	// 根据id查
	public Topic findByPrimaryKey(int id);

	// 查专题总数
	public int countAll();

	// 按标题包含字搜
	public List<Topic> findTopicsByTitle(Map<String, Object> map);

	// 查按标题包含字搜出的专题数
	public int countTopicsByTitle(Map<String, Object> map);
}
