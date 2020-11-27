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
		
		
		//ȣ��� ����Ͻ� �޼ҵ�  ARGS ���� ����
		Object[] args =  jp.getArgs();
		
		System.out.println("[ ���� ó�� ]" + method + "() �޼ҵ� ARGS ���� : " + args[0].toString());
	}
}