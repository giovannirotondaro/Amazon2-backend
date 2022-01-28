package com.techzon.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techzon.data.dto.RichiestaCorriereDto;
import com.techzon.data.services.RichiestaCorriereService;
import com.techzon.utilities.ThreadEmail;

@RestController
@RequestMapping("/richiestaCorriere")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RichiestaCorriereController {
	
	@Autowired
	private RichiestaCorriereService richiestaCorriereService;
	
	@GetMapping("/ottieniTutteRichieste")
	public ResponseEntity<List<RichiestaCorriereDto>> getAllRichieste(){
		return ResponseEntity.ok(richiestaCorriereService.getAll());
	}
	
	@DeleteMapping("/rimuoviRichiestaSenzaEmail")
	public HttpStatus deleteRichiestaSenzaEmail(@RequestParam("id") Long id) {
		
		Boolean ok = richiestaCorriereService.deleteRichiestaCorriere(id);
		if(ok)
			return HttpStatus.OK;
		
		return HttpStatus.FORBIDDEN;
	}
	
	@DeleteMapping("/rimuoviRichiesta")
	public HttpStatus deleteRichiesta(@RequestParam("id") Long id) {
		RichiestaCorriereDto richiestaCorriereDto=richiestaCorriereService.getRichiestaCorriere(id);
		String destinataioEmail=richiestaCorriereDto.getEmail();
		String testoEmail="Gentile aspirante corriere con la seguente mail vogliamo informarla che il suo curriculum vitae è stato visionato da un nostro \n"
				+ "team di esperti. Inoltre sulla base del colloquio di lavoro svolto con i manager di Techzon è emerso che la sua candidatura \n "
				+ "NON CORRISPONDE E NON SODDISFA i nostri requisiti lavorativi. Per tale motivo la sua richiesta è stata rigettata.\n"
				+ "Cordiali saluti, lo staff di Techzone.\n"
				+ "Per qualsiasi dubbio, o informazione si prega di contattare lo staff di Techzon\n";
		String oggettoEmail="Techzon - CORRIERI - LAVORA CON NOI";
		ThreadEmail t=new ThreadEmail(destinataioEmail,testoEmail,oggettoEmail);
		t.start();
		Boolean ok = richiestaCorriereService.deleteRichiestaCorriere(id);
		if(ok)
			return HttpStatus.OK;
		
		return HttpStatus.FORBIDDEN;
	}
	
	@PutMapping("/rimuoviAppuntamento")
	public HttpStatus deleteAppuntamento(@RequestBody RichiestaCorriereDto richiestaCorriereDto) {
		String destinataioEmail=richiestaCorriereDto.getEmail();
		String testoEmail="Gentile aspirante corriere con la seguente mail vogliamo informarla che il suo appuntamento del "+richiestaCorriereDto.getDataColloquio()+" delle ore "+richiestaCorriereDto.getOraColloquio()+" è stato annullato..\n"
				+ "\n"
				+ "\n"
				+ "Cordiali saluti, lo staff di Techzone.\n"
				+ "Per qualsiasi dubbio, o informazione si prega di contattare lo staff di Techzon\n"
				+ "\n";
		String oggettoEmail="Techzon - CORRIERI - LAVORA CON NOI";
		ThreadEmail t=new ThreadEmail(destinataioEmail,testoEmail,oggettoEmail);
		t.start();
		richiestaCorriereDto.setDataColloquio(null);
		richiestaCorriereDto.setOraColloquio(null);
		richiestaCorriereDto.setHannoColloquio(false);
		richiestaCorriereService.add(richiestaCorriereDto);
		return HttpStatus.OK;
	}
	
	@PutMapping("/updateColloquio")
	public HttpStatus updateColloquio(@RequestBody RichiestaCorriereDto richiestaCorriereDto){
		String destinataioEmail=richiestaCorriereDto.getEmail();
		String testoEmail="Gentile aspirante corriere con la seguente mail vogliamo informarla che il suo curriculum vitae è stato visionato da un nostro team di esperti. Ma non è ancora fatta, manca un piccolo passaggio. Infatti per\n "
				+ "diventare un dipendente della piattaforma Techzon è necessario presentarsi presso Il Dipartimento di Matematica e Informatica al cubo 31B nella data sotto indicata:\n"
				+ "\n"
				+ "Giorno:"+richiestaCorriereDto.getDataColloquio()+"\n"
				+ "Ora:"+ richiestaCorriereDto.getOraColloquio()+"\n"
				+ "\n"
				+ "Per qualsiasi dubbio, o informazione si prega di contattare lo staff di Techzon\n"
				+ "Cordiali Saluti.";
		String oggettoEmail="Techzon - CORRIERI - LAVORA CON NOI";
		ThreadEmail t=new ThreadEmail(destinataioEmail,testoEmail,oggettoEmail);
		t.start();
		richiestaCorriereService.add(richiestaCorriereDto);
		return HttpStatus.OK;
	}
	
	@PutMapping("/updateColloquioModificaAppuntamento")
	public HttpStatus updateColloquioModificaAppuntamento(@RequestBody RichiestaCorriereDto richiestaCorriereDto){
		String destinataioEmail=richiestaCorriereDto.getEmail();
		String testoEmail="Gentile aspirante corriere con la seguente mail vogliamo informarla che il suo appuntamento è stato modificato nella data riporata sotto"
				+ "\n"
				+ "Giorno:"+richiestaCorriereDto.getDataColloquio()+"\n"
				+ "Ora:"+ richiestaCorriereDto.getOraColloquio()+"\n"
				+ "\n"
				+ "Per qualsiasi dubbio, o informazione si prega di contattare lo staff di Techzon\n"
				+ "Cordiali Saluti.";
		String oggettoEmail="Techzon - CORRIERI - LAVORA CON NOI";
		ThreadEmail t=new ThreadEmail(destinataioEmail,testoEmail,oggettoEmail);
		t.start();
		richiestaCorriereService.add(richiestaCorriereDto);
		return HttpStatus.OK;
	}
	
	@GetMapping("/hannoAppuntamento")
	public ResponseEntity<List<RichiestaCorriereDto>> getAllAppuntamento(){
		return ResponseEntity.ok(richiestaCorriereService.getAllByhannoColloquio(true));
	}
	
	@PostMapping("/aggiungiRichiestaCorriere")
	public ResponseEntity<RichiestaCorriereDto> aggiungiRichiestaCorriere(@RequestBody RichiestaCorriereDto richiestaCorriereDto){
		richiestaCorriereService.add(richiestaCorriereDto);
		return ResponseEntity.ok(richiestaCorriereDto);
	}
	
	@GetMapping("/richiestaCorriereByEmail")
	public ResponseEntity<Boolean> getRichiestaCorriereByEmail(@RequestParam("emailCorriere") String emailCorriere) { 
	    boolean risultato=false;
	    RichiestaCorriereDto richiestaCorriereDto=richiestaCorriereService.getRichiestaCorriereEmail(emailCorriere);
	    if(richiestaCorriereDto==null) {
	    	risultato=false;
	    }
	    else{
	    	risultato=true;
	    }
		return ResponseEntity.ok(risultato);
	}
}
