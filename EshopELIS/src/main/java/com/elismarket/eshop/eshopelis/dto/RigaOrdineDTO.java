package com.elismarket.eshop.eshopelis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO for {@link com.elismarket.eshop.eshopelis.model.RigaOrdine RigaOrdine} class
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RigaOrdineDTO {
    public Long id;
    public Float prezzoTotale, scontoApplicato;
    public Integer quantitaProdotto;

    public Long ordine_id, prodotto_id;
}
