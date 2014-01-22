package com.ah3nong.wd.action.keyword;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Keyword;
import com.ah3nong.wd.service.KeywordService;
import com.ah3nong.wd.service.impl.ExpertServiceImpl;

public class EditKeywordAction extends GenericActionSupport<Keyword> {
	/**
	 * 修改关键字状态
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = LoggerFactory.getLogger(ExpertServiceImpl.class);
	private KeywordService keywordService;
	private Keyword keyword ;
	private int status;
	private int id;
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public KeywordService getKeywordService() {
		return keywordService;
	}

	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}

	public String execute() {
		String method = "UpdateKeywordAction";
		log.info(method + ", id:" + id );
		try {
			keyword=keywordService.findKeywordByPrimaryKey(id);
		} catch (Exception e) {
			log.error(method, e);
			e.getStackTrace();
		}
		return "success";
	}

	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}
}
