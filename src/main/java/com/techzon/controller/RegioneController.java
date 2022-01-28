package com.techzon.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.techzon.data.dto.RegioneDto;
import com.techzon.data.services.RegioneService;

@RestController
@RequestMapping("/regione")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegioneController {

	@Autowired
	private RegioneService regioneService;
	
	@GetMapping("/allRegione")
	public ResponseEntity<List<String>> getAllRegioni(){
		
		List<String> names = new ArrayList<>();
		
		List<RegioneDto> regioni=regioneService.getAllName();
		for(RegioneDto p:regioni) {
			names.add(p.getNome());
		}
		return ResponseEntity.ok(names);
	}
}
