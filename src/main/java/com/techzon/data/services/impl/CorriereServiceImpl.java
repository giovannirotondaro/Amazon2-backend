package com.techzon.data.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techzon.data.dao.CorriereDao;
import com.techzon.data.dto.CorriereDto;
import com.techzon.data.entities.Corriere;
import com.techzon.data.services.CorriereService;

@Service
public class CorriereServiceImpl implements CorriereService {
	
	@Autowired
	private CorriereDao corriereDao;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CorriereDto getByUsername(String username) {
		Corriere saved = corriereDao.findByUsername(username);
		if (saved != null)
		   return modelMapper.map(saved, CorriereDto.class);
		return null;
	}

	@Override
	public CorriereDto addCorriere(CorriereDto corriereDto) {
		Corriere corriere=modelMapper.map(corriereDto,Corriere.class);
		Corriere saved=corriereDao.save(corriere);
		return modelMapper.map(saved, CorriereDto.class);
	}

	@Override
	public List<CorriereDto> getAll() {
		List<Corriere> lista=corriereDao.findAll();
		return lista.stream().map(l->modelMapper.map(l,CorriereDto.class)).collect(Collectors.toList());
		
	}

	@Override
	public CorriereDto getByEmail(String email) {
		Corriere saved = corriereDao.findByEmail(email);
		if (saved != null)
		   return modelMapper.map(saved, CorriereDto.class);
		return null;
	}

}
