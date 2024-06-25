package com.spring.ex001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.spring.ex001.dto.BoardDto;
import com.spring.ex001.dto.CriteriaDto;

@Mapper
public interface BoardMapper {

	public List<BoardDto> getList(CriteriaDto cri);


	public int getTotalCnt(CriteriaDto cri);

	@Update("update board set counts=counts+1 where board_no=${board_no}")
	public int upCount(int board_no);
	
	@Select("select * from board where board_no=${board_no}")
	public BoardDto detaileBoard(int board_no);
	
	//@Delete("delete from board where board_no =#{board_no} and user_id=#{user_id}")
	/**
	 * del_yn(삭제여부)컬럼을 이용하여 테이블에서 데이터를 직접 삭제하지 않고
	 * 삭제 된 것 처럼 처리
	 * @param board
	 * @return
	 */
	@Update("update board set del_yn='Y' where board_no =#{board_no} and user_id=#{user_id}")
	public int delBoard(BoardDto board);

}
