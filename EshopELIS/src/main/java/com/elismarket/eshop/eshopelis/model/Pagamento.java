package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 *
 * Payment Method class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * This class is used to store all payment informations
 *
 */
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    //tipo is an enum
    private String tipo, descrizione;

    private String paypalMail;
    private Float contanti;
    private Boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id")
    private TipoMetodo tipoMetodo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;

    public static Pagamento of(PagamentoDTO metodoPagamentoDTO) {
        return Pagamento.builder()
                .contanti(metodoPagamentoDTO.contanti)
                .paypalMail(metodoPagamentoDTO.paypalMail)
                .descrizione(metodoPagamentoDTO.descrizione)
                .isDefault(metodoPagamentoDTO.isDefault)
                .build();
    }

    public static PagamentoDTO to(Pagamento pagamento) {
        PagamentoDTO p = new PagamentoDTO();

        p.id = pagamento.getId();
        p.paypalMail = pagamento.getPaypalMail();
        p.descrizione = pagamento.getDescrizione();
        p.contanti = pagamento.getContanti();
        p.isDefault = pagamento.getIsDefault();

        return p;
    }
}
