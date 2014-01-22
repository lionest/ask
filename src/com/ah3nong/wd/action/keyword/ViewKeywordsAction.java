package com.ah3nong.wd.action.keyword;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Keyword;
import com.ah3nong.wd.service.KeywordService;
import com.ah3nong.wd.service.impl.ExpertServiceImpl;

public class ViewKeywordsAction extends GenericActionSupport<Keyword>  {

	/**
	 *  显示关键字列表
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = LoggerFactory.getLogger(ExpertServiceImpl.class);
	private KeywordService keywordService;
	private Map map = new HashMap();
	private int id;
	private String content;
	private String status;
   
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public KeywordService getKeywordService() {
		return keywordService;
	}
	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}
	
   public String execute(){
	   String method= "ViewKeywordsAction";
	   if(id != 0){
		   map.put("id", id);
	   }
	   if(content!=null){
		   map.put("content", content);
	   }
	   if(status!=null){
		   map.put("status", status);
	   }
	   log.info(method+", id:"+id+",  content:"+content+", status:"+status);
	   try{
		pager = keywordService.getKeywordPagerByParam(map, pageNum, 10);
	   }catch (Exception e) {
			log.error(method, e);
			e.getStackTrace();
		}
	   return "success"; 
   }
}
