package com.elismarket.eshop.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/*
 *
 * DTO per interfacciamo API con frontend
 *
 */


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RigaOrdineDTO {
    public Long id;
    public Float prezzoTotale, scontoApplicato;
    public Integer quantitaProdotto;
}
