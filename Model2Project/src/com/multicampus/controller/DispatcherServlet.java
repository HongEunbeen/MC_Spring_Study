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
        System.out.println("===> DispatcherServlet 생성");
    }
    
    public void init(ServletConfig config) throws ServletException {
    	this.boardEncoding = config.getInitParameter("encoding");
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 한글 인코딩을 처리한다.
		request.setCharacterEncoding(boardEncoding);
		
		// 1. 사용자 요청 path 정보를 추출한다.
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println("추출된 PATH : " + path);
		
		// 2. 요청 path에 따라 로직을 분기처리한다. 
		if(path.equals("/login.do")) {
			System.out.println("로그인 인증 처리");
			
			// 1. 사용자 입력정보 추출
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			// 2. DB 연동 처리
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setPassword(password);
			
			UserDAOJDBC userDAO = new UserDAOJDBC();
			UserVO user = userDAO.getUser(vo);
			
			// 3. 화면네비게이션
			if(user != null) {
				//로그인 성공시, 상태 정보를 세션에 등록한다.
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				
				response.sendRedirect("getBoardList.do");
			} else {
				response.sendRedirect("login.jsp");
			}
			
		} else if(path.equals("/insertBoard.do")) {
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
			response.sendRedirect("getBoardList.do"); // session 거쳤다가 가야하니까 jsp로 바로 안 움직이고 do 거쳐서 간다.
			
		} else if(path.equals("/updateBoard.do")) {
			System.out.println("게시 글 수정 처리");
			
			// 1. 사용자 입력정보 추출
			String title = request.getParameter("title");
			String seq = request.getParameter("seq");
			String content = request.getParameter("content");
			
			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setSeq(Integer.parseInt(seq));
			vo.setContent(content);
			
			BoardDAOJDBC boardDAO = new BoardDAOJDBC();
			boardDAO.updateBoard(vo);
			
			// 3. 화면 네비게이션
			response.sendRedirect("getBoard.do?seq=" +vo.getSeq());
			
		} else if(path.equals("/deleteBoard.do")) {
			System.out.println("게시 글 삭제 처리");
			
			// 1. 사용자 입력정보 추출
			String seq = request.getParameter("seq");
			
			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAOJDBC boardDAO = new BoardDAOJDBC();
			boardDAO.deleteBoard(vo);
			
			// 3. 화면 네비게이션
			response.sendRedirect("getBoardList.do");
			
		} else if(path.equals("/getBoard.do")) {
			System.out.println("게시 글 상세 조회 처리");
			
			// 1. 사용자 입력정보 추출
			String seq = request.getParameter("seq");
			
			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAOJDBC boardDAO = new BoardDAOJDBC();
			BoardVO board = boardDAO.getBoard(vo);
			
			// 3. 검색 결과를 JSP에서 사용할 수 있도록 세션에 등록한다.
			HttpSession session = request.getSession();
			session.setAttribute("board", board);
			
			//검색 결과를 출력할 JSP로 이동한다.
			response.sendRedirect("getBoard.jsp");
			
		} else if(path.equals("/getBoardList.do")) {
			System.out.println("게시 글 목록 검색 처리");
			
			// 1. 사용자 입력정보 추출(검색 정보 추출은 여러분이 하세요.)
			
			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			BoardDAOJDBC boardDAO = new BoardDAOJDBC();
			List<BoardVO> boardList = boardDAO.getBoardList(vo);
			
			// 3. 검색된 글 목록을 View(JSP)에서 사용할 수 있도록 세션에 등록한다.
			HttpSession session = request.getSession();
			session.setAttribute("boardList", boardList);
			
			//글 목록 화면으로 이동한다.
			response.sendRedirect("getBoardList.jsp");
		} else {
			System.out.println("요청 URL을 다시 확인해주세요.");
		}
	}

}








