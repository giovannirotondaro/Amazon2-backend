package com.techzon;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.techzon.data.dao.CartaCreditoDao;
import com.techzon.data.dto.CartaCreditoDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.entities.CartaCredito;
import com.techzon.data.services.CartaCreditoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartaCreditoServiceTest {

	
	@Autowired
	CartaCreditoService cartaCreditoService;
	
	@Autowired
	CartaCreditoDao cartaCreditoDao;
	
	@Test
	public void testAdd() {
		CartaCreditoDto c = new CartaCreditoDto();
		c.setTitolare("Mario Rossi");
		c.setCvv("111");
		c.setNumeroCarta("1234567891234512");
		c.setTipologia("masterCard");
		c.setUtenti(new ArrayList<UserDto>());
		c.setAnnoScadenza("2020");
		c.setMeseScadenza("12");
		
		
		cartaCreditoService.add(c);
		List<CartaCredito> carte = cartaCreditoDao.findAll();
		Assert.assertTrue(carte.size() > 0);
	}
	
	@Test
	public void testGetAll() {
		CartaCreditoDto c = new CartaCreditoDto();
		c.setTitolare("Mario Rossi");
		c.setCvv("111");
		c.setNumeroCarta("1234567891234513");
		c.setTipologia("masterCard");
		c.setUtenti(new ArrayList<UserDto>());
		c.setAnnoScadenza("2020");
		c.setMeseScadenza("12");
		
		CartaCreditoDto c1 = new CartaCreditoDto();
		c1.setTitolare("Mario Bianchi");
		c1.setCvv("333");
		c1.setNumeroCarta("1234567891234333");
		c1.setTipologia("maestro");
		c1.setUtenti(new ArrayList<UserDto>());
		c1.setAnnoScadenza("2020");
		c1.setMeseScadenza("12");
		
		
		cartaCreditoService.add(c);
		cartaCreditoService.add(c1);
		List<CartaCreditoDto> carte = cartaCreditoService.getAll();
		Assert.assertTrue(carte.size()>=2);
	}
	
	@Test
	public void testExistWithNumeroCarta() {
		CartaCreditoDto c = new CartaCreditoDto();
		c.setTitolare("Mario Rossi");
		c.setCvv("222");
		c.setNumeroCarta("1234567891234514");
		c.setTipologia("masterCard");
		c.setUtenti(new ArrayList<UserDto>());
		c.setAnnoScadenza("2020");
		c.setMeseScadenza("12");
		
		cartaCreditoService.add(c);
		
		
		boolean exist=cartaCreditoService.existWithNumeroCarta("1234567891234514");
		Assert.assertTrue(exist);
	}
	
	@Test
	public void testgetByNumeroCarta() {
		CartaCreditoDto c = new CartaCreditoDto();
		c.setTitolare("Mario Rossi");
		c.setCvv("987");
		c.setNumeroCarta("1234567891234515");
		c.setTipologia("masterCard");
		c.setUtenti(new ArrayList<UserDto>());
		c.setAnnoScadenza("2020");
		c.setMeseScadenza("12");
		
		cartaCreditoService.add(c);
		
		CartaCreditoDto c1=cartaCreditoService.getByNumeroCarta("1234567891234515");
		Assert.assertTrue(c1.getTitolare()=="Mario Rossi" && c1.getCvv()=="987");
	}
	
	@Test
	public void testDeleteCarta() {
		CartaCreditoDto c = new CartaCreditoDto();
		c.setTitolare("Mario Rossi");
		c.setCvv("111");
		c.setNumeroCarta("1234567891234599");
		c.setTipologia("masterCard");
		c.setUtenti(new ArrayList<UserDto>());
		c.setAnnoScadenza("2020");
		c.setMeseScadenza("12");
		
		cartaCreditoService.add(c);
		
		cartaCreditoService.deleteByCardId(cartaCreditoService.getByNumeroCarta("1234567891234599").getId());
		
		boolean exist=cartaCreditoService.existWithNumeroCarta("1234567891234599");
		
		Assert.assertTrue(!exist);		
	}
	
	@Test
	public void testgetById() {
		CartaCreditoDto c = new CartaCreditoDto();
		c.setTitolare("Mario Bianchi");
		c.setCvv("111");
		c.setNumeroCarta("1234567891234517");
		c.setTipologia("masterCard");
		c.setUtenti(new ArrayList<UserDto>());
		c.setAnnoScadenza("2020");
		c.setMeseScadenza("12");
		
		cartaCreditoService.add(c);
		
		
		CartaCreditoDto c1=cartaCreditoService.getById(Long.valueOf(1));
		Assert.assertTrue(c1.getTitolare()=="Mario Bianchi");
	}

	
	
	
	
	







}
