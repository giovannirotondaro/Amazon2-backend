package com.techzon;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.techzon.data.dto.RichiestaCorriereDto;
import com.techzon.data.entities.User.Genere;
import com.techzon.data.services.RichiestaCorriereService;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class RichiestaCorriereServiceTest {
	
	@Autowired
	RichiestaCorriereService richiestaCorriereService;
	
	@Test
	public void testAddAndGetAll() {
		RichiestaCorriereDto r = new RichiestaCorriereDto();
		r.setNome("Giovanni");
		r.setCognome("Bianchi");
		r.setEmail("bianchi@libero.it");
		r.setTelefono("324541097");
		r.setDataNascita(LocalDate.of(1995, 12, 11));
		r.setGenere(Genere.M);
		r.setUrlPdf("url1");
		r.setDataColloquio(LocalDate.of(2022, 3, 25));
		r.setOraColloquio(LocalTime.of(13, 50));
		r.setHannoColloquio(true);
		
		richiestaCorriereService.add(r);
		
		List<RichiestaCorriereDto> richieste = richiestaCorriereService.getAll();
		Assert.assertTrue(richieste.size() > 0);
	}
	
	@Test
	public void testDeleteRichiestaCorriereAndGetRichiestaCorriere() {
		RichiestaCorriereDto r = new RichiestaCorriereDto();
		r.setNome("Giovanni");
		r.setCognome("Bianchi");
		r.setEmail("bianchi@libero.it");
		r.setTelefono("324541097");
		r.setDataNascita(LocalDate.of(1995, 12, 11));
		r.setGenere(Genere.M);
		r.setUrlPdf("url1");
		r.setDataColloquio(LocalDate.of(2022, 3, 25));
		r.setOraColloquio(LocalTime.of(13, 50));
		r.setHannoColloquio(true);
		
		richiestaCorriereService.add(r);
		
		int firstSize = richiestaCorriereService.getAll().size();
		richiestaCorriereService.deleteRichiestaCorriere(Long.valueOf(1));
		
		int secondSize = richiestaCorriereService.getAll().size();

		Assert.assertEquals(firstSize-1, secondSize);
	}
	
	@Test
	public void testGetAllByHannoColloquio() {
		RichiestaCorriereDto r = new RichiestaCorriereDto();
		r.setNome("Giovanni");
		r.setCognome("Bianchi");
		r.setEmail("bianchi@libero.it");
		r.setTelefono("324541097");
		r.setDataNascita(LocalDate.of(1995, 12, 11));
		r.setGenere(Genere.M);
		r.setUrlPdf("url1");
		r.setDataColloquio(LocalDate.of(2022, 3, 25));
		r.setOraColloquio(LocalTime.of(13, 50));
		r.setHannoColloquio(true);
		
		richiestaCorriereService.add(r);
		
		RichiestaCorriereDto r2 = new RichiestaCorriereDto();
		r2.setNome("Antonio");
		r2.setCognome("Bianchi");
		r2.setEmail("bianchiAntonio@libero.it");
		r2.setTelefono("327130739");
		r2.setDataNascita(LocalDate.of(1995, 12, 11));
		r2.setGenere(Genere.M);
		r2.setUrlPdf("url1");
		r2.setDataColloquio(LocalDate.of(2022, 3, 25));
		r2.setOraColloquio(LocalTime.of(13, 50));
		r2.setHannoColloquio(true);
		
		richiestaCorriereService.add(r2);
		
		List<RichiestaCorriereDto> richieste = richiestaCorriereService.getAll();

		Assert.assertEquals(richieste.size(), 2);
	}
}
