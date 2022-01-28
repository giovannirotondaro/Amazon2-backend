package com.techzon.data.services;

import java.util.List;

import com.techzon.data.dto.SegnalazioneUtenteDto;
import com.techzon.data.dto.UserDto;

public interface SegnalazioneUtenteService {
	UserDto addSegnalazioneUtente(SegnalazioneUtenteDto segnalazioneUtenteDto);
	List<SegnalazioneUtenteDto> getAll();
	List<SegnalazioneUtenteDto> getAllSegnalazioniFatte(UserDto user);
	List<SegnalazioneUtenteDto> getAllSegnalazioniRicevute(UserDto user);
	Boolean deleteSegnalazione(SegnalazioneUtenteDto segnalazioneUtenteDto);
}
