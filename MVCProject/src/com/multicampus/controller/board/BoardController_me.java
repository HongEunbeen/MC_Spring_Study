/*package com.multicampus.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.multicampus.biz.board.BoardDAOJDBC;
import com.multicampus.biz.board.BoardVO;

@Controller
public class BoardController_me{
	
	
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.GET)
	public String insertBoardView(BoardVO vo, BoardDAOJDBC boardDAO) throws Exception {
		return "insertBoard"; // ViewResolver�� Ÿ�� �� �ȴ�
	}
	
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.POST)
	public String insertBoard(BoardVO vo, BoardDAOJDBC boardDAO) throws Exception {
		boardDAO.insertBoard(vo);
		return "forward:getBoardList.do"; // ViewResolver�� Ÿ�� �� �ȴ�
	}
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO vo, BoardDAOJDBC boardDAO) throws Exception {
		boardDAO.updateBoard(vo);
		return "forward:getBoardList.do"; // ViewResolver�� Ÿ�� �� �ȴ�
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo, BoardDAOJDBC boardDAO) throws Exception {
		boardDAO.deleteBoard(vo);
		return "forward:getBoardList.do"; // ViewResolver�� Ÿ�� �� �ȴ�
	}
	
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, BoardDAOJDBC boardDAO, HttpSession session) throws Exception {
		session.setAttribute("board", boardDAO.getBoard(vo)); 
		return "getBoard";// ���λ� ���̻� �ڵ����� �ٴ´�.
	}
	
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, BoardDAOJDBC boardDAO, HttpSession session) throws Exception {
		//Null check
		if(vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
		if(vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		
		session.setAttribute("boardList", boardDAO.getBoardList(vo)); // boardlist�� boardList�� �̸����� ���ǿ� �ʤ��ڴ�
		return "getBoardList"; //ViewResolver�� ����� View��
	}
}
	
public class InsertBoardController implements Controller{

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("�Խ� �� ��� ó��");
		
		// 1. ����� �Է����� ����
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		// 2. DB ���� ó��
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setWriter(writer);
		vo.setContent(content);
		
		BoardDAOJDBC boardDAO = new BoardDAOJDBC();
		boardDAO.insertBoard(vo);
		
		// 3. ȭ�� �׺���̼�
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:getBoardList.do");
		return mav;
	}

}
*/