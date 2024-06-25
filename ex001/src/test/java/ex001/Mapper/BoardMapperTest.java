package ex001.Mapper;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.spring.ex001.dto.BoardDto;
import com.spring.ex001.dto.CriteriaDto;
import com.spring.ex001.mapper.BoardMapper;
import com.spring.ex001.service.BoardService;
import com.spring.ex001.service.BookService;

import ex001.config.MvcConfigurationTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MvcConfigurationTest.class)
/**
 * 	테스트를 위한 설정
 * 	@WebAppConfiguration : 웹 애플리케이션의 환경설정을 로드하고, 필요한 서블릿 기능을 제공
 * 	+ servlet 버전 3.1.0 으로 업데이트
 */
@WebAppConfiguration
public class BoardMapperTest {

	@Autowired
	BoardMapper mapper;
	
	@Autowired
	CriteriaDto cri;
	
	@Autowired
	BoardService service;
	
	@Test
	public void delboard() {
		BoardDto board = new BoardDto();
		board.setUser_id("aa");
		board.setBoard_no(50);
		
		int res = mapper.delBoard(board);
		assertEquals(1, res);
		System.out.println(res);
	}
	
	@Test
	public void getSeach() {
		cri.setSearchField("user_id");
		cri.setSearchWord("1");
		System.out.println(service.getList(cri));
	}
	
	@Test
	public void getList() {
		// CriteriaDto : 페이지 정보 (pageNo, amount)
		//				 검색정보
		cri.setSearchField("user_id");
		cri.setSearchWord("1");
		List<BoardDto> list = mapper.getList(cri);
		System.out.println(list);
	}
}
