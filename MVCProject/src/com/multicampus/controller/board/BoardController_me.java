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
		return "insertBoard"; // ViewResolver를 타면 안 된다
	}
	
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.POST)
	public String insertBoard(BoardVO vo, BoardDAOJDBC boardDAO) throws Exception {
		boardDAO.insertBoard(vo);
		return "forward:getBoardList.do"; // ViewResolver를 타면 안 된다
	}
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO vo, BoardDAOJDBC boardDAO) throws Exception {
		boardDAO.updateBoard(vo);
		return "forward:getBoardList.do"; // ViewResolver를 타면 안 된다
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo, BoardDAOJDBC boardDAO) throws Exception {
		boardDAO.deleteBoard(vo);
		return "forward:getBoardList.do"; // ViewResolver를 타면 안 된다
	}
	
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, BoardDAOJDBC boardDAO, HttpSession session) throws Exception {
		session.setAttribute("board", boardDAO.getBoard(vo)); 
		return "getBoard";// 접두사 접미사 자동으로 붙는다.
	}
	
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, BoardDAOJDBC boardDAO, HttpSession session) throws Exception {
		//Null check
		if(vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
		if(vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		
		session.setAttribute("boardList", boardDAO.getBoardList(vo)); // boardlist를 boardList의 이름으로 세션에 너ㅎ겠다
		return "getBoardList"; //ViewResolver를 고려한 View이
	}
}
	
public class InsertBoardController implements Controller{

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("게시 글 등록 처리");
		
		// 1. 사용자 입력정보 추출
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		// 2. DB 연동 처리
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setWriter(writer);
		vo.setContent(content);
		
		BoardDAOJDBC boardDAO = new BoardDAOJDBC();
		boardDAO.insertBoard(vo);
		
		// 3. 화면 네비게이션
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:getBoardList.do");
		return mav;
	}

}
*/