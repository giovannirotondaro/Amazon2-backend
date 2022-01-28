package com.techzon.data.dto;

import java.io.Serializable;
import java.util.List;

public class RegioneDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	
	private List<ProvinciaDto> provincie;
	
	public RegioneDto() {}

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

	public List<ProvinciaDto> getProvincie() {
		return provincie;
	}

	public void setProvincie(List<ProvinciaDto> provincie) {
		this.provincie = provincie;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
