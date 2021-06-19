package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Prodotto class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * The class contains all Prodotto information
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Prodotto {

    /**
     * Primary key of the Entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * indicates the name of the Prodotto
     */
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String nome;

    /**
     * indicates a brief description for the Prodotto
     */
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String descrizione;

    /**
     * Indicates the price of the Prodotto
     */
    @Column(nullable = false)
    private Float prezzo;

    /**
     * minOrd indicates the minimum number of Prodotto that can be bought for an order
     * maxOrd indicates the maximum number of Prodotto that can be bought for an order
     * sconto indicates the % of sale on the Prodotto
     */
    @Column
    private Integer minOrd, maxOrd, sconto;

    /**
     * Quantita available for the Prodotto
     */
    @Column(nullable = false)
    private Integer quantita;

    /**
     * URL of the image showed in the site
     */
    @Lob
    @Column(nullable = false)
    private String image;

    /**
     * All {@link Prodotto Prodotto} linked to the RigaOrdine
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    private Utente utente;

    /**
     * All {@link Prodotto Prodotto} linked to the RigaOrdine
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    /**
     * All {@link Prodotto Prodotto} linked to the RigaOrdine
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prodotto")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<RigaOrdine> righeOrdine;

    /**
     * Returns an instance of RigaOrdine from a {@link RigaOrdineDTO RigaOrdineDTO}
     *
     * @param prodottoDTO instance of RigaOrdineDTO
     * @return RigaOrdine representation of RigaOrdineDTO
     */
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

    /**
     * Returns an instance of {@link RigaOrdineDTO RigaOrdineDTO} from a RigaOrdine
     *
     * @param prodotto instance of RigaOrdine
     * @return RigaOrdineDTO representation of the RigaOrdine
     */
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

        return p;
    }
}
