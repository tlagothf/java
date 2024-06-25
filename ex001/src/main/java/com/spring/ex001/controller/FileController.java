package com.spring.ex001.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FileController {

	/**
	 * http://localhost:8020/download?oname=실습_script.sql&sname=실습_script20240620_16342612.sql
	 * oname : 원본파일 이름
	 * sname : 저장된 파일 이름
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@GetMapping("/download")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dir = "c:\\upload";
		// 원본파일 이름 - 파일 다운로드시 다운로드 된 파일의 이름을 지정하기 위해서
		String oname = "";
		if(request.getParameter("oname") != null) {
			oname = URLEncoder.encode( request.getParameter("oname"), "UTF-8");			
		}
		// 저장된 이름으로 파일을 생성
		String sname = request.getParameter("sname");
		
		System.out.println("oname : " + oname);
		System.out.println("sname : " + sname);
		
		File file = new File(dir + File.separator + sname);
		
		// 파일이 존재 하는 경우, 다운로드
		if (file.exists()) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Length", "" + file.length());
			// 다운로드 파일 이름을 지정 합니다
			response.setHeader("Content-Disposition", "attachmemt; filename=\"" + oname + "\"");
			
			ServletOutputStream ostream = response.getOutputStream();
			FileInputStream istream = new FileInputStream(file);
			
			int bytesRead = -1;
			byte[] buffer = new byte[4096];
			while((bytesRead = istream.read(buffer) )> -1) {
				 ostream.write(buffer, 0, bytesRead);
			} 
			
			istream.close();
			ostream.close();
			
		// 파일이 존재하지 않는 경우 ,메세지 처리
		} else {
			request.setAttribute("msg", "파일이 존재하지 않습니다");
			request.getRequestDispatcher("/WEB-INF/views/common/msgBox.jsp").forward(request, response);
		}
	}
}
