package com.elismarket.eshop.model.entities;

import com.elismarket.eshop.model.dto.RigaOrdineDTO;
import com.elismarket.eshop.model.interfaces.RigaOrdine;
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
public class RigaOrdineImpl implements RigaOrdine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    //sconto applicato è la somma in denaro sottratta dal totale
    private Float prezzoTotale, scontoApplicato;
    private Integer quantitaProdotto;

    @ManyToOne
    private OrdineImpl ordine;

    @ManyToOne
    private ProdottoImpl prodotto;

    public static RigaOrdineImpl of(RigaOrdineDTO rigaOrdineDTO) {
        return RigaOrdineImpl.builder()
                .prezzoTotale(rigaOrdineDTO.prezzoTotale)
                .scontoApplicato(rigaOrdineDTO.scontoApplicato)
                .quantitaProdotto(rigaOrdineDTO.quantitaProdotto)
                .build();
    }
}
