package com.techzon.data.services.impl;

import com.techzon.data.dao.PreferitoDao;

import com.techzon.data.dto.*;
import com.techzon.data.entities.*;
import com.techzon.data.services.PreferitoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PreferitoServiceImpl implements PreferitoService {
	
	@Autowired
	private PreferitoDao preferitoDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public List<PreferitoDto> getAllByUtente(UserDto userDto) {
		User utente = modelMapper.map(userDto, User.class);
		List<Preferito> preferiti = preferitoDao.findAllByUser(utente);
		return preferiti.stream().map(prod -> modelMapper.map(prod, PreferitoDto.class)).collect(Collectors.toList());
	}

	@Override
	public PreferitoDto getPreferitoByProdotto(ProdottoDto prodottoDto) {
		Prodotto prodotto = modelMapper.map(prodottoDto, Prodotto.class);
		Preferito preferito = preferitoDao.findByProdotto(prodotto);
		return modelMapper.map(preferito, PreferitoDto.class);
	}


	@Override
	public Boolean addPreferito(PreferitoDto preferitoDto) {
		Preferito p=modelMapper.map(preferitoDto,Preferito.class);
		List<Preferito> preferiti = preferitoDao.findAllByUser(p.getUser());
		for(Preferito pr:preferiti){
			if(pr.getProdotto().getId().equals(p.getProdotto().getId()))
				return false;
		}
		preferitoDao.save(p);
		return true;
	}

	@Override
	public Boolean rimuoviPreferito(PreferitoDto preferito) {
		Preferito p=modelMapper.map(preferito,Preferito.class);
		if(preferitoDao.findById(p.getId()) == null){
			return false;
		}
		preferitoDao.delete(p);
		return true;
	}
}
