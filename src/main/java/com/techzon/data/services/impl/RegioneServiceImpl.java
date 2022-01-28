package com.techzon.data.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techzon.data.dao.RegioneDao;
import com.techzon.data.dto.RegioneDto;
import com.techzon.data.entities.Regione;
import com.techzon.data.services.RegioneService;

@Service
public class RegioneServiceImpl implements RegioneService {

	@Autowired
	private RegioneDao regioneDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<RegioneDto> getAllName() {
		List<Regione> province = regioneDao.findAll();
		 return province.stream().map(prod -> modelMapper.map(prod, RegioneDto.class)).collect(Collectors.toList());
	}

}
