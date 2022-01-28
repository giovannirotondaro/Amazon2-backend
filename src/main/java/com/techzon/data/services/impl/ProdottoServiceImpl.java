package com.techzon.data.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techzon.data.dao.ProdottoDao;
import com.techzon.data.dto.ProdottoDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.entities.Prodotto;
import com.techzon.data.entities.User;
import com.techzon.data.services.ProdottoService;

@Service
public class ProdottoServiceImpl implements ProdottoService {
	
	@Autowired
	private ProdottoDao prodottoDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ProdottoDto> getAll() {
		 List<Prodotto> prodotti = prodottoDao.findAll();
		 return prodotti.stream().map(prod -> modelMapper.map(prod, ProdottoDto.class)).collect(Collectors.toList());
	}

	@Override
	public ProdottoDto getProdotto(Long id) {
		Prodotto p = prodottoDao.getById(id);
		return modelMapper.map(p, ProdottoDto.class);
	}
	
	
	@Override
	public List<ProdottoDto> getAllByCategoria(String categoria){
		List<Prodotto> prodotti = prodottoDao.findAllByCategoria(categoria);
		return prodotti.stream().map(prod -> modelMapper.map(prod, ProdottoDto.class)).collect(Collectors.toList());
	}

	@Override
	public ProdottoDto addProdotto(ProdottoDto prodottoDto) {
		Prodotto prodotto=modelMapper.map(prodottoDto,Prodotto.class);
		Prodotto saved=prodottoDao.save(prodotto);
		return modelMapper.map(saved, ProdottoDto.class);
	}
	
	@Override
	public List<ProdottoDto> getAllByTitolo(String titolo) {
		List<Prodotto> prodotti = prodottoDao.findAllByTitolo(titolo);
		return prodotti.stream().map(prod -> modelMapper.map(prod, ProdottoDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ProdottoDto> getAllVendutoDa(UserDto user) {
		User u =modelMapper.map(user, User.class);
		List<Prodotto> prodotti = prodottoDao.findByVendutoDa(u);
		return prodotti.stream().map(prod -> modelMapper.map(prod, ProdottoDto.class)).collect(Collectors.toList());
	}

	@Override
	public Boolean deleteProdotto(ProdottoDto prodottoDto) {
		Prodotto p=modelMapper.map(prodottoDto, Prodotto.class);
		if(p == null) {
			return false;
		}
		else {
			prodottoDao.delete(p);
			return true;
		}
	}


	
}
