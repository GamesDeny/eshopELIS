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
public class UtenteDTO {
    public Long id;
    public String mail, username;
    public String password, nome, cognome;
    public Integer siglaResidenza;
    public LocalDate dataNascita;
    public Boolean logged;
    public Boolean isAdmin;
    public String profileImage;
}
