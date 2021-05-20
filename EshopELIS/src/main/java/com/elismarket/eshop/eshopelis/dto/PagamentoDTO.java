package com.elismarket.eshop.eshopelis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/*
 *
 * DTO per interfacciamo API con frontend
 *
 */


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagamentoDTO {
    public Long id;
    public String tipo, descrizione, paypalMail;
    public Integer contanti;
}
