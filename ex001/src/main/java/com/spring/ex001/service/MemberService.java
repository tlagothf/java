package com.spring.ex001.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.ex001.dto.CriteriaDto;
import com.spring.ex001.dto.MemberDto;
import com.spring.ex001.dto.PageDto;
import com.spring.ex001.mapper.MemberMapper;

@Service
public class MemberService {

	@Autowired
	MemberMapper mapper;
	
	@Autowired
	PageDto pageDto;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	public MemberDto login(MemberDto member) {
		
		// 해당 아이디의 사원의 정보를 조회
		MemberDto dbmember = mapper.login(member);
		if(dbmember == null) {
			return dbmember;
		}
		// 사용자가 입력한 비밀번호오 데이터 베이스에 입력된 비밀번호가 일치하는지 확인
		if(encoder.matches(member.getPw(), dbmember.getPw())) {
			// 로그인 성공
			return dbmember;
		}else {
			// 로그인 실패
			return null;
		}
		
		//return mapper.login(member);
	}

	public Map<String, Object> getmemberList(CriteriaDto cri) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 회원목록
		List<MemberDto> list = mapper.getmemberList(cri);
		// 페이지 블럭을 그리기 위해 총건수 조회
		int totalCnt = mapper.getTotalCnt();
		pageDto.setPageDto(totalCnt, cri.getPageNo(), cri.getAmount());
		
		map.put("list", list);
		map.put("pageDto", pageDto);
		return map;
	}
	
	public MemberDto checkId(MemberDto member) {
		return mapper.checkId(member);
	}

	/**
	 * 회원가입
	 * @param member
	 * @return
	 */
	// 회원가입
	public int insertMamber(MemberDto member) {
		// 비밀번호 암호화 처리
		member.setPw(encoder.encode(member.getPw()));
		return mapper.insertMember(member);
	}

	public int changeAdminYN(MemberDto member) {
		return mapper.changeAdminYN(member);
	}
}
