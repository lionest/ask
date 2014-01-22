package com.ah3nong.wd.action.keyword;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Keyword;
import com.ah3nong.wd.service.KeywordService;
import com.ah3nong.wd.service.impl.ExpertServiceImpl;

public class DelKeywordAction extends GenericActionSupport<Keyword> {

	/**
	 * 删除关键字
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = LoggerFactory.getLogger(ExpertServiceImpl.class);
	private KeywordService keywordService;
	private int id;
	private String[] entityIds;

	public void setEntityIds(String[] entityIds) {
		this.entityIds = entityIds;
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

	public String delKeyword() {
		String method = "DelKeywordAction";
		try {
			keywordService.delKeywordByPrimaryKey(id);
		} catch (Exception e) {
			log.error(method, e);
			e.getStackTrace();
		}
		return "success";
	}

	public String delKeywords(){
    	String method="delKeywords";
    	if(entityIds ==null){
    		log.error(method+", entityIds is null");
    		return "error";
    	}
    	log.info(method+", entityIds:"+entityIds.toString());
		try{
			for(int i=0;i<entityIds.length;i++){
			keywordService.delKeywordByPrimaryKey(Integer.parseInt(entityIds[i]));
			}
			}catch (Exception e) {
			log.error(method, e);
			e.getStackTrace();
		}
    	return "success";
    }
}
