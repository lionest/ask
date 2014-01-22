package com.ah3nong.wd.action.topic;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Topic;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.service.TopicService;

public class ListTopicAction extends GenericActionSupport<Topic> {
	private static final long serialVersionUID = 5177911779846890314L;
	
	private TopicService topicService;
	public String execute(){
		if (pageNum == 0) {
			pageNum = 1;
		}
		if (numPerPage == 0) {
			numPerPage = Config.getInt("PAGESIZE");
		}
		pager = topicService.findTopicsByPageNum(pageNum,numPerPage);
		
		return SUCCESS;
	}
	
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
}
