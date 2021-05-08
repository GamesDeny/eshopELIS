package com.elismarket.eshop.model.entities;

import com.elismarket.eshop.model.dto.PagamentoDTO;
import com.elismarket.eshop.model.interfaces.Pagamento;
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
public class PagamentoImpl implements Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    //tipo is an enum
    private String tipo, descrizione, paypalMail;
    private Integer contanti;

    @ManyToOne
    private UtenteImpl utente;

    public static PagamentoImpl of(PagamentoDTO metodoPagamentoDTO) {
        return PagamentoImpl.builder()
                .tipo(metodoPagamentoDTO.tipo)
                .descrizione(metodoPagamentoDTO.descrizione)
                .paypalMail(metodoPagamentoDTO.paypalMail)
                .contanti(metodoPagamentoDTO.contanti)
                .build();
    }

    enum PagamentoEnum {
        PAYPAL,
        CONTANTI
    }
}