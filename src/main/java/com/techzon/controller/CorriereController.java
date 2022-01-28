
package com.techzon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techzon.data.dto.CorriereDto;
import com.techzon.data.services.CorriereService;
import com.techzon.utilities.EncryptPassword;
import com.techzon.utilities.ThreadEmail;

@RestController
@RequestMapping("/corriere")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CorriereController {
	
	@Autowired
	private CorriereService corriereService;

	@GetMapping("/corriereByUsername")
	public ResponseEntity<CorriereDto> getCorriereByUsername(@RequestParam("userCorriere") String userCorriere) { 
	    return ResponseEntity.ok(corriereService.getByUsername(userCorriere));
	}
	
	@GetMapping("/corriereByEmail")
	public ResponseEntity<Boolean> getCorriereByEmail(@RequestParam("emailCorriere") String emailCorriere) { 
	    boolean risultato=false;
	    CorriereDto corriereDto=corriereService.getByEmail(emailCorriere);
	    if(corriereDto==null) {
	    	risultato=false;
	    }
	    else{
	    	risultato=true;
	    }
		return ResponseEntity.ok(risultato);
	}
	
	@GetMapping("/corrieriAll")
	public ResponseEntity<List<CorriereDto>> getAllCorrieri(){
		return ResponseEntity.ok(corriereService.getAll());
	}
	
	@PostMapping("/aggiungiCorriere")
	public ResponseEntity<CorriereDto> aggiungiCorriere(@RequestBody CorriereDto corriere) {

		CorriereDto corriereDto =new CorriereDto();
		corriereDto.setNome(corriere.getNome());
		corriereDto.setCognome(corriere.getCognome());
		corriereDto.setDataNascita(corriere.getDataNascita());
		corriereDto.setEmail(corriere.getEmail());
		corriereDto.setTelefono(corriere.getTelefono());
		corriereDto.setPassword(corriere.getPassword());
		corriereDto.setGenere(corriere.getGenere());
		corriereDto.setUsername(corriere.getUsername());

		
		String destinataioEmail=corriere.getEmail();

		String testoEmail="Gentile aspirante corriere con la seguente mail vogliamo informarla che il suo curriculum vitae è stato visionato da un nostro \n"
						+ "team di esperti. Inoltre sulla base del colloquio di lavoro svolto con i manager di Techzon è emerso che la sua candidatura \n "
						+ "CORRISPONDE E SODDISFA i nostri requisiti lavorativi. Per tale motivo di seguito può trovare le credenziali per poter accedere\n"
						+ "al portale come corriere.\n"
						+ "\n"
						+ "Username: "+corriere.getUsername()+"\n"
						+ "Password: "+corriere.getPassword()+"\n"
						+ "\n"
						+ "Per qualsiasi dubbio, o informazione si prega di contattare lo staff di Techzon\n"
						+ "\n";
		
		corriereDto.setPassword(EncryptPassword.getInstance().generatePasswordCrypted(corriere.getPassword()));
		String oggettoEmail="Techzon - CORRIERI - LAVORA CON NOI";
		ThreadEmail t=new ThreadEmail(destinataioEmail,testoEmail,oggettoEmail);
		t.start();

		corriereService.addCorriere(corriereDto);
		return ResponseEntity.ok(corriereDto);
	}
	
}
