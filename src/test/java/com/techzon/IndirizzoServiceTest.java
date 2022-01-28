package com.techzon;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.techzon.data.dao.IndirizzoDao;
import com.techzon.data.dto.IndirizzoDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.entities.Indirizzo;
import com.techzon.data.entities.User.Genere;
import com.techzon.data.services.IndirizzoService;
import com.techzon.data.services.UserService;
import com.techzon.utilities.EncryptPassword;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class IndirizzoServiceTest {
	
	@Autowired
	UserService userService;
	
	@Autowired
	IndirizzoService indirizzoService;
	
	@Autowired
	IndirizzoDao indirizzoDao;
	
	@Before
	public void setUp() {
	    UserDto user = new UserDto();
		user.setNome("Mario");
		user.setCognome("Rossi");
		user.setEmail("prova@example.com");
		user.setUsername("Mario98");
		user.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("mariorossi"));
		user.setTelefono("3245433432");
		user.setSaldo(0.0);
		user.setBloccato(false);
		user.setPunti(0);
		user.setImmagineProfilo("");
		user.setDataNascita(LocalDate.of(1999, 12, 12));
		user.setGenere(Genere.M);
		
		userService.addUser(user);
	}
	
	@Test
	public void testAdd() {
		IndirizzoDto indirizzoDto = new IndirizzoDto();
		indirizzoDto.setRegione("Calabria");
		indirizzoDto.setProvincia("Cosenza");
		indirizzoDto.setCitta("Rende");
		indirizzoDto.setCap("88834");
		indirizzoDto.setVia("Via Aldo Moro");
		
		indirizzoDto.setUser(userService.getByUsername("Mario98"));
		
		indirizzoService.add(indirizzoDto);
		
		List<Indirizzo> indirizzi = indirizzoDao.findAll();
		
		Assert.assertTrue(indirizzi.size() > 0);
	}
	
	@Test
	public void testGetByUser() {
		IndirizzoDto indirizzoDto = new IndirizzoDto();
		indirizzoDto.setRegione("Calabria");
		indirizzoDto.setProvincia("Cosenza");
		indirizzoDto.setCitta("Rende");
		indirizzoDto.setCap("88834");
		indirizzoDto.setVia("Via Aldo Moro");
		
		indirizzoDto.setUser(userService.getByUsername("Mario98"));
		
		indirizzoService.add(indirizzoDto);
		
		IndirizzoDto ind = indirizzoService.getByUser(userService.getByUsername("Mario98"));
		
		Assert.assertEquals(ind.getUser().getUsername(), "Mario98");
	}
	
	@Test
	public void testExistsIndByUser() {
		IndirizzoDto indirizzoDto = new IndirizzoDto();
		indirizzoDto.setRegione("Calabria");
		indirizzoDto.setProvincia("Cosenza");
		indirizzoDto.setCitta("Rende");
		indirizzoDto.setCap("88834");
		indirizzoDto.setVia("Via Aldo Moro");
		
		indirizzoDto.setUser(userService.getByUsername("Mario98"));
		
		indirizzoService.add(indirizzoDto);
		
		boolean exists = indirizzoService.existsIndByUser(userService.getByUsername("Mario98"));
		
		Assert.assertTrue(exists);
	}
	
	@Test
	public void testDeleteIndirizzo() {
		IndirizzoDto indirizzoDto = new IndirizzoDto();
		indirizzoDto.setRegione("Calabria");
		indirizzoDto.setProvincia("Cosenza");
		indirizzoDto.setCitta("Rende");
		indirizzoDto.setCap("88834");
		indirizzoDto.setVia("Via Aldo Moro");
		
		indirizzoDto.setUser(userService.getByUsername("Mario98"));
		
		indirizzoService.add(indirizzoDto);
		
		int firstSize = indirizzoDao.findAll().size();
		indirizzoService.deleteIndirizzo(indirizzoService.getByUser(userService.getByUsername("Mario98")));
		
		int secondSize = indirizzoDao.findAll().size();
		Assert.assertEquals(firstSize-1, secondSize);
	}
	
	@Test
	public void testGetMaxId() {
		IndirizzoDto indirizzoDto = new IndirizzoDto();
		indirizzoDto.setRegione("Calabria");
		indirizzoDto.setProvincia("Cosenza");
		indirizzoDto.setCitta("Rende");
		indirizzoDto.setCap("88834");
		indirizzoDto.setVia("Via Aldo Moro");
		
		indirizzoDto.setUser(userService.getByUsername("Mario98"));
		
		indirizzoService.add(indirizzoDto);
		
		IndirizzoDto indirizzoMax = indirizzoService.getById(indirizzoService.getMaxId());
		
		Assert.assertEquals(indirizzoMax.getUser().getUsername(), "Mario98");
	}
	
	@Test
	public void testGetById() {
		IndirizzoDto indirizzoDto = new IndirizzoDto();
		indirizzoDto.setRegione("Calabria");
		indirizzoDto.setProvincia("Cosenza");
		indirizzoDto.setCitta("Rende");
		indirizzoDto.setCap("88834");
		indirizzoDto.setVia("Via Aldo Moro");
		
		indirizzoDto.setUser(userService.getByUsername("Mario98"));
		
		indirizzoService.add(indirizzoDto);
		
		IndirizzoDto indirizzoMax = indirizzoService.getById(indirizzoService.getMaxId());
		
		Assert.assertEquals(indirizzoMax.getUser().getUsername(), "Mario98");
	}
}
