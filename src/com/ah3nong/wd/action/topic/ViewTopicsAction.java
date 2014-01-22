package com.ah3nong.wd.action.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Topic;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.TopicService;

public class ViewTopicsAction extends GenericActionSupport<Topic> {
	private static final long serialVersionUID = -9001983119056542063L;
	private static final int ACCEPTED_EXPERT_COUNT = 8;
	private static final int PAGE_SIZE = 9;

	private TopicService topicService;
	private QuestionService questionService;

	private int currentPage;
	
	private int count;
	private int countSize;
	private List<Map<String, Object>> expertAcceptedCountMap;
	private List<Topic> topicList = new ArrayList<Topic>();

	public String execute() {
		try {
			// 获取推荐专家以及被采纳的问题的数量
			expertAcceptedCountMap = questionService.getExpertAcceptedCountMap(ACCEPTED_EXPERT_COUNT);
			// 获取专题列表
			if (currentPage == 0) {
				currentPage = 1;
			}
			topicList = topicService.findTopicListByPageNum(currentPage, PAGE_SIZE);
			count = topicService.countAll();
			if (count % PAGE_SIZE == 0) {
				countSize = count / PAGE_SIZE;
			} else {
				countSize = (count + PAGE_SIZE - 1) / PAGE_SIZE;
			}
			request.setAttribute("homeon", "topic");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<Map<String, Object>> getExpertAcceptedCountMap() {
		return expertAcceptedCountMap;
	}

	public List<Topic> getTopicList() {
		return topicList;
	}

	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCount() {
		return count;
	}

	public int getCountSize() {
		return countSize;
	}
	
}
