package com.techzon.data.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CarrelloDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@JsonIgnoreProperties({"carrello", "carte"})
	private UserDto user;
	
	public List<ProdottoCarrelloDto> prodottoCarrello;
	
	public CarrelloDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public List<ProdottoCarrelloDto> getProdottoCarrello() {
		return prodottoCarrello;
	}

	public void setProdottoCarrello(List<ProdottoCarrelloDto> prodottoCarrello) {
		this.prodottoCarrello = prodottoCarrello;
	}
}
