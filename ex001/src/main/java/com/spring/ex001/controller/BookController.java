package com.spring.ex001.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oreilly.servlet.MultipartRequest;
import com.spring.ex001.dto.BookDto;
import com.spring.ex001.dto.CriteriaDto;
import com.spring.ex001.dto.FileDto;
import com.spring.ex001.service.BookService;
import com.spring.ex001.service.FileService;

@Controller
public class BookController {
	
	@Autowired
	BookService service;
	
	@Autowired
	FileService fileservice;
	
	/**
	 * 상세 페이지에서 리스트 페이지를 요청 할 때 , pageNo를 전달 함으로서 원하는 페이지를 만들 수 있다
	 * cri :
	 * 1. 페이지 번호와 페이지당 게시물 수 - 페이징 처리
	 * 2. 검색어 검색필드 - 검색
	 * @param cri
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/bookList")
	public String getBookList(CriteriaDto cri, Model model) {
		// pageNo 를 출력
		System.out.println("pageNo : " + cri.getPageNo());
		cri.getSearchField();
		cri.getSearchWord();

		// 서비스를 통해 도서목록과 pageDto 객체를 반환
		model.addAttribute("map", service.getBookList(cri));
		
		return "book/bookList";
	}
	
	// 화면이동
	@GetMapping("/bookReg")
	public String bookReg() {
		return "/book/bookRegister";
	}
	
	// 등록 처리
	@PostMapping("/bookRegAction")
	public String bookRegAction(HttpServletRequest request) {
		
		Map<String, Object> map = service.bookReg(request);
		
		
		// 메세지 처리 후 화면 이동
		// 서블릿 호출 : 새로운 요청으로 이전의 조건을 없앤다
		return "redirect:/bookList";
	}
	
	// 상세화면
	
	@GetMapping("/datailBook")
	// @RequestParam 을 이용할 경우 , 필수가 아닌경우 required = false 를 넣어주어야함
	// integer 를 사용하여 형변환 하지 않을 경우, 값이 입력되지 않으면 null 오류 발생
	public String detailBook(@RequestParam(value = "no", required = false) Integer no, Model model) {
		
		// 도서번호가 입력되지 않은 경우
		if (no == 0) {
			model.addAttribute("msg", "존재하지 않는 도서입니다");
			return "/common/msgBox";
		}
		// 화면 수정 - 제목을 클릭하면 /detailBook 을 요청
		// book.dateilBook.jsp 를 반환
		// 도서정보를 조회
		// 1. bookDto 조회
		// no 를 받아서 조회
		Map<String, Object> map = service.getBookno(no);
		model.addAttribute("map", map);
		// 2. 파일정보 조회 - 리스트
		// mapper 인터페이스에 메서드 추가
		// mapper xml 에 쿼리 추가
		return "/book/detailBook";
	}
	
	@GetMapping("/delBook")
	public String delBook(@RequestParam("pageNo") int pageNo, @RequestParam("no") int no, Model model) {
		// 도서번호에 해당하는 번호의 정보를 삭제
		int res = service.delBook(no);
		// 삭제 성공
		if(res>0) {
			model.addAttribute("msg", "삭제 되었습니다" );
			model.addAttribute("url", "/bookList?pageNo="+pageNo);
		}else {
			// 삭제 실패
			model.addAttribute("msg", "삭제증 오류가 발생했습니다");
			
		}
		return "/common/msgBox";
	}
	
	@GetMapping("/updateBook")
	public String updateBook() {
		return "redirect:datailBook";
	}
	
	
}
