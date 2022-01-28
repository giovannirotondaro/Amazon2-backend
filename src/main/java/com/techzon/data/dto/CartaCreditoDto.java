package com.techzon.data.dto;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CartaCreditoDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String titolare;
	
	private String numeroCarta;
	
	private String meseScadenza;
	
	private String annoScadenza;
	
	private String cvv;
	
	private String tipologia;
	
	@JsonIgnore
	private List<UserDto> utenti;
	
	public CartaCreditoDto() {}

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

	public List<UserDto> getUtenti() {
		return utenti;
	}

	public void setUtenti(List<UserDto> utenti) {
		this.utenti = utenti;
	}
}
