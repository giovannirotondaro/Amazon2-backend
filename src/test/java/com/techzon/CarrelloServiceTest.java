package com.techzon;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.techzon.data.dao.CarrelloDao;
import com.techzon.data.dto.CarrelloDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.entities.Carrello;
import com.techzon.data.entities.User.Genere;
import com.techzon.data.services.CarrelloService;
import com.techzon.data.services.UserService;
import com.techzon.utilities.EncryptPassword;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrelloServiceTest {
	
	@Autowired
	CarrelloService carrelloService;
	
	@Autowired
	CarrelloDao carrelloDao;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Test
	public void testGetByUser() {
		UserDto user = new UserDto();
		user.setNome("Mario");
		user.setCognome("Rossi");
		user.setEmail("prova@example.com");
		user.setUsername("Mario98");
		user.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("mariorossi"));
		user.setTelefono("3245436524");
		user.setSaldo(0.0);
		user.setBloccato(false);
		user.setPunti(0);
		user.setImmagineProfilo("");
		user.setDataNascita(LocalDate.of(1999, 12, 12));
		user.setGenere(Genere.M);
		
		userService.addUser(user);
		UserDto s = userService.getByUsername("Mario98");
		CarrelloDto carrelloDto = new CarrelloDto();
		carrelloDto.setUser(s);
		carrelloService.add(carrelloDto);
		
		CarrelloDto c = carrelloService.getByUser(s);
		Assert.assertEquals(c.getUser().getUsername(), "Mario98");
	}

	@Test
	public void testAdd() {
		CarrelloDto c = new CarrelloDto();
		UserDto user = new UserDto();
		user.setNome("Mario");
		user.setCognome("Rossi");
		user.setEmail("mario@example.com");
		user.setUsername("Mario99");
		user.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("mariorossi"));
		user.setTelefono("324543652");
		user.setSaldo(0.0);
		user.setBloccato(false);
		user.setPunti(0);
		user.setImmagineProfilo("");
		user.setDataNascita(LocalDate.of(1999, 12, 12));
		user.setGenere(Genere.M);
		userService.addUser(user);
		
		c.setUser(userService.getByUsername("Mario99"));
		
		carrelloService.add(c);
		List<Carrello> carrelli = carrelloDao.findAll();
		Assert.assertTrue(carrelli.size() > 0);
	}
	
	@Test
	public void testGetById() {
		CarrelloDto c = new CarrelloDto();
		UserDto user = new UserDto();
		user.setNome("Mario");
		user.setCognome("Rossi");
		user.setEmail("mario90@example.com");
		user.setUsername("Mario90");
		user.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("mariorossi"));
		user.setTelefono("324543651");
		user.setSaldo(0.0);
		user.setBloccato(false);
		user.setPunti(0);
		user.setImmagineProfilo("");
		user.setDataNascita(LocalDate.of(1999, 12, 12));
		user.setGenere(Genere.M);
		userService.addUser(user);
		
		UserDto s = userService.getByUsername("Mario90");
		
		c.setUser(s);
		
		carrelloService.add(c);
		CarrelloDto cr = carrelloService.getById(Long.valueOf(3));
		Assert.assertEquals(cr.getUser().getUsername(), "Mario90");	
	}
	
	@Test
	public void testDeleteCarrello() {
		CarrelloDto c = new CarrelloDto();
		UserDto user = new UserDto();
		user.setNome("Mario");
		user.setCognome("Rossi");
		user.setEmail("mario91@example.com");
		user.setUsername("Mario91");
		user.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("mariorossi"));
		user.setTelefono("324543623");
		user.setSaldo(0.0);
		user.setBloccato(false);
		user.setPunti(0);
		user.setImmagineProfilo("");
		user.setDataNascita(LocalDate.of(1999, 12, 12));
		user.setGenere(Genere.M);
		userService.addUser(user);
		
		UserDto s = userService.getByUsername("Mario91");
		
		c.setUser(s);
		
		carrelloService.add(c);
		
		List<Carrello> carrelli = carrelloDao.findAll();
		int firstSize = carrelli.size();
		carrelloService.deleteCarrello(modelMapper.map(carrelli.get(0), CarrelloDto.class));
		
		int secondSize = carrelloDao.findAll().size();

		Assert.assertEquals(firstSize-1, secondSize);	
	}
	

	@Test
	public void testGetMaxId() {
		CarrelloDto c = new CarrelloDto();
		UserDto user = new UserDto();
		user.setNome("Mario");
		user.setCognome("Rossi");
		user.setEmail("mario93@example.com");
		user.setUsername("Mario93");
		user.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("mariorossi"));
		user.setTelefono("324543612");
		user.setSaldo(0.0);
		user.setBloccato(false);
		user.setPunti(0);
		user.setImmagineProfilo("");
		user.setDataNascita(LocalDate.of(1999, 12, 12));
		user.setGenere(Genere.M);
		userService.addUser(user);
		
		UserDto s = userService.getByUsername("Mario93");
		
		c.setUser(s);
		
		carrelloService.add(c);
		
		CarrelloDto cartMax = carrelloService.getById(carrelloService.getMaxId());
		
		Assert.assertEquals(cartMax.getUser().getUsername(), user.getUsername());	
	}
}
