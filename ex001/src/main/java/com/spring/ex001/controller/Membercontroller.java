package com.spring.ex001.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.ex001.dto.CriteriaDto;
import com.spring.ex001.service.MemberService;

@Controller
public class Membercontroller {

	@Autowired
	MemberService service;
	
	@GetMapping(value = "/memberList")
	public String getMemberList(CriteriaDto cri, Model model) {
		Map<String, Object> map = service.getmemberList(cri);		
		model.addAttribute("map", map);
		System.out.println(" 서치 :" + cri.getSearchField());
		System.out.println(" 설치 :" + cri.getSearchWord());

		
		
		return "member/MemberList";
	}
}
