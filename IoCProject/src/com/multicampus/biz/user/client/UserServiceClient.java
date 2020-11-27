package com.multicampus.biz.user.client;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.multicampus.biz.board.BoardService;
import com.multicampus.biz.board.BoardVO;
import com.multicampus.biz.user.UserService;
import com.multicampus.biz.user.UserVO;

public class UserServiceClient {
	public static void main(String[] args) {
		// 1. 스프링 컨테이너를 구동한다.
		GenericXmlApplicationContext container = 
			new GenericXmlApplicationContext("business-layer.xml");
		
		// 2. 스프링 컨테이너로부터  BoardService 타입의 비즈니스 객체(BoardServiceImpl)를  Lookup한다. 
		UserService userService = (UserService) container.getBean("userService");
		
		if(userService != null) System.out.println(userService + " 객체 Lookup 성공!!");
		
		// 3. Lookup한 비즈니스 객체(BoardServiceImpl)의 메소드를 테스트한다. 
		UserVO vo = new UserVO();
		vo.setId("test");
		vo.setPassword("test");
		UserVO user = userService.getUser(vo);
		
		if(user != null) {
			System.out.println(user.getName() + "님 로그인 환영^^");
		}else{
			System.out.println("로그인 실패ㅠㅠ");
		}
		
		// 4. 스프링 컨테이너를 종료한다. 
		container.close();
	}
}
