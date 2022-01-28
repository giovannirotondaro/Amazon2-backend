package com.techzon.data.services;

import java.util.List;

import com.techzon.data.dto.ProdottoDto;
import com.techzon.data.dto.UserDto;

public interface ProdottoService {
	
	List<ProdottoDto> getAll();
	
	ProdottoDto getProdotto(Long id);
	
	List<ProdottoDto> getAllByCategoria(String categoria);
	
	ProdottoDto addProdotto(ProdottoDto prodottoDto);
	
	List<ProdottoDto> getAllByTitolo(String titolo);
	
	List<ProdottoDto> getAllVendutoDa(UserDto user);
	
	Boolean deleteProdotto(ProdottoDto prodottoDto);
}
