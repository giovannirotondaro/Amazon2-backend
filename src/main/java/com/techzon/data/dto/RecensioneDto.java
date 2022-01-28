package com.techzon.data.dto;

import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RecensioneDto implements Serializable {


	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String recensione;
	
	private LocalDate dataAggiunta;

	@JsonIgnoreProperties({"carrello", "prodottiComprati","segnalazioneUtente"})
	private UserDto creataDa;
	
	@JsonIgnoreProperties({"vendutoDa","compratoDa","recensioni","presenteNelCarrello"})
	private ProdottoDto prodottoRecensito;

	private Boolean approvata;

	private Integer rating;
	
	public RecensioneDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecensione() {
		return recensione;
	}

	public void setRecensione(String recensione) {
		this.recensione = recensione;
	}

	public LocalDate getDataAggiunta() {
		return dataAggiunta;
	}

	public void setDataAggiunta(LocalDate dataAggiunta) {
		this.dataAggiunta = dataAggiunta;
	}

	public UserDto getCreataDa() {
		return creataDa;
	}

	public void setCreataDa(UserDto creataDa) {
		this.creataDa = creataDa;
	}

	public ProdottoDto getProdottoRecensito() {
		return prodottoRecensito;
	}

	public void setProdottoRecensito(ProdottoDto prodottoRecensito) {
		this.prodottoRecensito = prodottoRecensito;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getApprovata() {
		return approvata;
	}

	public void setApprovata(Boolean approvata) {
		this.approvata = approvata;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "RecensioneDto{" +
				"id=" + id +
				", recensione='" + recensione + '\'' +
				", dataAggiunta=" + dataAggiunta +
				", creataDa=" + creataDa +
				", prodottoRecensito=" + prodottoRecensito +
				", approvata=" + approvata +
				", rating=" + rating +
				'}';
	}
}
