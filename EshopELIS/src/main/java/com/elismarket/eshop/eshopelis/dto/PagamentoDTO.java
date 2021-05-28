package com.elismarket.eshop.eshopelis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/*
 *
 * DTO per interfacciamo API con frontend
 *
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class PagamentoDTO {
    public Long id;
    public String tipo, descrizione, paypalMail;
    public Integer contanti;
    public Long utente_id;
    public List<Long> ordini_id;
}
