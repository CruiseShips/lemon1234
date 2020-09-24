package com.lemon1234.start;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.lemon1234.entity.ApacheOSType;
import com.lemon1234.entity.Link;
import com.lemon1234.entity.Menu;
import com.lemon1234.service.ApacheOSTypeService;
import com.lemon1234.service.LinkService;
import com.lemon1234.service.MenuService;

@Component("lemon1234ServerStart")
public class Lemon1234ServerStart implements ApplicationListener<ContextRefreshedEvent>{
	
	private ServletContext servletContext = null;
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private LinkService linkService;
	@Autowired
	private ApacheOSTypeService apacheOSTypeService;
	
	public void initData() {
		List<Menu> menuList = menuService.getlist(null);
		List<Link> linkList = linkService.getlist(null);
		List<ApacheOSType> apacheOSTypes = apacheOSTypeService.getlist(null);
		
		servletContext.setAttribute("menuList", menuList);
		servletContext.setAttribute("linkList", linkList);
		servletContext.setAttribute("apacheOSTypeList", apacheOSTypes);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
        WebApplicationContext webApplicationContext = (WebApplicationContext)event.getApplicationContext();
        // 从 webApplicationContext 中获取  servletContext 
        ServletContext servletContext = webApplicationContext.getServletContext();
        // servletContext设置值
        this.servletContext = servletContext;
        this.initData();
	}

	
}
