package com.ah3nong.wd.tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionImg;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.util.DateUtil;

public class QuestionListTag extends TagSupport {
	private static final long serialVersionUID = -6390757596844174983L;

	private Integer count;
	private Boolean recommended;
	private Integer status;
	private String orderBy;
	private String var;

	public int doStartTag() {
		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
		QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("count", count);
		if (recommended != null) {
			params.put("recommended", recommended ? 1 : 0);
		}
		params.put("status", status);
		params.put("orderBy", orderBy);
		params.put("var", var);
		List<Question> questions = questionService.findQuestion(params);
		//改变时间显示样式。并把改后的时间存在extracontent里面
		if(questions.size()>0){
			for(int i=0;i<questions.size();i++){
				String timeStr = questions.get(i).getCreatedTime().toLocaleString();
				DateUtil.getTime(timeStr);
				questions.get(i).setExtraContent(DateUtil.getTime(timeStr));
			}
		}
		List<Integer> askForExpertList = new ArrayList<Integer>();
		List<Integer> newQuestions =  new ArrayList<Integer>();
		List<Integer> imgQuestions =  new ArrayList<Integer>();
		try {
			Date now = new Date();
			for(Question question:questions){			
				//看是否专家
				if(questionService.findExpertByQuestionId(question.getId())!= null){
					askForExpertList.add((Integer)question.getId());
				}else{
					//看是否是最新问题
					if(now.getTime()-question.getCreatedTime().getTime()<(1000*60*60*6)){
						newQuestions.add((Integer)question.getId());
					}
				}
				//看是否有图片
				List<QuestionImg> qImgList = questionService.findImgsByQuestionId(question.getId(), Config.getInt("QUESTIONIMG_NORMAL"));
				if(qImgList!=null&&qImgList.size()>0){
					imgQuestions.add((Integer)question.getId());
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		pageContext.setAttribute(var, questions);
		pageContext.setAttribute("expt"+var, askForExpertList);
		pageContext.setAttribute("newq"+var, newQuestions);
		pageContext.setAttribute("img"+var, imgQuestions);
		return SKIP_BODY;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setRecommended(Boolean recommended) {
		this.recommended = recommended;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void setVar(String var) {
		this.var = var;
	}
}
