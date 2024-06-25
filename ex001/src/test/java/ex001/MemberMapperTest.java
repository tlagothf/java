package ex001;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.spring.ex001.config.MvcConfiguration;
import com.spring.ex001.dto.CriteriaDto;
import com.spring.ex001.dto.MemberDto;
import com.spring.ex001.mapper.MemberMapper;
import com.spring.ex001.service.MemberService;

import ex001.config.MvcConfigurationTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MvcConfigurationTest.class)
/**
 * 	테스트를 위한 설정
 * 	@WebAppConfiguration : 웹 애플리케이션의 환경설정을 로드하고, 필요한 서블릿 기능을 제공
 * 	+ servlet 버전 3.1.0 으로 업데이트
 */
@WebAppConfiguration
public class MemberMapperTest {
	@Autowired
	MemberMapper mapper;
	
	@Autowired
	MemberService service;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	
	@Test
	public void updateMember() {
		MemberDto member = new MemberDto();
		member.setId("aa");
		member.setPw(passwordEncoder.encode("1234"));
		member.setAdmin_yn("Y");
		int res = mapper.updatePw(member);
		System.out.println("res : "+ res);
	}
	
	@Test
	public void insertMember() {
		MemberDto member = new MemberDto();
		member.setId("zzzzz");
		member.setPw("12");
		member.setName("말랑이");
		member.setAdmin_yn("Y");
		int res = service.insertMamber(member);
		System.out.println("res : "+ res);
	}
	
	@Test
	public void checkId() {
		MemberDto member = new MemberDto();
		member.setId("id82302");
		MemberDto res = mapper.checkId(member);
		// 등록된 ID : member 객체
		// 미등록 ID : null
		System.out.println("res : " + res);
	}
	
	@Test
	public void checkIdService() {
		MemberDto member = new MemberDto();
		member.setId("id");
		MemberDto res = service.checkId(member);
		// 등록된 ID : member 객체
		// 미등록 ID : null
		System.out.println("res : " + res);
	}
	
	
	@Test
	public void memberLoginService() {
		MemberDto dto = new MemberDto();
		dto.setId("id");
		dto.setPw("pw");
		MemberDto member = service.login(dto);
		assertEquals(dto.getId(), member.getId());
	}
	
	@Test
	public void memberLogin() {
		System.out.println(mapper);
		MemberDto dto = new MemberDto();
		dto.setId("id");
		dto.setPw("pw");
		MemberDto member = mapper.login(dto);
		// 일치하는  회원이 없을 경우 null 을 반환
		System.out.println("member + " + member);
		System.out.println("로그인사용자 :" + member.getId());
		assertEquals(dto.getId(), member.getId());
		
	}
	
	@Test
	public void getTotalCnt() {
		int res = mapper.getTotalCnt() ;
		System.out.println(res);
		assertEquals(2560, res);
	}
	@Test
	public void getmemberList() {
		List<MemberDto> map = mapper.getmemberList(new CriteriaDto());
		System.out.println(map);
	}
	
	@Test
	public void getMemberService() {
		Map<String, Object> map = service.getmemberList(new CriteriaDto());

	    assertNotNull(map.get("list"));
	    assertNotNull(map.get("pageDto"));
	}
	
	@Test
	public void seach() {
		CriteriaDto cri = new CriteriaDto("2", "id", "555");
		List<MemberDto> list = mapper.getmemberList(cri);
		System.out.println(list);
	}
	

	
}
