package com.techzon.data.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "UTENTE_PRODOTTO")
public class UtenteProdotto {

	public enum Stato{
		NIENTE,
		PRESO_IN_CARICO,
		IN_CONSEGNA,
		CONSEGNATO
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="ID_USER",referencedColumnName = "ID")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="ID_PRODOTTO",referencedColumnName = "ID")
	private Prodotto prodotto;
	
	@ManyToOne
	@JoinColumn(name="ID_CORRIERE",referencedColumnName = "ID")
	private Corriere corriere;
	
	@Basic(optional=false)
	@Column(name="STATO")
	@Enumerated(EnumType.STRING)
	public Stato stato;
	
	@Basic(optional = false)
	@Column(name = "QUANTITA")
	private Integer quantita;
	
	@OneToOne(mappedBy = "numeroOrdine")
	private Reso reso;
	
	public UtenteProdotto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public Corriere getCorriere() {
		return corriere;
	}

	public void setCorriere(Corriere corriere) {
		this.corriere = corriere;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public Reso getReso() {
		return reso;
	}

	public void setReso(Reso reso) {
		this.reso = reso;
	}
}
