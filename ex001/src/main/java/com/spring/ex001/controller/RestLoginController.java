package com.spring.ex001.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ex001.dto.BoardDto;
import com.spring.ex001.dto.CriteriaDto;
import com.spring.ex001.dto.MemberDto;
import com.spring.ex001.service.MemberService;


/**
 * 객체나 문자열을 반환 
 * 
 * 화면을 건드리지 않고 화면을 전환 해주기 위해서 RestController 를 사용한다
 */
@RestController
public class RestLoginController {
	
	@Autowired
	MemberService service;

	@GetMapping("/checkId")
	public ResponseEntity<String> checkId(
											// Dto 객체가 정의되지 않은 경우, 파라메터를 전달 받는 방법
											//@RequestParam(required = false) String id
											// Dto 객체에가 정의되어 있는경우, 클래스명 변수명 을 입력해주면 된다
											MemberDto member) {
		String msg = "";
		System.out.println("id : " + member.getId());
		
		// 파라매터로 아이디가 넘어오지 않은 경우
		if (member.getId() == null || "".equals(member.getId())) {
			msg = "아이디를 입력해주세요";
		}else {
			
			// 서비스를 통해서 파라메터로 전달받은 id가 데이터 베이스에 존재하는지 확인
			MemberDto res = service.checkId(member);
			if (res == null) {
				// 사용가능한 ID
				
			}else {
				// 중복된 ID
				msg = "이미 사용중인 ID 입니다";
			}
		}
		
		// import Spring
		// ResponseEntity : HTTP 헤더 정보와 추가적인 데이터를 전달 할 떄 사용
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/plain; charset=UTF-8");
		
		// json 형식의 문자열을 생성 {"키 : ""값:, msg" : "아이디를 입력해주세요"}
		String jsonMsg = "{\"msg\": \""+msg+"\"}";
		
		return new ResponseEntity<String>(jsonMsg, header, HttpStatus.OK);
		
		// @ResponseBoby 타입명(또는 클래스명) 과 return 의 변수명은 함께 바꿔줘야한다
		// 객체를 json 형식의 문자열로 반환
		// @ResponseBoby를 붙이지 않으면 jsp를 찾아간다ㅓ
	}
	
	@PostMapping("/changeAdminYN")
	// @RequestBody : JSON 형식의 문자열을 전달 받아서 자바 객체로 변환
	public ResponseEntity<Map<String, Object>>changeAdminYN(@RequestBody MemberDto member){
		System.out.println("member :" + member);
		// member 정보 업데이트
		int res = service.changeAdminYN(member);
		// 키의 타입, 값의 타입
		Map<String, Object> map = new HashMap<String, Object>();
		// admin_yn 값을 업데이트 후 결과를 반환
		map.put("res", res);
		
		Map<String, Object> mamberMap = service.getmemberList(new CriteriaDto());
		map.put("mamberMap", mamberMap);
		
		// 웹드라우저로 전달할 객체를 생성
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json; charset=UTF-8");
		return new ResponseEntity<Map<String, Object>>(map, header, HttpStatus.OK);
	}

}
