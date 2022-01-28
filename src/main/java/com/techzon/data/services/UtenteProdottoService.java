package com.techzon.data.services;

import java.util.List;

import com.techzon.data.dto.CorriereDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.dto.UtenteProdottoDto;
import com.techzon.data.entities.UtenteProdotto.Stato;

public interface UtenteProdottoService {
	List<UtenteProdottoDto> getAllByUtente(UserDto userDto);
	List<UtenteProdottoDto> getAllByStato(Stato stato);
	List<UtenteProdottoDto> getAllByCorriere(CorriereDto corriere);
	UtenteProdottoDto getUtenteProdotto(Long id);
	UtenteProdottoDto addUtenteProdottoDto(UtenteProdottoDto utenteProdottoDto);
	Boolean  rimuoviUtenteProdotto(UtenteProdottoDto utenteProdottoDto);
	UtenteProdottoDto getByIdProdotto(Long id);
	
}
