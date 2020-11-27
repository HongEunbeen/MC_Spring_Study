package com.multicampus.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.multicampus.biz.board.BoardDAOJDBC;
import com.multicampus.biz.board.BoardVO;
import com.multicampus.biz.user.UserDAOJDBC;
import com.multicampus.biz.user.UserVO;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String boardEncoding;
    
    public DispatcherServlet() {
        System.out.println("===> DispatcherServlet ����");
    }
    
    public void init(ServletConfig config) throws ServletException {
    	this.boardEncoding = config.getInitParameter("encoding");
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. �ѱ� ���ڵ��� ó���Ѵ�.
		request.setCharacterEncoding(boardEncoding);
		
		// 1. ����� ��û path ������ �����Ѵ�.
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println("����� PATH : " + path);
		
		// 2. ��û path�� ���� ������ �б�ó���Ѵ�. 
		if(path.equals("/login.do")) {
			System.out.println("�α��� ���� ó��");
			
			// 1. ����� �Է����� ����
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			// 2. DB ���� ó��
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setPassword(password);
			
			UserDAOJDBC userDAO = new UserDAOJDBC();
			UserVO user = userDAO.getUser(vo);
			
			// 3. ȭ��׺���̼�
			if(user != null) {
				//�α��� ������, ���� ������ ���ǿ� ����Ѵ�.
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				
				response.sendRedirect("getBoardList.do");
			} else {
				response.sendRedirect("login.jsp");
			}
			
		} else if(path.equals("/insertBoard.do")) {
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
			response.sendRedirect("getBoardList.do"); // session ���ƴٰ� �����ϴϱ� jsp�� �ٷ� �� �����̰� do ���ļ� ����.
			
		} else if(path.equals("/updateBoard.do")) {
			System.out.println("�Խ� �� ���� ó��");
			
			// 1. ����� �Է����� ����
			String title = request.getParameter("title");
			String seq = request.getParameter("seq");
			String content = request.getParameter("content");
			
			// 2. DB ���� ó��
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setSeq(Integer.parseInt(seq));
			vo.setContent(content);
			
			BoardDAOJDBC boardDAO = new BoardDAOJDBC();
			boardDAO.updateBoard(vo);
			
			// 3. ȭ�� �׺���̼�
			response.sendRedirect("getBoard.do?seq=" +vo.getSeq());
			
		} else if(path.equals("/deleteBoard.do")) {
			System.out.println("�Խ� �� ���� ó��");
			
			// 1. ����� �Է����� ����
			String seq = request.getParameter("seq");
			
			// 2. DB ���� ó��
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAOJDBC boardDAO = new BoardDAOJDBC();
			boardDAO.deleteBoard(vo);
			
			// 3. ȭ�� �׺���̼�
			response.sendRedirect("getBoardList.do");
			
		} else if(path.equals("/getBoard.do")) {
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
			
			//�˻� ����� ����� JSP�� �̵��Ѵ�.
			response.sendRedirect("getBoard.jsp");
			
		} else if(path.equals("/getBoardList.do")) {
			System.out.println("�Խ� �� ��� �˻� ó��");
			
			// 1. ����� �Է����� ����(�˻� ���� ������ �������� �ϼ���.)
			
			// 2. DB ���� ó��
			BoardVO vo = new BoardVO();
			BoardDAOJDBC boardDAO = new BoardDAOJDBC();
			List<BoardVO> boardList = boardDAO.getBoardList(vo);
			
			// 3. �˻��� �� ����� View(JSP)���� ����� �� �ֵ��� ���ǿ� ����Ѵ�.
			HttpSession session = request.getSession();
			session.setAttribute("boardList", boardList);
			
			//�� ��� ȭ������ �̵��Ѵ�.
			response.sendRedirect("getBoardList.jsp");
		} else {
			System.out.println("��û URL�� �ٽ� Ȯ�����ּ���.");
		}
	}

}








