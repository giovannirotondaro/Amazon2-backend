package com.techzon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techzon.data.dto.IndirizzoDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.services.IndirizzoService;
import com.techzon.data.services.UserService;

@RestController
@RequestMapping("/indirizzo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IndirizzoController {
	
	@Autowired
	private IndirizzoService indirizzoService;
	 
	@Autowired
	private UserService userService;
	
	@PutMapping("/addIndirizzo")
	  public ResponseEntity<String> addIndirizzo(@RequestBody IndirizzoDto indirizzoDto, @RequestParam("username") String username){
	    UserDto userDto = userService.getByUsername(username);
	    IndirizzoDto indDto = indirizzoService.getByUser(userDto);
	    indDto.setCap(indirizzoDto.getCap());
	    indDto.setCitta(indirizzoDto.getCitta());
	    indDto.setProvincia(indirizzoDto.getProvincia());
	    indDto.setRegione(indirizzoDto.getRegione());
	    indDto.setVia(indirizzoDto.getVia());
	    indirizzoService.add(indDto);
	    return ResponseEntity.ok("Indirizzo aggiunto");
	  }
	
	@GetMapping("/existsIndirizzo")
	public ResponseEntity<Boolean> existsIndirizzo(@RequestParam("username") String username){
		UserDto userDto = userService.getByUsername(username);
		if(indirizzoService.existsIndByUser(userDto))
			return ResponseEntity.ok(true);
		return ResponseEntity.ok(false);
	}
	
	@PostMapping("/addIndForReg")
	public ResponseEntity<IndirizzoDto> addIndNew(@RequestBody UserDto userDto){
		IndirizzoDto indirizzoDto = new IndirizzoDto();
		indirizzoDto.setUser(null);
		indirizzoService.add(indirizzoDto);
		return ResponseEntity.ok(indirizzoService.getById(indirizzoService.getMaxId()));
	}
	
	@PutMapping("/updateIndirizzo")
	public ResponseEntity<Boolean> updateIndNew(@RequestBody UserDto userDto, @RequestParam("id") Long id){
		IndirizzoDto indirizzoDto = indirizzoService.getById(id);
		indirizzoDto.setUser(userDto);
		indirizzoService.add(indirizzoDto);
		return ResponseEntity.ok(true);
	}
}
