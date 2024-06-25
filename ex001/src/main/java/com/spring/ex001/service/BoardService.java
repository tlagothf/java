package com.spring.ex001.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ex001.dto.BoardDto;
import com.spring.ex001.dto.CriteriaDto;
import com.spring.ex001.dto.PageDto;
import com.spring.ex001.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	BoardMapper mapper;
	
	@Autowired
	PageDto pageDto;
	
	public Map<String, Object> getList(CriteriaDto cri){
		Map<String, Object> map = new HashMap<String, Object>();
		// 리스트 조회 및 map 에 저장
		List<BoardDto> list = mapper.getList(cri);
		map.put("list", list);
		
		int totalCnt = mapper.getTotalCnt(cri);
		pageDto.setPageDto(totalCnt, cri.getPageNo(), cri.getAmount());
		map.put("pageDto", pageDto);
		return map;
	}

	public BoardDto detaileBoard(int board_no) {
		// 조회수 없
		int res = mapper.upCount(board_no);
		// 게시물의 정보를 반환
		return mapper.detaileBoard(board_no);
	}
	
	public int delBoard(BoardDto board) {
		return mapper.delBoard(board);
	}


}
