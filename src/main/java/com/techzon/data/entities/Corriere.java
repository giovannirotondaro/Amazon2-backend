package com.techzon.data.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.techzon.data.entities.User.Genere;

@Entity(name="CORRIERE")
public class Corriere {
	
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
	
	@Basic(optional=false)
	@Column(name="DATA_NASCITA")
	private LocalDate dataNascita;
	
	@Basic(optional=false)
	@Column(name="GENERE")
	@Enumerated(EnumType.STRING)
	public Genere genere;
	
	@OneToMany(mappedBy = "corriere")
	private List<UtenteProdotto> utentiProdotto;
	
	public Corriere() {
		
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

	public List<UtenteProdotto> getUtentiProdotto() {
		return utentiProdotto;
	}

	public void setUtentiProdotto(List<UtenteProdotto> utentiProdotto) {
		this.utentiProdotto = utentiProdotto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((dataNascita == null) ? 0 : dataNascita.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((genere == null) ? 0 : genere.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((utentiProdotto == null) ? 0 : utentiProdotto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corriere other = (Corriere) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (dataNascita == null) {
			if (other.dataNascita != null)
				return false;
		} else if (!dataNascita.equals(other.dataNascita))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (genere != other.genere)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (utentiProdotto == null) {
			if (other.utentiProdotto != null)
				return false;
		} else if (!utentiProdotto.equals(other.utentiProdotto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Corriere [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", username="
				+ username + ", password=" + password + ", telefono=" + telefono + ", dataNascita=" + dataNascita
				+ ", genere=" + genere + ", utentiProdotto=" + utentiProdotto + "]";
	}
}
