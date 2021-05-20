package com.elismarket.eshop.eshopelis.dto;

import com.elismarket.eshop.eshopelis.model.Ordine;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

/*
 *
 * DTO per interfacciamo API con frontend
 *
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdineDTO extends Ordine {

    public Long id;
    public Boolean evaso;
    public LocalDate dataEvasione;

    public OrdineDTO() {
        super();
    }
}
