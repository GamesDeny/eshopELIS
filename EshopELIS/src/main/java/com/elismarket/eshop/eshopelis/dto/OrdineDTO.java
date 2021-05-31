package com.elismarket.eshop.eshopelis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

/*
 *
 * DTO per interfacciamo API con frontend
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdineDTO {
    public Long id;
    public Boolean evaso;
    public LocalDate dataEvasione;

    public List<Long> righeOrdine_id;
    public Long pagamento_id;
    public Long utente_id;
}
