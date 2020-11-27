package com.multicampus.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.multicampus.biz.board.BoardDAOJDBC;
import com.multicampus.biz.board.BoardVO;

public class GetBoardController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, 
			                          HttpServletResponse response) throws Exception {
		System.out.println("�Խ� �� �� ��ȸ ó��");
		
		// 1. ����� �Է����� ����
		String seq = request.getParameter("seq");
		
		// 2. DB ���� ó��
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq));
		
		BoardDAOJDBC boardDAO = new BoardDAOJDBC();
		BoardVO board = boardDAO.getBoard(vo);
		
		// 3. �˻� ����� JSP���� ����� �� �ֵ��� ���ǿ� ����Ѵ�.
		HttpSession session = request.getSession();
		session.setAttribute("board", board);
		
		// �� �� ȭ������ �̵��Ѵ�.
		// response.sendRedirect("getBoard.jsp");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("getBoard");
		return mav;
	}

}