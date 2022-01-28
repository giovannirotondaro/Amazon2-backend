package com.techzon.data.services;

import com.techzon.data.dto.AdminDto;

public interface AdminService {
	AdminDto getByUsername(String username);
}
