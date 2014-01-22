package com.ah3nong.wd.action.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.bean.Topic;
import com.ah3nong.wd.service.ExpertService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.TopicService;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final int PAGE_SIZE = 9; // 问答分类列表页的问题列表默认显示条数
	
	private QuestionService questionService;
	private ExpertService expertService;
	private TopicService topicService;
	private List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
	private String t ; //关键词
	private int currentPage = 1;
	private int count;
	private int countSize;
	private List<Map<String,Object>> expertAcceptedCountMap;
	private static final int ACCEPTED_EXPERT_COUNT = 8;
	private int status;
	private long time ;
	//标记搜索专家还是问题还是专题
	private String searchFor;
	//搜索专家列表
	private List<Map<String, Object>> expertsList;
	//搜索知识专题列表
	private List<Topic> topicList = new ArrayList<Topic>();
	
	@Transactional(rollbackFor = Exception.class)
	public String execute() {
		try {
			//搜索开始时间
			long startTime = System.currentTimeMillis();
			
			//从第几条开始搜索
			int start=0;
			if(currentPage==0){
				start=0;
			}else{
				start=(currentPage-1)*PAGE_SIZE;
			}
			
			//判断t的编码类型 如果是utf-8类型则不变，否则转为utf-8
			boolean flag = java.nio.charset.Charset.forName("iso-8859-1").newEncoder().canEncode(t);
			if (flag) {  
				String newT = new String(t.getBytes("iso-8859-1"),"utf-8");
				t = newT;
			}
			
			//如果是搜专家
			if("expert".equals(searchFor)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("start", start);
				map.put("fullname", t);
				map.put("size", PAGE_SIZE);
				expertsList = expertService.findExpertsByFullname(map);
				count = expertService.countExpertsByFullname(map);
				
			//如果是搜知识专题
			}else if("topic".equals(searchFor)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("start", start);
				map.put("title", t);
				map.put("size", PAGE_SIZE);
				topicList = topicService.findTopicsByTitle(map);
				count = topicService.countTopicsByTitle(map);
				
			//否则：即搜问题
			}else{
				//根绝条件搜索相关的问题
				questions = questionService.searchByTerm(t,start,PAGE_SIZE,status);
				//获得问题的数量
				count = new Long(questionService.CountQuestionsSearchbyTerms(t,status))
				.intValue();
			}

			if (count % PAGE_SIZE == 0) {
				countSize = count / PAGE_SIZE;
			} else {
				countSize = (count + PAGE_SIZE - 1) / PAGE_SIZE;
			}
			
			expertAcceptedCountMap = questionService.getExpertAcceptedCountMap(ACCEPTED_EXPERT_COUNT);
			
			//搜索结束时间
			long endTime = System.currentTimeMillis();
			time = endTime - startTime;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if("expert".equals(searchFor)){
			return "expert";
		}else if("topic".equals(searchFor)){
			return "topic";
		}else{
			return SUCCESS;
		}
	}
	
	public String getNext() {
		try {
			long startTime = System.currentTimeMillis();  
			//根绝条件搜索相关的问题
			int start=0;
			if(currentPage==0){
				start=0;
			}else{
				start=(currentPage-1)*PAGE_SIZE;
			}
			String newT = new String(t.getBytes("iso-8859-1"),"utf-8");
			t = newT;
			questions = questionService.searchByTerm(newT,start,PAGE_SIZE,status);
			
			expertAcceptedCountMap = questionService.getExpertAcceptedCountMap(ACCEPTED_EXPERT_COUNT);
			//获得问题的数量
			count = new Long(questionService.CountQuestionsSearchbyTerms(t,status))
					.intValue();

			if (count % PAGE_SIZE == 0) {
				countSize = count / PAGE_SIZE;
			} else {
				countSize = (count + PAGE_SIZE - 1) / PAGE_SIZE;
			}
			long endTime = System.currentTimeMillis();
			time = endTime - startTime;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public List<Map<String, Object>> getQuestions() {
		return questions;
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

	public List<Map<String, Object>> getExpertAcceptedCountMap() {
		return expertAcceptedCountMap;
	}

	public void setExpertAcceptedCountMap(List<Map<String, Object>> expertAcceptedCountMap) {
		this.expertAcceptedCountMap = expertAcceptedCountMap;
	}
	
	public long getTime() {
		return time;
	}
	
	public void setSearchFor(String searchFor) {
		this.searchFor = searchFor;
	}

	public void setExpertService(ExpertService expertService) {
		this.expertService = expertService;
	}

	public List<Map<String, Object>> getExpertsList() {
		return expertsList;
	}

	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	public List<Topic> getTopicList() {
		return topicList;
	}
	
}
