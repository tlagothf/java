package com.spring.ex001.dto;

import lombok.Data;

@Data
public class MemberDto {
	private int rn;
	private String id;
	private String pw;
	private String name;
	private String admin_yn;
	private String reg_date;
	private String udt_date;
}
