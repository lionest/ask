package com.ah3nong.wd.action.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionImg;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.util.DateUtil;

/**
 * 问题分类Action
 */
public class DomainAction extends GenericActionSupport<Question> {

	private static final long serialVersionUID = 1L;
	private static final int PAGE_SIZE = 19; // 问答分类列表页的问题列表默认显示条数
	// 专家被采纳的问题数的排行取前多少条
	private static final int ACCEPTED_EXPERT_COUNT = 8;
	private QuestionService questionService;
	private DomainService domainService;

	private List<Map<String, Object>> mostDomainAndQuestionsNum;
	private List<Map<String, Object>> expertAcceptedCountMap;
	private List<Question> allQuestions;
	private List<Integer> imgQuestions = new ArrayList<Integer>();
	private Map<String, Long> recommendedExpert;
	private int currentPage = 1;
	private int count;
	private int countSize;
	private String terms = "status";
	private int domain;
	private String domainKeyword;
	private List<Domain> domains;
	private List<Domain> titleDomains = new ArrayList<Domain>();
	private int parentId;
	private String homeon;
	private List<Question> experienceQuestions = new ArrayList<Question>();
	private List<Map<String, Object>> imgQuestionList = new ArrayList<Map<String, Object>>();

	public String execute() {
		try {
			/*if(homeon == null || "".equals(homeon) ){*/
				if(domain != 0){
					String firstPath = domainService.findDomainByPrimaryKey(domain).getNodePath().substring(0,2);
					Domain firstDomain = domainService.findDomainByNodePath(firstPath);
					parentId = firstDomain.getParentId();
					if(parentId==-1){
						homeon = "agriculture";
					}else if(parentId == -2){
						homeon = "market";
					}else if(parentId == -3){
						homeon = "policy";
					}else if(parentId == -4){
						homeon = "life";
					}
				}
			/*}*/
			
			int t = 1;
			domains = domainService.findDomainsByNodePath(domain);
			// 分类
			int tmpParentId = 0;

			if (domain != 0) {
				// 获得反向排序的分类
				for (int i = 0; i < domains.size(); i++) {
					int tmp = i + 1;
					titleDomains.add(domains.get(domains.size() - tmp));
				}
				Domain d = domainService.findDomainByPrimaryKey(domain);
				tmpParentId = d.getParentId();
				t = d.hasChild() ? 1 : 0;
				domainKeyword = d.getName();
			}

			if (parentId == 0) {
				if (request.getSession().getAttribute("domainParentId") != null) {
					parentId = (Integer) request.getSession().getAttribute("domainParentId");
				}
			} else {
				request.getSession().setAttribute("domainParentId", parentId);
			}
			Map<String, Object> tmpParams = new HashMap<String, Object>();
			tmpParams.put("parentId", parentId);
			int start = (currentPage - 1) * PAGE_SIZE;
			tmpParams.put("start", start);
			tmpParams.put("size", PAGE_SIZE);
			tmpParams.put("type", terms);

			// 获得问题列表
			if (domain == 0) {
				allQuestions = questionService.findQuestionsAsk(tmpParams);
			} else {
				allQuestions = questionService.getAllQuestionsAndReplyNum(currentPage, PAGE_SIZE, terms, domain, t);
			}

			if (allQuestions.size() > 0) {
				// 改变时间显示样式。并把改后的时间存在extracontent里面
				for (int i = 0; i < allQuestions.size(); i++) {
					String timeStr = allQuestions.get(i).getCreatedTime().toLocaleString();
					DateUtil.getTime(timeStr);
					allQuestions.get(i).setExtraContent(DateUtil.getTime(timeStr));

					// 看是否有图片
					List<QuestionImg> qImgList = questionService.findImgsByQuestionId(allQuestions.get(i).getId(), Config.getInt("QUESTIONIMG_NORMAL"));
					if (qImgList != null && qImgList.size() > 0) {
						imgQuestions.add((Integer) allQuestions.get(i).getId());
					}
				}
			}
			// 获得所有问题的数量
			if (domain == 0) {
				if (parentId != 0) {
					count = questionService.countQuestionsAsk(tmpParams);
				} else {
					count = questionService.countAllQuestion(terms);
				}
			} else {
				count = questionService.countAllByDomainId(terms, domain);
			}
			// 获取推荐专家以及被采纳的问题的数量
			expertAcceptedCountMap = questionService.getExpertAcceptedCountMap(ACCEPTED_EXPERT_COUNT);

			if ((parentId < 0 && domain == 0) || (parentId < 0 && t!=1 && tmpParentId < 0)) {
				mostDomainAndQuestionsNum = questionService.findDomainAndQusetionNumAsk(tmpParams);
			} else {
				mostDomainAndQuestionsNum = questionService.getMostDomainNameAndQuestionNum("", t, domain);
			}

			if (count % PAGE_SIZE == 0) {
				countSize = count / PAGE_SIZE;
			} else {
				countSize = (count + PAGE_SIZE - 1) / PAGE_SIZE;
			}

			// 获得悬赏问题
			experienceQuestions = questionService.getAllQuestionsAndReplyNum(1, ACCEPTED_EXPERT_COUNT, "experience", 0, 0);

			// 获得图片问题列表
			Map<String,Object> imgParams = new HashMap<String,Object>();
			imgParams.put("parentId", parentId);
			imgParams.put("start", 0);
			imgParams.put("size",5);
			imgQuestionList = questionService.findImgQuestionsAsk(imgParams);

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public int getCountSize() {
		return countSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public static int getPAGE_SIZE() {
		return PAGE_SIZE;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Map<String, Object>> getMostDomainAndQuestionsNum() {
		return mostDomainAndQuestionsNum;
	}

	public List<Question> getAllQuestions() {
		return allQuestions;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public Map<String, Long> getRecommendedExpert() {
		return recommendedExpert;
	}

	public List<Map<String, Object>> getExpertAcceptedCountMap() {
		return expertAcceptedCountMap;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public void setDomain(int domain) {
		this.domain = domain;
	}

	public int getDomain() {
		return domain;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public String getDomainKeyword() {
		return domainKeyword;
	}

	public List<Domain> getDomains() {
		return domains;
	}

	public void setDomains(List<Domain> domains) {
		this.domains = domains;
	}

	public List<Domain> getTitleDomains() {
		return titleDomains;
	}

	public List<Integer> getImgQuestions() {
		return imgQuestions;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getHomeon() {
		return homeon;
	}

	public void setHomeon(String homeon) {
		this.homeon = homeon;
	}

	public List<Question> getExperienceQuestions() {
		return experienceQuestions;
	}

	public List<Map<String, Object>> getImgQuestionList() {
		return imgQuestionList;
	}

}
