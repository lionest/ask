package com.ah3nong.wd.action.expert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.ExpertService;
import com.ah3nong.wd.service.QuestionService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 专家目录页面
 */
public class ExpertClassifyAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final int ACCEPTED_EXPERT_COUNT = 8;
	private static final int MOST_VIEWCOUNT_QUESTION_COUNT = 8;
	private static final int PAGE_SIZE = 5; // 问答分类列表页的问题列表默认显示条数

	private DomainService domainService;
	private QuestionService questionService;
	private ExpertService expertService;
	private List<Map<String, Object>> domainAndExperts = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> expertAcceptedCountMap;
	private List<Map<String, Object>> expertReplyNumMap;
	private List<Map<String, Object>> AllExpertsAndReplyNumAndIsAccepteds;
	private int domain;
	private String domainName;
	private int count;
	private int countSize;
	private int currentPage;
	private List<Question> mostViewCountQuestions = new ArrayList<Question>();
	private List<Question> experienceQuestions = new ArrayList<Question>();

	public String execute() {

		try {

			// 获得一级领域以及该领域下所有的专家的数量
			domainAndExperts = domainService.getDomainAndAllExperts();

			// 获得所有的专家列表
			AllExpertsAndReplyNumAndIsAccepteds = expertService
					.getAllExpertsAndReplyNumAndIsAccepteds(currentPage*PAGE_SIZE,
							PAGE_SIZE, domain);
			
			// 获取推荐专家-按采纳数排序
			expertAcceptedCountMap = expertService.getAllExpertsAndReplyNumAndIsAccepteds(0,ACCEPTED_EXPERT_COUNT, 0);
			
			//按回答数排序专家
			expertReplyNumMap = expertService.getAllExpertsAndReplyNumc(0,ACCEPTED_EXPERT_COUNT, 0);
			
			if (domain == 0) {
				//获得所有专家的数量
				count = expertService.countAllExpert();

			} else {
				//根据一级领域的ID获得该领域下面所有专家的数量
				count = new Long(expertService.countAllExpertByDomainId(domain))
						.intValue();
			}

			if (count % PAGE_SIZE == 0) {
				countSize = count / PAGE_SIZE;
			} else {
				countSize = (count + PAGE_SIZE - 1) / PAGE_SIZE;
			}
			
			if(domain==0){
				domainName = "所有专家";
			}else {
				domainName = domainService.findDomainByPrimaryKey(domain).getName();
			}
			
			//获得点击率最多问题
			mostViewCountQuestions = questionService.findMostViewCountQuestions(MOST_VIEWCOUNT_QUESTION_COUNT);
			//获得悬赏问题
			experienceQuestions = questionService.getAllQuestionsAndReplyNum(1, MOST_VIEWCOUNT_QUESTION_COUNT,"experience",0,0);
		} catch (ServiceException e) {
			e.printStackTrace();
			return ERROR;
		}

		return SUCCESS;
	}

	public List<Map<String, Object>> getDomainAndExperts() {
		return domainAndExperts;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public int getDomain() {
		return domain;
	}

	public void setDomain(int domain) {
		this.domain = domain;
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

	public List<Map<String, Object>> getAllExpertsAndReplyNumAndIsAccepteds() {
		return AllExpertsAndReplyNumAndIsAccepteds;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCountSize() {
		return countSize;
	}

	public void setCountSize(int countSize) {
		this.countSize = countSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public List<Question> getMostViewCountQuestions() {
		return mostViewCountQuestions;
	}

	public List<Question> getExperienceQuestions() {
		return experienceQuestions;
	}

	public List<Map<String, Object>> getExpertReplyNumMap() {
		return expertReplyNumMap;
	}
	
}
