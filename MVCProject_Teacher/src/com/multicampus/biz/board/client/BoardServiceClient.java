package com.multicampus.biz.board.client;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.multicampus.biz.board.BoardService;
import com.multicampus.biz.board.BoardVO;

public class BoardServiceClient {
	public static void main(String[] args) {
		// 1. 스프링 컨테이너를 구동한다.
		GenericXmlApplicationContext container = 
			new GenericXmlApplicationContext("business-layer.xml");
		
		// 2. 스프링 컨테이너로부터  BoardService 타입의 비즈니스 객체(BoardServiceImpl)를  Lookup한다. 
		BoardService boardService = (BoardService) container.getBean("boardService");
		
		if(boardService != null) System.out.println(boardService + " 객체 Lookup 성공!!");
		
		// 3. Lookup한 비즈니스 객체(BoardServiceImpl)의 메소드를 테스트한다. 
		BoardVO vo = new BoardVO();
		vo.setSeq(101);
		vo.setTitle("Spring 테스트");
		vo.setWriter("테스터");
		vo.setContent("Spring 테스트중입니다....");
//		boardService.insertBoard(vo);
		
		List<BoardVO> boardList = boardService.getBoardList(vo);
		System.out.println("[ BOARD LIST ]");
		for (BoardVO board : boardList) {
			System.out.println("---> " + board.toString());
		}
		
		// 4. 스프링 컨테이너를 종료한다. 
		container.close();
	}
}
