package com.lemon1234.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
		filtersMap.put("logout", shiroLogoutFilter());
		
		shiroFilterFactoryBean.setFilters(filtersMap);
		
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/");
	
        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        
        // 网站logo
        filterChainDefinitionMap.put("/ico/favicon.ico", "anon");
        
        // static 的东西
        filterChainDefinitionMap.put("/bootstrap3.3.7/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/layui/**", "anon");
        filterChainDefinitionMap.put("/info/**", "anon");
        filterChainDefinitionMap.put("/mouse/**", "anon");
        filterChainDefinitionMap.put("/login/**", "anon");
        filterChainDefinitionMap.put("/ckeditor/**", "anon");
        
        // 图片路径
        filterChainDefinitionMap.put("/documentImg/**", "anon");
        filterChainDefinitionMap.put("/openOSImg/**", "anon");
        filterChainDefinitionMap.put("/adminImg/**", "anon");
        filterChainDefinitionMap.put("/activityImg/**", "anon");
        filterChainDefinitionMap.put("/ckeditorImg/**", "anon");
        
        // 前端请求
        filterChainDefinitionMap.put("/anno/**", "anon");
        filterChainDefinitionMap.put("/open/**", "anon");
        filterChainDefinitionMap.put("/getOSCode", "anon");
        filterChainDefinitionMap.put("/aboutMe", "anon");
        filterChainDefinitionMap.put("/route", "anon");
        filterChainDefinitionMap.put("/document/**", "anon");
        filterChainDefinitionMap.put("/activity/**", "anon");
        filterChainDefinitionMap.put("/register/**", "anon");
        filterChainDefinitionMap.put("/message/**", "anon");
        filterChainDefinitionMap.put("/ivq/**", "anon");
        
        // 跳转后端登录页面
        filterChainDefinitionMap.put("/login", "anon");
        
        // 后端登录请求
        filterChainDefinitionMap.put("/admin/login", "anon");
        
        // wx 请求
        filterChainDefinitionMap.put("/wx/**", "anon");
        
        // 添加手机端
        filterChainDefinitionMap.put("/wap/**", "anon");
        
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/admin/logout", "logout");

        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");
        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
	}
	
	@Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(myRealm());
        return securityManager;
    }
	
    @Bean
    public ShiroRealm myRealm() {
    	ShiroRealm myRealm = new ShiroRealm();
        return myRealm;
    }
    
    public ShiroLogoutFilter shiroLogoutFilter(){
        ShiroLogoutFilter shiroLogoutFilter = new ShiroLogoutFilter();
        //配置登出后重定向的地址，等出后配置跳转到登录接口
        shiroLogoutFilter.setRedirectUrl("/login");
        return shiroLogoutFilter;
    }
    
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
}
