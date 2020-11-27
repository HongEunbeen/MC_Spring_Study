package com.multicampus.biz.board.client;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.multicampus.biz.board.BoardService;
import com.multicampus.biz.board.BoardVO;

public class BoardServiceClient {
	public static void main(String[] args) {
		// 1. ������ �����̳ʸ� �����Ѵ�.
		GenericXmlApplicationContext container = 
			new GenericXmlApplicationContext("business-layer.xml");
		
		// 2. ������ �����̳ʷκ���  BoardService Ÿ���� ����Ͻ� ��ü(BoardServiceImpl)��  Lookup�Ѵ�. 
		BoardService boardService = (BoardService) container.getBean("boardService");
		
		if(boardService != null) System.out.println(boardService + " ��ü Lookup ����!!");
		
		// 3. Lookup�� ����Ͻ� ��ü(BoardServiceImpl)�� �޼ҵ带 �׽�Ʈ�Ѵ�. 
		BoardVO vo = new BoardVO();
		vo.setSeq(101);
		vo.setTitle("Spring �׽�Ʈ");
		vo.setWriter("�׽���");
		vo.setContent("Spring �׽�Ʈ���Դϴ�....");
//		boardService.insertBoard(vo);
		
		List<BoardVO> boardList = boardService.getBoardList(vo);
		System.out.println("[ BOARD LIST ]");
		for (BoardVO board : boardList) {
			System.out.println("---> " + board.toString());
		}
		
		// 4. ������ �����̳ʸ� �����Ѵ�. 
		container.close();
	}
}
