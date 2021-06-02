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
    private Long id;

    @Column(nullable = false)
    private String nome, descrizione;

    @Column(nullable = false)
    private Float prezzo;

    //sconto è percentuale, esempio 55%
    @Column
    private Integer minOrd, maxOrd, sconto;

    @Column(nullable = false)
    private Integer quantita;

    @Lob
    @Column(nullable = false)
    //url of the product image
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prodotto")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<RigaOrdine> righeOrdine;


    public static Prodotto of(ProdottoDTO prodottoDTO) {
        return Prodotto.builder()
                .id(prodottoDTO.id)
                .nome(prodottoDTO.nome)
                .descrizione(prodottoDTO.descrizione)
                .prezzo(prodottoDTO.prezzo)
                .minOrd(prodottoDTO.minOrd)
                .maxOrd(prodottoDTO.maxOrd)
                .sconto(prodottoDTO.sconto)
                .quantita(prodottoDTO.quantita)
                .image(prodottoDTO.image)
                .build();
    }

    public static ProdottoDTO to(Prodotto prodotto) {
        ProdottoDTO p = new ProdottoDTO();

        p.id = prodotto.getId();
        p.nome = prodotto.getNome();
        p.descrizione = prodotto.getDescrizione();
        p.prezzo = prodotto.getPrezzo();
        p.minOrd = prodotto.getMinOrd();
        p.maxOrd = prodotto.getMaxOrd();
        p.sconto = prodotto.getSconto();
        p.quantita = prodotto.getQuantita();
        p.image = prodotto.getImage();
        p.categoria_id = prodotto.getCategoria().getId();
        p.utente_id = prodotto.getUtente().getId();

        return p;
    }
}
