package com.elismarket.eshop.model.entities;

import com.elismarket.eshop.model.dto.ProdottoDTO;
import com.elismarket.eshop.model.interfaces.Prodotto;
import lombok.*;

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
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
    //url of the product image
    private String image;

    @ManyToOne
    private UtenteImpl utente;

    @OneToMany(mappedBy = "prodotto")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
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

    public static ProdottoImpl of(ProdottoDTO prodotto) {
        return ProdottoImpl.builder()
                .id(prodotto.id)
                .nome(prodotto.nome)
                .descrizione(prodotto.descrizione)
                .nomeCategoria(prodotto.nomeCategoria)
                .prezzo(prodotto.prezzo)
                .minOrd(prodotto.minOrd)
                .maxOrd(prodotto.maxOrd)
                .sconto(prodotto.sconto)
                .quantita(prodotto.quantita)
                .build();
    }
}
