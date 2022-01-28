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

import com.techzon.data.dao.RecensioneDao;
import com.techzon.data.dto.ProdottoDto;
import com.techzon.data.dto.RecensioneDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.entities.User.Genere;
import com.techzon.data.services.ProdottoService;
import com.techzon.data.services.RecensioneService;
import com.techzon.data.services.UserService;
import com.techzon.utilities.EncryptPassword;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class RecensioneServiceTest {

	@Autowired
	RecensioneService recensioneService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProdottoService prodottoService;
	
	@Autowired
	RecensioneDao recensioneDao;
	
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
		user.setTelefono("3245437081");
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
		RecensioneDto r=new RecensioneDto();
		r.setRecensione("Recensione");
		r.setProdottoRecensito(prodottoService.getProdotto(Long.valueOf(1)));
		r.setCreataDa(userService.getUser(Long.valueOf(1)));
		r.setApprovata(true);
		r.setDataAggiunta(LocalDate.of(1999, 10, 12));
		r.setRating(3);
		recensioneService.addRecensione(r);
		
		
	}
	
	@Test
	public void testGetAllByUser() {
		List<RecensioneDto> recensioni = recensioneService.getAllUser(userService.getByUsername("nomeCognome"));
		Assert.assertEquals(recensioni.size(), 1);
	}
	
	@Test
	public void testGetAllByProdotto() {
		List<RecensioneDto> recensioni = recensioneService.getAllByProdotto(prodottoService.getProdotto(Long.valueOf(1)));
		Assert.assertEquals(recensioni.size(), 1);
	}
	
	@Test
	public void testRimuoviRecensione() {
		List<RecensioneDto> recensioni = recensioneService.getAllByProdotto(prodottoService.getProdotto(Long.valueOf(1)));
		recensioneService.rimuoviRecensione(recensioni.get(0));
		List<RecensioneDto> recensioni1 = recensioneService.getAllByProdotto(prodottoService.getProdotto(Long.valueOf(1)));
		Assert.assertEquals(recensioni1.size(), 0);
	}
}
