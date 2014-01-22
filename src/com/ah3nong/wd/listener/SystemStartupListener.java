package com.ah3nong.wd.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.common.DataCache;
import com.ah3nong.wd.constant.DataCacheKey;
import com.ah3nong.wd.dao.DomainDao;
import com.ah3nong.wd.system.SystemDataConfig;

public class SystemStartupListener implements ServletContextListener {

	
	public void contextInitialized(ServletContextEvent event) {
		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
		DomainDao domainDao = applicationContext.getBean(DomainDao.class);
		List<Domain> domains = domainDao.findAllDomain();
		if (domains != null) {
			DataCache.put(DataCacheKey.DOMAIN, domains);
		}
		
		ServletContext application = event.getServletContext();
		application.setAttribute("siteUrl", SystemDataConfig.getSiteUrl());
		application.setAttribute("casUrl", SystemDataConfig.getCasUrl());
		application.setAttribute("passportUrl", SystemDataConfig.getPassportUrl());
		application.setAttribute("regUrl", SystemDataConfig.getRegUrl());
		application.setAttribute("editUserUrl", SystemDataConfig.getEditUserUrl());
		application.setAttribute("editUserAvatarUrl", SystemDataConfig.getEditUserAvatarUrl());
		application.setAttribute("loginUrl", SystemDataConfig.getLoginUrl());
		application.setAttribute("loginOutUrl", SystemDataConfig.getLoginOutUrl());
		application.setAttribute("importExcelUrl", SystemDataConfig.getImportExcelUrl());
	}
	
	public void contextDestroyed(ServletContextEvent event) {
	}
}
