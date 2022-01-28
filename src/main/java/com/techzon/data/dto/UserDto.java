package com.techzon.data.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techzon.data.entities.User.Genere;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;

	private String cognome;
	
	private String email;
	
	private String username;

	private String password;

	private String telefono;

	private Double saldo;

	private boolean bloccato;

	private Integer punti;
	
	private String immagineProfilo;
	
	private LocalDate dataNascita;

	private Genere genere;
	
	private IndirizzoDto indirizzo;

	private List<CartaCreditoDto> carte;
	
	@JsonIgnore
	private SegnalazioneUtenteDto segnalazioneUtente;
	
	private CarrelloDto carrello;
	
	@JsonIgnore
	private List<UtenteProdottoDto> utenteProdottoInUser;
	
	@JsonIgnore
	private List<ProdottoDto> prodottiVendutiDa;
	
	public UserDto() {}

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

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public boolean isBloccato() {
		return bloccato;
	}

	public void setBloccato(boolean bloccato) {
		this.bloccato = bloccato;
	}

	public Integer getPunti() {
		return punti;
	}

	public void setPunti(Integer punti) {
		this.punti = punti;
	}

	public String getImmagineProfilo() {
		return immagineProfilo;
	}

	public void setImmagineProfilo(String immagineProfilo) {
		this.immagineProfilo = immagineProfilo;
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

	public IndirizzoDto getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(IndirizzoDto indirizzo) {
		this.indirizzo = indirizzo;
	}

	public List<CartaCreditoDto> getCarte() {
		return carte;
	}

	public void setCarte(List<CartaCreditoDto> carte) {
		this.carte = carte;
	}

	public SegnalazioneUtenteDto getSegnalazioneUtente() {
		return segnalazioneUtente;
	}

	public void setSegnalazioneUtente(SegnalazioneUtenteDto segnalazioneUtente) {
		this.segnalazioneUtente = segnalazioneUtente;
	}

	public CarrelloDto getCarrello() {
		return carrello;
	}

	public void setCarrello(CarrelloDto carrello) {
		this.carrello = carrello;
	}

	public List<UtenteProdottoDto> getUtenteProdottoInUser() {
		return utenteProdottoInUser;
	}

	public void setUtenteProdottoInUser(List<UtenteProdottoDto> utenteProdottoInUser) {
		this.utenteProdottoInUser = utenteProdottoInUser;
	}

	public List<ProdottoDto> getProdottiVendutiDa() {
		return prodottiVendutiDa;
	}

	public void setProdottiVendutiDa(List<ProdottoDto> prodottiVendutiDa) {
		this.prodottiVendutiDa = prodottiVendutiDa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", username="
				+ username + ", password=" + password + ", telefono=" + telefono + ", saldo=" + saldo + ", bloccato="
				+ bloccato + ", punti=" + punti + ", immagineProfilo=" + immagineProfilo + ", dataNascita="
				+ dataNascita + ", genere=" + genere + ", indirizzo=" + indirizzo + ", segnalazioneUtente="
				+ segnalazioneUtente + ", carrello=" + carrello + ", utenteProdottoInUser=" + utenteProdottoInUser
				+ "]";
	}
}
