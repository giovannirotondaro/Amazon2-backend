package com.techzon.data.services;

import java.util.List;

import com.techzon.data.dto.ProdottoDto;
import com.techzon.data.dto.RecensioneDto;
import com.techzon.data.dto.UserDto;


public interface RecensioneService {

	RecensioneDto addRecensione(RecensioneDto recensioneDto);
	Boolean rimuoviRecensione(RecensioneDto recensioneDto);
	List<RecensioneDto> getAllUser(UserDto user);
	Boolean deleteRecensione(RecensioneDto recensione);
	List<RecensioneDto> getAllByProdotto(ProdottoDto p);
	List<RecensioneDto> getAllByApprovata(boolean approvata);

}

