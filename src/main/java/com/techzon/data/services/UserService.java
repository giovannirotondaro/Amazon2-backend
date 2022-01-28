package com.techzon.data.services;

import java.util.List;
import com.techzon.data.dto.UserDto;

public interface UserService {
	UserDto addUser(UserDto userDto);
	UserDto getByUsername(String username);
	UserDto getByEmail(String email);
	List<UserDto> getAll();
	UserDto getUser(Long id);
	Boolean rimuoviUtente(UserDto userDto);
}
