package com.techzon.controller;

import com.techzon.data.dto.*;
import com.techzon.data.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/preferito")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PreferitoController {

	@Autowired
	private PreferitoService preferitoService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/preferitiByUsername")
	public ResponseEntity<List<PreferitoDto>> getPreferitiByUser(@RequestParam("username") String username){
		UserDto u = userService.getByUsername(username);
		
		List<PreferitoDto> preferiti=new ArrayList<PreferitoDto>();
		preferiti=preferitoService.getAllByUtente(u);

		return ResponseEntity.ok(preferiti);
	}

	@PostMapping("/aggiungiPreferito")
	public ResponseEntity<Boolean> aggiungiPreferito(@RequestBody PreferitoDto preferito) {
		PreferitoDto preferitoDto=new PreferitoDto();
		preferitoDto.setProdotto(preferito.getProdotto());
		preferitoDto.setUser(preferito.getUser());
		Boolean esiste= preferitoService.addPreferito(preferitoDto);
		return ResponseEntity.ok(esiste);
	}

	@PostMapping("/rimuoviPreferito")
	public ResponseEntity<Boolean> rimuoviRecensione(@RequestBody PreferitoDto preferito) {
		Boolean isRemoved = preferitoService.rimuoviPreferito(preferito);
		if (!isRemoved) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(isRemoved);
	}
}
