package com.techzon.data.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ProdottoCarrelloDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@JsonIgnoreProperties("prodottoCarrello")
	private CarrelloDto carrello;

	private ProdottoDto prodotto;
	
	private Integer quantita;
	
	public ProdottoCarrelloDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CarrelloDto getCarrello() {
		return carrello;
	}

	public void setCarrello(CarrelloDto carrello) {
		this.carrello = carrello;
	}

	public ProdottoDto getProdotto() {
		return prodotto;
	}

	public void setProdotto(ProdottoDto prodotto) {
		this.prodotto = prodotto;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
}
