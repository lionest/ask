package com.ah3nong.wd.action.topic;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Topic;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.service.TopicService;

public class DeleteTopicAction extends GenericActionSupport<Topic> {
	private static final long serialVersionUID = -3973889660940340925L;
	private int id;
	private TopicService topicService;
	
	public String execute(){
		Topic topic = topicService.findByPrimaryKey(id);
		topic.setStatus(Config.getInt("TOPIC_REMOVE"));
		topicService.updateTopicById(topic);
		return SUCCESS;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
}
