package com.ah3nong.wd.action.reply;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Notice;
import com.ah3nong.wd.bean.Probing;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.security.SecurityContext;
import com.ah3nong.wd.service.KeywordService;
import com.ah3nong.wd.service.NoticeService;
import com.ah3nong.wd.service.ProbingService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.ReplyService;
import com.ah3nong.wd.service.SysdataService;
import com.ah3nong.wd.util.StringHelper;

public class AddReplyAction extends GenericActionSupport<Reply> {
	private static final long serialVersionUID = 8287527356649789271L;

	private ReplyService replyService;
	private QuestionService questionService;
	private ProbingService probingService;
	private KeywordService keywordService;
	private NoticeService noticeService;
	private SysdataService sysdataService;
	private int questionId;
	private int probingId;
	private String replyContent;

	private Reply reply;

	@Transactional(rollbackFor = ServiceException.class)
	public String execute() {
		int questionId = reply.getQuestionId();
		try {
			User user = (User) SecurityContext.getUser();
			reply.setUserId(user.getId());

			// 获得回复的内容并对特殊字符转义
			if (reply.getContent() == null || "".equals(reply.getContent())) {
				reply.setContent(replyContent);
			}
			reply.setContent(StringHelper.encodeHTML(reply.getContent()));
			reply.setAccepted(Config.getInt("REPLY_NOT_ACCEPT"));
			reply.setCreatedTime(new Date());
			
			int audit = Integer.parseInt(sysdataService.findByName("auditReply").getContent());
			// 1.如果用户选择的为关键字审核
			if (audit == 2) {
				// 如果包含关键字，设置回复状态为审核中
				if (reply.getContent() != null && keywordService.findKeywordInString(reply.getContent())) {
					reply.setStatus(Config.getInt("REPLY_AUDITING"));
					// 否则设置为正常状态
				} else {
					reply.setStatus(Config.getInt("REPLY_UNPROBING"));
				}

			// 2.如果用户选择的为全部审核
			} else if (audit == 1) {
				reply.setStatus(Config.getInt("REPLY_AUDITING"));

			// 3.如果用户选择的为全部不审核
			} else {
				reply.setStatus(Config.getInt("REPLY_UNPROBING"));
			}

			// 如果是对追问做出的回答
			if (reply.getProbingId() != null) {
				// 判断是否已经有过回复，已经回复过的话直接返回
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("questionId", (Integer) reply.getQuestionId());
				map.put("status", (Integer) Config.getInt("REPLY_PROBINGED"));
				List<Reply> replyList = replyService.findReplyByQuestionIdAndStatus(map);
				for (int i = 0; i < replyList.size(); i++) {
					User u = replyList.get(i).getUser();
					int num1 = u.getId();
					int num2 = reply.getUserId();
					int num3 = 0;
					if(replyList.get(i).getProbingId()!=null){
						num3 = replyList.get(i).getProbingId();
					}
					if (num1 == num2 && num3 == reply.getProbingId()) {
						request.setAttribute("tips", "您的回复正在审核中，请勿重复回复...");
						return SUCCESS;
					}
				}
				
				// reply.setProbingId(probingId);
				Probing probing = new Probing();
				probing.setId(reply.getProbingId());
				probing.setStatus(Config.getInt("PROBING_REPLIED"));
				probingService.updateProbingByPrimaryKey(probing, true);

			// 如果不是对追问的回答
			} else {
				// 判断是否已经有过回复，已经回复过的话直接返回
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("questionId", (Integer) reply.getQuestionId());
				map.put("status", (Integer) Config.getInt("REPLY_AUDITING"));
				List<Reply> replyList = replyService.findReplyByQuestionIdAndStatus(map);
				for (int i = 0; i < replyList.size(); i++) {
					User u = replyList.get(i).getUser();
					int num1 = u.getId();
					int num2 = reply.getUserId();
					if (num1 == num2) {
						request.setAttribute("tips", "您的回复正在审核中，请勿重复回复...");
						request.setAttribute("questionId", questionId);
						return SUCCESS;
					}
				}
			}
			replyService.addReply(reply);
			if(reply.getStatus()==Config.getInt("REPLY_AUDITING")){
				request.setAttribute("tips", "回复成功，审核通过后将会在问题页面显示！");
			}else{
				request.setAttribute("tips", "回复成功！");
			}
			// 如果之前没回复过在对应的问题记录中的replyNum字段加1
			Question question2 = questionService.findQuestionByPrimaryKey(reply.getQuestionId());
			if (reply.getProbingId() == null && reply.getStatus()==Config.getInt("REPLY_UNPROBING")) {
				Question question = new Question();
				question.setId(reply.getQuestionId());
				question.setReplyNum(question2.getReplyNum() + 1);
				questionService.updateQuestionByPrimaryKey(question, true);
			}
			
			if(reply.getStatus()==Config.getInt("REPLY_UNPROBING")){
				// 增加一个Notice
				Notice notice = new Notice();
				notice.setQuestionId(question2.getId());
				notice.setUserId(question2.getUser().getId());
				notice.setType(Config.getInt("NOTICE_REPLY"));
				notice.setCreatedTime(new Date());
				// 获得锚点
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("questionId", question2.getId());
				List<Reply> replyList = replyService.findReplyByQuestionId(map);
				if (replyList.size() > 0) {
					for (int j = 0; j < replyList.size(); j++) {
						User tmpUser = replyList.get(j).getUser();
						int uid1 = tmpUser.getId();
						int uid2 = reply.getUserId();
						if (uid1 == uid2) {
							notice.setAnchor(replyList.get(j).getId());
						}
					}
				}
				noticeService.addNotice(notice);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("tips", "回复出错了...)");
		}
		request.setAttribute("questionId", questionId);
		return SUCCESS;
	}

	public int getProbingId() {
		return probingId;
	}

	public void setProbingId(int probingId) {
		this.probingId = probingId;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setProbingService(ProbingService probingService) {
		this.probingService = probingService;
	}

	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public void setSysdataService(SysdataService sysdataService) {
		this.sysdataService = sysdataService;
	}
 
}
