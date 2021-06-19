package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import lombok.*;

import javax.persistence.*;

import static java.util.Objects.isNull;

/**
 * RigaOrdine class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * The class contains all RigaOrdine information
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
public class RigaOrdine {

    /**
     * Primary key of the Entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Total price for the lotto
     */
    @Column(nullable = false)
    private Float prezzoTotale;

    /**
     * sale applied to the sale
     */
    private Float scontoApplicato;

    /**
     * Bought quantity
     */
    @Column(nullable = false)
    private Integer quantitaProdotto;

    /**
     * All {@link Prodotto Prodotto} linked to the RigaOrdine
     */
    @ManyToOne
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;

    /**
     * All {@link Prodotto Prodotto} linked to the RigaOrdine
     */
    @ManyToOne
    @JoinColumn(name = "prodotto_id")
    private Prodotto prodotto;

    /**
     * Returns an instance of RigaOrdine from a {@link RigaOrdineDTO RigaOrdineDTO}
     *
     * @param rigaOrdineDTO instance of RigaOrdineDTO
     * @return RigaOrdine representation of RigaOrdineDTO
     */
    public static RigaOrdine of(RigaOrdineDTO rigaOrdineDTO) {
        return RigaOrdine.builder()
                .prezzoTotale(rigaOrdineDTO.prezzoTotale)
                .scontoApplicato(rigaOrdineDTO.scontoApplicato)
                .quantitaProdotto(rigaOrdineDTO.quantitaProdotto)
                .build();
    }

    /**
     * Returns an instance of {@link RigaOrdineDTO RigaOrdineDTO} from a RigaOrdine
     *
     * @param rigaOrdine instance of RigaOrdine
     * @return RigaOrdineDTO representation of the RigaOrdine
     */
    public static RigaOrdineDTO to(RigaOrdine rigaOrdine) {
        RigaOrdineDTO r = new RigaOrdineDTO();

        r.id = rigaOrdine.getId();
        r.prezzoTotale = rigaOrdine.getPrezzoTotale();
        r.scontoApplicato = rigaOrdine.getScontoApplicato();
        r.quantitaProdotto = rigaOrdine.getQuantitaProdotto();
        r.ordine_id = isNull(r.ordine_id) ? null : rigaOrdine.getOrdine().getId();
        r.prodotto_id = rigaOrdine.getProdotto().getId();

        return r;
    }
}
