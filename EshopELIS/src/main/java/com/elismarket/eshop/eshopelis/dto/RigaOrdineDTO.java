package com.elismarket.eshop.eshopelis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 *
 * DTO per interfacciamo API con frontend
 *
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class RigaOrdineDTO {
    public Long id;
    public Float prezzoTotale, scontoApplicato;
    public Integer quantitaProdotto;

    public Long ordine_id, prodotto_id;
}
