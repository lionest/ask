package com.ah3nong.wd.action.topic;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Topic;
import com.ah3nong.wd.service.TopicService;

public class DeleteTopicQuestionAction extends GenericActionSupport<Topic> {
	private static final long serialVersionUID = -6647127753924304146L;
	private String questionId;
	
	private TopicService topicService;
	public String execute(){
		int tmpid = (Integer)request.getSession().getAttribute("currentTopicId");
		Topic t = topicService.findByPrimaryKey(tmpid);
		String[] qids = t.getQuestionIds().split("_");
		for(int i=0;i<qids.length;i++){
			if(qids[i].equals(questionId)){
				qids[i] = null;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int j=0;j<qids.length;j++){
			if(qids[j]!=null){
				sb.append(qids[j]);
				sb.append("_");
			}
		}
		String tmp = sb.toString();
		if(tmp.length()>0){
			t.setQuestionIds(tmp.substring(0,tmp.length()-1));
		}else{
			t.setQuestionIds("");
		}
		topicService.updateTopicById(t);
		return SUCCESS;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
}
