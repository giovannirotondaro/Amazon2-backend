package com.techzon.data.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techzon.data.dao.CittaDao;
import com.techzon.data.dto.CittaDto;
import com.techzon.data.entities.Citta;
import com.techzon.data.services.CittaService;

@Service
public class CittaServiceImpl implements CittaService {

	@Autowired
	private CittaDao cittaDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<CittaDto> getByProvincia(String provincia) {
		List<Citta> citta = cittaDao.findAll(cittaDao.theLast(provincia));
		return citta.stream().map(prod -> modelMapper.map(prod, CittaDto.class)).collect(Collectors.toList());
	}
}
