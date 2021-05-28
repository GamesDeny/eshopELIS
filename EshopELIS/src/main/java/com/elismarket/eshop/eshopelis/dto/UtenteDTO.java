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
public class UtenteDTO {
    public Long id;
    public String cognome;
    public LocalDate dataNascita;
    public Boolean isAdmin;
    public Boolean logged;
    public String mail, nome, password;
    public Integer siglaResidenza;
    public String username;

    public List<Long> proposte_id;
    public List<Long> pagamenti_id;
    public List<Long> prodotti_id;
    public List<Long> feedbacks_id;
}
