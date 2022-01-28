package com.techzon.data.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name="SEGNALAZIONE_UTENTE")
public class SegnalazioneUtente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Basic(optional = false)
	@Column(name="MOTIVAZIONE_PRINCIPALE")
	private String motivazionePrincipale;
	
	@OneToOne
	@JoinColumn(name="USER_SEGNALATO_ID", referencedColumnName = "ID")
	private User utenteSegnalato;
	
	@ManyToOne
	@JoinColumn(name="UTENTE_CHE_FA_SEGNALAZIONE",referencedColumnName = "ID")
	private User utenteCheFaSegnalazione;
	
	public SegnalazioneUtente() {}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((motivazionePrincipale == null) ? 0 : motivazionePrincipale.hashCode());
		result = prime * result + ((utenteCheFaSegnalazione == null) ? 0 : utenteCheFaSegnalazione.hashCode());
		result = prime * result + ((utenteSegnalato == null) ? 0 : utenteSegnalato.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SegnalazioneUtente other = (SegnalazioneUtente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (motivazionePrincipale == null) {
			if (other.motivazionePrincipale != null)
				return false;
		} else if (!motivazionePrincipale.equals(other.motivazionePrincipale))
			return false;
		if (utenteCheFaSegnalazione == null) {
			if (other.utenteCheFaSegnalazione != null)
				return false;
		} else if (!utenteCheFaSegnalazione.equals(other.utenteCheFaSegnalazione))
			return false;
		if (utenteSegnalato == null) {
			if (other.utenteSegnalato != null)
				return false;
		} else if (!utenteSegnalato.equals(other.utenteSegnalato))
			return false;
		return true;
	}

}