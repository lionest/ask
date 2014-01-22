package com.ah3nong.wd.action.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.Topic;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.TopicService;
import com.ah3nong.wd.service.UserService;

public class ViewQuestionAction extends GenericActionSupport<Question> {
	private static final long serialVersionUID = -3729646257086353903L;

	private QuestionService questionService;
	private UserService userService;
	private int id;
	private int submitForm;

	// 查询条件
	private int expertId;
	private int status;
	private String content;
	private String domainsStr;

	// 状态MAP
	private Map<String, Integer> statusMap;
	private List<User> expertList;
	private DomainService domainService;
	private TopicService topicService;

	@Transactional(rollbackFor = Exception.class)
	public String execute() {
		String method = "Find question list by page";
		logger.info(method + ",the method begin!");
		// 获得所有分类名
		try {
			logger.info("execute,find all domains for view domain list");
			List<Domain> domains = domainService.findAllDomain();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < domains.size(); i++) {
				Domain d = domains.get(i);
				if (d.getNodePath().length() == 2) {
					sb.append(d.getNodePath());
					sb.append("0000|");
				} else if (d.getNodePath().length() == 4) {
					sb.append(d.getNodePath());
					sb.append("00|");
				} else {
					sb.append(d.getNodePath());
					sb.append("|");
				}
				sb.append(d.getName());
				sb.append(",");
			}
			sb.append("000000|请选择");
			domainsStr = sb.toString();

			logger.info("excute,find " + domains.size() + " domains totally");
		} catch (ServiceException e) {
			logger.error("execute, find all domains exception.", e);
		}
		// 获得所有专题
		List<Topic> allTopics = topicService.findAllTopics();
		request.setAttribute("allTopics", allTopics);

		// 获得问题
		try {
			statusMap = new HashMap<String, Integer>();
			statusMap.put("未解决", Config.getInt("QUESTION_UNRESOLVED"));
			statusMap.put("已解决", Config.getInt("QUESTION_RESOLVED"));
			statusMap.put("已关闭", Config.getInt("QUESTION_CLOSED"));
			// statusMap.put("审核中", Config.getInt("QUESTION_AUDITING"));

			// 从User表中查找专家状态为1的用户列表
			expertList = userService.findExperts(1);
			logger.info("find expertList success!" + expertList);

			Map<String, Object> params = new HashMap<String, Object>();

			if (status != 1299 ) {
				if (status != 0) {
					logger.info("Got the param with status=" + status);
					request.getSession().setAttribute("listquestionstatus", status);
					params.put("status", status);
				} else {
					if(submitForm == 1 ){
						 request.getSession().removeAttribute("listquestionstatus");
					}
					if (submitForm != 1 && request.getSession().getAttribute("listquestionstatus") != null && !"".equals(request.getSession().getAttribute("listquestionstatus"))) {
						status = (Integer) request.getSession().getAttribute("listquestionstatus");
						params.put("status", status);
					}
				}

				if (content != null && !content.equals("")) {
					logger.info("Got the param with content=" + content);
					request.getSession().setAttribute("listquestioncontent", content);
					params.put("content", content);
				} else {
					if(submitForm == 1 ){
						 request.getSession().removeAttribute("listquestioncontent");
					}
					if (submitForm != 1 && request.getSession().getAttribute("listquestioncontent") != null && !"".equals(request.getSession().getAttribute("listquestioncontent"))) {
						content = (String) request.getSession().getAttribute("listquestioncontent");
						params.put("content", content);
					}
				}

				if (expertId != 0) {
					logger.info("Got the param with expertId=" + expertId);
					request.getSession().setAttribute("listquestionexpertId", expertId);
					params.put("expertId", expertId);
				} else {
					if (request.getSession().getAttribute("listquestionexpertId") != null && !"".equals(request.getSession().getAttribute("listquestionexpertId"))) {
						expertId = (Integer) request.getSession().getAttribute("listquestionexpertId");
						params.put("expertId", expertId);
					}
				}
			} else {
				request.getSession().removeAttribute("listquestionstatus");
				request.getSession().removeAttribute("listquestioncontent");
				request.getSession().removeAttribute("listquestionexpertId");
			}
			pager = questionService.getQuestionPagerByParam(params, pageNum, 10);
			logger.info("find question page success!");
		} catch (ServiceException e) {
			logger.error("find question page failed!", e);
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getExpertId() {
		return expertId;
	}

	public void setExpertId(int expertId) {
		this.expertId = expertId;
	}

	public Map<String, Integer> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<String, Integer> statusMap) {
		this.statusMap = statusMap;
	}

	public List<User> getExpertList() {
		return expertList;
	}

	public void setExpertList(List<User> expertList) {
		this.expertList = expertList;
	}

	public DomainService getDomainService() {
		return domainService;
	}

	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public String getDomainsStr() {
		return domainsStr;
	}

	public void setSubmitForm(int submitForm) {
		this.submitForm = submitForm;
	}

}
