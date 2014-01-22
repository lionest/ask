package com.ah3nong.wd.action.article;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Article;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionKeyword;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.DomainService;
import com.ah3nong.wd.service.QuestionKeywordService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.ReplyService;

public class AddArticleAction extends GenericActionSupport<Article> {
	private static final long serialVersionUID = -8524883095585787233L;
	private String title;
	private String domainNodepath;
	private String cont;
	private String keywords;
	
	private DomainService domainService;
	private QuestionService questionService;
	private ReplyService replyService;
	private QuestionKeywordService questionKeywordService;
	
	@Transactional(rollbackFor = ServiceException.class)
	public String execute(){
		try {
			Date date = new Date();
			//插入问题表
			Question question = new Question();
			question.setSubject(title);
			question.setContent(title);
			question.setUserId(Config.getInt("DEFAULT_ARTICLE_USER_ID"));
			question.setCreatedTime(date);
			question.setAnonymous(1);
			question.setStatus(Config.getInt("QUESTION_RESOLVED"));
			String nodePath = "";
			if(domainNodepath.substring(2,6).equals("0000")){
				nodePath=domainNodepath.substring(0,2);
			}else if(domainNodepath.substring(4,6).equals("00")){
				nodePath=domainNodepath.substring(0,4);
			}else{
				nodePath=domainNodepath;
			}
			int domainId = domainService.findDomainByNodePath(nodePath).getId();
			
			question.setDomainId(domainId);
			question.setSolvedTime(date);
			question.setReplyNum(1);
			question.setRecommended(0);
			question.setHead(0);
			question.setViewCount(20);
			questionService.addQuestion(question, null,null);
			//查刚刚 插入的问题id
			Question q = questionService.findBySubjectAndCreatedTime(question);
			
			//插入回复表
			Reply reply = new Reply();
			reply.setQuestionId(q.getId());
			reply.setUserId(Config.getInt("DEFAULT_ARTICLE_USER_ID"));
			reply.setContent(cont);
			reply.setAccepted(Config.getInt("REPLY_ACCEPT"));
			reply.setStatus(Config.getInt("REPLY_UNPROBING"));
			reply.setCreatedTime(date);
			reply.setUpdatedTime(date);
			replyService.addReply(reply);
			
			if(keywords!=null && !"".equals(keywords)){
				//增加问题关键字
				String[] kwds = keywords.split(",");
				if(kwds.length>0){
					for(int i=0;i<kwds.length;i++){
						QuestionKeyword qk = new QuestionKeyword();
						qk.setQuestionId(q.getId());
						qk.setKeyword(kwds[i]);
						questionKeywordService.addQuestionKeyword(qk);
					}
				}
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDomainNodepath(String domainNodepath) {
		this.domainNodepath = domainNodepath;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	public void setQuestionKeywordService(QuestionKeywordService questionKeywordService) {
		this.questionKeywordService = questionKeywordService;
	}

}
