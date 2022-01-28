package com.techzon.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;

public class ResoDto implements Serializable {


    private static final long serialVersionUID = 1L;

    private Long id;
    private String usernameMittente;
    private String usernameDestinatario;
   @JsonIgnoreProperties({"user","prodotto"})
    private UtenteProdottoDto numeroOrdine;
    private Long idProdotto;
    private String motivoReso;
    private Long quantita;
    private Float prezzo;
    private Boolean rimborsato;

    public ResoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsernameMittente() {
        return usernameMittente;
    }

    public void setUsernameMittente(String usernameMittente) {
        this.usernameMittente = usernameMittente;
    }

    public String getUsernameDestinatario() {
        return usernameDestinatario;
    }

    public void setUsernameDestinatario(String usernameDestinatario) {
        this.usernameDestinatario = usernameDestinatario;
    }

    public UtenteProdottoDto getNumeroOrdine() {
        return numeroOrdine;
    }

    public void setNumeroOrdine(UtenteProdottoDto numeroOrdine) {
        this.numeroOrdine = numeroOrdine;
    }

    public Long getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(Long idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getMotivoReso() {
        return motivoReso;
    }

    public void setMotivoReso(String motivoReso) {
        this.motivoReso = motivoReso;
    }

    public Long getQuantita() {
        return quantita;
    }

    public void setQuantita(Long quantita) {
        this.quantita = quantita;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public Boolean getRimborsato() {
        return rimborsato;
    }

    public void setRimborsato(Boolean rimborsato) {
        this.rimborsato = rimborsato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResoDto resoDto = (ResoDto) o;
        return Objects.equals(id, resoDto.id) && Objects.equals(usernameMittente, resoDto.usernameMittente) && Objects.equals(usernameDestinatario, resoDto.usernameDestinatario) && Objects.equals(numeroOrdine, resoDto.numeroOrdine) && Objects.equals(idProdotto, resoDto.idProdotto) && Objects.equals(motivoReso, resoDto.motivoReso) && Objects.equals(quantita, resoDto.quantita) && Objects.equals(prezzo, resoDto.prezzo) && Objects.equals(rimborsato, resoDto.rimborsato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usernameMittente, usernameDestinatario, numeroOrdine, idProdotto, motivoReso, quantita, prezzo, rimborsato);
    }

    @Override
    public String toString() {
        return "ResoDto{" +
                "id=" + id +
                ", usernameMittente='" + usernameMittente + '\'' +
                ", usernameDestinatario='" + usernameDestinatario + '\'' +
                ", numeroOrdine=" + numeroOrdine +
                ", idProdotto=" + idProdotto +
                ", motivoReso='" + motivoReso + '\'' +
                ", quantita=" + quantita +
                ", prezzo=" + prezzo +
                ", rimborsato=" + rimborsato +
                '}';
    }
}

