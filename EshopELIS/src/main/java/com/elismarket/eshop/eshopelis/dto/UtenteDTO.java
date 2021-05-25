package com.elismarket.eshop.eshopelis.dto;

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
public class UtenteDTO {
    public Long id;
    public String cognome;
    public LocalDate dataNascita;
    public Boolean isAdmin;
    public Boolean logged;
    public String mail, nome, password;
    public Integer siglaResidenza;
    public String username;
}
