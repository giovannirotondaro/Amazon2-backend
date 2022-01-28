package com.techzon.data.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techzon.data.entities.User.Genere;

public class CorriereDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private String cognome;
	
	private String email;
	
	private String username;
	
	private String password;
	
	private String telefono;
	
	private LocalDate dataNascita;

	public Genere genere;
	
	@JsonIgnore
	private List<UtenteProdottoDto> utentiProdotto;
	
	public CorriereDto() {}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "CorriereDto [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", username="
				+ username + ", password=" + password + ", telefono=" + telefono + ", dataNascita=" + dataNascita
				+ ", genere=" + genere + ", utentiProdotto=" + utentiProdotto + "]";
	}

	public List<UtenteProdottoDto> getUtentiProdotto() {
		return utentiProdotto;
	}

	public void setUtentiProdotto(List<UtenteProdottoDto> utentiProdotto) {
		this.utentiProdotto = utentiProdotto;
	}
}
