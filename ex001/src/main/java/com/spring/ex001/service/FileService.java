package com.spring.ex001.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oreilly.servlet.MultipartRequest;
import com.spring.ex001.dto.CriteriaDto;
import com.spring.ex001.dto.FileDto;
import com.spring.ex001.dto.PageDto;
import com.spring.ex001.mapper.FileMapper;

@Service
public class FileService {
	
	public static final String FILEPATH="c:\\upload";
	@Autowired
	FileMapper mapper;
	
	@Autowired
	PageDto pageDto;
	/**
	 * 외부로부터 파일객체에 입력 할 테이블정보, seq정보를 받아옴
	 * @param mr
	 * @param dto
	 */
	public void attachFileSave(MultipartRequest mr, FileDto dto) {

		//Enumeration<String> e = mr.getParameterNames();
		// 화면의 파일태그의 이름을 가지고 오는 메서드
		Enumeration<String> e = mr.getFileNames();
		int idx = 0;
		// 다음 요소가 있는지 확인 true/false 로 반환
		// 첨부된 파일의 정보를 데이터 베이스에 저장
		while (e.hasMoreElements()) {
			// 다음요소를 가져오는 메서드
			String elName = (String) e.nextElement();
			// 첨부된 원본 파일의 이름
			String oname = mr.getFilesystemName(elName);
			// 첨부된 파일이 없는 경우 다음 파일로 넘어간다
			if (oname==null) {
				continue;
			}
			// 저장되는 파일의 이름
			String sname  = rename(oname);
			
			dto.setIdx(idx);
			dto.setOfilename(oname);
			dto.setSfilename(sname);
			
			System.out.println("=================");
			System.out.println(dto.getIdx());
			System.out.println(dto.getOfilename());
			System.out.println(dto.getSfilename());
			int res = mapper.insertFile(dto);
			idx++;
			
		}
		
		/*
		// dto 객체에 파일정보를 추가
		// idx, ofilename, sfilename
		String fileName = mr.getFilesystemName("uploadFile");
		dto.setIdx(0);
		dto.setOfilename(fileName);
		// TODO:파일 이름이 중복되어서 파일이 소실 될 수 있어 이름을 변경해야함
		
		dto.setSfilename(fileName);
		int res = mapper.insertFile(dto);
		*/
	}
	
	/**
	 * 파일명이 중복될 경우, 파일이 소실 될 위험이 있으므로
	 * 파일 이름에 업로드 된 시간을 추가하여 파일명을 변경
	 * 원본파일명 -> 저장 파일명으로 변경 및 파일 이름 변경
	 * @param filename : 원본파일명
	 * @return 저장파일명
	 */
	public String rename(String filename) {
		//String filename ="aaa.jsp";
		
		//파일 이름
		String oldfilename = filename.substring(0,filename.lastIndexOf("."));
		// 파일 확장자
		String ext = filename.substring(filename.lastIndexOf("."));
		// 현재 시간을 형식에 맞게 문자열로 반환
		// aaa_20340607153720999.jpg - 업로드 한 시간을 이름에 대입
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		
		String newFileName = oldfilename+now+ext;
		
		File oldFile = new File(FILEPATH + File.separator + filename);
		File newfile = new File(FILEPATH + File.separator + newFileName);
		// 파일의 이름을 변경합니다
		oldFile.renameTo(newfile);
		
		// 새로운 파일 명을 반환
		return newFileName;
	}


	public List<FileDto> getfilList(String type, String seq) {
		List<FileDto> fileList = mapper.getFileList(type, seq);
		return fileList;
		
	}

	public void delFile(String type, String seq) {
		// 파일 목록 조회
		List<FileDto> fileList = getfilList("book", seq);
		// 파일 목록을 돌면서 파일을 삭제
		for(FileDto file : fileList) {
			String filepath = FileService.FILEPATH + File.separator + file.getSfilename();
			System.out.println("filepath :" + filepath);
			// 파일 객체 생성
			File realfile = new File(filepath);
			realfile.delete();
		}
		
		// 첨부파일 테이블의 데이터를 삭제
		int res = mapper.delFile(type, seq);
	}
}
