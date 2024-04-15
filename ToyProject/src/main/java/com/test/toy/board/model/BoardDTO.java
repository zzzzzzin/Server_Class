package com.test.toy.board.model;

import lombok.Data;

@Data
public class BoardDTO {

	private String seq;
	private String subject;
	private String content;
	private String id;
	private String regdate;
	private String readcount;
	
	private String name;	//작성자
	private double isnew;	//최신글
	
	
}
