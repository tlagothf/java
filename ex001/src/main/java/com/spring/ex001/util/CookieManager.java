package com.spring.ex001.util;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
	
	// 쿠키의 이름과 값, 유효기간을 사용자로부터 전달받아서 쿠키를 생성
	// HttpServletResponse response 객체는 참조형 변수로 주소의 값만을 참조하고있다
	// 때문에 반환값이 없어도 유지가 되고 값을 넣고 빼고 수정이 가능
	public static void makeCookie(HttpServletResponse response, 
									String name, String value, int maxAge) {
		// 쿠키생성
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		
		// 응답객체 추가
		response.addCookie(cookie);
	}
	// 쿠키삭제
	public static void deleteCookie(HttpServletResponse response, 
			String name) {
		// 쿠키생성 - 쿠키를 제거하기 위해서 응답시간은 0으로 설정
		Cookie cookie = new Cookie(name, "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		
		// 응답객체 추가
		response.addCookie(cookie);
}
	// 쿠키값을 반환
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie[] cookies =  request.getCookies();
		for(Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				return cookie.getValue();
			}
		}
		return "";
	}
}
