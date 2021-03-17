package com.elismarket.eshop.model;

import com.elismarket.eshop.interfaces.Prodotto;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


/*
 *
 * Product class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * The class contains all product informations
 *
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "prodotto")
public class ProdottoImpl implements Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nome, descrizione, nomeCategoria;


    @Column(nullable = false)
    private Float prezzo;

    //sconto è percentuale, esempio 55%
    private Integer minOrd, maxOrd, sconto, quantita;

    @OneToMany(mappedBy = "prodotto")
    private List<RigaOrdineImpl> righeOrdine;

    public ProdottoImpl(String nome, String descrizione, Float prezzo, Integer minOrd, Integer maxOrd, Integer sconto, Integer quantita) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.minOrd = minOrd;
        this.maxOrd = maxOrd;
        this.sconto = sconto;
        this.quantita = quantita;
    }
}
