package com.spring.ex001.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class JSFunction {
	
	/**
	 * 메세지를 출력 후 이전 페이지로 돌아갑니다.
	 * @param string
	 * @param response
	 */
	public static void alertBack(String msg, HttpServletResponse response) {
		try {
		response.setContentType("text/html;charset=utf-8");
		String script = ""
				+ "<script>"
				+ " alert('"+ msg +"');"
				+ " history.back();"
				+ "</script>";
			response.getWriter().print(script);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 메세지를 출력 후 url로 이동
	 * @param string
	 * @param response
	 */
	public static void alertLocation(String msg, String url, HttpServletResponse response) {
		try {
		response.setContentType("text/html;charset=utf-8");
		String script = ""
				+ "<script>"
				+ " alert('"+ msg +"');"
				+ " location.href='" + url +"';"
				+ "</script>";
			response.getWriter().print(script);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
