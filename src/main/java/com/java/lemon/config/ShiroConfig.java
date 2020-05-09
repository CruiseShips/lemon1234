package com.java.lemon.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * shiro 配置类
 * 
 */
@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
	
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/");
        
        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        
        // 静态资源
        filterChainDefinitionMap.put("/static/**", "anon");
        
        // 网站logo
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/authorInfo/getAuthorInfo", "anon");
        
        // 动态资源，存放一些图片等内容
        filterChainDefinitionMap.put("/lemon1234/technology/**", "anon");
        filterChainDefinitionMap.put("/lemon1234/document/**", "anon");
        
        // 前端请求
        filterChainDefinitionMap.put("/project/**", "anon");
        filterChainDefinitionMap.put("/technology/**", "anon");
        filterChainDefinitionMap.put("/document/**", "anon");
        filterChainDefinitionMap.put("/studyRoad/**", "anon");
        
//        filterChainDefinitionMap.put("/", "anon");
//        filterChainDefinitionMap.put("/login.html", "anon");
//        filterChainDefinitionMap.put("/register.html", "anon");
//        filterChainDefinitionMap.put("/findPassword.html", "anon");
        
//        filterChainDefinitionMap.put("/user/login", "anon");
//        filterChainDefinitionMap.put("/user/sendEmail", "anon");
//        filterChainDefinitionMap.put("/user/checkYzm", "anon");
//        filterChainDefinitionMap.put("/user/register", "anon");
//        filterChainDefinitionMap.put("/user/sign", "anon");
//        filterChainDefinitionMap.put("/article/**", "anon");
//        filterChainDefinitionMap.put("/comment/**", "anon");
//        filterChainDefinitionMap.put("/image/**", "anon");
//        filterChainDefinitionMap.put("/userImage/**", "anon");
//        filterChainDefinitionMap.put("/adminLogin.html", "anon");
//        filterChainDefinitionMap.put("/toVipPage", "anon");
//        filterChainDefinitionMap.put("/toDeclarePage", "anon");
        
//        filterChainDefinitionMap.put("/alipay/notifyUrl", "anon");
//        filterChainDefinitionMap.put("/weixinpay/notifyUrl", "anon");
        
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

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
	
    /**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     * 
     * @return
     */
    @Bean
    public ShiroRealm myRealm() {
    	ShiroRealm myRealm = new ShiroRealm();
        return myRealm;
    }
    
    /**
     * Shiro生命周期处理器
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
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
