package com.techzon.data.entities;

import javax.persistence.*;

@Entity(name = "PREFERITO")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"ID_PRODOTTO","ID_USER"}))
public class Preferito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
    private Long id;

	@ManyToOne
	@JoinColumn(name="ID_PRODOTTO",referencedColumnName = "ID")
	private Prodotto prodotto;

	@ManyToOne
	@JoinColumn(name="ID_USER",referencedColumnName = "ID")
	private User user;


	public Preferito() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
