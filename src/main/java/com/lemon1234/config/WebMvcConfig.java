package com.lemon1234.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component("webMvcComponent")
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Value("${documentImgFile}")
	private String documentImgFile;
	@Value("${adminImgFile}")
	private String adminImgFile;
	@Value("${apacheOSImgFile}")
	private String apacheOSImgFile;
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
		registry.addResourceHandler("/apacheOSImg/**").addResourceLocations(apacheOSImgFile);
		registry.addResourceHandler("/ckeditorImg/**").addResourceLocations(ckeditorImgFile);
		registry.addResourceHandler("/activityImg/**").addResourceLocations(activityImgFile);
	}

}
