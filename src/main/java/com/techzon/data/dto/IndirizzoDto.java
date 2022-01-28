package com.techzon.data.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class IndirizzoDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String regione;
	
	private String provincia;
	
	private String citta;
	
	private String via;
	
	private String cap;
	
	@JsonIgnore
	private UserDto user;
	
	public IndirizzoDto() {}

	public IndirizzoDto(Long id, String regione, String provincia, String citta, String via, String cap, UserDto user) {
		super();
		this.id = id;
		this.regione = regione;
		this.provincia = provincia;
		this.citta = citta;
		this.via = via;
		this.cap = cap;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
}
