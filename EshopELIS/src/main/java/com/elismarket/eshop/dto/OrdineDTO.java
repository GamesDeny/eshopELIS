package com.elismarket.eshop.dto;

import com.elismarket.eshop.model.OrdineImpl;
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
    public Long id;
    public Boolean evaso;
    public LocalDate dataEvasione;
}
