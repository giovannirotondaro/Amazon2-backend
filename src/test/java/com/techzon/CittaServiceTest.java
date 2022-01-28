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

import com.techzon.data.dao.CittaDao;
import com.techzon.data.dao.ProvinciaDao;
import com.techzon.data.dao.RegioneDao;
import com.techzon.data.dto.CittaDto;
import com.techzon.data.dto.ProvinciaDto;
import com.techzon.data.dto.RegioneDto;
import com.techzon.data.entities.Citta;
import com.techzon.data.entities.Provincia;
import com.techzon.data.entities.Regione;
import com.techzon.data.services.CittaService;
import com.techzon.data.services.ProvinciaService;
import com.techzon.data.services.RegioneService;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class CittaServiceTest {
		
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
	
	@Test
	public void testGetAllCitta() {
		RegioneDto regioneDto = new RegioneDto();
		regioneDto.setNome("Calabria");
		regioneDao.save(modelMapper.map(regioneDto, Regione.class));
		
		ProvinciaDto provinciaDto = new ProvinciaDto();
		provinciaDto.setNome("Cosenza");
		provinciaDto.setRegione(regioneService.getAllName().get(0));
		provinciaDao.save(modelMapper.map(provinciaDto, Provincia.class));
		
		CittaDto cittaDto = new CittaDto();
		cittaDto.setNome("Montalto Uffugo");
		cittaDto.setProvincia(provinciaService.getAllName().get(0));
		cittaDao.save(modelMapper.map(cittaDto, Citta.class));
		
		CittaDto cittaDto2 = new CittaDto();
		cittaDto2.setNome("Rende");
		cittaDto2.setProvincia(provinciaService.getAllName().get(0));
		cittaDao.save(modelMapper.map(cittaDto2, Citta.class));
		
		List<CittaDto> res = cittaService.getByProvincia("Cosenza");
		Assert.assertTrue(res.size() == 2);	
	}
}
