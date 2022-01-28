package com.techzon.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.techzon.data.dto.CarrelloDto;
import com.techzon.data.dto.CartaCreditoDto;
import com.techzon.data.dto.IndirizzoDto;
import com.techzon.data.dto.ProdottoCarrelloDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.services.CarrelloService;
import com.techzon.data.services.CartaCreditoService;
import com.techzon.data.services.IndirizzoService;
import com.techzon.data.services.ProdottoCarrelloService;
import com.techzon.data.services.UserService;
import com.techzon.utilities.InvioMail;

@RestController
@RequestMapping("/carrello")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CarrelloController {

	@Autowired
	private UserService userService;

	@Autowired
	private CarrelloService carrelloService;

	@Autowired
	private ProdottoCarrelloService prodottoCarrelloService;

	@Autowired
	private IndirizzoService indirizzoService;

	@Autowired
	private CartaCreditoService cartaCreditoService;

	@PostMapping("/addCart")
	public ResponseEntity<CarrelloDto> addCarrello(@RequestBody UserDto userDto) {
		CarrelloDto carrelloDto = new CarrelloDto();
		carrelloDto.setUser(null);
		carrelloDto.setProdottoCarrello(new ArrayList<ProdottoCarrelloDto>());
		carrelloService.add(carrelloDto);
		return ResponseEntity.ok(carrelloService.getById(carrelloService.getMaxId()));
	}

	@PutMapping("/updateCart")
	public ResponseEntity<Boolean> updateCarrello(@RequestBody UserDto userDto, @RequestParam("id") Long id) {
		CarrelloDto carrelloDto = carrelloService.getById(id);
		carrelloDto.setUser(userDto);
		carrelloService.add(carrelloDto);
		return ResponseEntity.ok(true);
	}

	@GetMapping("/sendEmail")
	public ResponseEntity<String> inviaEmail(@RequestParam("idUser") Long idUser, @RequestParam("totale") Double totale,
			@RequestParam("idCarta") Long idCarta) {
		UserDto userDto = userService.getUser(idUser);
		List<ProdottoCarrelloDto> prodottoCarrelloDto = prodottoCarrelloService
				.getAllByCarrello(carrelloService.getByUser(userDto));
		IndirizzoDto indirizzoDto = indirizzoService.getByUser(userDto);
		CartaCreditoDto cartaCreditoDto;
		if (idCarta > 0) {
			cartaCreditoDto = cartaCreditoService.getById(idCarta);
		} else
			cartaCreditoDto = null;

		String email = setTextEmail(prodottoCarrelloDto, totale, indirizzoDto, cartaCreditoDto);
		InvioMail mail = new InvioMail(userDto.getEmail(), email, "Acquisto Techzon");
		mail.inviaHtml();
		return ResponseEntity.ok("Email inviata");
	}

	private String setTextEmail(List<ProdottoCarrelloDto> prodottoCarrelloDto, Double totale, IndirizzoDto indirizzoDto, CartaCreditoDto cartaCreditoDto) {
		String text = "<h3><b>Grazie per il tuo acquisto su Techzon</b></h3>\r\n"
				+ "    <br>\r\n"
				+ "    <h3><b>Prodotti acquistati</b></h3>\r\n"
				+ "    <div style=\"margin: 0 !important; border: 1px solid; width: 380px;\">\r\n"
				+ "        <table style=\"margin-right: 80px !important;\">\r\n";
				
				for(ProdottoCarrelloDto prodotti: prodottoCarrelloDto) {
					text += "<tr><td>" + prodotti.getProdotto().getTitolo() + "<b> (x" + prodotti.getQuantita() + ")</b>, <b>Prezzo: </b>" + prodotti.getProdotto().getPrezzo() + " &euro;</td></tr>\r\n";
				}

		  text += "		   </table>\r\n"
		        + "    </div>\r\n"
				+ "    <br>\r\n"
				+ "    <h3><b>Indirizzo di consegna</b></h3>\r\n"
				+ "    <div style=\"margin: 0 !important; border: 1px solid; width: 380px\">\r\n"
				+ "        <table>\r\n"
				+ "            <tr><td><h4>" + indirizzoDto.getVia() + ", " + indirizzoDto.getCitta() + ", " + indirizzoDto.getProvincia() + ", " + indirizzoDto.getRegione() + "</h4></td></tr>\r\n"
				+ "        </table>\r\n"
				+ "    </div>\r\n"
				+ "    <br>\r\n"
				+ "    <h3><b>Totale e pagamento</b></h3>\r\n"
				+ "    <div style=\"margin: 0 !important; border: 1px solid; width: 230px\">\r\n"
				+ "        <table>\r\n"
				+ "            <tr><th>Totale: " + String.format("%.2f", totale) + " &euro;</th></tr>\r\n";
		 
		  	if(cartaCreditoDto != null) {
		  		text += "            <tr><td>Pagato con carta <b>" + cartaCreditoDto.getTipologia() + "</b></td></tr>\r\n"
						+ "            <tr><td>Termina con " + cartaCreditoDto.getNumeroCarta().substring(12, 16) + "</td></tr>\r\n"
						+ "            <tr><td>Titolare: " + cartaCreditoDto.getTitolare() + "</td></tr>\r\n"
						//+ "            <tr><td>" + cartaCreditoDto.getMeseScadenza() + "/" + cartaCreditoDto.getAnnoScadenza()+ "</td></tr>\r\n"
						+ "        </table>\r\n"
						+ "    </div>";
		  	}
		  	else
		  		text += "            <tr><td>Pagato con <b>Saldo Techzon</b></td></tr>\r\n";
		 
		    return text;
		
	}
}
