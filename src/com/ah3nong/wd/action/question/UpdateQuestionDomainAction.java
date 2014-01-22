package com.ah3nong.wd.action.question;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.Topic;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.TopicService;

public class UpdateQuestionDomainAction extends GenericActionSupport<Question> {
	private static final long serialVersionUID = -626609192773656757L;
	private int topicId;
	private String domainNodepath;
	private int[] entityIds;
	private QuestionService questionService;
	private DomainService domainService;
	private TopicService topicService;
	public String execute(){
		String method = "update the question domain Action!";
		logger.info(method+",the method begin!");
		try {
			String nodePath="";
			if(domainNodepath.substring(2,6).equals("0000")){
				nodePath=domainNodepath.substring(0,2);
			}else if(domainNodepath.substring(4,6).equals("00")){
				nodePath=domainNodepath.substring(0,4);
			}else{
				nodePath=domainNodepath;
			}
			int domainId = domainService.findDomainByNodePath(nodePath).getId();
			
			if(entityIds!=null){
				for(int i=0;i<entityIds.length;i++){
					Question q = new Question();
					q.setId(entityIds[i]);
					q.setDomainId(domainId);
					questionService.updateQuestionByPrimaryKey(q,true);
				}
			}
		} catch (ServiceException e) {
			logger.info("update question domain failed!"+e);
			e.printStackTrace();
		}
		request.getSession().setAttribute("info","操作成功...");
		return SUCCESS;
	}
	
	public String addQuestionToTopic(){
		Set<String> set = new HashSet<String>();
		Topic topic = topicService.findByPrimaryKey(topicId);
		//以前已经存在在专题里的问题id
		String topicQuestionIds = topic.getQuestionIds();
		if(topicQuestionIds!=null&&!"".equals(topicQuestionIds)){
			String[] ids = topic.getQuestionIds().split("_");
			if(ids.length > 0){
				for(int m = 0;m < ids.length;m ++){
					set.add(ids[m]);
				}
			}
		}
		if(entityIds.length>0){
			for(int n = 0;n < entityIds.length; n++){
				set.add(entityIds[n]+"");
			}
		}
		//如果以前的问题加新选的问题，总条数多于六条就返回input
		if(set.size()>6){
			request.getSession().setAttribute("info","添加失败，请将专题问题数控制在6个以内...");
			return INPUT;
		//否则就把新增的问题加到topic的questionIds里面去
		}else{
			StringBuilder sb = new StringBuilder();
		    Iterator<String> iterator = set.iterator();
		    while(iterator.hasNext())
		    {
		       sb.append("_");
		       sb.append(iterator.next());
		    } 
		    topic.setQuestionIds(sb.substring(1,sb.length()));
			
			topicService.updateTopicById(topic);
			request.getSession().setAttribute("info","添加成功");
			return SUCCESS;
		}
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public void setEntityIds(int[] entityIds) {
		this.entityIds = entityIds;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	public void setDomainNodepath(String domainNodepath) {
		this.domainNodepath = domainNodepath;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}
	
}
