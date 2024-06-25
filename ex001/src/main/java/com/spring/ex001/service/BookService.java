package com.spring.ex001.service;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oreilly.servlet.MultipartRequest;
import com.spring.ex001.dto.BookDto;
import com.spring.ex001.dto.CriteriaDto;
import com.spring.ex001.dto.FileDto;
import com.spring.ex001.dto.PageDto;
import com.spring.ex001.mapper.BookMapper;

@Service
public class BookService {
	
	@Autowired
	BookMapper mapper;
	
	@Autowired
	FileService fileservice;
	

	@Autowired
	PageDto pageDto;
	
	public Map<String, Object> getBookList(CriteriaDto cri){
		Map<String, Object> map = new HashMap<String, Object>();
		// 리스트 조회
		List<BookDto> list =  mapper.getBookList(cri);
		// 총 건수 조회
		int totalCnt = mapper.getTotalCnt();
		// pageDto 세팅
		pageDto.setPageDto(totalCnt, cri.getPageNo(), cri.getAmount());
		
		// map 에 담아서 반환
		map.put("list", list);
		map.put("pageDto", pageDto);
		return map;
	}

	public Map<String, Object> bookReg(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String saveDir = "c:\\upload";
		int maxSize = 1024*1000;
		String encoding = "UTF-8";
		try {
			MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, encoding);
			// 도서정보 취합
			BookDto book = new BookDto();
			book.setTitle(mr.getParameter("title"));
			book.setAuthor(mr.getParameter("author"));
			book.setPublisher(mr.getParameter("publisher"));
			book.setPrice(Integer.parseInt(mr.getParameter("price")));
			
			// book 테이블에 입력 - selectKey 를 통해서 no(seq값) 를 받아옴
			int res = mapper.insertBook(book);
			
			// 첨부파일의 이름
			//String fileName = mr.getFilesystemName("uploadFile");
			// file 태그의 이름 목록
			//Enumeration<String> e = mr.get
			// book 테이블에 입력 -> seq를 반환 받아옴
			FileDto file = new FileDto();
			file.setType("book");
			// book 테이블의 no 와 연동됨
			file.setSeq(book.getNo() + "");// 시퀀스번호
			// 첨부파일 저장
			fileservice.attachFileSave(mr, file);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/*
	 * 도서정보 및 첨부파일 정보를 조회
	 */
	public Map<String, Object> getBookno(int no) {
		// 도서정보
		BookDto book = mapper.getBookno(no);
		Map<String, Object> map = new HashMap<String, Object>();
		// 도서에 첨부된 파일 정보
		List<FileDto> fileList = fileservice.getfilList("book", no+"");
		
		map.put("book", book);
		map.put("fileList", fileList);
		
		return map;
	}

	public int delBook(int no) {
		int res = mapper.delBook(no);
		if(res > 0) {
			// 첨부파일 삭제
			fileservice.delFile("book", no+"");
		}
		return res;
	}
	
	
}
