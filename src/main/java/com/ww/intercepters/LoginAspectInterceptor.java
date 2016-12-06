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
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspectInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginAspectInterceptor.class);

	@Pointcut("execution(public * com.ww.actions..*.*(..))")
	public void pointCut() {

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
		LOGGER.info("位于：" + className + "调用" + methodName + "()方法-开始！");
		Object[] args = jp.getArgs(); // 获得参数列表
		if (args.length <= 0) {
			LOGGER.info("====" + methodName + "方法没有参数");
		} else {
			for (int i = 0; i < args.length; i++) {
				LOGGER.info("====参数  " + (i + 1) + "：" + args[i]);
			}
		}
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
