package com.elismarket.eshop.eshopelis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    public Boolean isDefault;

    public Long utente_id, tipoMetodo_id, ordine_id;
}
