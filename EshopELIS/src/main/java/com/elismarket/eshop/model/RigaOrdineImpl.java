package com.elismarket.eshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "riga_ordine")
public class RigaOrdineImpl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Float prezzoTotale, sconto;
    private Integer quantitaProdotto;

    @ManyToOne
    private OrdineImpl ordine;

    @ManyToOne
    private ProdottoImpl prodotto;

}
