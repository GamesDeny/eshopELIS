package com.elismarket.eshop.dto;

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
public class OrdineDTO {
    public Long id;
    public Boolean evaso;
    public LocalDate dataEvasione;
}
