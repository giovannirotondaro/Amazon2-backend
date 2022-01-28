package com.techzon.controller;


import com.techzon.data.dto.ProdottoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.techzon.data.dto.RecensioneDto;
import com.techzon.data.services.ProdottoService;
import com.techzon.data.services.RecensioneService;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/recensioni")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecensioneController {
	
	@Autowired
	private RecensioneService recensioneService;
	
	@Autowired
	private ProdottoService prodottoRecensioni;

	@GetMapping("/allRecensioni")
	public ResponseEntity<List<RecensioneDto>> getRecensioni(@RequestParam("id") Long id) {
		ProdottoDto p=prodottoRecensioni.getProdotto(id);
		List<RecensioneDto> recensioni = recensioneService.getAllByProdotto(p);
		return ResponseEntity.ok(recensioni);
	}
	
	@PostMapping("/aggiungiRecensione")
	public ResponseEntity<RecensioneDto> aggiungiRecensione(@RequestBody RecensioneDto recensione) {

		RecensioneDto recensioneDto=new RecensioneDto();
		recensioneDto.setId(recensione.getId());
		recensioneDto.setCreataDa(recensione.getCreataDa());
		LocalDate lt= LocalDate.now();
		recensioneDto.setDataAggiunta(lt);
		recensioneDto.setProdottoRecensito(recensione.getProdottoRecensito());
		recensioneDto.setRecensione(recensione.getRecensione());
		recensioneDto.setApprovata(recensione.getApprovata());
		recensioneDto.setRating(recensione.getRating());
		recensioneService.addRecensione(recensioneDto);
		return ResponseEntity.ok(recensioneDto);
	}

	@PostMapping("/rimuoviRecensione")
	public ResponseEntity<RecensioneDto> rimuoviRecensione(@RequestBody RecensioneDto recensione) {
		Boolean isRemoved = recensioneService.rimuoviRecensione(recensione);

		if (!isRemoved) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(recensione);
	}
	
	@GetMapping("/recensioniNonApprovate")
	public ResponseEntity<List<RecensioneDto>> getRecensioniNonApprovate() {
		List<RecensioneDto> recensioni = recensioneService.getAllByApprovata(false);
		return ResponseEntity.ok(recensioni);
	}
	
	@PutMapping("/approvaRecensione")
	public ResponseEntity<RecensioneDto> approvaRecensione(@RequestBody RecensioneDto recensione) {
		recensione.setApprovata(true);
		recensioneService.addRecensione(recensione);
		return ResponseEntity.ok(recensione);
	}
	
	@PutMapping("/nonApprovaRecensione")
	public ResponseEntity<RecensioneDto> nonApprovaRecensione(@RequestBody RecensioneDto recensione) {
		Boolean isRemoved = recensioneService.rimuoviRecensione(recensione);

		if (!isRemoved) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(recensione);
	}
}









