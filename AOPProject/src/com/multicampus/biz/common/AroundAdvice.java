package com.multicampus.biz.common;

import org.springframework.util.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect // Aspect = Pointcut + Advice
public class AroundAdvice {

	@Around("BoardPointcut.allPointcut()")
	public Object aroundLog(ProceedingJoinPoint jp) throws Throwable{ //JoinPoint�� ����Ѵ�.
		
		String method = jp.getSignature().getName();
		Object returnObj = null;
		
		StopWatch watch = new StopWatch();
		watch.start();
		returnObj = jp.proceed();
		watch.stop();
		System.out.println(method + "() �޼ҵ� ���࿡ �ҿ�� �ð� : " + watch.getTotalTimeMillis() + "(ms)��");

		return returnObj;
	}
}
