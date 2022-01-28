package com.techzon.data.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity(name="RESO")
public class Reso {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Basic(optional = false)
    @Column(name = "MITTENTE")
    private String usernameMittente;

    @Basic(optional = false)
    @Column(name = "DESTINATARIO")
    private String usernameDestinatario;

    @Basic(optional = false)
    @Column(name = "ID_PRODOTTO")
    private Long idProdotto;

    @Basic(optional = false)
    @Column(name = "MOTIVO")
    private String motivoReso;

    @Basic(optional = false)
    @Column(name = "QUANTITA")
    private Integer quantita;

    @Basic(optional = false)
    private Float prezzo;

    @Basic(optional = false)
    private Boolean rimborsato;

    @OneToOne
    @JoinColumn(name="UTENTE_PRODOTTO_ID",referencedColumnName = "ID", unique = true)
    private UtenteProdotto numeroOrdine;

    public Reso() {
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

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public UtenteProdotto getNumeroOrdine() {
        return numeroOrdine;
    }

    public void setNumeroOrdine(UtenteProdotto numeroOrdine) {
        this.numeroOrdine = numeroOrdine;
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
        Reso reso = (Reso) o;
        return Objects.equals(id, reso.id) && Objects.equals(usernameMittente, reso.usernameMittente) && Objects.equals(usernameDestinatario, reso.usernameDestinatario) && Objects.equals(idProdotto, reso.idProdotto) && Objects.equals(motivoReso, reso.motivoReso) && Objects.equals(quantita, reso.quantita) && Objects.equals(prezzo, reso.prezzo) && Objects.equals(rimborsato, reso.rimborsato) && Objects.equals(numeroOrdine, reso.numeroOrdine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usernameMittente, usernameDestinatario, idProdotto, motivoReso, quantita, prezzo, rimborsato, numeroOrdine);
    }

    @Override
    public String toString() {
        return "Reso{" +
                "id=" + id +
                ", usernameMittente='" + usernameMittente + '\'' +
                ", usernameDestinatario='" + usernameDestinatario + '\'' +
                ", idProdotto=" + idProdotto +
                ", motivoReso='" + motivoReso + '\'' +
                ", quantita=" + quantita +
                ", prezzo=" + prezzo +
                ", rimborsato=" + rimborsato +
                ", numeroOrdine=" + numeroOrdine +
                '}';
    }
}
