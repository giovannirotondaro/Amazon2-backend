package com.techzon.data.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techzon.data.dao.AdminDao;
import com.techzon.data.dto.AdminDto;
import com.techzon.data.entities.Admin;
import com.techzon.data.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AdminDto getByUsername(String username) {
		Admin saved = adminDao.findByUsername(username);
		if (saved != null)
			return modelMapper.map(saved, AdminDto.class);
		return null;
	}
	
	
}
