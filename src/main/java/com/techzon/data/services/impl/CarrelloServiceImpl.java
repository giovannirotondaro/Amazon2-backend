package com.techzon.data.services.impl;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techzon.data.dao.CarrelloDao;
import com.techzon.data.dto.CarrelloDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.entities.Carrello;
import com.techzon.data.entities.User;
import com.techzon.data.services.CarrelloService;

@Service
public class CarrelloServiceImpl implements CarrelloService{
	
	@Autowired
	private CarrelloDao carrelloDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	  public CarrelloDto getByUser(UserDto userDto) {
	    User user = modelMapper.map(userDto, User.class);
	    Carrello c = carrelloDao.findByUser(user);
	    return modelMapper.map(c, CarrelloDto.class);
	  }

	@Override
	public CarrelloDto add(CarrelloDto carrelloDto) {
		Carrello c = modelMapper.map(carrelloDto, Carrello.class);
		Carrello saved = carrelloDao.save(c);
		return modelMapper.map(saved, CarrelloDto.class);
	}

	@Override
	public CarrelloDto getById(Long id) {
		Optional<Carrello> p = carrelloDao.findById(id);
		if(p.isPresent()) {
			return modelMapper.map(p.get(), CarrelloDto.class);
		}
		else {
			throw new RuntimeException();
		}
	}

	@Override
	public Boolean deleteCarrello(CarrelloDto carrelloDto) {
		Carrello c=modelMapper.map(carrelloDto,Carrello.class);
		if(c == null) {
			return false;
		}
		else {
			carrelloDao.delete(c);
			return true;
		}
	}

	@Override
	public Long getMaxId() {
		return carrelloDao.findByMax();
	}
}
