package com.multicampus.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class LogAdvice {

	@Before("BoardPointcut.allPointcut()")
	public void printLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		
		
		//호출된 비즈니스 메소드  ARGS 정보 추출
		Object[] args =  jp.getArgs();
		
		System.out.println("[ 사전 처리 ]" + method + "() 메소드 ARGS 정보 : " + args[0].toString());
	}
}
