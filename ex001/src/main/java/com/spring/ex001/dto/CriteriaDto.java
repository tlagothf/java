package com.spring.ex001.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

/*
 * 페이징 처리를 위한 필드와
 * 검색을 위한 필드의 정보를 담고있는 객체
 * */
@Component
@Data
public class CriteriaDto {
	
	int pageNo = 1; /* 요청페이지번호*/
	int amount = 10;	/* 페이지당 게시물의 수*/
	
	String searchField = ""; /*검색필드*/
	String searchWord = ""; /*검색어*/
	
	public CriteriaDto() {}
	
	/*
	 * 초기화
	 * */
	public CriteriaDto(String parmpageNo) {
		super();
		try {
			if(parmpageNo != null && !"".equals(parmpageNo)) {
				this.pageNo = Integer.parseInt(parmpageNo);			
			}
		} catch (Exception e) {
			System.out.println("parmpageNo를 숫자로 변경중 문제가 발생하였습니다.");
		}
		

	}

	public CriteriaDto(String parmpageNo, String searchField, String searchWord) {
		this(parmpageNo);
		
		this.searchField = searchField;
		this.searchWord = searchWord;
	}

	
}
