package com.techzon.data.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techzon.data.dao.RichiestaCorriereDao;
import com.techzon.data.dto.RichiestaCorriereDto;
import com.techzon.data.entities.RichiestaCorriere;
import com.techzon.data.services.RichiestaCorriereService;

@Service
public class RichiestaCorriereServiceImpl implements RichiestaCorriereService{

	@Autowired
	private RichiestaCorriereDao richiestaCorriereDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public RichiestaCorriereDto add(RichiestaCorriereDto richiestaCorriereDto) {
		RichiestaCorriere c = modelMapper.map(richiestaCorriereDto, RichiestaCorriere.class);
		RichiestaCorriere saved = richiestaCorriereDao.save(c);
		return modelMapper.map(saved, RichiestaCorriereDto.class);
	}

	@Override
	public List<RichiestaCorriereDto> getAll() {
		List<RichiestaCorriere> richieste = richiestaCorriereDao.findAll();
		return richieste.stream().map(ric -> modelMapper.map(ric, RichiestaCorriereDto.class)).collect(Collectors.toList());
	}

	@Override
	public Boolean deleteRichiestaCorriere(Long id) {
		Optional<RichiestaCorriere> c = richiestaCorriereDao.findById(id);
		if(c.isPresent()) {
			richiestaCorriereDao.delete(c.get());
			return true;
		}
		return false;
	}

	@Override
	public RichiestaCorriereDto getRichiestaCorriere(Long id) {
		
		Optional<RichiestaCorriere> p = richiestaCorriereDao.findById(id);
		if(p.isPresent()) {
			return modelMapper.map(p.get(), RichiestaCorriereDto.class);
		}else {
		    throw new RuntimeException();
		}
	}

	@Override
	public List<RichiestaCorriereDto> getAllByhannoColloquio(boolean hannoColloquio) {
		List<RichiestaCorriere> richieste = richiestaCorriereDao.findAllByhannoColloquio(hannoColloquio);
		return richieste.stream().map(ric -> modelMapper.map(ric, RichiestaCorriereDto.class)).collect(Collectors.toList());
		
	}

	@Override
	public RichiestaCorriereDto getRichiestaCorriereEmail(String email) {
		RichiestaCorriere saved = richiestaCorriereDao.findByEmail(email);
		if (saved != null)
		   return modelMapper.map(saved, RichiestaCorriereDto.class);
		return null;
	}
}
