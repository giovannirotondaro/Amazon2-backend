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

import com.techzon.data.dao.CorriereDao;
import com.techzon.data.dto.CorriereDto;
import com.techzon.data.entities.User.Genere;
import com.techzon.data.services.CorriereService;
import com.techzon.utilities.EncryptPassword;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CorriereServiceTest {

	
	@Autowired
	CorriereService corriereService;
	
	@Autowired
	CorriereDao corriereDao;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Test
	public void testAdd() {
		CorriereDto c = new CorriereDto();
		c.setNome("Mario");
		c.setCognome("Rossi");
		c.setUsername("marioRossi");
		LocalDate l = LocalDate.of(1996,11,12);
		c.setDataNascita(l);
		c.setEmail("marioR@libero.it");
		c.setGenere(Genere.M);
		c.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("marioRossi"));
		c.setTelefono("345453543"); //Da cambiare dopo con string
		
		corriereService.addCorriere(c);
		
		List<CorriereDto> corrieri = corriereService.getAll();
		Assert.assertTrue(corrieri.size() > 0);
	}
	
	
	@Test
	public void testGetByUsername() {
		CorriereDto c = new CorriereDto();
		c.setNome("Mario");
		c.setCognome("Bianchi");
		c.setUsername("marioBianchi");
		LocalDate l = LocalDate.of(1996,11,12);
		c.setDataNascita(l);
		c.setEmail("mario@libero.it");
		c.setGenere(Genere.M);
		c.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("mariobianchi"));
		c.setTelefono("345453678"); //Da cambiare dopo con string
		
		corriereService.addCorriere(c);
		
		
		CorriereDto c2=corriereService.getByUsername("marioBianchi");
		Assert.assertTrue(c2.getNome()=="Mario" && c2.getCognome()=="Bianchi");
	}
	
	
	@Test
	public void testGetAll() {
		CorriereDto c = new CorriereDto();
		c.setNome("Giuseppe");
		c.setCognome("Verdi");
		c.setUsername("gv");
		LocalDate l = LocalDate.of(1996,11,12);
		c.setDataNascita(l);
		c.setEmail("peppe@libero.it");
		c.setGenere(Genere.M);
		c.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("giuseppe"));
		c.setTelefono("345453990"); //Da cambiare dopo con string
		
		corriereService.addCorriere(c);
		
		CorriereDto c2 = new CorriereDto();
		c2.setNome("Mario");
		c2.setCognome("Verdi");
		c2.setUsername("marioVerdi");
		LocalDate l1 = LocalDate.of(1996,11,12);
		c2.setDataNascita(l1);
		c2.setEmail("marioVerdi@libero.it");
		c2.setGenere(Genere.M);
		c2.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("marioverdi"));
		c2.setTelefono("345453548"); //Da cambiare dopo con string
		
		corriereService.addCorriere(c2);
		
		
		List<CorriereDto> corrieri = corriereService.getAll();
		Assert.assertTrue(corrieri.size()>=2);
	}

}
