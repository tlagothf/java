package ex001;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.spring.ex001.config.MvcConfiguration;

import ex001.config.MvcConfigurationTest;

	
/**
 * 비밀번호 암호화
 * 1. pw필드의 길이를 조정
 * 2. 회원가입시 비밀번호가 암호화되어 저장되도록 처리
 * 3. 로그인시 비밀번호를 조회 하여 matches함수 실행 결과로 로그인 판단
 * 	  사용자로부터 입력 받은 비밀번호와 데이터베이스에 저장된 비밀번호가 일치 하는지 확인
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MvcConfiguration.class)
@WebAppConfiguration
public class BCrtptTest {
	//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Test
	public void encoderTest(){
		System.out.println(encoder.encode("1234"));
		System.out.println(encoder.encode("1234"));
		System.out.println(encoder.encode("1234"));
		System.out.println(encoder.encode("1234"));
		System.out.println(encoder.encode("1234"));
		System.out.println(encoder.encode("1234"));
	}
	
	@Test
	public void march() {
		String encodePw = encoder.encode("123234");
		System.out.println(encoder.matches("1234", encodePw));
	}
}
