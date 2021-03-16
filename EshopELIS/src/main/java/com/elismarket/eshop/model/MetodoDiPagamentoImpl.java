package com.elismarket.eshop.model;

import com.elismarket.eshop.interfaces.Pagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "metodo_di_pagamento")
public class MetodoDiPagamentoImpl implements Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //tipo is an enum
    private String tipo, descrizione, paypalMail;
    private Integer contanti;

    @ManyToOne
    private UtenteImpl utente;

    @ManyToOne
    private RigaOrdineImpl lineaOrdine;
}
