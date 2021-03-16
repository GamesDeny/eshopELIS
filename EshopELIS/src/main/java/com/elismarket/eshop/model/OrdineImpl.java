package com.elismarket.eshop.model;

import com.elismarket.eshop.interfaces.Ordine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "ordine")
public class OrdineImpl implements Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean evaso;
    private LocalDate dataEvasione;

    public OrdineImpl(Boolean evaso, LocalDate dataEvasione) {
        this.evaso = evaso;
        this.dataEvasione = dataEvasione;
    }

    @ManyToOne
    private RigaOrdineImpl rigaOrdine;

    @ManyToOne
    private MetodoDiPagamentoImpl pagamento;
}
