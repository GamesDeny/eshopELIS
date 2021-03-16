package com.elismarket.eshop.model;

import com.elismarket.eshop.interfaces.Pagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/*
 *
 * Payment Method class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * This class is used to store all payment informations
 *
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "metodo_pagamento")
public class MetodoPagamentoImpl implements Pagamento {

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
