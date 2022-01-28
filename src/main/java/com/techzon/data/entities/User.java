package com.techzon.data.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="USER")
public class User {
	
	public enum Genere{
		M,
		F
	}

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
	@Column(name = "USERNAME", unique = true)
	private String username;

	@Basic(optional = false)
	@Column(name = "PASSWORD")
	private String password;

	@Basic(optional = false)
	@Column(name = "TELEFONO")
	private String telefono;

	@Basic(optional = false)
	@Column(name = "SALDO")
	private Double saldo;

	@Basic(optional = false)
	@Column(name = "BLOCCATO")
	private boolean bloccato;

	@Basic(optional = false)
	@Column(name = "PUNTI")
	private Integer punti;
	
	
	@Basic(optional=false)
	@Column(name="IMMAGINE_PROFILO")
	private String immagineProfilo;
	
	@Basic(optional=false)
	@Column(name="DATA_NASCITA")
	private LocalDate dataNascita;
	
	@Basic(optional=false)
	@Column(name="GENERE")
	@Enumerated(EnumType.STRING)
	public Genere genere;
	
	@OneToMany(mappedBy = "user")
	private List<UtenteProdotto> utenteProdottoInUser=new ArrayList<UtenteProdotto>();
	
	@OneToOne(mappedBy = "user")
	private Carrello carrello;
	
	@OneToOne(mappedBy = "user")
	private Indirizzo indirizzo;
	
	@OneToOne(mappedBy = "utenteSegnalato")
	private SegnalazioneUtente segnalazioneUtente;
	
	@ManyToMany(mappedBy = "utenti")
	private List<CartaCredito> carte = new ArrayList<CartaCredito>();
	
	
	@OneToMany(mappedBy = "vendutoDa")
	private List<Prodotto> prodottiVendutiDa=new ArrayList<Prodotto>();
	
	@OneToMany(mappedBy = "user")
	private List<Preferito> preferitiInUser=new ArrayList<Preferito>();
	
	public User() {}

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

	public List<UtenteProdotto> getUtenteProdottoInUser() {
		return utenteProdottoInUser;
	}

	public void setUtenteProdottoInUser(List<UtenteProdotto> utenteProdottoInUser) {
		this.utenteProdottoInUser = utenteProdottoInUser;
	}

	public Carrello getCarrello() {
		return carrello;
	}

	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}

	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

	public SegnalazioneUtente getSegnalazioneUtente() {
		return segnalazioneUtente;
	}

	public void setSegnalazioneUtente(SegnalazioneUtente segnalazioneUtente) {
		this.segnalazioneUtente = segnalazioneUtente;
	}

	public List<CartaCredito> getCarte() {
		return carte;
	}

	public void setCarte(List<CartaCredito> carte) {
		this.carte = carte;
	}

	public List<Prodotto> getProdottiVendutiDa() {
		return prodottiVendutiDa;
	}

	public void setProdottiVendutiDa(List<Prodotto> prodottiVendutiDa) {
		this.prodottiVendutiDa = prodottiVendutiDa;
	}

	public List<Preferito> getPreferitiInUser() {
		return preferitiInUser;
	}

	public void setPreferitiInUser(List<Preferito> preferitiInUser) {
		this.preferitiInUser = preferitiInUser;
	}
}
