package com.techzon.data.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.techzon.data.entities.User;

public class SegnalazioneUtenteDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String motivazionePrincipale;
	
	private String motivazioneAggiuntiva;
	
	@JsonIgnoreProperties({"prodottiComprati","prodottiCarrello","segnalazioneUtente"})
	private User utenteSegnalato;
	
	@JsonIgnoreProperties({"prodottiComprati","prodottiCarrello","segnalazioneUtente"})
	private User utenteCheFaSegnalazione;
	
	public SegnalazioneUtenteDto() {}

	public SegnalazioneUtenteDto(Long id, String motivazionePrincipale, String motivazioneAggiuntiva,
			User utenteSegnalato, User utenteCheFaSegnalazione) {
		super();
		this.id = id;
		this.motivazionePrincipale = motivazionePrincipale;
		this.motivazioneAggiuntiva = motivazioneAggiuntiva;
		this.utenteSegnalato = utenteSegnalato;
		this.utenteCheFaSegnalazione = utenteCheFaSegnalazione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMotivazionePrincipale() {
		return motivazionePrincipale;
	}

	public void setMotivazionePrincipale(String motivazionePrincipale) {
		this.motivazionePrincipale = motivazionePrincipale;
	}

	public String getMotivazioneAggiuntiva() {
		return motivazioneAggiuntiva;
	}

	public void setMotivazioneAggiuntiva(String motivazioneAggiuntiva) {
		this.motivazioneAggiuntiva = motivazioneAggiuntiva;
	}

	public User getUtenteSegnalato() {
		return utenteSegnalato;
	}

	public void setUtenteSegnalato(User userSegnalato) {
		this.utenteSegnalato = userSegnalato;
	}

	public User getUtenteCheFaSegnalazione() {
		return utenteCheFaSegnalazione;
	}

	public void setUtenteCheFaSegnalazione(User utenteCheFaSegnalazione) {
		this.utenteCheFaSegnalazione = utenteCheFaSegnalazione;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
