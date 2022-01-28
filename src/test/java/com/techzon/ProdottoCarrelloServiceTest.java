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

import com.techzon.data.dao.ProdottoCarrelloDao;
import com.techzon.data.dto.CarrelloDto;
import com.techzon.data.dto.ProdottoCarrelloDto;
import com.techzon.data.dto.ProdottoDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.entities.User.Genere;
import com.techzon.data.services.CarrelloService;
import com.techzon.data.services.ProdottoCarrelloService;
import com.techzon.data.services.ProdottoService;
import com.techzon.data.services.UserService;
import com.techzon.utilities.EncryptPassword;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class ProdottoCarrelloServiceTest {
	
	@Autowired
	ProdottoCarrelloService prodottoCarrelloService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CarrelloService carrelloService;
	
	@Autowired
	ProdottoService prodottoService;
	
	@Autowired
	ProdottoCarrelloDao prodottoCarrelloDao;
	
	@Before
	public void setUp() {
		UserDto user = new UserDto();
		user.setNome("Davide");
		user.setCognome("Rossi");
		user.setEmail("davide@example.com");
		user.setUsername("David99");
		user.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("davide99"));
		user.setTelefono("3245436512");
		user.setSaldo(0.0);
		user.setBloccato(false);
		user.setPunti(0);
		user.setImmagineProfilo("");
		user.setDataNascita(LocalDate.of(1999, 12, 12));
		user.setGenere(Genere.M);
		
		userService.addUser(user);
		
		UserDto user2 = new UserDto();
		user2.setNome("Franco");
		user2.setCognome("Rossi");
		user2.setEmail("frank@example.com");
		user2.setUsername("Frank99");
		user2.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("frank99"));
		user2.setTelefono("3245432122");
		user2.setSaldo(0.0);
		user2.setBloccato(false);
		user2.setPunti(0);
		user2.setImmagineProfilo("");
		user2.setDataNascita(LocalDate.of(1999, 12, 12));
		user2.setGenere(Genere.M);
		
		userService.addUser(user2);
		
		CarrelloDto carrelloDto = new CarrelloDto();
		carrelloDto.setUser(userService.getByUsername("David99"));
		carrelloService.add(carrelloDto);
		
		ProdottoDto p = new ProdottoDto();
		
		p.setTitolo("Samsung Galaxy S21");
		p.setAnteprima("anteprimaDescrizione");
		p.setDescrizione("descrizione");
		p.setDimensioni("1x1");
		p.setPeso(1.2);
		p.setMemoriaArchiviazione("500");
		p.setMemoriaRam("8");
		p.setMarca("Samsung");
		p.setPrezzo(400.00);
		p.setSconto(10);
		p.setColore("nero");
		p.setCategoria("smartphone");
		p.setQuantita(10);
		p.setDisponibile(true);
		LocalDate l = LocalDate.of(1996,11,12);
		p.setDataAggiunta(l);
		p.setUrl1("url1");
		p.setUrl2("url2");
		p.setUrl3("url3");
		p.setUrl4("url4");
		p.setVendutoDa(userService.getByUsername("frank99"));
		prodottoService.addProdotto(p);
		
		ProdottoCarrelloDto prodottoCarrelloDto = new ProdottoCarrelloDto();
		prodottoCarrelloDto.setCarrello(carrelloService.getByUser(userService.getByUsername("David99")));
		prodottoCarrelloDto.setProdotto(prodottoService.getAllByTitolo("Samsung Galaxy S21").get(0));
		prodottoCarrelloDto.setQuantita(4);
		prodottoCarrelloService.add(prodottoCarrelloDto);
	}
	
	@Test
	public void testGetAllByCarrello() {
		List<ProdottoCarrelloDto> prodotti = prodottoCarrelloService.getAllByCarrello(carrelloService.getByUser(userService.getByUsername("David99")));
		Assert.assertTrue(prodotti.size() > 0);
	}
	
	@Test
	public void testExistsProdottoInCarrello() {
		boolean exists = prodottoCarrelloService.existsProdottoInCarrello(carrelloService.getByUser(userService.getByUsername("David99")), prodottoService.getAllByTitolo("Samsung Galaxy S21").get(0));
		Assert.assertTrue(exists);
	}
	
	@Test
	public void testGetById() {
		ProdottoCarrelloDto prodotto = prodottoCarrelloService.getById(Long.valueOf(1));
		Assert.assertEquals(prodotto.getCarrello().getUser().getUsername(), "David99");
	}
	
	@Test
	public void testGetByCarrelloAndProdotto() {
		ProdottoCarrelloDto prodotto = prodottoCarrelloService.getByCarrelloAndProdotto(carrelloService.getByUser(userService.getByUsername("David99")), prodottoService.getAllByTitolo("Samsung Galaxy S21").get(0));
		Assert.assertEquals(prodotto.getCarrello().getUser().getUsername(), "David99");
	}
	
	@Test
	public void testGetAll() {
		List<ProdottoCarrelloDto> prodotti = prodottoCarrelloService.getAll();
		Assert.assertTrue(prodotti.size() > 0);
	}
	
	@Test
	public void testDeleteForCarrelloAndProdotto() {
		int firstSize = prodottoCarrelloService.getAll().size();
		prodottoCarrelloService.deleteForCarrelloAndProdotto(carrelloService.getByUser(userService.getByUsername("David99")), prodottoService.getAllByTitolo("Samsung Galaxy S21").get(0));
		
		int secondSize = prodottoCarrelloService.getAll().size();
		
		Assert.assertEquals(firstSize-1, secondSize);
	}
}
