package com.test.toy.board.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class BoardDTO {
	
	private String seq;
	private String subject;
	private String content;
	private String id;
	private String regdate;
	private String readcount;
	
	private String name; //작성자
	private double isnew;//최신글
	
	private String commentcount; //댓글 수
	
	private int thread; //답변형
	private int depth;
	
	private String attach; //첨부파일
	
	private ArrayList<String> tag; //해시 태그
	
	private String secret; //비밀글
	
	

}










