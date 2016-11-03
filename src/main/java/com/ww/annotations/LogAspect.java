package com.ww.annotations;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	@AfterReturning("@annotation(logannotation)")
	public void insertLogSuccess(JoinPoint joinpoint, LogAnnotation logannotation) {
		String moduleName = logannotation.moduleName();
		String signature = joinpoint.getSignature().toString();
		String methodName = signature.substring(signature.lastIndexOf(".") + 1, signature.indexOf("("));
		System.out.println("module:" + moduleName + "---method:" + methodName);
	}

}
