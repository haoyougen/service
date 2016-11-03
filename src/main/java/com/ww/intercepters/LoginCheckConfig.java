package com.ww.intercepters;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LoginCheckConfig {
	@Bean
	public Advisor jpaRepositoryAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(public * com.ww.actions..*.*(..))");
		return new DefaultPointcutAdvisor(pointcut, new LoginRequiredInterceptor());
	}
}
