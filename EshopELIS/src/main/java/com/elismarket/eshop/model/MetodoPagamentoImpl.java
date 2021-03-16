package com.elismarket.eshop.model;

import com.elismarket.eshop.interfaces.Pagamento;
import com.sun.istack.NotNull;
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
@Table(name = "metodo_pagamento")
public class MetodoPagamentoImpl implements Pagamento {

    //settings
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    //tipo is an enum
    private String tipo, descrizione, paypalMail;
    private Integer contanti;

    @ManyToOne
    private UtenteImpl utente;
}
