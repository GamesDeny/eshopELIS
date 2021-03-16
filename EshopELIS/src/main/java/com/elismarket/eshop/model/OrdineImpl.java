package com.elismarket.eshop.model;

import com.elismarket.eshop.interfaces.Ordine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "ordine")
public class OrdineImpl implements Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean evaso;
    private LocalDate dataEvasione;

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
    private List<ProdottoImpl> prodotti = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private UtenteImpl utente = null;
}
