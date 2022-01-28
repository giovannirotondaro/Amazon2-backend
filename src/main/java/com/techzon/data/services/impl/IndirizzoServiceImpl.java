package com.techzon.data.services.impl;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techzon.data.dao.IndirizzoDao;
import com.techzon.data.dto.IndirizzoDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.entities.Indirizzo;
import com.techzon.data.entities.User;
import com.techzon.data.services.IndirizzoService;

@Service
public class IndirizzoServiceImpl implements IndirizzoService{

	@Autowired
	private IndirizzoDao indirizzoDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public IndirizzoDto add(IndirizzoDto indirizzoDto) {
		Indirizzo indirizzo = modelMapper.map(indirizzoDto, Indirizzo.class);
		Indirizzo saved = indirizzoDao.save(indirizzo);
		return modelMapper.map(saved, IndirizzoDto.class);
	}

	@Override
	public IndirizzoDto getByUser(UserDto user) {
		User u=modelMapper.map(user, User.class);
		Indirizzo indirizzo=indirizzoDao.findByUser(u);
		return modelMapper.map(indirizzo, IndirizzoDto.class);
	}
	
	@Override
	public boolean existsIndByUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		if(indirizzoDao.existsByUser(user)) 
			return true;
		return false;
	}
	
	@Override
	public Boolean deleteIndirizzo(IndirizzoDto indirizzoDto) {
		Indirizzo i=modelMapper.map(indirizzoDto, Indirizzo.class);
		if(i == null) {
			return false;
		}
		else {
			indirizzoDao.delete(i);
			return true;
		}
	}

	@Override
	public Long getMaxId() {
		return indirizzoDao.findByMax();
	}

	@Override
	public IndirizzoDto getById(Long id) {
		Optional<Indirizzo> p = indirizzoDao.findById(id);
		if(p.isPresent()) {
			return modelMapper.map(p.get(), IndirizzoDto.class);
		}
		else {
			throw new RuntimeException();
		}
	}
}
