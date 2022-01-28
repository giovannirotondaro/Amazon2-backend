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
import com.techzon.data.dto.CittaDto;
import com.techzon.data.services.CittaService;

@RestController
@RequestMapping("/citta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CittaController {
	@Autowired
	private CittaService cittaService;

	@GetMapping("/allCitta")
	public ResponseEntity<List<String>> getAllCittaByProvincia(@RequestParam("provincia") String provincia){
		List<String> names = new ArrayList<>();
		List<CittaDto> provincie=cittaService.getByProvincia(provincia);
		for(CittaDto p:provincie) {
			names.add(p.getNome());
		}
		return ResponseEntity.ok(names);
	}
}
