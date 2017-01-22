package com.ww;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ww.intercepters.AccessInterceptor;

@SpringBootApplication
@ImportResource(value = { "classpath:service-web.xml" })
@EnableAutoConfiguration
@EnableConfigurationProperties
public class ServiceApplication extends WebMvcConfigurerAdapter{
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceApplication.class);

	public static void main(String[] args) {
		// logback.configurationFile
		System.setProperty("logging.config", System.getProperty("APP_HOME") + File.separator + "conf" + File.separator
				+ System.getProperty("env") + File.separator + "logback.xml");
		SpringApplication.run(ServiceApplication.class, args);
	}
	
	/**
	 * 配置拦截器
	 * 
	 * @author wangshuguang
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AccessInterceptor()).addPathPatterns("*");
		super.addInterceptors(registry);
	}
}
