package com.ah3nong.wd.action.freemaker;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.bean.Expert;
import com.ah3nong.wd.bean.Link;
import com.ah3nong.wd.bean.Notice;
import com.ah3nong.wd.bean.Probing;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionImg;
import com.ah3nong.wd.bean.QuestionKeyword;
import com.ah3nong.wd.bean.QuestionScore;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.security.SecurityContext;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.LinkService;
import com.ah3nong.wd.service.NoticeService;
import com.ah3nong.wd.service.ProbingService;
import com.ah3nong.wd.service.QuestionKeywordService;
import com.ah3nong.wd.service.QuestionScoreService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.ReplyCommentService;
import com.ah3nong.wd.service.ReplyService;
import com.ah3nong.wd.util.FreeMarkerUtil;

import freemarker.ext.jsp.TaglibFactory;

public class DetailQuestionFmAction extends GenericActionSupport<Question> {
	private static final long serialVersionUID = -3077961927550745101L;
	private QuestionService questionService;
	private ReplyService replyService;
	private ProbingService probingService;
	private DomainService domainService;
	private QuestionScoreService questionScoreService;
	private QuestionKeywordService questionKeywordService;
	private ReplyCommentService replyCommentService;
	private NoticeService noticeService;
	private LinkService linkService;
	private static final int MOST_VIEWCOUNT_QUESTION_COUNT = 8;
	private int questionId;
	public String execute() {
		try {
			Map<String, Object> root = new HashMap<String, Object>();
			String bathPath = request.getContextPath();

			questionId = Integer.parseInt(request.getParameter("questionId"));
			Question question = questionService.findQuestionByPrimaryKey(questionId);
			if (question == null) {
				return ERROR;
			}
			// 把question的id存到session用于登陆跳转
			request.getSession().setAttribute("LoginUrlQuestionId", questionId);
			// 获得该问题关键字
			List<QuestionKeyword> qkList = questionKeywordService.findKeywordsByQuestionId(questionId);
			// 相关问题
			String questionKeywords = null;
			if (qkList != null&&qkList.size()>0) {
				StringBuilder sb01 = new StringBuilder();
				for (int i = 0; i < qkList.size(); i++) {
					sb01.append(qkList.get(i).getKeyword());
					sb01.append(",");
					// 取相关问题
				}
				questionKeywords = sb01.toString();
				questionKeywords = questionKeywords.substring(0, questionKeywords.length() - 1);
			}
			// 相关问题
			int t = question.getDomain().hasChild() ? 1 : 0;
			List<Question> relevantQuestions = questionService.getAllQuestionsAndReplyNum(1, 9, "status", question.getDomain().getId(), t);

			// 判断相关问题是否为新问题
			Date now = new Date();
			Question tmpRemove = null;
			List<Integer> newQuestions = new ArrayList<Integer>();
			if (relevantQuestions.size() > 0) {
				for (Question questionr : relevantQuestions) {
					if (questionr.getId() != questionId) {
						if (questionr.getCreatedTime() != null) {
							if (now.getTime() - questionr.getCreatedTime().getTime() < (1000 * 60 * 60 * 6)) {
								newQuestions.add((Integer) question.getId());
							}
						}
					} else {
						tmpRemove = question;
					}
				}
			}
			if (tmpRemove != null) {
				relevantQuestions.remove(tmpRemove);
			} else {
				relevantQuestions.remove(relevantQuestions.size() - 1);
			}
			// 从容器获取user
			User user = SecurityContext.getUser();

			String method = "show details of question";
			logger.info(method + ", the method is begin with questionId=" + questionId);

			Question questionViewCount = questionService.findQuestionByPrimaryKey(questionId);
			questionViewCount.setViewCount(questionViewCount.getViewCount() + 1);
			questionService.updateQuestionByPrimaryKey(questionViewCount, true);

			Expert expert = questionService.findExpertByQuestionId((Integer) questionId);
			List<QuestionImg> qImgList = questionService.findImgsByQuestionId(questionId, Config.getInt("QUESTIONIMG_NORMAL"));

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("questionId", questionId);
			map.put("ExcludeStatus", Config.getInt("REPLY_REMOVE"));
			List<Reply> replyList = replyService.findReplyByQuestionId(map);
			for (Reply replytmp : replyList) {
				replytmp.setCommentNum(replyCommentService.findCommentNumByReplyId(replytmp.getId()));
			}
			map.put("status2", Config.getInt("PROBING_REMOVE"));
			List<Probing> probingList = probingService.findProbingByQuestionId(map);

			List<Domain> domainList = domainService.findDomainsByNodePath(question.getDomain().getId());
			List<Domain> titleDomains = new ArrayList<Domain>();
			if (domainList.size() > 0) {
				// 获得反向排序的分类
				for (int i = 0; i < domainList.size(); i++) {
					int tmp = i + 1;
					titleDomains.add(domainList.get(domainList.size() - tmp));
				}
			}
			String domainKeyWord = domainList.get(0).getName();
			int userType = 0;
			if (user != null && question.getStatus() != 99) {
				if (user.getId().equals(question.getUser().getId())) {
					// 如果登录用户的ID与提问者ID相同，则该用户为提问�?
					userType = Config.getInt("ASKER");
				}
				// 查找回复者中有没有用户ID
				for (Reply reply : replyList) {
					if (user.getId().equals(reply.getUser().getId())) {
						// 如果在回复列表中找到登录用户ID，则用户�?查看问题且有过回答�?
						userType = Config.getInt("CHECK_REPLIED");
						break;
					}
				}
				if (userType != Config.getInt("ASKER") && userType != Config.getInt("CHECK_REPLIED")) {
					userType = Config.getInt("CHECK_UNREPLIED");
				}
			} else {
				userType = Config.getInt("VISITOR");
			}
			// 获得点击率最多问题
			List<Question> mostViewCountQuestions = questionService.findMostViewCountQuestions(MOST_VIEWCOUNT_QUESTION_COUNT);
			// 获得该问题的分数
			QuestionScore questionScore = questionScoreService.findQuestionScoreByQuestionId(questionId);
			int score = 0;
			String scoreContent;
			if (questionScore == null) {
				score = 0;
				int tmp = Config.getString("QUESTION_SCORE_4").indexOf("|");
				scoreContent = Config.getString("QUESTION_SCORE_4").substring(0, tmp);
			} else {
				score = questionScore.getScore();
				String s = "QUESTION_SCORE_" + score;
				int tmp = Config.getString(s).indexOf("|");
				scoreContent = Config.getString(s).substring(0, tmp);
			}

			StringBuilder sb = new StringBuilder();
			// 获得每个星级评分的描述
			sb.append(Config.getString("QUESTION_SCORE_1"));
			sb.append("_");
			sb.append(Config.getString("QUESTION_SCORE_2"));
			sb.append("_");
			sb.append(Config.getString("QUESTION_SCORE_3"));
			sb.append("_");
			sb.append(Config.getString("QUESTION_SCORE_4"));
			String questionStarDescribeStr = sb.toString();

			// 消除用户该问题的新提醒
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
			// 已解决问题
			Map<String, Object> params4 = new HashMap<String, Object>();
			params4.put("count", 8);
			params4.put("status", 2);
			params4.put("orderBy", "solved_time desc");
			List<Question> latestSolvedQuestions = questionService.findQuestion(params4);
			// 未解决
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("count", 8);
			params.put("status", 1);
			params.put("orderBy", "created_time desc");
			List<Question> latestQuestions = questionService.findQuestion(params);
			// 友链
			List<Link> linkList = linkService.findLinksByStatus(1);
			// 存入参数
			root.put("bathPath", bathPath);
			root.put("titleDomains", titleDomains);
			root.put("domainList", domainList);
			root.put("probingList", probingList);
			root.put("question", question);
			root.put("qkList", qkList);
			root.put("questionKeywords", questionKeywords);
			root.put("relevantQuestions", relevantQuestions);
			root.put("newQuestions", newQuestions);
			root.put("expert", expert);
			root.put("score", score);
			root.put("qImgList", qImgList);
			root.put("replyList", replyList);
			root.put("userType", userType);
			root.put("mostViewCountQuestions", mostViewCountQuestions);
			root.put("questionScore", questionScore);
			root.put("scoreContent", scoreContent);
			root.put("questionStarDescribeStr", questionStarDescribeStr);
			root.put("latestSolvedQuestions", latestSolvedQuestions);
			root.put("latestQuestions", latestQuestions);
			root.put("linkList", linkList);
			root.put("domainKeyWord", domainKeyWord);
			
			root.put("request", ServletActionContext.getRequest());
			root.put("JspTaglibs", new TaglibFactory(request.getSession()  
	                .getServletContext()));  
			String htmlStr = questionId+".html";
			FreeMarkerUtil.createHtml(ServletActionContext.getServletContext(), root, "/ftl", "/html/question", "view.ftl", htmlStr);

		} catch (ServiceException e) {
			logger.error("method failed!", e);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	public void setProbingService(ProbingService probingService) {
		this.probingService = probingService;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public void setQuestionScoreService(QuestionScoreService questionScoreService) {
		this.questionScoreService = questionScoreService;
	}

	public void setQuestionKeywordService(QuestionKeywordService questionKeywordService) {
		this.questionKeywordService = questionKeywordService;
	}

	public void setReplyCommentService(ReplyCommentService replyCommentService) {
		this.replyCommentService = replyCommentService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}

	public int getQuestionId() {
		return questionId;
	}

}
