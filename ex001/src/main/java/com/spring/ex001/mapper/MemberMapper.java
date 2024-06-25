package com.spring.ex001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.spring.ex001.dto.CriteriaDto;
import com.spring.ex001.dto.MemberDto;

/**
 * 1. mapper 는 인터페이스로 생성
 * 2. Mapper 어노테이션 지정
 */
@Mapper
public interface MemberMapper {

	@Select("select * from member where id = #{id}")
	MemberDto login(MemberDto member);

		
	List<MemberDto> getmemberList(CriteriaDto cri);
	
	@Select("select count(*) from member")
	int getTotalCnt();
	
	@Select("select * from member where id = #{id}")
	MemberDto checkId(MemberDto member);
	
	int insertMember(MemberDto mamber);
	
	int updatePw(MemberDto member);

	@Update("update member set admin_yn = #{admin_yn} where id = #{id}")
	int changeAdminYN(MemberDto member);
	}




