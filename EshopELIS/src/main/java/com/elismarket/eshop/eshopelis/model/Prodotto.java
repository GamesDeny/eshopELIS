package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
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
public class Prodotto {

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

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Utente utente;

    @OneToMany(mappedBy = "prodotto")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<RigaOrdine> righeOrdine;

    public Prodotto(String nome, String descrizione, Float prezzo, Integer minOrd, Integer maxOrd, Integer sconto, Integer quantita) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.minOrd = minOrd;
        this.maxOrd = maxOrd;
        this.sconto = sconto;
        this.quantita = quantita;
    }

    public static Prodotto of(ProdottoDTO prodotto) {
        return Prodotto.builder()
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

    public static ProdottoDTO to(Prodotto prodotto) {
        ProdottoDTO p = new ProdottoDTO();

        p.setId(prodotto.getId());
        p.setNome(prodotto.getNome());
        p.setDescrizione(prodotto.getDescrizione());
        p.setNomeCategoria(prodotto.getNomeCategoria());
        p.setPrezzo(prodotto.getPrezzo());
        p.setImage(prodotto.getImage());
        p.setMinOrd(prodotto.getMinOrd());
        p.setMaxOrd(prodotto.getMaxOrd());
        p.setSconto(prodotto.getSconto());
        p.setQuantita(prodotto.getQuantita());

        return p;
    }
}
