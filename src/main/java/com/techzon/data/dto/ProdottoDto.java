package com.techzon.data.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class ProdottoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String titolo;
	
	private String anteprima;
	
	private String descrizione;

	private String dimensioni;
	
	private Double peso;

	private String memoriaRam;

	private String memoriaArchiviazione;

	private String marca;
	
	private String modello;
	
	private Double prezzo;

	private Integer sconto;
	
	private String colore;

	private String categoria;

	private Integer quantita;
	
	private Boolean disponibile;

	private LocalDate dataAggiunta;

	private String url1;

	private String url2;
	
	private String url3;
	
	private String url4;
	
	@JsonIgnoreProperties({"carrello","prodottiCarrello","segnalazioneUtente","carte"})
	private UserDto vendutoDa;
	
	@JsonIgnore
	private List<UserDto> compratoDa = new ArrayList<>();
	
	@JsonIgnore
	private List<RecensioneDto> recensioni = new ArrayList<>();
	
	public ProdottoDto() {}

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
	
	public UserDto getVendutoDa() {
		return vendutoDa;
	}

	public void setVendutoDa(UserDto vendutoDa) {
		this.vendutoDa = vendutoDa;
	}

	/*public List<ProdottoCarrelloDto> getPresenteNelCarrello() {
		return presenteNelCarrello;
	}

	public void setPresenteNelCarrello(List<ProdottoCarrelloDto> presenteNelCarrello) {
		this.presenteNelCarrello = presenteNelCarrello;
	}*/

	public List<UserDto> getCompratoDa() {
		return compratoDa;
	}

	public void setCompratoDa(List<UserDto> compratoDa) {
		this.compratoDa = compratoDa;
	}

	public List<RecensioneDto> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<RecensioneDto> recensioni) {
		this.recensioni = recensioni;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return ""+
		this.id+"\n"+
		this.titolo+"\n"+
		this.anteprima+"\n"+
		this.descrizione+"\n"+
		this.dimensioni+
		this.peso+"\n"+
		this.memoriaRam+"\n"+
		this.memoriaArchiviazione+"\n"+
		this.marca+"\n"+
		this.modello+"\n"+
		this.prezzo+"\n"+
		this.sconto+"\n"+
		this.colore+"\n"+
		this.categoria+"\n"+
		this.quantita+"\n"+
		this.disponibile+"\n"+
		this.dataAggiunta+"\n"+
		this.url1+"\n"+
		this.url2+"\n"+
		this.url3+"\n"+
		this.url4+"\n";
		
	}
}
