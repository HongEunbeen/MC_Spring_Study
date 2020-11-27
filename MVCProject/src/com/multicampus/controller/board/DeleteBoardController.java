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
		System.out.println("�Խ� �� ���� ó��");
		
		// 1. ����� �Է����� ����
		String seq = request.getParameter("seq");
		
		// 2. DB ���� ó��
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq));
		
		BoardDAOJDBC boardDAO = new BoardDAOJDBC();
		boardDAO.deleteBoard(vo);
		
		// 3. ȭ�� �׺���̼�
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:getBoardList.do");
		return mav;
	}

}