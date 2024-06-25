package com.spring.ex001.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex001.dto.BookDto;

/**
 * 하나의 파일에 여러개의 url 매핑가능
 * 파라메터를 자동으로 수집
 * 편리한 jsp 매핑
 */
@Controller
public class HomeController {

	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
	
	/**
	 * mvc 모델
	 * 컨드톨러가 사용자의 요청을 받고 화면을 반환
	 * 
	 * 컨트롤러의 반환 타입
	 * String : 경로/파일명 
	 * 			요청 url 과 반환되는 페이지가 다른 경우
	 * 			로그인 -> 성공, 실패
	 * 			등록 -> 성공, 실패
	 * 			이렇게 반환되는 페이지가 다를 때 반환타입을 주어 다른 페이지로 요청을 할 수 있다
	 * /web-inf/views/파일명.jsp
	 * 
	 * 매개변수에 객체를 지정하면 전달 된 이름에 맞게 매핑해서 데이터를 자동으로 수집 해줍니다
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@GetMapping(value="/bookList1")
	public String test01(BookDto dto, Model model) throws IOException{
		System.out.println("요청정보===================");
		System.out.println(dto.getTitle());
		System.out.println(dto.getAuthor());
		//System.out.println(dto.getList());
		
		// 내장객체의 영역에 저장
		// request.setaddAttribute 와 같은 역할
		model.addAttribute("dto", dto);
		
		// jsp 화면을 서비스
		// 경로 및 파일 이름을 반환 viewresolver에 의해서 경로 및 확장자가 붙어서 파일을 연결
		
		// redirect: 를 붙여주면 redirect(요청정보를 공유하지 않고 페이지주소도 변경)
		// forward : 를 붙여주면 forward (기본값)
		return "redirect:/book/bookList";
	}
	
	/**
	 * 등록화면, 로그인 화면 등 서블릿 로직이 필요하지 않은 경우 void 를 반환타입으로 만들어 줄 수 있다
	 */
	@GetMapping(value = "book/bookList")
	public void test2() {
		
	}
}
