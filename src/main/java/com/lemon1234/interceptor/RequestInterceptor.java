package com.lemon1234.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.lemon1234.util.DeviceUtil;

/**
 * pc 或者 手机请求过滤器
 * 
 * @date 2021年1月16日
 * @author lemon1234.zhihua
 */
public class RequestInterceptor implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String contextPath = request.getRequestURI();
		String userAgent = request.getHeader("user-agent");
		
		logger.info("请求路径：" + contextPath + "，请求端为" + userAgent);
		
		if(DeviceUtil.isMobileDevice(userAgent)){
			if("/".equals(contextPath)){
                request.getRequestDispatcher("/wap/index").forward(request,response);
                return false;
            }
		}
		return true;
	}
}
