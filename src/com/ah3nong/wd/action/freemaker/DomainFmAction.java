package com.ah3nong.wd.action.freemaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.bean.Link;
import com.ah3nong.wd.bean.Notice;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.LinkService;
import com.ah3nong.wd.service.NoticeService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.util.DateUtil;
import com.ah3nong.wd.util.FreeMarkerUtil;

public class DomainFmAction extends GenericActionSupport<Question> {
	private static final long serialVersionUID = 6372437075636171364L;
	private int PAGE_SIZE = 12;
	private DomainService domainService;
	private QuestionService questionService;
	private NoticeService noticeService;
	private LinkService linkService;
	int domain = 0;
	public String execute() {
		try {
			Map<String, Object> root = new HashMap<String, Object>();
			String bathPath = request.getContextPath();
			
			if (request.getParameter("domain") != null) {
				domain = Integer.parseInt(request.getParameter("domain"));
			}
			String terms = request.getParameter("terms");
			if (terms == null || "".equals(terms)) {
				terms = "status";
			}
			String homeon = "domain";
			int currentPage = 1;
			if (request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			int t1 = 1;
			List<Domain> domains = domainService.findDomainsByNodePath(domain);
			List<Domain> titleDomains = new ArrayList<Domain>();
			if (domain != 0) {
				// 获得反向排序的分类
				for (int i = 0; i < domains.size(); i++) {
					int tmp = i + 1;
					titleDomains.add(domains.get(domains.size() - tmp));
				}
				Domain d = domainService.findDomainByPrimaryKey(domain);
				t1 = d.hasChild() ? 1 : 0;
			}
			// 获得全部的问题以及问题的回答数、问题的领域、问题的添加时间
			List<Question> allQuestions = questionService.getAllQuestionsAndReplyNum(currentPage, PAGE_SIZE, terms, domain, t1);
			// 改变时间显示样式。并把改后的时间存在extracontent里面
			if (allQuestions.size() > 0) {
				for (int i = 0; i < allQuestions.size(); i++) {
					String timeStr = allQuestions.get(i).getCreatedTime().toLocaleString();
					DateUtil.getTime(timeStr);
					allQuestions.get(i).setExtraContent(DateUtil.getTime(timeStr));
				}
			}

			// 获得所有问题的数量
			int count = 0;
			if (domain == 0) {
				count = questionService.countAllQuestion(terms);
			} else {
				count = questionService.countAllByDomainId(terms, domain);
			}

			// 获得一级领域以及该领域下的所有问题的数量
			List<Map<String, Object>> mostDomainAndQuestionsNum = new ArrayList<Map<String, Object>>();
			if (domain == 0) {
				mostDomainAndQuestionsNum = questionService.getMostDomainNameAndQuestionNumAll("", t1, domain);
			} else {
				mostDomainAndQuestionsNum = questionService.getMostDomainNameAndQuestionNum("", t1, domain);
			}
			int countSize = 0;
			if (count % PAGE_SIZE == 0) {
				countSize = count / PAGE_SIZE;
			} else {
				countSize = (count + PAGE_SIZE - 1) / PAGE_SIZE;
			}
			// 已解决问题
			Map<String, Object> params4 = new HashMap<String, Object>();
			params4.put("count", 8);
			params4.put("status", 2);
			params4.put("orderBy", "solved_time desc");
			List<Question> latestSolvedQuestions = questionService.findQuestion(params4);

			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {
				root.put("user", user);
				// 获得notice
				Notice notice = new Notice();
				notice.setUserId(user.getId());
				// 回复提醒数
				notice.setType(Config.getInt("NOTICE_REPLY"));
				int noticeReplyCount = noticeService.countByUserIdAndType(notice);
				// 采纳数
				notice.setType(Config.getInt("NOTICE_ACCEPT"));
				int noticeAcceptCount = noticeService.countByUserIdAndType(notice);
				// 评论数
				notice.setType(Config.getInt("NOTICE_COMMENT"));
				int noticeCommentCount = noticeService.countByUserIdAndType(notice);
				// 追问数
				notice.setType(Config.getInt("NOTICE_PROBING"));
				int noticeProbingCount = noticeService.countByUserIdAndType(notice);
				root.put("noticeReplyCount", noticeReplyCount);
				root.put("noticeAcceptCount", noticeAcceptCount);
				root.put("noticeCommentCount", noticeCommentCount);
				root.put("noticeProbingCount", noticeProbingCount);
			}
			// 获取推荐专家以及被采纳的问题的数量
			List<Map<String, Object>> expertAcceptedCountMap = questionService.getUserAcceptCountMap(8);

			// 友链
			List<Link> linkList = linkService.findLinksByStatus(1);

			// 存入参数
			root.put("bathPath", bathPath);
			root.put("titleDomains", titleDomains);
			root.put("domain", domain);
			root.put("mostDomainAndQuestionsNum", mostDomainAndQuestionsNum);
			root.put("allQuestions", allQuestions);
			root.put("count", count);
			root.put("currentPage", currentPage);
			root.put("countSize", countSize);
			root.put("terms", terms);
			root.put("homeon", homeon);
			root.put("domains", domains);
			root.put("latestSolvedQuestions", latestSolvedQuestions);
			root.put("expertAcceptedCountMap", expertAcceptedCountMap);
			root.put("linkList", linkList);

			root.put("request", ServletActionContext.getRequest());
			
			String htmlStr = domain+".html";
			FreeMarkerUtil.createHtml(ServletActionContext.getServletContext(), root, "/ftl", "/html/domain", "domain.ftl", htmlStr);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}

	public int getDomain() {
		return domain;
	}
	
}
