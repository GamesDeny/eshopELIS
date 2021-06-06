package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Pagamento class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * The class contains all Pagamento information
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pagamento")
public class Pagamento {

    /**
     * Primary key of the Entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Description for the payment
     */
    @Column(nullable = false)
    private String descrizione;

    /**
     * indicates the Paypal mail where money came from
     */
    @Column()
    private String paypalMail;

    /**
     * indicates how much he paid with cash
     */
    @Column
    private Float contanti;

    /**
     * tells if it is the default payment method for the user
     */
    @Column
    private Boolean isDefault;

    /**
     * All {@link Prodotto Prodotto} linked to the Pagamento
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    private Utente utente;

    /**
     * All {@link Prodotto Prodotto} linked to the Pagamento
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id")
    private TipoMetodo tipoMetodo;

    /**
     * All {@link Prodotto Prodotto} linked to the Pagamento
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;

    /**
     * Returns an instance of Pagamento from a {@link PagamentoDTO PagamentoDTO}
     *
     * @param pagamentoDTO instance of PagamentoDTO
     * @return Pagamento representation of PagamentoDTO
     */
    public static Pagamento of(PagamentoDTO pagamentoDTO) {
        return Pagamento.builder()
                .contanti(pagamentoDTO.contanti)
                .paypalMail(pagamentoDTO.paypalMail)
                .descrizione(pagamentoDTO.descrizione)
                .isDefault(pagamentoDTO.isDefault)
                .build();
    }

    /**
     * Returns an instance of {@link PagamentoDTO PagamentoDTO} from a Pagamento
     *
     * @param pagamento instance of Pagamento
     * @return PagamentoDTO representation of the Pagamento
     */
    public static PagamentoDTO to(Pagamento pagamento) {
        PagamentoDTO p = new PagamentoDTO();

        p.id = pagamento.getId();
        p.paypalMail = pagamento.getPaypalMail();
        p.descrizione = pagamento.getDescrizione();
        p.contanti = pagamento.getContanti();
        p.isDefault = pagamento.getIsDefault();
        p.tipoMetodo_id = pagamento.getTipoMetodo().getId();
        p.ordine_id = Objects.isNull(pagamento.getOrdine()) ? null : pagamento.getOrdine().getId();
        p.utente_id = pagamento.getUtente().getId();

        return p;
    }
}
