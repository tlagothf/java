package com.spring.ex001.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
// 기본생성자
@NoArgsConstructor
// 모든 필드를 이용한 생성자
@AllArgsConstructor
@Data
	public class PageDto {
		/* 페이지 블럭을 그리기 위해서 필요한 값 */
		private int startNo;	/* 페이지 블럭의 시작번호 */
		private int endNo;		/* 페이지 블럭의 끝번호*/
		private boolean prev, next; /* 페이지 블럭을 이동하기 위한 버튼*/
		
		private int realEndNo;	/* endNo, next를 설정하는데 필요한 값 전체게시물수로 구한 진짜 끝번호 */
		
		/* 페이지블럭을 그리기 위한 값을 설정하기 위해 필요한 값*/
		int totalCnt; 	/* 게시물의 총건수 */
		int pageNo;		/* 사용자가 요청한 페이지 번호 */
		int amount; 	/* 페이지당 보여줄 게시물 번호 */
		
		//public PageDto() {}
		
		/**
		 * 생성자를 이용해서 페이지블럭을 초기화 합니다.
		 * @param totalCnt
		 * @param pageNo
		 * @param amount
		 */
		public PageDto(int totalCnt, int pageNo, int amount) {
			this.totalCnt = totalCnt;
			this.pageNo = pageNo;
			this.amount = amount; /*페이지당 게시물의수*/
			
			// 1. 페이지 블럭의 끝번호 구하기
			// 7페이지를 요청, 블럭당 5의 페이지를 보여준다면
			// 올림(7/5) * 5 = 10
			endNo = (int)(Math.ceil(pageNo/5.0) * 5) ;

			// 2. 페이지 블럭의 시작번호 구하기
			startNo = endNo - (5-1);
			
			// 3. 끝 페이지 번호
			// 총건수와 페이지당 게시물수를 이용하여 진짜 끝 페이지의 번호를 구합니다.
			// 총게시물의 수를 페이지당 게시물의 수로 나누어 진짜 끝페이지 번호를 구할수 있다
			// 이때, 정수타입으로 연산시 소수점 올림처리를 할수 없으므로 1.0을 곱하여 실수로 변환후 연산하고 연산결과를 정수로 형변환 처리 한다
			realEndNo = (int)(Math.ceil((totalCnt*1.0)/amount));
			
			// 페이지 블럭의 끝번호가 실제 페이지의 끝번호 보다 큰경우, 의미없이 페이지번호가 출력 될수 있으므로 페이지블럭의 끝번호를 실제 페이지 끝번호로 변경한다
			endNo = endNo > realEndNo ? realEndNo : endNo;
					
			// 4. 페이지블럭을 이동할 수 있는 버튼을 보여줄지 말지를 설정
			prev = startNo > 1 ? true : false;
			next = endNo == realEndNo ? false : true;
			
			
		}
		
		/**
		 * 생성자를 이용해서 페이지블럭을 초기화 합니다.
		 * @param totalCnt
		 * @param pageNo
		 * @param amount
		 */
		public void setPageDto(int totalCnt, int pageNo, int amount) {
			this.totalCnt = totalCnt;
			this.pageNo = pageNo;
			this.amount = amount; /*페이지당 게시물의수*/
			
			// 1. 페이지 블럭의 끝번호 구하기
			// 7페이지를 요청, 블럭당 5의 페이지를 보여준다면
			// 올림(7/5) * 5 = 10
			endNo = (int)(Math.ceil(pageNo/5.0) * 5) ;

			// 2. 페이지 블럭의 시작번호 구하기
			startNo = endNo - (5-1);
			
			// 3. 끝 페이지 번호
			// 총건수와 페이지당 게시물수를 이용하여 진짜 끝 페이지의 번호를 구합니다.
			// 총게시물의 수를 페이지당 게시물의 수로 나누어 진짜 끝페이지 번호를 구할수 있다
			// 이때, 정수타입으로 연산시 소수점 올림처리를 할수 없으므로 1.0을 곱하여 실수로 변환후 연산하고 연산결과를 정수로 형변환 처리 한다
			realEndNo = (int)(Math.ceil((totalCnt*1.0)/amount));
			
			// 페이지 블럭의 끝번호가 실제 페이지의 끝번호 보다 큰경우, 의미없이 페이지번호가 출력 될수 있으므로 페이지블럭의 끝번호를 실제 페이지 끝번호로 변경한다
			endNo = endNo > realEndNo ? realEndNo : endNo;
					
			// 4. 페이지블럭을 이동할 수 있는 버튼을 보여줄지 말지를 설정
			prev = startNo > 1 ? true : false;
			next = endNo == realEndNo ? false : true;
		}
	} 

