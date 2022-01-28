package com.techzon.data.entities;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Regione {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@Basic(optional = false)
	@Column(name="NOME")
	private String nome;
	
	@OneToMany(mappedBy="regione")
	private List<Provincia> provincie;
	
	public Regione() {}

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

	public List<Provincia> getProvincie() {
		return provincie;
	}

	public void setProvincie(List<Provincia> provincie) {
		this.provincie = provincie;
	}
}
