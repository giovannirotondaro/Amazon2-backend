package com.techzon.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.techzon.data.entities.UtenteProdotto.Stato;

public class UtenteProdottoDto {
	
	private Long id;
	
	@JsonIgnoreProperties({"carrello", "segnalazioneUtente"})
	private UserDto user;
	
	@JsonIgnoreProperties({"recensioni","presenteNelCarrello"})
	private ProdottoDto prodotto;
	
	public Stato stato;
	
	private CorriereDto corriere;
	
	private Integer quantita;
	
	@JsonIgnore
	private ResoDto reso;
	
	public UtenteProdottoDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public ProdottoDto getProdotto() {
		return prodotto;
	}

	public void setProdotto(ProdottoDto prodotto) {
		this.prodotto = prodotto;
	}

	public CorriereDto getCorriere() {
		return corriere;
	}

	public void setCorriere(CorriereDto corriere) {
		this.corriere = corriere;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public ResoDto getReso() {
		return reso;
	}

	public void setReso(ResoDto reso) {
		this.reso = reso;
	}
}
