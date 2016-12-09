package com.ww.intercepters;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ww.service.AccessTraceService;
import com.ww.vo.AccessLogVO;

//@Aspect
@Component
public class LoginAspectInterceptor {
	private static String accessLoggerName = "accessLog";
	private static final Logger LOGGER = LoggerFactory.getLogger(accessLoggerName);
	@Autowired
	private AccessTraceService accessTraceService;

	@Pointcut("execution(public * com.ww.actions..*.*(..))")
	public void LoginAspectInterceptor() {

	}

	@AfterReturning(pointcut = "pointCut()")
	public void simpleAdvice() {
		LOGGER.info("");
	}

	@Around("pointCut()")
	public Object aroundLogCalls(ProceedingJoinPoint jp) throws Throwable {
		LOGGER.info("正常运行");

		return jp.proceed();
	}

	@Before("pointCut()")
	public void before(JoinPoint jp) {
		String className = jp.getThis().toString();
		String methodName = jp.getSignature().getName(); // 获得方法名
		AccessLogVO logvo = new AccessLogVO();
		Object[] args = jp.getArgs(); // 获得参数列表

		LOGGER.info("=====================================");
	}

	@AfterThrowing("pointCut()")
	public void catchInfo() {
		LOGGER.info("异常信息");
	}

	@After("pointCut()")
	public void after(JoinPoint jp) {
		LOGGER.info("" + jp.getSignature().getName() + "()方法-结束！");
		LOGGER.info("=====================================");
	}
}
