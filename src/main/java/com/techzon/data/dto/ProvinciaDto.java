package com.techzon.data.dto;

import java.io.Serializable;

public class ProvinciaDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private RegioneDto regione;
	
	
	public ProvinciaDto() {}
	
	public ProvinciaDto(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
	
	public RegioneDto getRegione() {
		return regione;
	}

	public void setRegione(RegioneDto regione) {
		this.regione = regione;
	}

}
