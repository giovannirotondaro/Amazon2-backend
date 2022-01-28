package com.techzon;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
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

import com.techzon.data.dao.ProdottoDao;
import com.techzon.data.dto.ProdottoDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.entities.User.Genere;
import com.techzon.data.services.ProdottoService;
import com.techzon.data.services.UserService;
import com.techzon.utilities.EncryptPassword;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class ProdottoServiceTest {

	@Autowired
	ProdottoService prodottoService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProdottoDao prodottoDao;
	
	@Autowired
	ModelMapper modelMapper;
		
	@Before
	public void setUp() {
			
		UserDto user = new UserDto();
		user.setNome("Nome");
		user.setCognome("Cognome");
		user.setEmail("cognome@example.com");
		user.setUsername("nomeCognome");
		user.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("nm"));
		user.setTelefono("324543708");
		user.setSaldo(0.0);
		user.setBloccato(false);
		user.setPunti(0);
		user.setImmagineProfilo("");
		user.setDataNascita(LocalDate.of(1999, 10, 12));
		user.setGenere(Genere.M);
		userService.addUser(user);	
		
		ProdottoDto p = new ProdottoDto();
		
		p.setTitolo("Samsung S21");
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
		p.setVendutoDa(userService.getByUsername("nomeCognome"));
		prodottoService.addProdotto(p);
	}
	
	@Test
	public void testGetAll() {		
		List<ProdottoDto> prodotti = prodottoService.getAll();
		Assert.assertTrue(prodotti.size()>0);
	}
	
	@Test
	public void testGetById() {
		ProdottoDto prodotto = prodottoService.getProdotto(Long.valueOf(1));
		Assert.assertEquals(prodotto.getTitolo(), "Samsung S21");
	}
	
	@Test
	public void testGetAllByCategoria() {
		List<ProdottoDto> prodotto = prodottoService.getAllByCategoria("smartphone");
		Assert.assertEquals(prodotto.size(), 1);
	}
	
	@Test
	public void testGetAllByTitolo() {
		List<ProdottoDto> prodotto = prodottoService.getAllByTitolo("Samsung S20");
		Assert.assertEquals(prodotto.size(), 0);
	}
	
	@Test
	public void testGetAllByVendutoDa() {
		List<ProdottoDto> prodotto = prodottoService.getAllVendutoDa(userService.getByUsername("nomeCognome"));
		Assert.assertEquals(prodotto.size(), 1);
	}
	
	@Test
	public void testDeleteProdotto() {
		int firstSize = prodottoService.getAll().size();
		prodottoService.deleteProdotto(prodottoService.getProdotto(Long.valueOf(1)));
		
		int secondSize = prodottoService.getAll().size();
		Assert.assertEquals(firstSize-1, secondSize);
	}
}
