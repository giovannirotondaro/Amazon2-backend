package com.techzon.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.techzon.data.dto.CarrelloDto;
import com.techzon.data.dto.CartaCreditoDto;
import com.techzon.data.dto.IndirizzoDto;
import com.techzon.data.dto.PreferitoDto;
import com.techzon.data.dto.ProdottoDto;
import com.techzon.data.dto.RecensioneDto;
import com.techzon.data.dto.SegnalazioneUtenteDto;
import com.techzon.data.dto.UserDto;
import com.techzon.data.dto.UtenteProdottoDto;
import com.techzon.data.services.CarrelloService;
import com.techzon.data.services.CartaCreditoService;
import com.techzon.data.services.IndirizzoService;
import com.techzon.data.services.PreferitoService;
import com.techzon.data.services.ProdottoService;
import com.techzon.data.services.RecensioneService;
import com.techzon.data.services.SegnalazioneUtenteService;
import com.techzon.data.services.UserService;
import com.techzon.data.services.UtenteProdottoService;
import com.techzon.utilities.EncryptPassword;
import com.techzon.utilities.ThreadEmail;

@RestController
@RequestMapping("/utenti")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProdottoService prodottoService;
	
	@Autowired
	private CarrelloService carrelloService;
	
	@Autowired
	private RecensioneService recensioneService;
	
	@Autowired
	private SegnalazioneUtenteService segnalazioneUtenteService;
	
	@Autowired
	private UtenteProdottoService utenteProdottoService;
	
	@Autowired
	private IndirizzoService indirizzoService;
	
	@Autowired
	private CartaCreditoService cartaCreditoService;
	
	@Autowired
	private PreferitoService preferitoService;
	
	private static ArrayList<Character> arrayCaratteriConsentiti=new ArrayList<Character>(
			Arrays.asList(')','(','=','?','!','=',';',':','_','-','1','2','3','4','5','6','7','8','9')
	);
	
	@GetMapping("/allUsers")
	public ResponseEntity<List<UserDto>> all() {
	    return ResponseEntity.ok(userService.getAll());
	}
	
	@GetMapping("/user")
	public ResponseEntity<UserDto> getUser(@RequestParam("id") Long id) { 
	    return ResponseEntity.ok(userService.getUser(id));
	}
	
	@PostMapping("/blocca")
	public ResponseEntity<Boolean> bloccaUtente(@RequestParam("id") Long id) {
		//ritorna true se lo ha bloccato
		//ritorna false se era gia bloccato
		Boolean giaBloccato=false;
		UserDto userDto=userService.getUser(id);
		
		if(!userDto.isBloccato()) {
			userDto.setBloccato(true);
		}
		else{
			giaBloccato=true;
		}
		
		userService.addUser(userDto);
		
		return ResponseEntity.ok(giaBloccato);
	}
	
	@GetMapping("/userByUsername")
	public ResponseEntity<UserDto> getUserByUsername(@RequestParam("username") String username) { 
		UserDto userApp=null;
		try {
			userApp=userService.getByUsername(username);
		}
		catch (Exception e) {
			userApp=null;
		}
	    return ResponseEntity.ok(userApp);
	}
	
	@GetMapping("/userByEmail")
	public ResponseEntity<UserDto> getUserByEmail(@RequestParam("email") String email) { 
		UserDto userApp=null;
		try {
			userApp=userService.getByEmail(email);
		}
		catch (Exception e) {
			userApp=null;
		}
		return ResponseEntity.ok(userApp);
	}
	
	@PutMapping("/modificaUtente")
	public ResponseEntity<UserDto> modificaUser(@RequestBody UserDto user) {
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setNome(user.getNome());
		userDto.setCognome(user.getCognome());
		userDto.setDataNascita(user.getDataNascita());
		userDto.setEmail(user.getEmail());
		userDto.setTelefono(user.getTelefono());
		userDto.setPassword(user.getPassword());
		userDto.setGenere(user.getGenere());
		userDto.setImmagineProfilo(user.getImmagineProfilo());
		userDto.setBloccato(false);
		userDto.setPunti(user.getPunti());
		userDto.setSaldo(user.getSaldo());
		userDto.setUsername(user.getUsername());
		userService.addUser(userDto);
		return ResponseEntity.ok(userDto);
	}
	
	@PutMapping("/modificaPassword")
	public ResponseEntity<String> modificaPassowrd(@RequestBody UserDto userDto, @RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass){
		if(EncryptPassword.getInstance().checkPassword(oldPass, userDto.getPassword())) {
			String passToSave = EncryptPassword.getInstance().generatePasswordCrypted(newPass);
			userDto.setPassword(passToSave);
			userDto.setIndirizzo(null);
			userService.addUser(userDto);
			return ResponseEntity.ok("Password modificata con successo");
		}
		
		return ResponseEntity.ok("Controlla che la password attuale sia corretta");
	}
	
	@PutMapping("/updateUserSaldo")
	public ResponseEntity<UserDto> updateUserSaldo(@RequestBody UserDto userDto, @RequestParam("saldo") Double saldo) { 
		userDto.setSaldo(saldo);
		userDto.setIndirizzo(null);
		userService.addUser(userDto);
	    return ResponseEntity.ok(userDto);
	}

	@PutMapping("/updateUserPunti")
	public ResponseEntity<UserDto> updateUserPunti(@RequestBody UserDto userDto, @RequestParam("punti") Double punti) { 
		Integer p = punti.intValue();
		userDto.setPunti(p);
		userDto.setIndirizzo(null);
		userService.addUser(userDto);
	    return ResponseEntity.ok(userDto);
	}
	
	@PutMapping("/updateUserSaldoPunti")
	public ResponseEntity<UserDto> updateUserSaldoPunti(@RequestBody UserDto userDto, @RequestParam("saldo") Double saldo, @RequestParam("punti") Double punti) { 
		Integer p = punti.intValue();
		userDto.setPunti(p);
		userDto.setSaldo(saldo);
		userDto.setIndirizzo(null);
		userService.addUser(userDto);
	    return ResponseEntity.ok(userDto);
	}
	
	@PostMapping("/cancellazioneAccount")
	public ResponseEntity<Boolean> eliminaAccount(@RequestBody UserDto user){
		cancellaProdottiPerUtente(user);
		cancellaCarrelloPerUtente(user);
		cancellaRecensioniPerUtente(user);
		cancellaSegnalazioniPerUtente(user);
		cancellaUtenteProdottoPerUtente(user);
		cancellaIndirizzoPerUtente(user);
		cancellaCartePerUtente(user);
		cancellaPreferitiPerUtente(user);
		user.setIndirizzo(null);
		userService.rimuoviUtente(user);
		return ResponseEntity.ok(true);
	}
	
	public void cancellaProdottiPerUtente(UserDto user) {
		List<ProdottoDto> listaProdotti = prodottoService.getAllVendutoDa(user);
		for(int i=0;i<listaProdotti.size();i++) {
			prodottoService.deleteProdotto(listaProdotti.get(i));
		}

	}
	
	public void cancellaCarrelloPerUtente(UserDto user) {
		
		CarrelloDto carrelloDto = carrelloService.getByUser(user);
		if(carrelloDto!=null) {
			carrelloService.deleteCarrello(carrelloDto);
		}
		
	}
	
	public void cancellaRecensioniPerUtente(UserDto user) {
		List<RecensioneDto> listaRecensioni = recensioneService.getAllUser(user);
		for(int i=0;i<listaRecensioni.size();i++) {
			recensioneService.deleteRecensione(listaRecensioni.get(i));
		}

	}
	
	public void cancellaSegnalazioniPerUtente(UserDto user) {
		List<SegnalazioneUtenteDto> listaSegnalazioniFatte = segnalazioneUtenteService.getAllSegnalazioniFatte(user);
		List<SegnalazioneUtenteDto> listaSegnalazioniRicevute = segnalazioneUtenteService.getAllSegnalazioniRicevute(user);
		for(int i=0; i<listaSegnalazioniFatte.size(); i++) {
			segnalazioneUtenteService.deleteSegnalazione(listaSegnalazioniFatte.get(i));
		}
		for(int i=0; i<listaSegnalazioniRicevute.size(); i++) {
			segnalazioneUtenteService.deleteSegnalazione(listaSegnalazioniRicevute.get(i));
		}
		
		
	}
	
	public void cancellaUtenteProdottoPerUtente(UserDto user) {
		List<UtenteProdottoDto> listaUtenteProdotto = utenteProdottoService.getAllByUtente(user);
		for(int i=0;i<listaUtenteProdotto.size();i++) {
			utenteProdottoService.rimuoviUtenteProdotto(listaUtenteProdotto.get(i));
		}

	}
	
	public void cancellaIndirizzoPerUtente(UserDto user) {
		IndirizzoDto indirizzoDto = indirizzoService.getByUser(user);
		if(indirizzoDto!=null) {
			indirizzoService.deleteIndirizzo(indirizzoDto);
		}

	}
	
public void cancellaCartePerUtente(UserDto userDto) {
		
		for(CartaCreditoDto carta : userDto.getCarte()) {
			CartaCreditoDto c = cartaCreditoService.getById(carta.getId());
			int index = 0;
		    for(int i=0; i<c.getUtenti().size(); i++) {
		      if(c.getUtenti().get(i).getId() == userDto.getId()) {
		        index = i;
		        break;
		      }
		    }
		    if(c.getUtenti().size() == 1) {
		      cartaCreditoService.deleteByCardId(c.getId());
		    }
		    else {
		      c.getUtenti().remove(index);
		      cartaCreditoService.add(c);
		    }
		}
	}
	
	public void cancellaPreferitiPerUtente(UserDto userDto) {
		List<PreferitoDto> preferiti = preferitoService.getAllByUtente(userDto);
		for(PreferitoDto p : preferiti) {
			preferitoService.rimuoviPreferito(p);
		}
		
	}
	
	@GetMapping("/utente/{username}")
	public ResponseEntity<UserDto> getUser(@PathVariable("username") String username) {
		return ResponseEntity.ok(userService.getByUsername(username));
	}
	
	@GetMapping("/generaCodice")
	public ResponseEntity<Integer> generaCodice(@RequestParam("email") String email) {
		int num=generaNuovoCodice();
		String destinatarioEmail=email;
		String nomeUtente=userService.getByEmail(email).getNome();
		String testoEmail="Gentile utente "+nomeUtente+" il codice per poter recuperare le tue credenziali è "+num;
		String oggettoEmail="Techzon - RECUPERA CREDENZIALI";
		ThreadEmail t=new ThreadEmail(destinatarioEmail,testoEmail,oggettoEmail);
		t.start();
		return ResponseEntity.ok(num);
	}
	
	public int generaNuovoCodice() {
		StringBuilder nuovoCodice=new StringBuilder();
		int numeriPerGenerareCodice=6;
		for(int i=0;i<numeriPerGenerareCodice;i++) {
			int number = (int)((Math.random()*10)+1);
			int numeroGenerato=number-1;
			nuovoCodice.append(numeroGenerato);
		}
		return Integer.parseInt(nuovoCodice.toString());
	}
	
	@PostMapping("/registraUtente")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto user) {
		UserDto userDto=new UserDto();
		userDto.setNome(user.getNome());
		userDto.setCognome(user.getCognome());
		userDto.setDataNascita(user.getDataNascita());
		userDto.setEmail(user.getEmail());
		userDto.setTelefono(user.getTelefono());
		userDto.setPassword(EncryptPassword.getInstance().generatePasswordCrypted(user.getPassword()));
		userDto.setGenere(user.getGenere());
		userDto.setImmagineProfilo(user.getImmagineProfilo());
		userDto.setBloccato(false);
		userDto.setPunti(0);
		userDto.setSaldo(0.0);
		userDto.setUsername(user.getUsername());
		userService.addUser(userDto);
		return ResponseEntity.ok(userDto);
	}
	
	
	@GetMapping("/recuperaUsername")
	public HttpStatus recuperaUsername(@RequestParam("email") String email) {
		String username=userService.getByEmail(email).getUsername();
		String destinataioEmail=email;
		String testoEmail="Gentile "+email+" il tuo username per accedere alla piattaforma è "+username;
		String oggettoEmail="Techzon - RECUPERA USERNAME";
		ThreadEmail t=new ThreadEmail(destinataioEmail,testoEmail,oggettoEmail);
		t.start();
		return HttpStatus.OK;
	}
	
	
	@PutMapping("/recuperaPassword")
	public HttpStatus recuperaPassword(@RequestParam("email") String email) {
		UserDto user=userService.getByEmail(email);
		String nuovaPassword=generaNuovaPassword();
		user.setPassword(EncryptPassword.getInstance().generatePasswordCrypted(nuovaPassword));
		userService.addUser(user);
		String destinataioEmail=user.getEmail();
		String testoEmail="Gentile "+user.getUsername()+" la tua nuova password per accedere alla piattaforma è "+nuovaPassword;
		String oggettoEmail="Techzon - RECUPERA PASSWORD";
		ThreadEmail t=new ThreadEmail(destinataioEmail,testoEmail,oggettoEmail);
		t.start();
		return HttpStatus.OK;
	}
	
	
	public static String generaNuovaPassword() {
		for(char i='a';i<='z';i++) {
			arrayCaratteriConsentiti.add(i);
		}
		for(char i='A';i<='Z';i++) {
			arrayCaratteriConsentiti.add(i);
		}
		StringBuilder nuovaPassword=new StringBuilder();
		int numCaratteriPassword=8;
		for(int i=0;i<numCaratteriPassword;i++) {
			int number = (int)(Math.random() * arrayCaratteriConsentiti.size()+1);
			int indice=number-1;
			nuovaPassword.append(arrayCaratteriConsentiti.get(indice));
		}
		return nuovaPassword.toString();
	}
}
