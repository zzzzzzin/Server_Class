package com.test.place.model;

import lombok.Data;

@Data
public class PlaceDTO {

	private String seq;
	private String name;
	private String lat;
	private String lng;
	private String address;
	private String description;
	private String category;
	
	private String marker;
}
