package com.techzon.data.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.techzon.data.entities.User;

public class PremioDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Integer valore;
	
	private Integer punti;
	
	private String nome;
	
	private List<User> hannoRiscattato = new ArrayList<>();

	public PremioDto(Long id, Integer valore, Integer punti, String nome, List<User> hannoRiscattato) {
		super();
		this.id = id;
		this.valore = valore;
		this.punti = punti;
		this.nome = nome;
		this.hannoRiscattato = hannoRiscattato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getValore() {
		return valore;
	}

	public void setValore(Integer valore) {
		this.valore = valore;
	}

	public Integer getPunti() {
		return punti;
	}

	public void setPunti(Integer punti) {
		this.punti = punti;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<User> getHannoRiscattato() {
		return hannoRiscattato;
	}

	public void setHannoRiscattato(List<User> hannoRiscattato) {
		this.hannoRiscattato = hannoRiscattato;
	}
}
