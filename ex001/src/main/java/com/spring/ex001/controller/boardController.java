package com.spring.ex001.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.ex001.dto.BoardDto;
import com.spring.ex001.dto.CriteriaDto;
import com.spring.ex001.service.BoardService;

@Controller
public class boardController {

	@Autowired
	BoardService service;
	
	@GetMapping("/boardList")
	public String getList(CriteriaDto cri, Model model) {
		try {
			
			// 게시물의 목록을 조회후 화면에서 출력을 위해 map에 저장
			model.addAttribute("map", service.getList(cri));
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "/common/msgBox";
		}
		
		return "/board/boardList";
	}
	
	@GetMapping("/detaileboard")
	public String detailBoard(@RequestParam(value="board_no", required = false) int board_no,Model model) {
		
		if (board_no == 0) {
			model.addAttribute("msg", "글이 존재하지 않습니다");
			return "/common/msgBox";
		}
		
		BoardDto board = service.detaileBoard(board_no);
		model.addAttribute("board",board);
		
		return "/board/detaileBoard";
	}
	
	@GetMapping("/delboard")
	public String delboard(BoardDto board, @RequestParam(value = "pageNo", required = false)Integer pageNo, HttpSession session, 
							Model model , RedirectAttributes rttr) {
		
		String login_id = "";
		if (session.getAttribute("id") != null) {
			login_id = (String)session.getAttribute("id");
		}
		
		board.setUser_id(login_id);
		System.out.println("board :" + board.getBoard_no());
		System.out.println("board :" + board.getUser_id());
		
		int res = service.delBoard(board);
		
		if (res > 0) {
			// ㅠㅔ이지 번호를 유지하기 위해서 화면으로부터 페이지번호를 받아옴
			rttr.addAttribute("pageNo", pageNo);
			// 삭제성공
			return "redirect:/boardList";
		} else {
			model.addAttribute("msg", "삭제중 예외사항 발생.관리자에게 문의해주세요.");
			// 삭제실패
			return "common/msgBox";
		}
		
	}
}
