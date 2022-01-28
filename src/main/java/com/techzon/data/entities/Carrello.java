package com.techzon.data.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "CARRELLO")
public class Carrello {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
	private User user;
	
	@OneToMany(mappedBy = "prodotto")
	private List<ProdottoCarrello> prodottoCarrello = new ArrayList<ProdottoCarrello>();
	
	public Carrello() {}

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

	public List<ProdottoCarrello> getProdottoCarrello() {
		return prodottoCarrello;
	}

	public void setProdottoCarrello(List<ProdottoCarrello> prodottiCarrello) {
		this.prodottoCarrello = prodottiCarrello;
	}
}
