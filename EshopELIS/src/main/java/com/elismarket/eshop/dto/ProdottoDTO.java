package com.elismarket.eshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/*
 *
 * DTO per interfacciamo API con frontend
 *
 */


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdottoDTO {

    public Long id;
    public String nome, descrizione, nomeCategoria;
    public Float prezzo;
    public Integer minOrd, maxOrd, sconto, quantita;
    public String image;
}
