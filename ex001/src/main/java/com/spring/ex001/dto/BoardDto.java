package com.spring.ex001.dto;

import lombok.Data;

@Data
public class BoardDto {

	private int board_no;
	private String board_type;
	private String title;
	private String contents;
	private String user_id;
	private String created_time;
	private String updated_time;
	private int counts;
}
