package com.ww.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ww.intercepters.AccessInterceptor;

@Component
public class WebAppConfig extends WebMvcConfigurerAdapter{

	/**
	 * 配置拦截器
	 * 
	 * @author wangshuguang
	 * @param registry
	 */
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AccessInterceptor()).addPathPatterns("/*");
	}
}
