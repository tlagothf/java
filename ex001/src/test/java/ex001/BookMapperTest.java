package ex001;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.spring.ex001.config.MvcConfiguration;
import com.spring.ex001.dto.BookDto;
import com.spring.ex001.dto.CriteriaDto;
import com.spring.ex001.dto.PageDto;
import com.spring.ex001.mapper.BookMapper;
import com.spring.ex001.service.BookService;

import ex001.config.MvcConfigurationTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MvcConfiguration.class)
/**
 * 	테스트를 위한 설정
 * 	@WebAppConfiguration : 웹 애플리케이션의 환경설정을 로드하고, 필요한 서블릿 기능을 제공
 * 	+ servlet 버전 3.1.0 으로 업데이트
 */
@WebAppConfiguration
public class BookMapperTest {
	
	@Autowired
	BookMapper mapper;
	
	@Autowired
	BookService service;
	
	@Test
	public void regBook() {
		BookDto book = new BookDto();
		book.setTitle("안녕");
		book.setAuthor("반가워");
		book.setPublisher("중앙");
		book.setPrice(20000);
		// selectKey에 의해서 조회된 시퀀스 번호가 dto 의 no 에 저장된다 
		int res = mapper.insertBook(book);
		System.out.println("no : " + book.getNo());
		assertEquals(1, res);
	}
	
	@Test
	public void testService() {
		Map<String, Object> map = service.getBookList(new CriteriaDto());
		List<BookDto> list = (List<BookDto>)map.get("list");
		PageDto pageDto = (PageDto)map.get("pageDto");
		System.out.println(list);
		System.out.println(pageDto);
	}
	
	@Test
	public void getTotalCnt() {
		int cnt = mapper.getTotalCnt();
		System.out.println(cnt);
		assertEquals(608, cnt);
	}
	
	@Test
	public void getBookList() {
		List<BookDto> list = mapper.getBookList(new CriteriaDto());
		System.out.println(list);
		assertNotNull(list);
	}
	
	@Test
	public void select() {
		String sysdate = mapper.select();
		System.out.println(sysdate);
		
		LocalDate now = LocalDate.now();
		System.out.println(now.toString());
		System.out.println(sysdate.substring(0,11));
		//assertNotNull(sysdate);
		assertEquals(sysdate.substring(0,11), sysdate.substring(0,11).toString());
	}
}
