<%@page import="com.multicampus.biz.board.BoardDAOJDBC"%>
<%@page import="com.multicampus.biz.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	// 1. ����� �Է����� ����
	
	request.setCharacterEncoding("EUC-KR");
	String seq = request.getParameter("seq");
	
	// 2. DB ���� ó��
	BoardVO vo = new BoardVO();
	vo.setSeq(Integer.parseInt(seq));
	
	BoardDAOJDBC boardDAO = new BoardDAOJDBC();
	boardDAO.deleteBoard(vo);
	
	//3. ȭ�� �׺���̼�
	response.sendRedirect("getBoardList.jsp");
%>