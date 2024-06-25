package com.spring.ex001.dto;



import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class BookDto {

	private int no;
	private String title;
	private String author;
	private String publisher;
	private int price;
	private String pub_day;
	private String rent_yn_str;
	private int rent_no;
}
