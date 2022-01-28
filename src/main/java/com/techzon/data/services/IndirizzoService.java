package com.techzon.data.services;

import com.techzon.data.dto.IndirizzoDto;
import com.techzon.data.dto.UserDto;

public interface IndirizzoService {
	IndirizzoDto add(IndirizzoDto indirizzoDto);
	IndirizzoDto getByUser(UserDto user);
	boolean existsIndByUser(UserDto user);
	Boolean deleteIndirizzo(IndirizzoDto indirizzoDto);
	Long getMaxId();
	IndirizzoDto getById(Long id);
}
