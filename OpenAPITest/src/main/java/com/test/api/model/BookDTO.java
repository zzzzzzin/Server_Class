package com.test.api.model;

import lombok.Data;

@Data
public class BookDTO {
	
	private String title;
	private String link;
	private String image;
	private String author;
	private String discount;
	private String publisher;
	private String description;
}
