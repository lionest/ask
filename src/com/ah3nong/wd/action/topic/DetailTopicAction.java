package com.ah3nong.wd.action.topic;

import java.util.ArrayList;
import java.util.List;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.Topic;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.TopicService;

public class DetailTopicAction extends GenericActionSupport<Topic> {
	private static final long serialVersionUID = 2660704173695232753L;
	private TopicService topicService;
	private QuestionService questionService;
	private int id;
	private List<Question> topicQuestions = new ArrayList<Question>();
	private Topic topic;
	private List<Topic> topicList = new ArrayList<Topic>();

	public String execute() {
		try {
			if(id!=0){
				topic = topicService.findByPrimaryKey(id);
				request.getSession().setAttribute("currentTopicId", id);
			}else{
				int tmpid = (Integer)request.getSession().getAttribute("currentTopicId");
				topic = topicService.findByPrimaryKey(tmpid);
			}
			String qids = topic.getQuestionIds();
			if (qids != null && !"".equals(qids)) {
				String[] ids = qids.split("_");
				for (int i = 0; i < ids.length; i++) {
					Question q = questionService.findQuestionByPrimaryKey(Integer.parseInt(ids[i]));
					topicQuestions.add(q);
				}
			}
			//获得最新4条专题
			topicList = topicService.findTopicListByPageNum(1, 4);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<Question> getTopicQuestions() {
		return topicQuestions;
	}

	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Topic getTopic() {
		return topic;
	}

	public List<Topic> getTopicList() {
		return topicList;
	}
	
}
