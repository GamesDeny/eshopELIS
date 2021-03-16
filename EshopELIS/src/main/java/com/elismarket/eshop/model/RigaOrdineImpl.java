package com.elismarket.eshop.model;

import com.sun.xml.bind.v2.model.core.ID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "rigaOrdine")
    private List<OrdineImpl> ordini;

    @OneToMany(mappedBy = "rigaOrdine")
    private List<ProdottoImpl> prodotti;

}
