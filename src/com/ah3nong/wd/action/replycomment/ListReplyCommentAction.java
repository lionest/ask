package com.ah3nong.wd.action.replycomment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.ReplyComment;
import com.ah3nong.wd.service.ReplyCommentService;

public class ListReplyCommentAction extends GenericActionSupport<ReplyComment> {
	private static final long serialVersionUID = 3202574081931389098L;
	private ReplyCommentService replyCommentService;
	private int replyId ;
	
	private List<Map<String,Object>> replyCommentList = new ArrayList<Map<String,Object>>();
	public String execute(){
		List<ReplyComment> replyComments = replyCommentService.findReplyCommentByReplyId(replyId);
	
		if(replyComments.size()>0){
			for(int i=0;i<replyComments.size();i++){
				ReplyComment rc = replyComments.get(i);
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("username", rc.getUser().getScreenName());
				m.put("createdTime", rc.getCreatedTime().toLocaleString());
				m.put("content", rc.getContent());
				replyCommentList.add(m);
			}
		}
		return SUCCESS;
	}
	
	public List<Map<String, Object>> getReplyCommentList() {
		return replyCommentList;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public void setReplyCommentService(ReplyCommentService replyCommentService) {
		this.replyCommentService = replyCommentService;
	}
}
