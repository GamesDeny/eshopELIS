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
    public String descrizione, paypalMail;
    public Float contanti;
    public Long utente_id, tipoMetodo_id;
    public List<Long> ordini_id;
    public Boolean isDefault;
}
