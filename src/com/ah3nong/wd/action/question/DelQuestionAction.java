package com.ah3nong.wd.action.question;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionExpert;
import com.ah3nong.wd.bean.QuestionImg;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.service.impl.QuestionServiceImpl;

public class DelQuestionAction extends GenericActionSupport<Question> {
	private static final long serialVersionUID = 2373740238689012088L;
	
	private int questionId;
	private QuestionService questionService;

	@Transactional(rollbackFor = Exception.class)
	public String execute(){
		String method = "delQuestionAction";
		logger.info(method+", the method is begin!");
		try {
			if(questionId!=0){
				questionService.delQuestionByPrimaryKey(questionId);
				logger.info("delete Question,QuestionExpert,QuestionImg,Probing,Reply success !");
			}else{
				logger.error("qestionId is null! Can not delete!");
			}
						
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			logger.error(method+" failed.", e);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	
	
}
