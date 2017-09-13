package com.siqian.oa.util;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.siqian.oa.domain.Privilege;
import com.siqian.oa.service.PrivilegeService;

public class InitListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		//获取容器与相关的Service对象
		//WebApplicationContextUtils工具类，获取创建的容器对象
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		
		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeServiceImpl");
		//准备数据：
		
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		sce.getServletContext().setAttribute("topPrivilegeList", topPrivilegeList);
		System.out.println("----------->已准备数据<------------");
		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}



}
