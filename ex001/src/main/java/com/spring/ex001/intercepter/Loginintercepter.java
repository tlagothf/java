package com.spring.ex001.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 컨트롤러에 들어오는 요청과 컨트롤러의 응답을 가로채는 역할
 * 전처리, 후처리에 사용
 */
public class Loginintercepter implements HandlerInterceptor{

	/**
	 * 컨트롤러를 실행하기 전에 먼저 실행
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 로그인 체크
		HttpSession session = request.getSession();
		System.out.println("인터셉터 => loginId : " + session.getAttribute("id"));
		if (session.getAttribute("id") == null) {
			request.setAttribute("msg", "로그인이 필요한 메뉴입니다");
			request.setAttribute("url", "/login/login");
			request.getRequestDispatcher("/WEB-INF/views/common/msgBox.jsp").forward(request, response);
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
