package com.ah3nong.wd.action.question;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.Expert;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.ExpertService;
import com.ah3nong.wd.service.UserService;
import com.ah3nong.wd.util.JsonUtil;

public class FindExpertAction extends GenericActionSupport<Expert> {
	private static final long serialVersionUID = 2373740238689012088L;

	private int id;

	private ExpertService expertService;
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * AJAX请求方法，在页面进行选择分类的时候，根据分类获取相应的EXPERT
	 */
	@Transactional(rollbackFor = Exception.class)
	public void findExpert() {
		String method = "findExpert";
		logger.info(method + ",the method is begin!");
		try {
			List<Expert> expertList = expertService.findExpertByDomainId(id);

			if (expertList.size() > 0) {
				logger.info("find " + expertList.size() + " experts!");
			} else {
				logger.info("find no expert!");
			}
			JsonUtil json = new JsonUtil();
			String str = json.list2json(expertList);
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(str);
			writer.flush();
			writer.close();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			logger.error("find expert failed!", e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("find expert failed!", e);
			e.printStackTrace();
		}
	}

	/**
	 * 根据用户在前台选择的专家，来获得次专家的信息 用JSON格式返回
	 */
	@SuppressWarnings("static-access")
	public void findExpertInfo() {
		String method = "findExpertInfo";
		logger.info(method + ",the method is begin!");
		try {
			Expert expert = expertService.findExpertByPrimaryKey(id);
			if (expert != null) {
				logger
						.info("find  an expert with id="+id);
			} else {
				logger.info("can not find any expert!the id does not exists");
			}
			JsonUtil json = new JsonUtil();
			User user=userService.findUserByPrimaryKey(expert.getId());
			//如果fullname不为空则使用fullname否则使用username
			if(user.getFullName()!=null&&!"".equals(user.getFullName())){
				expert.setFullName(user.getFullName());
			}else{
				expert.setFullName(user.getUsername());
			}
			String str = json.object2json(expert);
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(str);
			writer.flush();
			writer.close();
		} catch (ServiceException e) {
			logger.error("find expert info failed!", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("find expert info failed!", e);
			e.printStackTrace();
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setExpertService(ExpertService expertService) {
		this.expertService = expertService;
	}

}
