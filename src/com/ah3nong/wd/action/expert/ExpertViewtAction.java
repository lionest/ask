package com.ah3nong.wd.action.expert;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.ExpertService;
import com.ah3nong.wd.service.QuestionService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 专家信息页面
 */
public class ExpertViewtAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final int ACCEPTED_EXPERT_COUNT = 8;
	private static final int PAGE_SIZE = 16; // 问答分类列表页的问题列表默认显示条数

	private QuestionService questionService;
	private ExpertService expertService;
	
	private List<Map<String, Object>> expertAcceptedCountMap;
	private List<Map<String, Object>> expert;
	private List<Map<String,Object>> getQuestionsBuExpertId;
 	private int expertId;
	private int count;
	private int countSize;
	private int currentPage = 1;
	private String s=null;
  
	public String execute(){
		try {
			// 获取推荐专家以及被采纳的问题的数量
			expertAcceptedCountMap = questionService
					.getExpertAcceptedCountMap(ACCEPTED_EXPERT_COUNT);
			
			//根据专家ID获得该专家的所有信息
			expert = expertService.getExpertsByExpertId(expertId);
			//查询时的开始条数  （因为service里面有 -1 所以在这 +1）
			int start = (currentPage-1)*PAGE_SIZE + 1 ;
			
			if(s!=null&&!"".equals(s)&&s.equals("a")){
				//根据专家的ID获得该专家下面的所有问题
				getQuestionsBuExpertId = questionService.getQuestionsAskByExpert(expertId, start, PAGE_SIZE);
				//根据专家的Id获得该专家下的所有问题的总数
				count = new Long(questionService.getCountQuestionsAskByExpert(expertId)).intValue();
			}else{
				//根据专家的ID获得该专家下面的所有问题
				getQuestionsBuExpertId = questionService.getQuestionsBuExpertId(expertId, start, PAGE_SIZE);
				//根据专家的Id获得该专家下的所有问题的总数
				count = new Long(questionService.getCountQuestionsByExpertId(expertId)).intValue();				
			}
			if (count % PAGE_SIZE == 0) {
				countSize = count / PAGE_SIZE;
			} else {
				countSize = (count + PAGE_SIZE - 1) / PAGE_SIZE;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}


	public List<Map<String, Object>> getExpertAcceptedCountMap() {
		return expertAcceptedCountMap;
	}


	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}


	public void setExpertService(ExpertService expertService) {
		this.expertService = expertService;
	}


	public int getExpertId() {
		return expertId;
	}


	public void setExpertId(int expertId) {
		this.expertId = expertId;
	}


	public List<Map<String, Object>> getExpert() {
		return expert;
	}

	public void setExpert(List<Map<String, Object>> expert) {
		this.expert = expert;
	}
	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public List<Map<String, Object>> getGetQuestionsBuExpertId() {
		return getQuestionsBuExpertId;
	}


	public int getCount() {
		return count;
	}


	public int getCountSize() {
		return countSize;
	}


	public void setGetQuestionsBuExpertId(
			List<Map<String, Object>> getQuestionsBuExpertId) {
		this.getQuestionsBuExpertId = getQuestionsBuExpertId;
	}


	public String getS() {
		return s;
	}


	public void setS(String s) {
		this.s = s;
	}
	
}
