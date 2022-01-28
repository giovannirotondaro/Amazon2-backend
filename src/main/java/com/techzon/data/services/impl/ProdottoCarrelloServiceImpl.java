package com.techzon.data.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techzon.data.dao.ProdottoCarrelloDao;
import com.techzon.data.dto.CarrelloDto;
import com.techzon.data.dto.ProdottoCarrelloDto;
import com.techzon.data.dto.ProdottoDto;
import com.techzon.data.entities.Carrello;
import com.techzon.data.entities.Prodotto;
import com.techzon.data.entities.ProdottoCarrello;
import com.techzon.data.services.ProdottoCarrelloService;

@Service
public class ProdottoCarrelloServiceImpl implements ProdottoCarrelloService{
	
	@Autowired
	private ProdottoCarrelloDao prodottoCarrelloDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ProdottoCarrelloDto> getAllByCarrello(CarrelloDto carrelloDto) {
		Carrello carrello = modelMapper.map(carrelloDto, Carrello.class);
		List<ProdottoCarrello> prodotti = prodottoCarrelloDao.findAllByCarrello(carrello);
		return prodotti.stream().map(prod -> modelMapper.map(prod, ProdottoCarrelloDto.class)).collect(Collectors.toList());
	}

	@Override
	public boolean existsProdottoInCarrello(CarrelloDto carrelloDto, ProdottoDto prodottoDto) {
		Carrello carrello = modelMapper.map(carrelloDto, Carrello.class);
		Prodotto prodotto = modelMapper.map(prodottoDto, Prodotto.class);
		if(prodottoCarrelloDao.existsByCarrelloAndProdotto(carrello, prodotto))
			return true;
		return false;
	}

	@Override
	public ProdottoCarrelloDto add(ProdottoCarrelloDto prodottoCarrelloDto) {
		ProdottoCarrello p = modelMapper.map(prodottoCarrelloDto, ProdottoCarrello.class);
		ProdottoCarrello prodottoCarrello = prodottoCarrelloDao.save(p);
		return modelMapper.map(prodottoCarrello, ProdottoCarrelloDto.class);
	}

	@Override
	public ProdottoCarrelloDto getById(Long id) {
		Optional<ProdottoCarrello> p = prodottoCarrelloDao.findById(id);
		if(p.isPresent()) {
			return modelMapper.map(p.get(), ProdottoCarrelloDto.class);
		}
		else {
			throw new RuntimeException();
		}
	}

	@Override
	@Transactional
	public void deleteForCarrelloAndProdotto(CarrelloDto carrelloDto, ProdottoDto prodottoDto) {
		Carrello carrello = modelMapper.map(carrelloDto, Carrello.class);
		Prodotto prodotto = modelMapper.map(prodottoDto, Prodotto.class);
		prodottoCarrelloDao.deleteByCarrelloAndProdotto(carrello, prodotto);
	}

	@Override
	public ProdottoCarrelloDto getByCarrelloAndProdotto(CarrelloDto carrelloDto, ProdottoDto prodottoDto) {
		Carrello carrello = modelMapper.map(carrelloDto, Carrello.class);
		Prodotto prodotto = modelMapper.map(prodottoDto, Prodotto.class);
		ProdottoCarrello prodottoCarrello = prodottoCarrelloDao.findByCarrelloAndProdotto(carrello, prodotto);
		return modelMapper.map(prodottoCarrello, ProdottoCarrelloDto.class);
	}
	
	@Override
	public List<ProdottoCarrelloDto> getAll() {
		List<ProdottoCarrello> prodotti = prodottoCarrelloDao.findAll();
		return prodotti.stream().map(prod -> modelMapper.map(prod, ProdottoCarrelloDto.class)).collect(Collectors.toList());
	}
}
