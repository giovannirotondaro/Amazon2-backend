package com.techzon.data.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.techzon.data.entities.User.Genere;

@Entity(name="RICHIESTA_CORRIERE")
public class RichiestaCorriere {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Basic(optional = false)
	@Column(name = "NOME", length = 40)
	private String nome;

	@Basic(optional = false)
	@Column(name = "COGNOME", length = 40)
	private String cognome;

	@Basic(optional = false)
	@Column(name = "EMAIL", unique = true)
	private String email;
	
	@Basic(optional = false)
	@Column(name = "TELEFONO")
	private String telefono;
	
	@Basic(optional=false)
	@Column(name="DATA_NASCITA")
	private LocalDate dataNascita;
	
	@Basic(optional=false)
	@Column(name="GENERE")
	@Enumerated(EnumType.STRING)
	private Genere genere;
	
	@Basic(optional=false)
	@Column(name="URL_PDF")
	private String urlPdf;

	@Column(name="DATA_COLLOQUIO")
	private LocalDate dataColloquio;
	
	@Column(name="ORA_COLLOQUIO")
	private LocalTime oraColloquio;
	
	@Column(name="HANNO_COLLOQUIO")
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
