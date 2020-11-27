package com.multicampus.biz.board;

import java.sql.Date;

import lombok.Data;

// 1. VO(Value Object) 클래스
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode
@Data
public class BoardVO {
	// BOARD 테이블의 컬럼 이름과 데이터타입이 일치하는 private 멤버변수 선언
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
}







