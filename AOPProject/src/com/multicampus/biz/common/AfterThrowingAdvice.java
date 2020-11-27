package com.multicampus.biz.common;

import java.sql.SQLException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterThrowingAdvice {

	@AfterThrowing(pointcut = "BoardPointcut.allPointcut()", throwing="exceptionObj")
	public void exceptionLog(JoinPoint jp, Exception exceptionObj) {
		String method = jp.getSignature().getName();		
		System.out.println("[ ���� ó�� ]" + method +  "() �޼ҵ� ���� �� ���� �߻�");
		
		if(exceptionObj instanceof IllegalArgumentException) {
			System.out.println("�������� ������ �ԷµǾ����ϴ�.");
		}else if(exceptionObj instanceof ArithmeticException) {
			System.out.println("0���� ���ڸ� ���� ����  �����ϴ�.");
		}else if(exceptionObj instanceof SQLException) {
			System.out.println("SQL ������ ������ �ֽ��ϴ�.");
		}else {
			System.out.println("���� �߻�!!!");
		}
	}
}
