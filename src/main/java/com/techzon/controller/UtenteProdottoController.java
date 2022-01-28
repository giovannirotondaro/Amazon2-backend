package com.techzon.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techzon.data.dto.CorriereDto;
import com.techzon.data.dto.ProdottoDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.dto.UtenteProdottoDto;
import com.techzon.data.entities.UtenteProdotto;
import com.techzon.data.entities.UtenteProdotto.Stato;
import com.techzon.data.services.CorriereService;
import com.techzon.data.services.ProdottoService;
import com.techzon.data.services.UserService;
import com.techzon.data.services.UtenteProdottoService;

@RestController
@RequestMapping("/utenteProdotto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UtenteProdottoController {

	@Autowired
	private UtenteProdottoService utenteProdottoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProdottoService prodottoService;
	
	@Autowired
	private CorriereService corriereService;
	
	@GetMapping("/allCronologiaAcquisti")
	public ResponseEntity<List<UtenteProdottoDto>> getProdottiCompratiUser(@RequestParam("username") String username){
		UserDto u = userService.getByUsername(username);
		
		List<UtenteProdottoDto> prodottiAcquistati=new ArrayList<UtenteProdottoDto>();
		prodottiAcquistati=utenteProdottoService.getAllByUtente(u);
		for(int i=0;i<prodottiAcquistati.size();i++) {
			prodottiAcquistati.get(i).getProdotto().setVendutoDa(prodottoService.getProdotto(prodottiAcquistati.get(i).getProdotto().getId()).getVendutoDa());
		}
		return ResponseEntity.ok(prodottiAcquistati);
	}
	
	@GetMapping("/cronologiaAcquisto")
	public ResponseEntity<UtenteProdottoDto> getProdottoCompratoUser(@RequestParam("id") Long id){
		UtenteProdottoDto ut = utenteProdottoService.getUtenteProdotto(id);
		return ResponseEntity.ok(ut);
	}
	
	@GetMapping("/acquistiNonConsegnati")
	public ResponseEntity<List<UtenteProdottoDto>> getProdottiNonConsegnati(){
		return ResponseEntity.ok(utenteProdottoService.getAllByStato(Stato.NIENTE));
	}
	
	@GetMapping("/acquisizioniDeterminatoCorriere")
	public ResponseEntity<List<UtenteProdottoDto>> getAcquisizioniDeterminatoCorriere(@RequestParam("username") String username){
		CorriereDto corriere=corriereService.getByUsername(username);
		return ResponseEntity.ok(utenteProdottoService.getAllByCorriere(corriere));
	}
	
	@PutMapping("/acquisisci")
	public ResponseEntity<UtenteProdottoDto> updatePrendiAcquisizioneDeterminatoCorriere(@RequestBody UtenteProdottoDto utenteProdottoDto) {
		UtenteProdottoDto u=utenteProdottoService.getUtenteProdotto(utenteProdottoDto.getId());
		u.setCorriere(utenteProdottoDto.getCorriere());
		u.setStato(UtenteProdotto.Stato.PRESO_IN_CARICO);
		utenteProdottoService.addUtenteProdottoDto(u);
		return ResponseEntity.ok(u);
	}
	
	@PostMapping("/rimuoviUtenteProdotto")
	public ResponseEntity<UtenteProdottoDto> rimuoviUtenteProdotto(@RequestBody UtenteProdottoDto utenteProdottoDto) {
		ProdottoDto prodotto = prodottoService.getProdotto(utenteProdottoDto.getProdotto().getId());
		int quantitaVecchia=prodotto.getQuantita();
		prodotto.setQuantita(quantitaVecchia + utenteProdottoDto.getQuantita());
		if(prodotto.getDisponibile()==false) {
			prodotto.setDisponibile(true);
		}
		prodottoService.addProdotto(prodotto);
		Boolean isRemoved = utenteProdottoService.rimuoviUtenteProdotto(utenteProdottoDto);
		if (!isRemoved) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(utenteProdottoDto);
	}
	
	@PutMapping("/modificaStatoSpedizione")
	public ResponseEntity<UtenteProdottoDto> updateStatoSpedizione(@RequestBody UtenteProdottoDto utenteProdottoDto) {
		utenteProdottoService.addUtenteProdottoDto(utenteProdottoDto);
		return ResponseEntity.ok(utenteProdottoDto);
	}
	
	@PostMapping("/aggiungiUtenteProdotto")
	  public ResponseEntity<UtenteProdottoDto> addUtenteProdotto(@RequestBody UtenteProdottoDto utenteProdottoDto) {

	    utenteProdottoService.addUtenteProdottoDto(utenteProdottoDto);
	    return ResponseEntity.ok(utenteProdottoDto);
	  }
}
