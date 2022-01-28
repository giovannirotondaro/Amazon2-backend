package com.techzon.data.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "PRODOTTO")
public class Prodotto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Basic(optional = false)
	@Column(name = "TITOLO")
	private String titolo;
	
	@Basic(optional = false)
	@Column(name = "ANTEPRIMA_DESCRIZIONE", length = 255)
	private String anteprima;
	
	@Basic(optional = false)
	@Column(name = "DESCRIZIONE", length = 2000)
	private String descrizione;
	
	@Basic(optional = true)
	@Column(name = "DIMENSIONI")
	private String dimensioni;
	
	@Basic(optional = true)
	@Column(name = "PESO")
	private Double peso;
	
	@Basic(optional = true)
	@Column(name = "MEMORIA_RAM")
	private String memoriaRam;
	
	@Basic(optional = true)
	@Column(name = "MEMORIA_ARC")
	private String memoriaArchiviazione;
	
	@Basic(optional = true)
	@Column(name = "MARCA")
	private String marca;
	
	@Basic(optional = true)
	@Column(name = "MODELLO")
	private String modello;
	
	@Basic(optional = false)
	@Column(name = "PREZZO")
	private Double prezzo;
	
	@Basic(optional = false)
	@Column(name = "SCONTO")
	private Integer sconto;
	
	@Basic(optional = false)
	@Column(name = "COLORE")
	private String colore;
	
	@Basic(optional = false)
	@Column(name = "CATEGORIA")
	private String categoria;
	
	@Basic(optional = false)
	@Column(name = "QUANTITA")
	private Integer quantita;
	
	@Basic(optional = false)
	@Column(name = "DISPONIBILE")
	private Boolean disponibile;
	
	@Basic(optional = false)
	@Column(name = "DATA_AGGIUNTA")
	private LocalDate dataAggiunta;
	
	@Basic(optional = false)
	@Column(name = "URL1")
	private String url1;
	
	@Basic(optional = false)
	@Column(name = "URL2")
	private String url2;
	
	@Basic(optional = false)
	@Column(name = "URL3")
	private String url3;
	
	@Basic(optional = false)
	@Column(name = "URL4")
	private String url4;
	
	@ManyToOne
	@JoinColumn(name = "VENDUTO_DA", referencedColumnName = "ID")
	private User vendutoDa;
	
	@OneToMany(mappedBy = "carrello")
	private List<ProdottoCarrello> presenteNelCarrello = new ArrayList<ProdottoCarrello>();
	
	@OneToMany(mappedBy = "prodotto")
	private List<UtenteProdotto> utenteProdottoInProdotto=new ArrayList<UtenteProdotto>();

	@OneToMany(mappedBy = "prodotto")
	private List<Preferito> preferitiInProdotto = new ArrayList<Preferito>();

	@OneToMany(mappedBy = "prodottoRecensito", fetch = FetchType.EAGER)
	private List<Recensione> recensioni = new ArrayList<>();
	
	public Prodotto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAnteprima() {
		return anteprima;
	}

	public void setAnteprima(String anteprima) {
		this.anteprima = anteprima;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDimensioni() {
		return dimensioni;
	}

	public void setDimensioni(String dimensioni) {
		this.dimensioni = dimensioni;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getMemoriaRam() {
		return memoriaRam;
	}

	public void setMemoriaRam(String memoriaRam) {
		this.memoriaRam = memoriaRam;
	}

	public String getMemoriaArchiviazione() {
		return memoriaArchiviazione;
	}

	public void setMemoriaArchiviazione(String memoriaArchiviazione) {
		this.memoriaArchiviazione = memoriaArchiviazione;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public Integer getSconto() {
		return sconto;
	}

	public void setSconto(Integer sconto) {
		this.sconto = sconto;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public Boolean getDisponibile() {
		return disponibile;
	}

	public void setDisponibile(Boolean disponibile) {
		this.disponibile = disponibile;
	}

	public LocalDate getDataAggiunta() {
		return dataAggiunta;
	}

	public void setDataAggiunta(LocalDate dataAggiunta) {
		this.dataAggiunta = dataAggiunta;
	}

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public String getUrl3() {
		return url3;
	}

	public void setUrl3(String url3) {
		this.url3 = url3;
	}

	public String getUrl4() {
		return url4;
	}

	public void setUrl4(String url4) {
		this.url4 = url4;
	}

	public User getVendutoDa() {
		return vendutoDa;
	}

	public void setVendutoDa(User vendutoDa) {
		this.vendutoDa = vendutoDa;
	}

	public List<ProdottoCarrello> getPresenteNelCarrello() {
		return presenteNelCarrello;
	}

	public void setPresenteNelCarrello(List<ProdottoCarrello> presenteNelCarrello) {
		this.presenteNelCarrello = presenteNelCarrello;
	}

	public List<UtenteProdotto> getUtenteProdottoInProdotto() {
		return utenteProdottoInProdotto;
	}

	public void setUtenteProdottoInProdotto(List<UtenteProdotto> utenteProdottoInProdotto) {
		this.utenteProdottoInProdotto = utenteProdottoInProdotto;
	}

	public List<Recensione> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
	}

	public List<Preferito> getPreferitiInProdotto() {
		return preferitiInProdotto;
	}

	public void setPreferitiInProdotto(List<Preferito> preferitiInProdotto) {
		this.preferitiInProdotto = preferitiInProdotto;
	}
}
