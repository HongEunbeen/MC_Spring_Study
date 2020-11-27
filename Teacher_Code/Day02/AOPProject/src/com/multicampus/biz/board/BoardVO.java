package com.multicampus.biz.board;

import java.sql.Date;

import lombok.Data;

// 1. VO(Value Object) Ŭ����
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode
@Data
public class BoardVO {
	// BOARD ���̺��� �÷� �̸��� ������Ÿ���� ��ġ�ϴ� private ������� ����
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
}







