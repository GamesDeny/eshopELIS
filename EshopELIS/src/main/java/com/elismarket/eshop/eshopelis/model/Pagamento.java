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

    //tipo is an enum
    private String tipo, descrizione, paypalMail;
    private Integer contanti;

    @ManyToOne
    private Utente utente;

    public static Pagamento of(PagamentoDTO metodoPagamentoDTO) {
        return Pagamento.builder()
                .tipo(metodoPagamentoDTO.tipo)
                .descrizione(metodoPagamentoDTO.descrizione)
                .paypalMail(metodoPagamentoDTO.paypalMail)
                .contanti(metodoPagamentoDTO.contanti)
                .build();
    }

    public static PagamentoDTO to(Pagamento pagamento) {
        PagamentoDTO p = new PagamentoDTO();

        p.setId(pagamento.getId());
        p.setContanti(pagamento.getContanti());
        p.setDescrizione(pagamento.getDescrizione());
        p.setPaypalMail(pagamento.getPaypalMail());
        p.setTipo(pagamento.getTipo());

        return p;
    }

    enum PagamentoEnum {
        PAYPAL,
        CONTANTI
    }
}
