package com.techzon.data.services;

import com.techzon.data.dto.CarrelloDto;
import com.techzon.data.dto.UserDto;

public interface CarrelloService {
	
	CarrelloDto getByUser(UserDto user);
	CarrelloDto add(CarrelloDto carrelloDto);
	CarrelloDto getById(Long id);
	Boolean deleteCarrello(CarrelloDto carrelloDto);
	Long getMaxId();
}
