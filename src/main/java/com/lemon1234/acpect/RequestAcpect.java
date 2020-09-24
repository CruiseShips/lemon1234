package com.lemon1234.acpect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * AOP
 */
@Aspect
@Component
public class RequestAcpect {

	private static Logger logger = LoggerFactory.getLogger(RequestAcpect.class);
	
	/**
	 * 定义切点
	 */
	@Pointcut("execution(public * com.lemon1234.controller.*.*(..))")
	public void log() {
	}
	
	/**
	 * 执行前
	 */
	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		// 获取到 HttpRequest 请求
		ServletRequestAttributes sra=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=sra.getRequest();
        String url = request.getRemoteHost();
        int port = request.getRemotePort();
        String methon = request.getRequestURI();
        logger.info("请求地址: " + url + "， 请求端口: " + port + "， 请求方法" + methon);
	}
}
