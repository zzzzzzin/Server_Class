package com.test.todo.model;

import lombok.Data;

@Data
public class TodoDTO {

	private String seq;
	private String todo; 
	private String state;
	private String regdate;
}
