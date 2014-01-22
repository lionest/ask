package com.ah3nong.wd.timertask;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.service.QuestionService;

public class CloseQuestionTask {
	private QuestionService questionService;
	public void closeQuestion(){
		System.out.println("CloseQuestionTask..closeQuestion..run"+new Date());
		//获得问题关闭的日期
		Calendar now = Calendar.getInstance();  
        now.setTime(new Date());  
        now.set(Calendar.DATE, now.get(Calendar.DATE) - Config.getInt("QUESTION_CLOSED_TIME"));  
        Date overdue = now.getTime();
        System.out.println(overdue.toLocaleString());
        //把日期在问题关闭日期之前的问题状态全改为99_即关闭状态
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status", (Integer)Config.getInt("QUESTION_CLOSED"));
        map.put("overdue", (Date)overdue);
        questionService.updateStatusByCreatedTime(map);
	}
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}	
}
