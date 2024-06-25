package com.spring.ex001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.spring.ex001.dto.CriteriaDto;
import com.spring.ex001.dto.FileDto;

@Mapper
public interface FileMapper {

	public int insertFile(FileDto dto);

	/**
	 * 파일목록 조회
	 * @param type
	 * @param seq
	 * @return
	 */
	@Select("select * from attach_file where type=#{type} and seq=#{seq}")
	public List<FileDto> getFileList(@Param("type") String type
										,@Param("seq") String seq);

	/**
	 * 파일목록 삭제
	 * @param type
	 * @param seq
	 * @return
	 */
	@Delete("select * from attach_file where type=#{type} and seq=#{seq}")
	public int delFile(@Param("type")String type
							, @Param("seq") String seq);

	
}
