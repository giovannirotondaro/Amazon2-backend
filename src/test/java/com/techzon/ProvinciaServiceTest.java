package com.techzon;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.techzon.data.dao.CittaDao;
import com.techzon.data.dao.ProvinciaDao;
import com.techzon.data.dao.RegioneDao;
import com.techzon.data.dto.ProvinciaDto;
import com.techzon.data.dto.RegioneDto;
import com.techzon.data.entities.Provincia;
import com.techzon.data.entities.Regione;
import com.techzon.data.services.CittaService;
import com.techzon.data.services.ProvinciaService;
import com.techzon.data.services.RegioneService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProvinciaServiceTest {
	
	@Autowired
	CittaService cittaService;
	
	@Autowired
	ProvinciaService provinciaService;
	
	@Autowired
	RegioneService regioneService;
	
	@Autowired
	CittaDao cittaDao;
	
	@Autowired
	ProvinciaDao provinciaDao;
	
	@Autowired
    RegioneDao regioneDao;
	
	@Autowired
	ModelMapper modelMapper;
	
	private static boolean setUpIsDone = false;
	
	@Before
	public void setUp() {
	    if (setUpIsDone) {
	        return;
	    }
	    setUpIsDone = true;
		RegioneDto regioneDto = new RegioneDto();
		regioneDto.setNome("Basilicata");
		regioneDao.save(modelMapper.map(regioneDto, Regione.class));
		
		ProvinciaDto provinciaDto = new ProvinciaDto();
		provinciaDto.setNome("Matera");
		provinciaDto.setRegione(regioneService.getAllName().get(0));
		provinciaDao.save(modelMapper.map(provinciaDto, Provincia.class));
		
		ProvinciaDto provinciaDto2 = new ProvinciaDto();
		provinciaDto2.setNome("Potenza");
		provinciaDto2.setRegione(regioneService.getAllName().get(0));
		provinciaDao.save(modelMapper.map(provinciaDto2, Provincia.class));
	}
	
	@Test
	public void testGetAllName() {
		List<ProvinciaDto> res = provinciaService.getAllName();
		Assert.assertTrue(res.size() == 2);	
	}
	
	@Test
	public void testGetByRegione() {
		List<ProvinciaDto> res = provinciaService.getByRegione("Basilicata");
		Assert.assertTrue(res.size() == 2);	
	}
}
