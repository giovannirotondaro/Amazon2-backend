package com.techzon.data.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Provincia {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@Basic(optional = false)
	@Column(name="NOME")
	private String nome;

	@ManyToOne
	@JoinColumn(name="REGIONE_ID",referencedColumnName="ID")
	private Regione regione;
	
	public Provincia() {}

	public Regione getRegione() {
		return regione;
	}

	public void setRegione(Regione regione) {
		this.regione = regione;
	}

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
}
