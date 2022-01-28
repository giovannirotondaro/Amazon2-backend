package com.techzon;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.techzon.data.dao.RegioneDao;
import com.techzon.data.dto.RegioneDto;
import com.techzon.data.entities.Regione;
import com.techzon.data.services.RegioneService;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class RegioneServiceTest {
	
	@Autowired
	RegioneDao regioneDao;
	
	@Autowired
	RegioneService regioneService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Test
	public void testGetAllName() {
		RegioneDto regioneDto = new RegioneDto();
		regioneDto.setNome("Basilicata");
		regioneDao.save(modelMapper.map(regioneDto, Regione.class));
		
		RegioneDto regioneDto2 = new RegioneDto();
		regioneDto2.setNome("Calabria");
		regioneDao.save(modelMapper.map(regioneDto2, Regione.class));
		
		List<RegioneDto> regioni = regioneService.getAllName();
		
		Assert.assertEquals(regioni.size(), 2);
	}
}
