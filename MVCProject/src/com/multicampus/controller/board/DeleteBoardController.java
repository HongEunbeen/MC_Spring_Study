package com.multicampus.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.multicampus.biz.board.BoardDAOJDBC;
import com.multicampus.biz.board.BoardVO;

public class DeleteBoardController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("게시 글 삭제 처리");
		
		// 1. 사용자 입력정보 추출
		String seq = request.getParameter("seq");
		
		// 2. DB 연동 처리
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq));
		
		BoardDAOJDBC boardDAO = new BoardDAOJDBC();
		boardDAO.deleteBoard(vo);
		
		// 3. 화면 네비게이션
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:getBoardList.do");
		return mav;
	}

}
