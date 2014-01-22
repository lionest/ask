package com.ah3nong.wd.action.article;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Article;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionKeyword;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.ArticleService;
import com.ah3nong.wd.service.QuestionKeywordService;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.ReplyService;

public class ArticleToQuestionAction extends GenericActionSupport<Article> {
	private static final long serialVersionUID = 3312011412494356213L;
	
	private ArticleService articleService;
	private QuestionService questionService;
	private ReplyService replyService;
	private QuestionKeywordService questionKeywordService;
	
	@Transactional(rollbackFor = ServiceException.class)
	public String execute(){
		List<Article> articleList = articleService.findAll();
		for(int i=0;i<articleList.size();i++){
			Article article = articleList.get(i);
			try {
				Date date = new Date();
				GregorianCalendar cal=(GregorianCalendar) Calendar.getInstance();
				cal.add(Calendar.MONTH, -1);
				date=cal.getTime();
				//插入问题表
				Question question = new Question();
				question.setSubject(article.getTitle());
				question.setContent(article.getTitle());
				question.setUserId(Config.getInt("DEFAULT_ARTICLE_USER_ID"));
				question.setCreatedTime(date);
				question.setStatus(Config.getInt("QUESTION_RESOLVED"));
				question.setDomainId(article.getDomainId());
				question.setSolvedTime(date);
				question.setReplyNum(1);
				question.setRecommended(0);
				question.setHead(0);
				question.setViewCount(20);
				questionService.addQuestion(question, null,null);
				Question q = questionService.findBySubjectAndCreatedTime(question);
				
				//插入回复表
				Reply reply = new Reply();
				reply.setQuestionId(q.getId());
				reply.setUserId(Config.getInt("DEFAULT_ARTICLE_USER_ID"));
				reply.setContent(article.getContent());
				reply.setAccepted(Config.getInt("REPLY_ACCEPT"));
				reply.setStatus(Config.getInt("REPLY_UNPROBING"));
				reply.setCreatedTime(date);
				reply.setUpdatedTime(date);
				replyService.addReply(reply);
				
				//增加问题关键字
				String kwd = article.getKeyword();
				if(kwd!=null&&kwd.length()>2){
					String[] kwds = kwd.substring(1,kwd.length()-1).split("\\|");
					if(kwds.length>0){
						for(int j=0;j<kwds.length;j++){
							QuestionKeyword qk = new QuestionKeyword();
							qk.setQuestionId(q.getId());
							qk.setKeyword(kwds[j]);
							questionKeywordService.addQuestionKeyword(qk);
						}
					}
				}
				
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
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
