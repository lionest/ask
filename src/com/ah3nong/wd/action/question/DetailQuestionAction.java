package com.ah3nong.wd.action.question;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.bean.Expert;
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
import com.ah3nong.wd.service.NoticeService;
import com.ah3nong.wd.service.ProbingService;
import com.ah3nong.wd.service.QuestionKeywordService;
import com.ah3nong.wd.service.QuestionScoreService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.ReplyCommentService;
import com.ah3nong.wd.service.ReplyService;

public class DetailQuestionAction extends GenericActionSupport<Question> {

	private static final long serialVersionUID = 8287527356649789271L;

	private int questionId;
	private QuestionService questionService;
	private ReplyService replyService;
	private ProbingService probingService;
	private DomainService domainService;
	private QuestionScoreService questionScoreService;
	private QuestionKeywordService questionKeywordService;
	private ReplyCommentService replyCommentService;
	private NoticeService noticeService;

	private Question question;
	private Expert expert;
	private User user;
	private int userType;
	private String domainKeyWord;
	private String questionStarDescribeStr;
	private Reply auditReply;
	private List<Probing> auditProbingList;

	// 专家被采纳的问题数的排行取前多少?
	private static final int ACCEPTED_EXPERT_COUNT = 8;
	
	private static final int MOST_VIEWCOUNT_QUESTION_COUNT = 8;
	private List<QuestionImg> qImgList;
	private List<Reply> replyList;
	private List<Probing> probingList;
	private List<Domain> domainList;
	private List<Question> relevantQuestions = new ArrayList<Question>();
	private List<Question> mostViewCountQuestions = new ArrayList<Question>();
	private List<Question> experienceQuestions = new ArrayList<Question>();
	private int score;
	private String scoreContent;
	private String questionKeywords;
	private List<QuestionKeyword> qkList;
	private List<Domain> titleDomains = new ArrayList<Domain>();
	private List<Integer> newQuestions = new ArrayList<Integer>();
	private String homeon;

	private List<Map<String, Object>> expertAcceptedCountMap;

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	@Transactional(rollbackFor = ServiceException.class)
	public String execute() {
		try {
			question = questionService.findQuestionByPrimaryKey(questionId);
			if(question==null){
				return ERROR;
			}
			if(question.getAnonymous()==1){
				question.getUser().setNickname("匿名网友");
			}
			// 把question的id存到session用于登陆跳转
			request.getSession().setAttribute("LoginUrlQuestionId", questionId);
			// 获得该问题关键字
			qkList = questionKeywordService.findKeywordsByQuestionId(questionId);
			// 相关问题
			if (qkList.size() > 0) {
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
			relevantQuestions = questionService.getAllQuestionsAndReplyNum(1, 9, "status", question.getDomain().getId(), t);
			
			//判断相关问题是否为新问题
			Date now = new Date();
			Question tmpRemove = null ;
			if(relevantQuestions.size()>0){
				for(Question question:relevantQuestions){
					if(question.getId()!=questionId){
						if(question.getCreatedTime()!=null){
							if(now.getTime()-question.getCreatedTime().getTime()<(1000*60*60*6)){
								newQuestions.add((Integer)question.getId());
							}
						}
					}else{
						tmpRemove = question;
					}
				}
			}
			if(tmpRemove!=null){
				relevantQuestions.remove(tmpRemove);
			}else if(relevantQuestions.size() > 8){
				relevantQuestions.remove(relevantQuestions.size()-1);
			}
			// 从容器获取user
			user = SecurityContext.getUser();

			String method = "show details of question";
			logger.info(method + ", the method is begin with questionId=" + questionId);

			Question questionViewCount = questionService.findQuestionByPrimaryKey(questionId);
			questionViewCount.setViewCount(questionViewCount.getViewCount() + 1);
			questionService.updateQuestionByPrimaryKey(questionViewCount, true);

			expert = questionService.findExpertByQuestionId((Integer) questionId);
			qImgList = questionService.findImgsByQuestionId(questionId, Config.getInt("QUESTIONIMG_NORMAL"));
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("questionId", questionId);
			map.put("ExcludeStatus", Config.getInt("REPLY_REMOVE"));
			replyList = replyService.findReplyByQuestionId(map);
			for (Reply replytmp : replyList) {
				replytmp.setCommentNum(replyCommentService.findCommentNumByReplyId(replytmp.getId()));
			}
			map.put("status2", Config.getInt("PROBING_REMOVE"));
			probingList = probingService.findProbingByQuestionId(map);
			//如果为匿名问题，把所有追问名字改为“匿名网友”
			
			expertAcceptedCountMap = questionService.getExpertAcceptedCountMap(ACCEPTED_EXPERT_COUNT);

			domainList = domainService.findDomainsByNodePath(question.getDomain().getId());

			if (domainList.size() > 0) {
				// 获得反向排序的分类
				for (int i = 0; i < domainList.size(); i++) {
					int tmp = i + 1;
					titleDomains.add(domainList.get(domainList.size() - tmp));
				}
				//获得问题属于哪个大类
				int pId = domainList.get(0).getParentId();
				if(pId == -2){
					homeon = "market" ;
				}else if (pId == -3){
					homeon = "policy" ;
				}else if (pId == -4){
					homeon = "life" ;
				}else{
					homeon = "agriculture" ;
				}
				
			}

			domainKeyWord = domainList.get(0).getName();

			if (user != null && question.getStatus() != 99) {
				if (user.getId().equals(question.getUser().getId())) {
					// 如果登录用户的ID与提问者ID相同，则该用户为提问�?
					userType = Config.getInt("ASKER");
					Map<String,Object> promap = new HashMap<String,Object>();
					promap.put("status", "4,5");
					promap.put("questionId", questionId);
					auditProbingList = probingService.findProbingByQuestionIdAndStatus(promap);
				}
				
				for (Reply reply : replyList) {
					if (user.getId().equals(reply.getUser().getId())) {
						// 如果在回复列表中找到登录用户ID，则用户�?查看问题且有过回答�?
						userType = Config.getInt("CHECK_REPLIED");
						break;
					}
				}
				
				//检查是否有用户审核中或者审核未通过的回复
				Map<String,Object> pmap = new HashMap<String,Object>();
				pmap.put("status", "4,5");
				pmap.put("userId", user.getId());
				pmap.put("questionId", questionId);
				List<Reply> rlist = replyService.findReplyByQuestionIdAndStatusAndUserId(pmap);
				if(rlist!=null && rlist.size()>0){
					auditReply=rlist.get(0);
					userType = Config.getInt("CHECK_REPLIED");
				}
				
				if (userType != Config.getInt("ASKER") && userType != Config.getInt("CHECK_REPLIED")) {
					userType = Config.getInt("CHECK_UNREPLIED");
				}
			} else {
				userType = Config.getInt("VISITOR");
			}
			//获得点击率最多问题
			mostViewCountQuestions = questionService.findMostViewCountQuestions(MOST_VIEWCOUNT_QUESTION_COUNT);
			//获得悬赏问题
			experienceQuestions = questionService.getAllQuestionsAndReplyNum(1, MOST_VIEWCOUNT_QUESTION_COUNT,"experience",0,0);
			// 获得该问题的分数
			QuestionScore questionScore = questionScoreService.findQuestionScoreByQuestionId(questionId);
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
			sb.append("_");
			sb.append(Config.getString("QUESTION_SCORE_5"));
			questionStarDescribeStr = sb.toString();
			
			//消除用户该问题的新提醒
			if(user!=null){
				Notice notice = new Notice();
				notice.setUserId(user.getId());
				notice.setQuestionId(questionId);
				notice.setStatus(Config.getInt("NOTICE_READED"));
				noticeService.updateStatusByQuestionIdAndUserId(notice);
				
				//获得notice数
				Notice notice2 = new Notice();
				notice2.setUserId(user.getId());
				//回复提醒数
				notice2.setType(Config.getInt("NOTICE_REPLY"));
				int noticeReplyCount = noticeService.countByUserIdAndType(notice2);
				request.getSession().setAttribute("noticeReplyCount",noticeReplyCount);
				//采纳数
				notice2.setType(Config.getInt("NOTICE_ACCEPT"));
				int noticeAcceptCount = noticeService.countByUserIdAndType(notice2);
				request.getSession().setAttribute("noticeAcceptCount",noticeAcceptCount);
				//评论数
				notice2.setType(Config.getInt("NOTICE_COMMENT"));
				int noticeCommentCount = noticeService.countByUserIdAndType(notice2);
				request.getSession().setAttribute("noticeCommentCount",noticeCommentCount);
				//追问数
				notice2.setType(Config.getInt("NOTICE_PROBING"));
				int noticeProbingCount = noticeService.countByUserIdAndType(notice2);
				request.getSession().setAttribute("noticeProbingCount",noticeProbingCount);
				//审核未通过数
				notice2.setType(Config.getInt("NOTICE_AUDIT_FAIL"));
				int auditFailedCount = noticeService.countByUserIdAndType(notice2);
				request.getSession().setAttribute("auditFailedCount",auditFailedCount);
			}

		} catch (ServiceException e) {
			logger.error("method failed!", e);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}

	public List<QuestionImg> getQImgList() {
		return qImgList;
	}

	public void setQImgList(List<QuestionImg> imgList) {
		qImgList = imgList;
	}

	public List<Reply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}

	public List<Probing> getProbingList() {
		return probingList;
	}

	public void setProbingList(List<Probing> probingList) {
		this.probingList = probingList;
	}

	public void setProbingService(ProbingService probingService) {
		this.probingService = probingService;
	}

	public void setQuestionScoreService(QuestionScoreService questionScoreService) {
		this.questionScoreService = questionScoreService;
	}

	public void setReplyCommentService(ReplyCommentService replyCommentService) {
		this.replyCommentService = replyCommentService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDomainKeyWord() {
		return domainKeyWord;
	}

	public void setDomainKeyWord(String domainKeyWord) {
		this.domainKeyWord = domainKeyWord;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public List<Map<String, Object>> getExpertAcceptedCountMap() {
		return expertAcceptedCountMap;
	}

	public void setExpertAcceptedCountMap(List<Map<String, Object>> expertAcceptedCountMap) {
		this.expertAcceptedCountMap = expertAcceptedCountMap;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public List<Domain> getDomainList() {
		return domainList;
	}

	public void setDomainList(List<Domain> domainList) {
		this.domainList = domainList;
	}

	public int getScore() {
		return score;
	}

	public String getQuestionStarDescribeStr() {
		return questionStarDescribeStr;
	}

	public String getScoreContent() {
		return scoreContent;
	}

	public void setQuestionKeywordService(QuestionKeywordService questionKeywordService) {
		this.questionKeywordService = questionKeywordService;
	}

	public String getQuestionKeywords() {
		return questionKeywords;
	}

	public List<QuestionKeyword> getQkList() {
		return qkList;
	}

	public List<Domain> getTitleDomains() {
		return titleDomains;
	}

	public List<Question> getRelevantQuestions() {
		return relevantQuestions;
	}

	public List<Integer> getNewQuestions() {
		return newQuestions;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public List<Question> getMostViewCountQuestions() {
		return mostViewCountQuestions;
	}

	public List<Question> getExperienceQuestions() {
		return experienceQuestions;
	}

	public String getHomeon() {
		return homeon;
	}

	public Reply getAuditReply() {
		return auditReply;
	}

	public List<Probing> getAuditProbingList() {
		return auditProbingList;
	}

}
