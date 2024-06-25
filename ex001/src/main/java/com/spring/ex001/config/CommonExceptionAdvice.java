package com.spring.ex001.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionAdvice {

	// Exception 이 발생된 모든 경우 해당 메서드를 실행
	@ExceptionHandler(Exception.class)
	public String except(Exception e, Model model) {
		e.printStackTrace();
		model.addAttribute("msg", "예외사항 발생. 관리자에게 문의해주세요" + e.getMessage());
		return "common/msgBox";
	}
}
