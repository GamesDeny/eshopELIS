package com.elismarket.eshop.eshopelis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/*
 *
 * DTO per interfacciamo API con frontend
 *
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdottoDTO {

    public Long id;
    public String nome, descrizione, nomeCategoria;
    public Float prezzo;
    public Integer minOrd, maxOrd, sconto, quantita;
    public String image;
    public Long utente_id;
    public List<Long> righeOrdine_id;
}
