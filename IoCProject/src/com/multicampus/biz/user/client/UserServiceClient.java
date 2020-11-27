package com.multicampus.biz.user.client;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.multicampus.biz.board.BoardService;
import com.multicampus.biz.board.BoardVO;
import com.multicampus.biz.user.UserService;
import com.multicampus.biz.user.UserVO;

public class UserServiceClient {
	public static void main(String[] args) {
		// 1. ������ �����̳ʸ� �����Ѵ�.
		GenericXmlApplicationContext container = 
			new GenericXmlApplicationContext("business-layer.xml");
		
		// 2. ������ �����̳ʷκ���  BoardService Ÿ���� ����Ͻ� ��ü(BoardServiceImpl)��  Lookup�Ѵ�. 
		UserService userService = (UserService) container.getBean("userService");
		
		if(userService != null) System.out.println(userService + " ��ü Lookup ����!!");
		
		// 3. Lookup�� ����Ͻ� ��ü(BoardServiceImpl)�� �޼ҵ带 �׽�Ʈ�Ѵ�. 
		UserVO vo = new UserVO();
		vo.setId("test");
		vo.setPassword("test");
		UserVO user = userService.getUser(vo);
		
		if(user != null) {
			System.out.println(user.getName() + "�� �α��� ȯ��^^");
		}else{
			System.out.println("�α��� ���ФФ�");
		}
		
		// 4. ������ �����̳ʸ� �����Ѵ�. 
		container.close();
	}
}
