package com.test.toy.user.model;

import lombok.Data;

@Data
public class UserDTO {

	private String id; 
	private String pw;
	private String name;
	private String email;
	private String pic;
	private String intro;
	private String regdate;
	private String lv;
	
}
