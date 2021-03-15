package com.elismarket.eshop.model;

import com.elismarket.eshop.interfaces.Pagamento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class PagamentoImpl implements Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    //tipo is an enum
    private String tipo, descrizione, numCarta, cvu, paypalmail;

    @Getter
    @Setter
    private LocalDate dataCarta, dataPagamento;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    @Getter
    @Setter
    private UtenteImpl utente;

    @OneToOne
    @JoinColumn(name = "ordine_id")
    @Getter
    @Setter
    private OrdineImpl ordine;
}
