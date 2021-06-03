package com.elismarket.eshop.eshopelis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * DTO for {@link com.elismarket.eshop.eshopelis.model.Proposta Proposta} class
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PropostaDTO {
    public Long id;
    public String nome, descrizione, motivoRifiuto, image;
    public Float prezzoProposto;
    public Integer quantita;
    public Boolean isAccettato;
    public LocalDate submissionDate;

    public Long categoria_id;
    public Long utente_id;
}
