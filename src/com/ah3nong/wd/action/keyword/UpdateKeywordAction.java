package com.ah3nong.wd.action.keyword;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Keyword;
import com.ah3nong.wd.service.KeywordService;
import com.ah3nong.wd.service.impl.ExpertServiceImpl;
import com.ah3nong.wd.util.StringHelper;

public class UpdateKeywordAction extends GenericActionSupport<Keyword> {
	/**
	 * 修改关键字状态
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = LoggerFactory.getLogger(ExpertServiceImpl.class);
	private KeywordService keywordService;
	private Keyword keyword = new Keyword();
	private int status;
	private int id;
	private Map map = new HashMap();
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
		log.info(method + ", id:" + id + ",   status:" + status);
		try {
			if (status == 1) {
				map.put("status", 0);
				keyword.setStatus(0);
			} else {
				map.put("status", 1);
				keyword.setStatus(1);
			}
			if (id != 0) {
				map.put("id", id);
				keyword.setId(id);
			}
			if (content != null) {
				keyword.setContent(StringHelper.encodeHTML(content));
			}
			log.info(method + ", id:" + keyword.getId() + ",   status:"
					+ map.get("status"));
			int r = keywordService.updateKeywordByPrimaryKey(keyword, true,
					map);
			if (r == -1) {
				request.setAttribute("errorMsg", "关键字已存在!");
				return INPUT;
			}
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
