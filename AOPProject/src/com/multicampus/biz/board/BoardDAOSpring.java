package com.multicampus.biz.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.multicampus.biz.common.JDBCUtil;

// 2. DAO(Data Access Object) Ŭ����
//@Reqository
public class BoardDAOSpring implements BoardDAO/* extends JdbcDaoSupport*/{ //���߿� �ٸ� Ŭ���� ��� ����(���� ��� x)
	
	@Autowired
	private JdbcTemplate spring; //���� ������ ���� Ŭ���� 
	
	// BOARD ���̺� ���� SQL ���ɾ��
	private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values((select nvl(max(seq), 0)+1 from board),?,?,?)";
	private final String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOARD_GET    = "select * from board where seq=?";
	private final String BOARD_LIST   = "select * from board order by seq desc";
	
	/*@Autowired
	public void setSuperDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	*/
	// CRUD ����� �޼ҵ� ����
	// �� ���
	public void insertBoard(BoardVO vo) {
		System.out.println("===> SPRING ������� insertBoard() ��� ó��");
		spring.update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
		//getJdbcTemplet().update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
	}

	// �� ����
	public void updateBoard(BoardVO vo) {
		System.out.println("===> SPRING ������� updateBoard() ��� ó��");
		spring.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
	}

	// �� ����
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> SPRING ������� deleteBoard() ��� ó��");
		spring.update(BOARD_DELETE, vo.getSeq());
	}
	
	// �� �� ��ȸ
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> SPRING ������� getBoard() ��� ó��");
		Object[] args = {vo.getSeq()};
		return spring.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}

	// �� ��� �˻�
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> SPRING ������� getBoardList() ��� ó��");
		return spring.query(BOARD_LIST, new BoardRowMapper());
	}
}