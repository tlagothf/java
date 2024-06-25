package com.spring.ex001.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.ex001.dto.MemberDto;
import com.spring.ex001.service.MemberService;
import com.spring.ex001.util.CookieManager;

@Controller
public class LoginController {
	
	@Autowired
	MemberService service;
	
	@GetMapping(value = "/login/login")
	public void login(@CookieValue(name = "id", required = false) String saveCooikeId
						, Model model) {
		// 내장객체의 영역에 저장
		model.addAttribute("saveCooikeId", saveCooikeId);
	}
	
	// 로그인 버튼이 클릭되면 실행 할 메서드를 정의
	// 사용자로부터 입력받은 아이디/비밀번호를 수집 - memberdto 에 자동수집
	// id,pw 출력
	// list 페이지로 이동
	@PostMapping(value = "/loginAction")
	public String loginAction(MemberDto paramMember, HttpSession session, Model model, RedirectAttributes rttr
								// 서버로 전달된 파라메터를 수집, 필수값이 아닌 경우 required = false 를 넣어주어야 함
								// 여러개의 속성이 나열 될 경우 value 속성도 입력 해야함
								, @RequestParam(value = "chkSabeId", required = false) String chkSaveId
								, HttpServletResponse response) {
		// filter 를 통해서 요청 객체의 문자 인코딩을 지정
		//request.setCharacterEncoding("utf-8");
		
		// 체크박스에 체크가 되었으면 쿠키에 아이디를 저장
		// 체크박스가 체크 되었다면
		if(chkSaveId != null) {
			// 쿠키에 아이디를 저장
			// 쿠키 생성 후 응답객체에 추가하는 메서드
			CookieManager.makeCookie(response, "id", paramMember.getId(), 60*60*24*7);
		} else {
			CookieManager.deleteCookie(response, "id");
		}
		System.out.println(paramMember.getId());
		System.out.println(paramMember.getPw());
		// 로그인
		
		// 서비스 - 사용자로부터 입력받은 사용자의 id/pw에 일치하는 회원이 있으면 member 객체를 생성하여 반환
		MemberDto member = service.login(paramMember);
		// mapper
		if(member == null) {
			// 로그인실패 - 로그인페이지로 이동후 메세지 출력
			// model.addAttribute("msg", "id/pw를 확인해주세요.");
			// 리다이렉트시 요청 영역이 공유되지 않으므로 리다이렉트시 데이터를 유지하기 위해서
			// 잠시 세션영역에 저장
			rttr.addFlashAttribute("msg", "id,pw를 확인해주세요");
			// 로그인 페이지로 리다이렉트(서블릿을 호출)
			return "redirect:/login/login";
		} else {
			// 로그인
			// request영역에 저장된 경우, redirect시에 데이터가 공유되지 않는다!!!!!
			model.addAttribute("modelId", member.getId());
			// 내부적으로 세션객체에 담아서 한번 사용하고 폐기
			rttr.addFlashAttribute("rttrId", member.getId());
			
			// 로그인성공 - 세션에 저장후 리스트로 이동
			session.setAttribute("id", member.getId());
			session.setAttribute("memberDto", member);
			return "redirect:/bookList";
		}
		// memberDto 에 값을 받아오고 반환
		
		// 서블릿을 호출 하는 방법
		// forward, redirect
		// 만약 호출하려고 하는 서블릿의 메서드와 호출된 메서드가 다른 경우 redirect 해주어야함
		// 반환하는 서블릿이 같은 요청 메서드를 가지고 있을 경우 forward

		
	}
	
	// logout 이 호출되면 로그아웃 처리 후 로그인페이지로 전환
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		// 세션 만료 후 로그인 페이지로 리다이렉트
		return "redirect:/login/login";
	}
	
	@GetMapping(value = "/signup")
	public String signup(HttpSession session) {
		session.invalidate();
		
		return "/login/signup";
	}
	
	@GetMapping(value = "/signupAction")
	public String signupAction(MemberDto member, Model model) {
		
		int res = service.insertMamber(member);
		// 회원가입 처리
		if (res > 0) {
			// 가입이 성공이면
			model.addAttribute("msg", "회원가입 완료.재로그인 해주세요");
			model.addAttribute("url", "/login/login");			
		}else {
			// 회원가입 실패
			model.addAttribute("msg", "회원가입 도중 예외 발생.다시 시도 해주세요");
		}
		// msg 출력 후 지정된 url 로 이동 (url 이 없으면 뒤로가기)
		return "/common/msgBox";
	}
	
}
