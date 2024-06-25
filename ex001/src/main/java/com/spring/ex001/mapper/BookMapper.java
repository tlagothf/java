package com.spring.ex001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.spring.ex001.dto.BookDto;
import com.spring.ex001.dto.CriteriaDto;

@Mapper
public interface BookMapper {

	@Select("select sysdate from dual")
	public String select();
	
	/**
	 * 동적쿼리를 생성 하기 위해서 xml 파일을 생성
	 */
	public List<BookDto> getBookList(CriteriaDto cri);

	/**
	 * 페이징 처리를 위해 총 건수를 조회 합니다
	 * @return
	 */
	@Select("select count(*) from book")
	public int getTotalCnt();

	public int insertBook(BookDto book);


	@Select("select * from book where no = ${no}")
	public BookDto getBookno(int no);

	@Delete("delete from book where no =${no}")
	public int delBook(int no);
	
}
