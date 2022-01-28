package com.techzon.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="RECENSIONE")
public class Recensione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="ID")
	private Long id;
	
	@Basic(optional = false)
	@Column(name="RECENSIONE")
	private String recensione;
	
	@Basic(optional = false)
	@Column(name = "DATA_AGGIUNTA")
	private LocalDate dataAggiunta;
	
	@ManyToOne
	@JoinColumn(name = "CREATA_DA", referencedColumnName = "ID")
	private User creataDa;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "PRODOTTO_RECENSITO", referencedColumnName = "ID")
	private Prodotto prodottoRecensito;

	@Basic(optional = false)
	@Column(name = "APPROVATA")
	private Boolean approvata;

	@Basic(optional = false)
	@Column(name = "RATING")
	private Integer rating;

	public Recensione() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRecensione() {
		return recensione;
	}

	public void setRecensione(String r) {
		this.recensione = r;
	}


	public LocalDate getData() {
		return dataAggiunta;
	}

	public void setDataNascita(LocalDate data) {
		this.dataAggiunta = data;
	}

	public LocalDate getDataAggiunta() {
		return dataAggiunta;
	}

	public void setDataAggiunta(LocalDate dataAggiunta) {
		this.dataAggiunta = dataAggiunta;
	}

	public User getCreataDa() {
		return creataDa;
	}

	public void setCreataDa(User creataDa) {
		this.creataDa = creataDa;
	}

	public Prodotto getProdottoRecensito() {
		return prodottoRecensito;
	}

	public void setProdottoRecensito(Prodotto prodottoRecensito) {
		this.prodottoRecensito = prodottoRecensito;
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
}
