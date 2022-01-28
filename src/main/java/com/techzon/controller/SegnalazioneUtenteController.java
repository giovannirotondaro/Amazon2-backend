package com.techzon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.techzon.data.dto.SegnalazioneUtenteDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.services.SegnalazioneUtenteService;

@RestController
@RequestMapping("/segnalazioni")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SegnalazioneUtenteController {
	
	@Autowired
	private SegnalazioneUtenteService segnalazioneUtenteService;
	
	@PostMapping("/addSegnalazioneUtente")
	public ResponseEntity<UserDto> addUser(@RequestBody SegnalazioneUtenteDto segnalazione) {
		
		SegnalazioneUtenteDto segnalazioneUtenteDto=new SegnalazioneUtenteDto();
		segnalazioneUtenteDto.setMotivazionePrincipale(segnalazione.getMotivazionePrincipale());
		segnalazioneUtenteDto.setMotivazioneAggiuntiva(segnalazione.getMotivazioneAggiuntiva());
		segnalazioneUtenteDto.setUtenteSegnalato(segnalazione.getUtenteSegnalato());
		segnalazioneUtenteDto.setUtenteCheFaSegnalazione(segnalazione.getUtenteCheFaSegnalazione());
		segnalazioneUtenteService.addSegnalazioneUtente(segnalazioneUtenteDto);
		
		return ResponseEntity.ok(null);
	}
}
