package com.elismarket.eshop.model;

import com.elismarket.eshop.interfaces.Categoria;
import com.elismarket.eshop.interfaces.Prodotto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "prodotto")
public class ProdottoImpl implements Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome, descrizione;
    private Float prezzo;
    //sconto è percentuale, esempio 55%
    private Integer minOrd, maxOrd, sconto;


    public ProdottoImpl(String nome, String descrizione, Float prezzo, Integer minOrd, Integer maxOrd, Integer sconto) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.minOrd = minOrd;
        this.maxOrd = maxOrd;
        this.sconto = sconto;
    }

    public ProdottoImpl(String nome, String descrizione, Float prezzo, Integer minOrd, Integer maxOrd, Integer sconto, List<OrdineImpl> ordini) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.minOrd = minOrd;
        this.maxOrd = maxOrd;
        this.sconto = sconto;
        this.ordini = ordini;
    }

    @ManyToMany
    @JoinTable (name = "prodotto_ordini", joinColumns = @JoinColumn(name = "ordine_id"), inverseJoinColumns = @JoinColumn(name = "prodotto_id"))
    private List<OrdineImpl> ordini = new ArrayList<>();

    @ManyToOne
    private CategoriaImpl categoria;
}
