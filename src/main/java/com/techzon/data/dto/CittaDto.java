package com.techzon.data.dto;
import java.io.Serializable;

public class CittaDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private ProvinciaDto provincia;
	
	public CittaDto() {}

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

	public ProvinciaDto getProvincia() {
		return provincia;
	}

	public void setProvincia(ProvinciaDto provincia) {
		this.provincia = provincia;
	}
}
