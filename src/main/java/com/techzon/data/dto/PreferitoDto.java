package com.techzon.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;

public class PreferitoDto {

	private Long id;

	@JsonIgnoreProperties({"carrello", "segnalazioneUtente"})
	private UserDto user;

	@JsonIgnoreProperties({"recensioni","presenteNelCarrello"})
	private ProdottoDto prodotto;

	public PreferitoDto() {}

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

	public ProdottoDto getProdotto() {
		return prodotto;
	}

	public void setProdotto(ProdottoDto prodotto) {
		this.prodotto = prodotto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PreferitoDto that = (PreferitoDto) o;
		return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(prodotto, that.prodotto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, user, prodotto);
	}

	@Override
	public String toString() {
		return "PreferitoDto{" +
				"id=" + id +
				", user=" + user +
				", prodotto=" + prodotto +
				'}';
	}
}
