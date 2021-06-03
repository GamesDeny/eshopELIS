package com.elismarket.eshop.eshopelis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * DTO for {@link com.elismarket.eshop.eshopelis.model.TipoMetodo TipoMetodo} class
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoMetodoDTO {
    public Long id;
    public String nome;
    public List<Long> pagamenti_id;
}
