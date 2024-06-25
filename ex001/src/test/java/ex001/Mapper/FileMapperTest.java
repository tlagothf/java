package ex001.Mapper;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.spring.ex001.config.MvcConfiguration;
import com.spring.ex001.dto.FileDto;
import com.spring.ex001.mapper.FileMapper;
import com.spring.ex001.service.FileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MvcConfiguration.class)
@WebAppConfiguration
public class FileMapperTest {

	@Autowired
	FileMapper mapper;
	
	@Autowired
	FileService service;
	
	@Test
	public void delFile() {
		// 파일 목록 조회
		List<FileDto> fileList = service.getfilList("book", "751");
		for(FileDto file : fileList) {
			String filepath = FileService.FILEPATH + File.separator + file.getSfilename();
			System.out.println("filepath :" + filepath);
		}
		// 파일 객체 생성
		File realfile = new File("c:\\auploa\\20240619_1693708.pptx");
		realfile.delete();
	}
	
	@Test
	public void getFileTest() {
		mapper.getFileList("book", "30");
		System.out.println("getFileList");
		
	}

	@Test
	public void regFileTset() {
		FileDto dto = new FileDto();
		dto.setType("book");
		dto.setSeq("560");
		dto.setIdx(1);
		dto.setOfilename("oname");
		dto.setSfilename("sname");
		int res = mapper.insertFile(dto);
	}

}
