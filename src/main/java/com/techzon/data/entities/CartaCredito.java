package com.techzon.data.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "CARTA_CREDITO")
public class CartaCredito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Basic(optional = false)
	@Column(name = "TITOLARE")
	private String titolare;
	
	@Basic(optional = false)
	@Column(name = "NUMERO_CARTA", unique = true)
	private String numeroCarta;
	
	@Basic(optional = false)
	@Column(name = "MESE_SCADENZA")
	private String meseScadenza;
	
	@Basic(optional = false)
	@Column(name = "ANNO_SCADENZA")
	private String annoScadenza;
	
	@Basic(optional = false)
	@Column(name = "CVV")
	private String cvv;
	
	@Basic(optional = false)
	@Column(name = "TIPOLOGIA")
	private String tipologia;
	
	@ManyToMany
	@JoinTable(
			name = "UTENTE_CARTA",
			joinColumns = @JoinColumn(name = "ID_CARTA", referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
	)
	private List<User> utenti = new ArrayList<User>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolare() {
		return titolare;
	}

	public void setTitolare(String titolare) {
		this.titolare = titolare;
	}

	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public String getMeseScadenza() {
		return meseScadenza;
	}

	public void setMeseScadenza(String meseScadenza) {
		this.meseScadenza = meseScadenza;
	}

	public String getAnnoScadenza() {
		return annoScadenza;
	}

	public void setAnnoScadenza(String annoScadenza) {
		this.annoScadenza = annoScadenza;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public List<User> getUtenti() {
		return utenti;
	}

	public void setUtenti(List<User> utenti) {
		this.utenti = utenti;
	}
}
