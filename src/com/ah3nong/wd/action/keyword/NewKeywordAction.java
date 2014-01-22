package com.ah3nong.wd.action.keyword;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Keyword;
import com.ah3nong.wd.service.KeywordService;
import com.ah3nong.wd.service.impl.ExpertServiceImpl;
import com.ah3nong.wd.util.StringHelper;

public class NewKeywordAction extends GenericActionSupport<Keyword>{

	/**
	 * 添加关键字
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = LoggerFactory.getLogger(ExpertServiceImpl.class);
	private KeywordService keywordService;
	private Keyword keyword;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Keyword getKeyword() {
		return keyword;
	}
	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}
	public KeywordService getKeywordService() {
		return keywordService;
	}
	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}
	public String execute(){
		String method="NewKeywordAction";
		if(keyword==null){
			log.info(method+",keyword is null");
		}
		log.info(method+",  keyword:"+keyword.toString());
		try{
			if(status.equals("T")){
				keyword.setStatus(1);
			}else{
				keyword.setStatus(0);
			}
			keyword.setContent(StringHelper.encodeHTML(keyword.getContent()));
			int r=keywordService.addKeyword(keyword);
			if(r==-1){
				request.setAttribute("info", "关键字已存在!");
				return INPUT;
			}
		}catch (Exception e) {
			log.error(method, e);
			e.getStackTrace();
		}
		return "success";
	}

}
