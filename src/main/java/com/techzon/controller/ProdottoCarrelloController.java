package com.techzon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techzon.data.dto.CarrelloDto;
import com.techzon.data.dto.ProdottoCarrelloDto;
import com.techzon.data.dto.ProdottoDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.services.CarrelloService;
import com.techzon.data.services.ProdottoCarrelloService;
import com.techzon.data.services.ProdottoService;
import com.techzon.data.services.UserService;

@RestController
@RequestMapping("/prodottoCarrello")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdottoCarrelloController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CarrelloService carrelloService;
	
	@Autowired
	private ProdottoCarrelloService prodottoCarrelloService;
	
	@Autowired
	private ProdottoService prodottoService;

	@GetMapping("/all")
	public ResponseEntity<List<ProdottoCarrelloDto>> carrello(@RequestParam("username") String username) {
	    if(!(userService.getByUsername(username) == null)) {
	    	UserDto userDto = userService.getByUsername(username);
		    if(username.trim().equals(userDto.getUsername())) {
		    	CarrelloDto carrelloDto = carrelloService.getByUser(userDto);
		    	List<ProdottoCarrelloDto> prodottoCarrello = prodottoCarrelloService.getAllByCarrello(carrelloDto);
		    	for(ProdottoCarrelloDto prodottoCarrelloDto : prodottoCarrello) {
		    		if(prodottoCarrelloDto.getProdotto().getDisponibile() && prodottoCarrelloDto.getQuantita() == 0) {
		    			prodottoCarrelloDto.setQuantita(1);
		    			prodottoCarrelloService.add(prodottoCarrelloDto);
		    		}
		    	}
		    	return ResponseEntity.ok(prodottoCarrello);
		    }
	    }
		return null;
	}
	
	@DeleteMapping("/deleteProductInCart")
	public HttpStatus deleteProductCart(@RequestParam("idCarrello") Long idCarrello, @RequestParam("idProdotto") Long idProdotto){
		CarrelloDto carrelloDto = carrelloService.getById(idCarrello);
		ProdottoDto prodottoDto = prodottoService.getProdotto(idProdotto);
		prodottoCarrelloService.deleteForCarrelloAndProdotto(carrelloDto, prodottoDto);
		return HttpStatus.OK;
	}
	
	@GetMapping("/allProdottiCarrello")
	public ResponseEntity<List<ProdottoCarrelloDto>> allProdottiCarrello() {
		return ResponseEntity.ok(prodottoCarrelloService.getAll());
	}
	
	@PutMapping("/updateQuantity")
	public HttpStatus updateProductCart(@RequestBody ProdottoDto prodottoDto, @RequestParam("idCarrello") Long idCarrello, @RequestParam("quantita") Integer quantita){
		CarrelloDto carrelloDto = carrelloService.getById(idCarrello);
		ProdottoCarrelloDto prodotto = prodottoCarrelloService.getByCarrelloAndProdotto(carrelloDto, prodottoDto);
		prodotto.setQuantita(quantita);
		prodottoCarrelloService.add(prodotto);
		return HttpStatus.OK;
	}
	
	@PostMapping("/addProductInCart")
	public ResponseEntity<String> addProductCart(@RequestBody ProdottoDto prodottoDto, @RequestParam("username") String username){
		if(!(userService.getByUsername(username) == null)) {
	    	UserDto userDto = userService.getByUsername(username);
	    	CarrelloDto carrelloDto = carrelloService.getByUser(userDto);
		    if(username.trim().equals(userDto.getUsername())) {
		    	if(prodottoDto.getDisponibile()) {
		    		if(prodottoDto.getVendutoDa().getId() != userDto.getId()) {
				    	if(!(prodottoCarrelloService.existsProdottoInCarrello(carrelloDto, prodottoDto))) {
				    		ProdottoCarrelloDto prodottoCarrelloDto = new ProdottoCarrelloDto();
				    		prodottoCarrelloDto.setCarrello(carrelloDto);
				    		prodottoCarrelloDto.setProdotto(prodottoDto);
				    		prodottoCarrelloDto.setQuantita(1);
							prodottoCarrelloService.add(prodottoCarrelloDto);
				    	}
				    	else {
				    		ProdottoCarrelloDto prodotto = prodottoCarrelloService.getByCarrelloAndProdotto(carrelloDto, prodottoDto);
				    		prodotto.setQuantita(prodotto.getQuantita() + 1);
				    		if(prodottoDto.getQuantita() < prodotto.getQuantita()) {
				    			return ResponseEntity.ok("Superata la quantità disponibile del prodotto");
				    		}
				    		prodottoCarrelloService.add(prodotto);
				    	}
				    	return ResponseEntity.ok("Prodotto aggiunto nel carrello");
		    		}
		    		return ResponseEntity.ok("Non puoi comprare un prodotto messo in vendita da te");
		    	}
		    	return ResponseEntity.ok("Prodotto non disponibile");
		    }
		}
		return ResponseEntity.ok("Loggati o registrati per acquistare");
	}
}
