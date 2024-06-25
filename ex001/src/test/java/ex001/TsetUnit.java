package ex001;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.ex001.config.MvcConfiguration;
import com.spring.ex001.dto.BookDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MvcConfiguration.class)

public class TsetUnit {
	/**
	 * 단위테스트도구
	 * 
	 * Test어노테이션이 적용된 메서드를 실행하고 실행결과 목록을 출력
	 * 	초록 : 성공
	 * 	파란 : 실패
	 * 	빨강 : 오류
	 * 
	 * 실행하고 싶은 메서드를 선택후 실행 하면 해당 메서드만 실행이 된다
	 *
	 * 스프링설정파일을 읽어 IOC컨테이너를 만들고 객체를 미리 생성해 두었다고 
	 * 필요한 시점에 주입 받아서 사용
	 * 
	 * IOC컨테이너에 생성된 객체를 테스트 하기 위해서 설정이 필요!!!
	 */ 
	
	// 객체를 주입
	@Autowired
	BookDto dto;
	
	@Test
	public void test() {
		System.out.println(dto);
		assertNotNull(dto);
	}
	
	@Test
	public void test1() {
		int i = 10*5;
		// 실행 결과와 예상값을 비교
		assertEquals(i, 50);
	}
	@Test
	public void test2() {
		BookDto dto = null;
		// 실행 결과와 예상값을 비교
		assertNull(dto);
	}
}
