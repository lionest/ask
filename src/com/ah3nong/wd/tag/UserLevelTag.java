package com.ah3nong.wd.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ah3nong.wd.bean.Sysdata;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.SysdataService;
import com.ah3nong.wd.service.UserService;

public class UserLevelTag extends TagSupport {
	private static final long serialVersionUID = -7613858879601666804L;
	private int userId;
	private String var;

	public int doStartTag() {
		try {
			WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
			UserService userService = (UserService) applicationContext.getBean("userService");
			SysdataService sysdataService = (SysdataService) applicationContext.getBean("sysdataService");
			int experience = userService.findUserByPrimaryKey(userId).getExperience();
			Map<String,Sysdata> leveldata = new HashMap<String,Sysdata>();
			List<Sysdata> sysdataList = sysdataService.findAll();
			for(int i=0;i<sysdataList.size();i++){
				Sysdata sd = sysdataList.get(i);
				if(sd.getName().length()==6&&"LEVEL".equals(sd.getName().substring(0,5))){
					leveldata.put(sd.getName(), sd);
				}
			}
			
			String level = leveldata.get("LEVEL6").getCname();
			if(experience<=Integer.parseInt(leveldata.get("LEVEL2").getContent())){
				level=leveldata.get("LEVEL1").getCname();
			}else if(experience<=Integer.parseInt(leveldata.get("LEVEL3").getContent())){
				level=leveldata.get("LEVEL2").getCname();
			}else if(experience<=Integer.parseInt(leveldata.get("LEVEL4").getContent())){
				level=leveldata.get("LEVEL3").getCname();
			}else if(experience<=Integer.parseInt(leveldata.get("LEVEL5").getContent())){
				level=leveldata.get("LEVEL4").getCname();
			}else if(experience<=Integer.parseInt(leveldata.get("LEVEL6").getContent())){
				level=leveldata.get("LEVEL5").getCname();
			}
			pageContext.setAttribute(var, level);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setVar(String var) {
		this.var = var;
	}

}
