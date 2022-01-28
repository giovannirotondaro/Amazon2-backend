package com.techzon.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techzon.data.dto.ProvinciaDto;
import com.techzon.data.services.ProvinciaService;

@RestController
@RequestMapping("/provincia")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProvinciaController {

	@Autowired
	private ProvinciaService provinciaService;
	
	@GetMapping("/allProvince")
	public ResponseEntity<List<String>> getAllProvinceByRegione(@RequestParam("regione") String regione){
		List<String> names = new ArrayList<>();
		List<ProvinciaDto> provincie=provinciaService.getByRegione(regione);
		for(ProvinciaDto p:provincie) {
			names.add(p.getNome());
		}
		return ResponseEntity.ok(names);
	}
}
