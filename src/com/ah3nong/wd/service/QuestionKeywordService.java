package com.ah3nong.wd.service;

import java.util.List;
import java.util.Map;

import com.ah3nong.wd.bean.QuestionKeyword;

public interface QuestionKeywordService {
	/**
	 * 增加一条记录。
	 * @param QuestionKeyword 问题关键字
	 */
	public void addQuestionKeyword(QuestionKeyword questionKeyword);
	/**
	 * 通过问题id找到该问题所有关键字。
	 * @param questionId 问题id
	 * @return List<QuestionKeyword> 问题关键字list
	 */
	public List<QuestionKeyword> findKeywordsByQuestionId(int questionId);
	/**
	 * 删除该问题所有关键字。
	 * @param questionId 问题id
	 */
	public void deleteQuestionkeywordByQuestionId(int questionId);
	/**
	 * 查最热的关键字
	 * @param count 需要查找的最热关键字数量
	 * @return List<QuestionKeyword> 最热关键字list
	 */
	public  List<Map<String,Object>> findHotKeywords(int size);
}
