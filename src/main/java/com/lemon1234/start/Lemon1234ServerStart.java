package com.lemon1234.start;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.lemon1234.controller.wx.WxController;
import com.lemon1234.entity.Announcement;
import com.lemon1234.entity.Link;
import com.lemon1234.entity.Menu;
import com.lemon1234.entity.OpenOSType;
import com.lemon1234.service.AnnouncementService;
import com.lemon1234.service.LinkService;
import com.lemon1234.service.MenuService;
import com.lemon1234.service.OpenOSTypeService;

@Component("lemon1234ServerStart")
public class Lemon1234ServerStart implements ApplicationListener<ContextRefreshedEvent>{
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private ServletContext servletContext = null;
	
	private Logger logger = LoggerFactory.getLogger(Lemon1234ServerStart.class);
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private LinkService linkService;
	@Autowired
	private OpenOSTypeService openOSTypeService;
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private WxController wxController;
	
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
	
	// 2小时 - 3分钟
	@Scheduled(fixedRate = 7020000)
    public void testTasks() {
		String token = null;
		if(servletContext != null) {
			try {
				token = wxController.getAccessToken();
				servletContext.setAttribute("token", token);
				logger.info("微信 AccessToken 更新 ： " + token);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(token == null) {
					int i = 0;
					while(true) {
						try {
							Thread.sleep(3000);
							token = wxController.getAccessToken();
							if(token != null) {
								servletContext.setAttribute("token", token);
								logger.info("微信 AccessToken 更新 ： " + token);
								break;
							} else {
								i++;
							}
							if(i == 3) {
								break;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
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
