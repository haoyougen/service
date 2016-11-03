package com.ww.intercepters;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ww.annotations.LoginRequired;

/**
 * @author wangshuguang
 */
public class LoginRequiredInterceptor implements MethodInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginRequiredInterceptor.class);

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Object[] args = methodInvocation.getArguments();
		for (Object obj : args) {
			if (obj instanceof HttpServletRequest) {
				LOGGER.info("This is a httpservlet request");
			}
		}

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		LOGGER.info("Interceptor-User-Agent: {}", request.getHeader("User-Agent"));
		boolean loginrequired = methodInvocation.getMethod().isAnnotationPresent(LoginRequired.class);
		if (loginrequired) {
			LOGGER.info("---The method can be executed after login ----");
		}
		return methodInvocation.proceed();
	}

}
