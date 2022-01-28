package com.techzon.data.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techzon.data.dao.SegnalazioneUtenteDao;
import com.techzon.data.dto.SegnalazioneUtenteDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.entities.SegnalazioneUtente;
import com.techzon.data.entities.User;
import com.techzon.data.services.SegnalazioneUtenteService;

@Service
public class SegnalazioneUtenteImpl implements SegnalazioneUtenteService {
	
	@Autowired
	private SegnalazioneUtenteDao segnalazioneUtenteDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto addSegnalazioneUtente(SegnalazioneUtenteDto segnalazioneUtenteDto) {
		SegnalazioneUtente segnalazioneUtente=modelMapper.map(segnalazioneUtenteDto,SegnalazioneUtente.class);
		SegnalazioneUtente saved=segnalazioneUtenteDao.save(segnalazioneUtente);
		return modelMapper.map(saved, UserDto.class);
	}

	@Override
	public List<SegnalazioneUtenteDto> getAll() {
		List<SegnalazioneUtente> segnalazioneUtente=segnalazioneUtenteDao.findAll();
		return segnalazioneUtente.stream().map(segnalazione -> modelMapper.map(segnalazione, SegnalazioneUtenteDto.class)).collect(Collectors.toList());		
		
	}

	@Override
	public List<SegnalazioneUtenteDto> getAllSegnalazioniFatte(UserDto user) {
		User u =modelMapper.map(user, User.class);
		List<SegnalazioneUtente> segnalazioni = segnalazioneUtenteDao.findByUtenteCheFaSegnalazione(u);
		return segnalazioni.stream().map(prod -> modelMapper.map(prod, SegnalazioneUtenteDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<SegnalazioneUtenteDto> getAllSegnalazioniRicevute(UserDto user) {
		User u =modelMapper.map(user, User.class);
		List<SegnalazioneUtente> segnalazioniRicevute = segnalazioneUtenteDao.findByUtenteSegnalato(u);
		return segnalazioniRicevute.stream().map(prod -> modelMapper.map(prod, SegnalazioneUtenteDto.class)).collect(Collectors.toList());
	}

	@Override
	public Boolean deleteSegnalazione(SegnalazioneUtenteDto segnalazioneUtenteDto) {
		SegnalazioneUtente s = modelMapper.map(segnalazioneUtenteDto, SegnalazioneUtente.class);
		if(s==null)
			return false;
		else {
			segnalazioneUtenteDao.delete(s);
			return true;
		}
	}
}
