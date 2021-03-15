package com.elismarket.eshop.model;

import com.elismarket.eshop.interfaces.Ordine;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrdineImpl implements Ordine {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private Boolean evaso;

    @Getter
    @Setter
    private LocalDate dataEvasione;

    public OrdineImpl() {
    }

    public OrdineImpl(Boolean evaso, LocalDate dataEvasione) {
        this.evaso = evaso;
        this.dataEvasione = dataEvasione;
    }

    public OrdineImpl(Boolean evaso, LocalDate dataEvasione, List<ProdottoImpl> prodotti, UtenteImpl utente) {
        this.evaso = evaso;
        this.dataEvasione = dataEvasione;
        this.prodotti = prodotti;
        this.utente = utente;
    }

    @ManyToMany
    @Getter
    @Setter
    private List<ProdottoImpl> prodotti = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_utente")
    @Getter
    @Setter
    private UtenteImpl utente = null;
}
