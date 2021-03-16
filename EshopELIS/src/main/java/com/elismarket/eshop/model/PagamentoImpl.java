package com.elismarket.eshop.model;

import com.elismarket.eshop.interfaces.Pagamento;
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
@Table(name = "pagamento")
public class PagamentoImpl implements Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //tipo is an enum
    private String tipo, descrizione, numCarta, cvu, paypalmail;
    private LocalDate dataCarta, dataPagamento;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private UtenteImpl utente;

    @OneToOne
    @JoinColumn(name = "ordine_id")
    private OrdineImpl ordine;
}
