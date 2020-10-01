package com.lemon1234.start;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.lemon1234.entity.OpenOSType;
import com.lemon1234.entity.Announcement;
import com.lemon1234.entity.Link;
import com.lemon1234.entity.Menu;
import com.lemon1234.service.OpenOSTypeService;
import com.lemon1234.service.AnnouncementService;
import com.lemon1234.service.LinkService;
import com.lemon1234.service.MenuService;

@Component("lemon1234ServerStart")
public class Lemon1234ServerStart implements ApplicationListener<ContextRefreshedEvent>{
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private ServletContext servletContext = null;
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private LinkService linkService;
	@Autowired
	private OpenOSTypeService openOSTypeService;
	@Autowired
	private AnnouncementService announcementService;
	
	public void initData() {
		List<Menu> menuList = menuService.getlist(null);
		List<Link> linkList = linkService.getlist(null);
		List<OpenOSType> openOSTypes = openOSTypeService.getlist(null);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", 0);
		param.put("limit", 6);
		List<Announcement> announcementList = announcementService.getlist(param);
		
		for (Announcement a:announcementList) {
			a.setYyyyMMdd(sdf.format(a.getCreateDt()));
		}
		servletContext.setAttribute("announcementList", announcementList);
		servletContext.setAttribute("menuList", menuList);
		servletContext.setAttribute("linkList", linkList);
		servletContext.setAttribute("openOSTypes", openOSTypes);
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
