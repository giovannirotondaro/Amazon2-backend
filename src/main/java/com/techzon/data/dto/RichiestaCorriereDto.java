package com.techzon.data.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import com.techzon.data.entities.User.Genere;

public class RichiestaCorriereDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;

	private String cognome;

	private String email;
	
	private String telefono;
		
	private LocalDate dataNascita;
	
	private Genere genere;
	
	private String urlPdf;
	
	private LocalDate dataColloquio;
	
	private LocalTime oraColloquio;
	
	private boolean hannoColloquio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Genere getGenere() {
		return genere;
	}

	public void setGenere(Genere genere) {
		this.genere = genere;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getUrlPdf() {
		return urlPdf;
	}
	
	public void setUrlPdf(String urlPdf) {
		this.urlPdf = urlPdf;
	}

	public LocalDate getDataColloquio() {
		return dataColloquio;
	}

	public void setDataColloquio(LocalDate dataColloquio) {
		this.dataColloquio = dataColloquio;
	}

	public LocalTime getOraColloquio() {
		return oraColloquio;
	}

	public void setOraColloquio(LocalTime oraColloquio) {
		this.oraColloquio = oraColloquio;
	}

	public boolean isHannoColloquio() {
		return hannoColloquio;
	}

	public void setHannoColloquio(boolean hannoColloquio) {
		this.hannoColloquio = hannoColloquio;
	}
}
