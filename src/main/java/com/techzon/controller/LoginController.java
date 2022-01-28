package com.techzon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techzon.data.dto.AdminDto;
import com.techzon.data.dto.CorriereDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.services.AdminService;
import com.techzon.data.services.CorriereService;
import com.techzon.data.services.UserService;
import com.techzon.utilities.EncryptPassword;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CorriereService corriereService;
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/loginUtente")
	public ResponseEntity<Boolean> login(@RequestParam("username") String username,@RequestParam("password") String password) {
		if(!(userService.getByUsername(username) == null)) {
	    	UserDto userDto = userService.getByUsername(username);
	    	if(userDto.isBloccato()) {
	    		return ResponseEntity.ok(false);
	    	}
	    	
		    if(username.trim().equals(userDto.getUsername()) && EncryptPassword.getInstance().checkPassword(password.trim(),userDto.getPassword()))
		      return ResponseEntity.ok(true);
		    return ResponseEntity.ok(false);
	    }
	    return ResponseEntity.ok(false);
	}
	
	@GetMapping("/loginCorrieri")
	public ResponseEntity<Boolean> loginCorrieri(@RequestParam("username") String username,@RequestParam("password") String password) {
		if(!(corriereService.getByUsername(username) == null)) {
	    	CorriereDto corriereDto = corriereService.getByUsername(username);
		    if(username.trim().equals(corriereDto.getUsername()) && EncryptPassword.getInstance().checkPassword(password.trim(), corriereDto.getPassword()) )
		      return ResponseEntity.ok(true);
		    return ResponseEntity.ok(false);
	    }
	    return ResponseEntity.ok(false);
	}
	
	@GetMapping("/loginAdmin")
	public ResponseEntity<Boolean> loginAdmin(@RequestParam("username") String username,@RequestParam("password") String password) {
		if(!(adminService.getByUsername(username) == null)) {
	    	AdminDto adminDto = adminService.getByUsername(username);
		    if(username.trim().equals(adminDto.getUsername()) && password.trim().equals(adminDto.getPassword()))
		      return ResponseEntity.ok(true);
		    return ResponseEntity.ok(false);
	    }
	    return ResponseEntity.ok(false);
	}
}
