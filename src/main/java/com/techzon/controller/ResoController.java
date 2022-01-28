package com.techzon.controller;

import com.techzon.data.dto.ResoDto;
import com.techzon.data.services.ResoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cronologiaResi")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ResoController {


	@Autowired
	private ResoService resoService;

	@PostMapping("/addReso")
	public ResponseEntity<ResoDto> addReso(@RequestBody ResoDto reso) {

		ResoDto resoDto=new ResoDto();
		resoDto.setUsernameMittente(reso.getUsernameMittente());
		resoDto.setUsernameDestinatario(reso.getUsernameDestinatario());
		resoDto.setNumeroOrdine(reso.getNumeroOrdine());
		resoDto.setIdProdotto(reso.getIdProdotto());
		resoDto.setMotivoReso(reso.getMotivoReso());
		resoDto.setQuantita(reso.getQuantita());
		resoDto.setPrezzo(reso.getPrezzo()*reso.getQuantita());
		resoDto.setRimborsato(reso.getRimborsato());
		resoService.addReso(resoDto);
		return ResponseEntity.ok(resoDto);
	}

	@GetMapping("/getResiByVenditore")
	public ResponseEntity<List<ResoDto>> getResiByVenditore(@RequestParam("username") String username) {
		List<ResoDto> resi=resoService.getResiByUsernameDestinatario(username);

		return ResponseEntity.ok(resi);
	}

	@GetMapping("/isPresenteResoByUtenteProdotto")
	public ResponseEntity<ResoDto> isPresenteResoByUtenteProdotto(@RequestParam("id") Long id) {
		ResoDto reso = resoService.isPresenteReso(id);

		return ResponseEntity.ok(reso);
	}

	@PutMapping("/rimborsoReso")
	public ResponseEntity<Boolean> rimborsoReso (@RequestBody ResoDto reso) {
		ResoDto resoDto=new ResoDto();
		resoDto.setId(reso.getId());
		resoDto.setUsernameMittente(reso.getUsernameMittente());
		resoDto.setUsernameDestinatario(reso.getUsernameDestinatario());
		resoDto.setNumeroOrdine(reso.getNumeroOrdine());
		resoDto.setIdProdotto(reso.getIdProdotto());
		resoDto.setMotivoReso(reso.getMotivoReso());
		resoDto.setQuantita(reso.getQuantita());
		resoDto.setPrezzo(reso.getPrezzo());
		resoDto.setRimborsato(true);
		resoService.addReso(resoDto);
		if(resoService.effettuaRimborso(resoDto))
			return ResponseEntity.ok(true);
		return ResponseEntity.ok(false);
	}

}
