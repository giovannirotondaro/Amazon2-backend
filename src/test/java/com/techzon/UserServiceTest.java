package com.techzon;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.techzon.data.dto.UserDto;
import com.techzon.data.entities.User.Genere;
import com.techzon.data.services.UserService;
import com.techzon.utilities.EncryptPassword;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@Test
	public void testAddAndGetByUsername() {
		UserDto user = new UserDto();
		user.setNome("Giovanni");
		user.setCognome("Rossi");
		user.setEmail("giovanni@example.com");
		user.setUsername("Gio99");
		user.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("giovanni"));
		user.setTelefono("3245431233");
		user.setSaldo(0.0);
		user.setBloccato(false);
		user.setPunti(0);
		user.setImmagineProfilo("");
		user.setDataNascita(LocalDate.of(1999, 12, 12));
		user.setGenere(Genere.M);
		
		userService.addUser(user);
		
		UserDto d = userService.getByUsername("Gio99");
		Assert.assertEquals(d.getEmail(), "giovanni@example.com");
	}
	
	@Test
	public void testGetByEmail() {
		UserDto user = new UserDto();
		user.setNome("Giovanni");
		user.setCognome("Rossi");
		user.setEmail("giovanni@example.com");
		user.setUsername("Gio99");
		user.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("giovanni"));
		user.setTelefono("3245436594");
		user.setSaldo(0.0);
		user.setBloccato(false);
		user.setPunti(0);
		user.setImmagineProfilo("");
		user.setDataNascita(LocalDate.of(1999, 12, 12));
		user.setGenere(Genere.M);
		
		userService.addUser(user);
		
		UserDto d = userService.getByEmail("giovanni@example.com");
		Assert.assertEquals(d.getUsername(), "Gio99");
	}
	
	@Test
	public void testGetAll() {
		UserDto user = new UserDto();
		user.setNome("Giovanni");
		user.setCognome("Rossi");
		user.setEmail("giovanni@example.com");
		user.setUsername("Gio99");
		user.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("giovanni"));
		user.setTelefono("324543654");
		user.setSaldo(0.0);
		user.setBloccato(false);
		user.setPunti(0);
		user.setImmagineProfilo("");
		user.setDataNascita(LocalDate.of(1999, 12, 12));
		user.setGenere(Genere.M);
		
		userService.addUser(user);
		
		List<UserDto> d = userService.getAll();
		Assert.assertTrue(d.size() > 0);
	}
	
	@Test
	public void testGetUser() {
		UserDto user = new UserDto();
		user.setNome("Giovanni");
		user.setCognome("Rossi");
		user.setEmail("giovanni@example.com");
		user.setUsername("Gio99");
		user.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("giovanni"));
		user.setTelefono("3245431235");
		user.setSaldo(0.0);
		user.setBloccato(false);
		user.setPunti(0);
		user.setImmagineProfilo("");
		user.setDataNascita(LocalDate.of(1999, 12, 12));
		user.setGenere(Genere.M);
		
		userService.addUser(user);
		
		UserDto d = userService.getUser(Long.valueOf(1));
		Assert.assertEquals(d.getUsername(), "Gio99");
	}
	
	@Test
	public void testRimuoviUtente() {
		UserDto user = new UserDto();
		user.setNome("Giovanni");
		user.setCognome("Rossi");
		user.setEmail("giovanni@example.com");
		user.setUsername("Gio99");
		user.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("giovanni"));
		user.setTelefono("0204789542");
		user.setSaldo(0.0);
		user.setBloccato(false);
		user.setPunti(0);
		user.setImmagineProfilo("");
		user.setDataNascita(LocalDate.of(1999, 12, 12));
		user.setGenere(Genere.M);
		
		userService.addUser(user);
		
		int firstSize = userService.getAll().size();
		userService.rimuoviUtente(userService.getByUsername("Gio99"));
		
		int secondSize = userService.getAll().size();

		Assert.assertEquals(firstSize-1, secondSize);
	}
}
