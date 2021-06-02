package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import lombok.*;

import javax.persistence.*;

/*
 *
 * Order Row class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * The class contains all rows of an order related to an user
 * When user X makes an order, he can buy many objects
 * When this happens we store all rows (all different products) in this class
 *
 */

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "riga_ordine")
public class RigaOrdine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //sconto applicato è la somma in denaro sottratta dal totale
    @Column(nullable = false)
    private Float prezzoTotale;

    private Float scontoApplicato;

    @Column(nullable = false)
    private Integer quantitaProdotto;

    @ManyToOne
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "prodotto_id")
    private Prodotto prodotto;

    public static RigaOrdine of(RigaOrdineDTO rigaOrdineDTO) {
        return RigaOrdine.builder()
                .prezzoTotale(rigaOrdineDTO.prezzoTotale)
                .scontoApplicato(rigaOrdineDTO.scontoApplicato)
                .quantitaProdotto(rigaOrdineDTO.quantitaProdotto)
                .build();
    }

    public static RigaOrdineDTO to(RigaOrdine rigaOrdine) {
        RigaOrdineDTO r = new RigaOrdineDTO();

        r.id = rigaOrdine.getId();
        r.prezzoTotale = rigaOrdine.getPrezzoTotale();
        r.scontoApplicato = rigaOrdine.getScontoApplicato();
        r.quantitaProdotto = rigaOrdine.getQuantitaProdotto();
        r.ordine_id = rigaOrdine.getOrdine().getId();
        r.prodotto_id = rigaOrdine.getProdotto().getId();

        return r;
    }
}
