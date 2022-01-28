package com.techzon.data.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.techzon.data.dao.RecensioneDao;
import com.techzon.data.dto.ProdottoDto;
import com.techzon.data.dto.RecensioneDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.entities.Prodotto;
import com.techzon.data.entities.Recensione;
import com.techzon.data.entities.User;
import com.techzon.data.services.RecensioneService;
import org.springframework.stereotype.Service;

@Service
public class RecensioneServiceImpl implements RecensioneService{

	@Autowired
	private RecensioneDao recensioneDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public RecensioneDto addRecensione(RecensioneDto recensioneDto) {
		Recensione r=modelMapper.map(recensioneDto,Recensione.class);
		Recensione saved=recensioneDao.save(r);
		return modelMapper.map(saved, RecensioneDto.class);
	}

	@Override
	public Boolean rimuoviRecensione(RecensioneDto recensioneDto) {
		Recensione r=modelMapper.map(recensioneDto,Recensione.class);
		if(recensioneDao.findById(r.getId()) == null){
			return false;
		}
		recensioneDao.delete(r);
		return true;
	}

	@Override
	public List<RecensioneDto> getAllUser(UserDto user) {
		User u =modelMapper.map(user, User.class);
		List<Recensione> recensioni = recensioneDao.findByCreataDa(u);
		return recensioni.stream().map(prod -> modelMapper.map(prod, RecensioneDto.class)).collect(Collectors.toList());
	}

	@Override
	public Boolean deleteRecensione(RecensioneDto recensione) {
		Recensione r = modelMapper.map(recensione, Recensione.class);
		if (r == null) {
			return false;
		}
		else {
			recensioneDao.delete(r);
			return true;
		}
	}

	@Override
	public List<RecensioneDto> getAllByProdotto(ProdottoDto prodotto) {
		Prodotto p = modelMapper.map(prodotto, Prodotto.class);
		List<Recensione> recensioni = recensioneDao.findAllByProdottoRecensito(p);
		return recensioni.stream().map(prod -> modelMapper.map(prod, RecensioneDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<RecensioneDto> getAllByApprovata(boolean approvata) {
		List<Recensione> recensioni = recensioneDao.findAllByApprovata(false);
		return recensioni.stream().map(prod -> modelMapper.map(prod, RecensioneDto.class)).collect(Collectors.toList());
	}



}
