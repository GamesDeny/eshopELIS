package com.elismarket.eshop.eshopelis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropostaDTO {
    public Long id;
    public String nome, descrizione, motivoRifiuto;
    public Float prezzoProposto;
    public Integer quantita;
    public Boolean isAccettato;
    public LocalDate submissionDate;
}
