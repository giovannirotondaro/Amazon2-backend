package com.techzon.data.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techzon.data.dao.ProvinciaDao;
import com.techzon.data.dto.ProvinciaDto;
import com.techzon.data.entities.Provincia;
import com.techzon.data.services.ProvinciaService;

@Service
public class ProvinciaServiceImpl implements ProvinciaService{
	
	@Autowired
	private ProvinciaDao provinciaDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ProvinciaDto> getAllName() {
		List<Provincia> province = provinciaDao.findAll();
		 return province.stream().map(prod -> modelMapper.map(prod, ProvinciaDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ProvinciaDto> getByRegione(String regione) {
		List<Provincia> province = provinciaDao.findAll(provinciaDao.theLast(regione));
		return province.stream().map(prod -> modelMapper.map(prod, ProvinciaDto.class)).collect(Collectors.toList());
	}

}
