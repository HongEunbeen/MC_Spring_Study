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
	public Object aroundLog(ProceedingJoinPoint jp) throws Throwable{ //JoinPoint를 상속한다.
		
		String method = jp.getSignature().getName();
		Object returnObj = null;
		
		StopWatch watch = new StopWatch();
		watch.start();
		returnObj = jp.proceed();
		watch.stop();
		System.out.println(method + "() 메소드 수행에 소요된 시간 : " + watch.getTotalTimeMillis() + "(ms)초");

		return returnObj;
	}
}
