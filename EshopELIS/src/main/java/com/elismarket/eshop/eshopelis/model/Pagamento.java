package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = true)
    private String paypalMail;

    @Column(nullable = true)
    private Integer contanti;

    @ManyToOne
    private Utente utente;

    @OneToMany
    private List<Ordine> ordini = new ArrayList<>();

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

        p.id = pagamento.getId();
        p.tipo = pagamento.getTipo();
        p.descrizione = pagamento.getDescrizione();
        p.paypalMail = pagamento.getPaypalMail();
        p.contanti = pagamento.getContanti();

        return p;
    }

    enum PagamentoEnum {
        PAYPAL,
        CONTANTI
    }
}
