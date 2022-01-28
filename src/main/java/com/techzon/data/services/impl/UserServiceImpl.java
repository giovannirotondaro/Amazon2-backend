package com.techzon.data.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techzon.data.dao.UserDao;
import com.techzon.data.dto.UserDto;
import com.techzon.data.entities.User;
import com.techzon.data.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto addUser(UserDto userDto) {
		User user=modelMapper.map(userDto,User.class);
		if(userDto.isBloccato() != user.isBloccato())
			user.setBloccato(userDto.isBloccato());
		User saved=userDao.save(user);
		return modelMapper.map(saved, UserDto.class);
	}

	@Override
	public UserDto getByUsername(String username) {
		User saved = userDao.findByUsername(username);
		if (saved != null)
		   return modelMapper.map(saved, UserDto.class);
		return null;
	}

	@Override
	public List<UserDto> getAll() {
		List<User> user = userDao.findAll();
		 return user.stream().map(prod -> modelMapper.map(prod, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDto getUser(Long id) {
		Optional<User> p = userDao.findById(id);
		if(p.isPresent()) {
			return modelMapper.map(p.get(), UserDto.class);
		}else {
		    throw new RuntimeException();
		}
		
	}

	@Override
	public Boolean rimuoviUtente(UserDto userDto) {
		User u = modelMapper.map(userDto, User.class);
		if(u==null)
			return false;
		else {
			userDao.delete(u);
			return true;
		}
	}

	@Override
	public UserDto getByEmail(String email) {
		User saved = userDao.findByEmail(email);
		if (saved != null)
		   return modelMapper.map(saved, UserDto.class);
		return null;
	}

}
