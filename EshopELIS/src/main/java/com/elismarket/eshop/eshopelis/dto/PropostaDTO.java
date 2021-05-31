package com.elismarket.eshop.eshopelis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

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
