package com.techzon.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techzon.data.dto.ProdottoDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.services.ProdottoService;
import com.techzon.data.services.UserService;
import com.techzon.utilities.Matching;

@RestController
@RequestMapping("/prodotto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdottoController {

	
	@Autowired
	private ProdottoService prodottoService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/allProdotti")
	public ResponseEntity<List<ProdottoDto>> all() {
		return ResponseEntity.ok(prodottoService.getAll());
	}
	
	@GetMapping("/prodottoId/{id}")
	public ResponseEntity<ProdottoDto> getProdottoPath(@PathVariable("id") Long id) { 
	    return ResponseEntity.ok(prodottoService.getProdotto(id));
	}
	
	@GetMapping("/prodottoNotPath")
	public ResponseEntity<ProdottoDto> getProdotto(@RequestParam("id") Long id) { 
	    return ResponseEntity.ok(prodottoService.getProdotto(id));
	}
	
	@GetMapping("/{categoria}")
	public ResponseEntity<List<ProdottoDto>> getProdottiByCategoria(@PathVariable("categoria") String categoria){
		return ResponseEntity.ok(prodottoService.getAllByCategoria(categoria));
	}
	
	@PostMapping("/inserisciProdotto")
	public ResponseEntity<ProdottoDto> addProdotto(@RequestBody ProdottoDto prodotto) {
		UserDto user = userService.getByUsername(prodotto.getVendutoDa().getUsername());
		ProdottoDto prodottoDto=new ProdottoDto();
		prodottoDto.setAnteprima(prodotto.getAnteprima());
		prodottoDto.setCategoria(prodotto.getCategoria());
		prodottoDto.setColore(prodotto.getColore());
		LocalDate lt= LocalDate.now();
		prodottoDto.setDataAggiunta(lt);
		prodottoDto.setDescrizione(prodotto.getDescrizione());
		prodottoDto.setDimensioni(prodotto.getDimensioni());
		prodottoDto.setDisponibile(true);
		prodottoDto.setMarca(prodotto.getMarca());
		prodottoDto.setMemoriaArchiviazione(prodotto.getMemoriaArchiviazione());
		prodottoDto.setMemoriaRam(prodotto.getMemoriaRam());
		prodottoDto.setModello(prodotto.getModello());
		prodottoDto.setPeso(prodotto.getPeso());
		prodottoDto.setPrezzo(prodotto.getPrezzo());
		prodottoDto.setQuantita(prodotto.getQuantita());
		prodottoDto.setSconto(prodotto.getSconto());
		prodottoDto.setUrl1(prodotto.getUrl1());
		prodottoDto.setUrl2(prodotto.getUrl2());
		prodottoDto.setUrl3(prodotto.getUrl3());
		prodottoDto.setUrl4(prodotto.getUrl4());
		prodottoDto.setTitolo(prodotto.getTitolo());
		prodottoDto.setVendutoDa(user);
		prodottoService.addProdotto(prodottoDto);
		return ResponseEntity.ok(prodottoDto);
	}
	
	@GetMapping("/ricercaProdotto")
	public ResponseEntity<List<ProdottoDto>> searched(@RequestParam("titolo") String titolo){
		List<ProdottoDto> prodotti = prodottoService.getAll();
		List<ProdottoDto> prodottiTrovati = Matching.apply(titolo, prodotti);
		return ResponseEntity.ok(prodottiTrovati);
	}
	
	@PutMapping("/updateQuantity")
	public ResponseEntity<ProdottoDto> updateProdotto(@RequestBody ProdottoDto prodottoDto, @RequestParam("quantita") Integer quantita){
		prodottoDto.setQuantita(quantita);
		if(quantita == 0) {
			prodottoDto.setDisponibile(false);
		}
			
		prodottoService.addProdotto(prodottoDto);
		return ResponseEntity.ok(prodottoDto);
	}
	
	@GetMapping("/elencoVenduti")
	public ResponseEntity<List<ProdottoDto>> getProdottiVendutitiUser(@RequestParam("username") String username){
		UserDto u = userService.getByUsername(username);
		
		List<ProdottoDto> prodottiVenduti=new ArrayList<ProdottoDto>();
		prodottiVenduti=prodottoService.getAllVendutoDa(u);
		
		return ResponseEntity.ok(prodottiVenduti);
	}

	@PutMapping("/updateProdotto")
	public ResponseEntity<ProdottoDto> modificaProdotto(@RequestBody ProdottoDto prodottoDto){

		prodottoService.addProdotto(prodottoDto);
		return ResponseEntity.ok(prodottoDto);
	}
}
