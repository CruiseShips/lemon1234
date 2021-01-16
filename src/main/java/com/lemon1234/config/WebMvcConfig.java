package com.lemon1234.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lemon1234.interceptor.RequestInterceptor;

@Component("webMvcComponent")
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Value("${documentImgFile}")
	private String documentImgFile;
	@Value("${adminImgFile}")
	private String adminImgFile;
	@Value("${openOSImgFile}")
	private String openOSImgFile;
	@Value("${ckeditorImgFile}")
	private String ckeditorImgFile;
	@Value("${activityImgFile}")
	private String activityImgFile;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowCredentials(true)
				.allowedMethods("GET", "POST", "HEAD", "PUT", "DELETE", "OPTIONS")
				.maxAge(3600);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/documentImg/**").addResourceLocations(documentImgFile);
		registry.addResourceHandler("/adminImg/**").addResourceLocations(adminImgFile);
		registry.addResourceHandler("/openOSImg/**").addResourceLocations(openOSImgFile);
		registry.addResourceHandler("/ckeditorImg/**").addResourceLocations(ckeditorImgFile);
		registry.addResourceHandler("/activityImg/**").addResourceLocations(activityImgFile);
	}

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor())
                .addPathPatterns("/**");
    }
}
