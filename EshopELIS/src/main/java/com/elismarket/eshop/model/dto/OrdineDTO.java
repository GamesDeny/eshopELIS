package com.elismarket.eshop.model.dto;

import com.elismarket.eshop.model.entities.OrdineImpl;
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
public class OrdineDTO extends OrdineImpl {

    public OrdineDTO() {
        super();
    }

    public Long id;
    public Boolean evaso;
    public LocalDate dataEvasione;
}
