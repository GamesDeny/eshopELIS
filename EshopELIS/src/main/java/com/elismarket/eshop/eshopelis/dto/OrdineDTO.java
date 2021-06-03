package com.elismarket.eshop.eshopelis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link com.elismarket.eshop.eshopelis.model.Ordine Ordine} class
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
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
