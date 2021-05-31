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
@Table(name = "metodo_pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    //tipo is an enum
    private String tipo, descrizione;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private TipoMetodo tipoMetodo;

    @OneToOne(mappedBy = "pagamento")
    private Ordine ordine;

    public static Pagamento of(PagamentoDTO metodoPagamentoDTO) {
        return Pagamento.builder()
                .tipo(metodoPagamentoDTO.tipo)
                .descrizione(metodoPagamentoDTO.descrizione)
                .build();
    }

    public static PagamentoDTO to(Pagamento pagamento) {
        PagamentoDTO p = new PagamentoDTO();

        p.id = pagamento.getId();
        p.tipo = pagamento.getTipo();
        p.descrizione = pagamento.getDescrizione();

        return p;
    }

    enum PagamentoEnum {
        PAYPAL,
        CONTANTI
    }
}
