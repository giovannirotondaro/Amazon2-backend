package com.techzon.data.services;

import java.util.List;

import com.techzon.data.dto.CarrelloDto;
import com.techzon.data.dto.ProdottoCarrelloDto;
import com.techzon.data.dto.ProdottoDto;

public interface ProdottoCarrelloService {
	
	List<ProdottoCarrelloDto> getAllByCarrello(CarrelloDto carrello);
	ProdottoCarrelloDto getById(Long id);
	boolean existsProdottoInCarrello(CarrelloDto carrello, ProdottoDto prodotto);
	ProdottoCarrelloDto add(ProdottoCarrelloDto prodottoCarrello);
	void deleteForCarrelloAndProdotto(CarrelloDto carrello, ProdottoDto prodotto);
	ProdottoCarrelloDto getByCarrelloAndProdotto(CarrelloDto carrello, ProdottoDto prodotto);
	List<ProdottoCarrelloDto> getAll();
}
