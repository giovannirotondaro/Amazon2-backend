package com.techzon;

import java.security.NoSuchAlgorithmException;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.techzon.data.dao.AdminDao;
import com.techzon.data.dto.AdminDto;
import com.techzon.data.entities.Admin;
import com.techzon.data.services.AdminService;
import com.techzon.utilities.EncryptPassword;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Test
	public void testGetByUsername() throws NoSuchAlgorithmException{
		AdminDto admin = new AdminDto();
		
		admin.setNome("Mario");
		admin.setCognome("Rossi");
		admin.setEmail("prova@example.com");
		admin.setUsername("Mario99");
		admin.setPassword(EncryptPassword.getInstance().generatePasswordCrypted("mariorossi"));
		admin.setTelefono(Long.valueOf(345453543)); //Da cambiare dopo con string
		
		Admin s = modelMapper.map(admin, Admin.class);
		adminDao.save(s);
		
		AdminDto a = adminService.getByUsername("Mario99");
		Assert.assertEquals(s.getUsername(), a.getUsername());
	}
}
